var t = null;
t = setTimeout(time, 1000); //開始运行
function time() {
	clearTimeout(t); //清除定时器
	dt = new Date();
	var y = dt.getFullYear();
	var mt = dt.getMonth() + 1;
	var d = dt.getDate();
	var day = dt.getDay();
	var h = dt.getHours(); //获取时
	var m = dt.getMinutes(); //获取分
	var s = dt.getSeconds(); //获取秒
	document.querySelector(".time").innerHTML = doub(h) + ":" + doub(m) + ":" + doub(s)	;
	document.querySelector(".date").innerHTML = y + "年" + mt + "月" + d +"日&nbsp周"+day;
	t = setTimeout(time, 1000); //设定定时器，循环运行     
}
function doub(data){
	data = data>9?data:"0"+data;
	return data;
}