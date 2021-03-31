var whetherToFetch = true;
(function($) {
	var show_num = [];
	draw(show_num);
	$("#canvas").on('click', function() {
		draw(show_num);
	})
	var captchaVal = $("#captcha-input").val().toLowerCase();

	var errorMsgPlaceholder = {
		username: $('#username').parent().prev(),
		email: $('#email').parent().prev(),
		password: $('#password').parent().prev(),
		passwordComfirm: $('#password-comfirm').parent().prev(),
		captcha: $('#captcha-input').parent().prev()
	}

	function formValidation(username,email, password, passwordComfirm, captchaVal) {
		if(username == '') {
			errorMsgPlaceholder.username.addClass('msg-show').text("请输入用户名！")
			whetherToFetch = false
		} 
		if(!/.+@.+\..+/.test(email)) {
			errorMsgPlaceholder.email.addClass('msg-show').text("请输入正确的邮箱地址！")
			whetherToFetch = false
		} else if(!/^.{1,50}$/.test(email)) {
			errorMsgPlaceholder.email.addClass('msg-show').text("不能超过50个字符！")
			whetherToFetch = false
		}
		if(!/^.{6,20}$/.test(password)) {
			errorMsgPlaceholder.password.addClass('msg-show').text("请输入6～20位密码！")
			whetherToFetch = false
		} else if(/\s/.test(password)) {
			errorMsgPlaceholder.password.addClass('msg-show').text("密码不可使用空格！")
			whetherToFetch = false
		} else if(!zxcvbn(password).score) {
			errorMsgPlaceholder.password.addClass('msg-show').text("密码不能是123456、111111、123abc等容易猜测的密码！")
			whetherToFetch = false
		} else if(password !== passwordComfirm) {
			errorMsgPlaceholder.passwordComfirm.addClass('msg-show').text("两次输入的密码不一致！")
			whetherToFetch = false
		}
		var num = show_num.join("");
		if(captchaVal == '') {
			errorMsgPlaceholder.captcha.addClass('msg-show').text("请输入验证码！")
			whetherToFetch = false
		} else if(captchaVal == num) {} else {
			errorMsgPlaceholder.captcha.addClass('msg-show').text("验证码错误！请重新输入！")
			whetherToFetch = false
		}
		return whetherToFetch
	}

	$('.main-form .main-form-button').click(function() {
		$this = $(this)
		for(var key in errorMsgPlaceholder) errorMsgPlaceholder[key].removeClass('msg-show')
		var username = $('#username').val();
		var email = $('#email').val();
		var password = $('#password').val();
		var passwordComfirm = $('#password-comfirm').val();
		var captchaVal = $('#captcha-input').val();
		var whetherToFetch = formValidation(username,email, password, passwordComfirm, captchaVal);
		

		$.ajax({
			async: false,
			　
			url: "json/login-data.json",
			type: "GET",
			dataType: "json",
			success: function(data) {
				var flag = 0;
				$.each(data, function(i, item) {
					if(username == item["username"] && flag == 0) {
						alert("该用户名已经注册！");
						window.open("./register.html", "_self");
						flag = 1;
						whetherToFetch = false;
					}
					if(email == item["email"] && flag == 0){
						alert("该邮箱已经注册！");
						window.open("./register.html", "_self");
						flag = 1;
						whetherToFetch = false;
					}
				})
				if(flag == 0 && whetherToFetch) {
					alert("注册成功!请登录!");
					window.open("./login.html", "_self");
				} else {
					//alert("注册失败!");
					return
				}
			}
		})
	})

})(jQuery)



//生成并渲染出验证码图形
function draw(show_num) {
	var canvas_width = $('#canvas').width();
	var canvas_height = $('#canvas').height();
	var canvas = document.getElementById("canvas"); //获取到canvas的对象，演员
	var context = canvas.getContext("2d"); //获取到canvas画图的环境，演员表演的舞台
	canvas.width = canvas_width;
	canvas.height = canvas_height;
	var sCode = "a,b,c,d,e,f,g,h,i,j,k,m,n,p,q,r,s,t,u,v,w,x,y,z,A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
	var aCode = sCode.split(",");
	var aLength = aCode.length; //获取到数组的长度
	for(var i = 0; i < 4; i++) { //这里的for循环可以控制验证码位数（如果想显示6位数，4改成6即可）
		var j = Math.floor(Math.random() * aLength); //获取到随机的索引值
		// var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
		var deg = Math.random() - 0.5; //产生一个随机弧度
		var txt = aCode[j]; //得到随机的一个内容
		show_num[i] = txt.toLowerCase();
		var x = 10 + i * 20; //文字在canvas上的x坐标
		var y = 20 + Math.random() * 8; //文字在canvas上的y坐标
		context.font = "bold 23px 微软雅黑";
		context.translate(x, y);
		context.rotate(deg);
		context.fillStyle = randomColor();
		context.fillText(txt, 0, 0);
		context.rotate(-deg);
		context.translate(-x, -y);
	}
	for(var i = 0; i <= 5; i++) { //验证码上显示线条
		context.strokeStyle = randomColor();
		context.beginPath();
		context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
		context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
		context.stroke();
	}
	for(var i = 0; i <= 30; i++) { //验证码上显示小点
		context.strokeStyle = randomColor();
		context.beginPath();
		var x = Math.random() * canvas_width;
		var y = Math.random() * canvas_height;
		context.moveTo(x, y);
		context.lineTo(x + 1, y + 1);
		context.stroke();
	}
};
//得到随机的颜色值
function randomColor() {
	var r = Math.floor(Math.random() * 256);
	var g = Math.floor(Math.random() * 256);
	var b = Math.floor(Math.random() * 256);
	return "rgb(" + r + "," + g + "," + b + ")";
};