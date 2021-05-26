$('.main-form-button').click(function() {
	var name = $("#username").val();
	var pwd = $("#password").val();
	// window.open("./html/index/index.html", "_self");
	
	// 对接登录接口
	// getHttp({
	// 	url: '/json/login-data.json' 
	// }).then(res => {
	// 	alert("登录成功！");
	// 	window.open("./html/index/index.html", "_self");
	// }).catch(res => {
	// 	alert("登录失败！");
	// })
 	
		$.ajax({
			async: false,
			data:{
				"username":name,
				"password":pwd
			}
			url: " ",
			type: "post",
			dataType: "json",
			success: function(data) {
				if(data.flag == true) {	
						window.open("./html/index/index.html", "_self");
				}
				else {
					alert("登录失败！您输入的用户名或者密码错误！");
				}
			},
			error:function(data){
				console.log(data);
			}
		})
		

			 
		 

})
