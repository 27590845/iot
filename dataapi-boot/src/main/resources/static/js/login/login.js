$('.main-form-button').click(function() {
	var name = $("#username").val();
	var pass = $("#password").val();
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
 	
	
		var flag = 0;
		$.ajax({
			async: false,
			
			url: "json/login-data.json",
			type: "GET",
			dataType: "json",
			success: function(data) {
	
				$.each(data, function(i, item) {
					if(name == item["username"] && pass == item["password"]) {
						flag = 1;
						window.open("./html/index/index.html", "_self");
					}
				})
				if(flag == 0) {
					alert("登录失败！您输入的用户名或者密码错误！");
				}
			}
		})
		

			 
		 

})
