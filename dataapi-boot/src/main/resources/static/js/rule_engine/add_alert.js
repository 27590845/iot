var ntId = 0;
var numlength = 0;
$(function() {
	trajectoryUnCode(location.pathname, location.search);
	addCondition();

})

/**  
 * 添加一条关联条件
 */
function addCondition() {
	numlength = numlength + 1;
	console.log(numlength);
	// 条件域
	var conditions = $("#conditions");
	// 当条件数量大于10个，不再添加
	if ($(".condition", conditions).length > 8) {
		return;
	}
	var condition = $("#template .condition").clone(true).show();
	conditions.append(condition);
	bindCondition(condition);
	showNode(numlength - 1);
}
// 绑定删除
function bindCondition(condition) {
	$(".remove", condition).click(function() {
		numlength--;
		console.log(numlength);
		if ($(".condition", conditions).length == 1) {
			return;
		}
		condition.remove();
	});
}

$(document).ready(function() {
	var naaType = $("input[type='radio'][ name='actionType']:checked").val() //警报类型，1为短信，2为email，3为站内信
	if (naaType == "1") {
		document.getElementById("email").disabled = true;
		document.getElementById("phonenum").disabled = false;
	} else if (naaType == "2") {
		document.getElementById("phonenum").disabled = true;
		document.getElementById("email").disabled = false;
	} else if (naaType == "3") {
		document.getElementById("phonenum").disabled = true;
		document.getElementById("email").disabled = true;
	}
});
$(document).click(function() {
	var naaType = $("input[type='radio'][ name='actionType']:checked").val() //警报类型，1为短信，2为email，3为站内信
	if (naaType == "1") {
		document.getElementById("email").disabled = true;
		document.getElementById("phonenum").disabled = false;
	} else if (naaType == "2") {
		document.getElementById("phonenum").disabled = true;
		document.getElementById("email").disabled = false;
	} else if (naaType == "3") {
		document.getElementById("phonenum").disabled = true;
		document.getElementById("email").disabled = true;
	}
});



//将sceneID 与 nodeSn 与 naId 进行绑定
function showNode(num) {

	document.getElementsByClassName('sceneID')[num].onblur = (function() {
		// $("#sceneID").blur(function(){
		var sceneSn = document.getElementsByClassName('sceneID')[num].value //场景id 场景标识
		console.log(sceneSn);
		let html1 = `<option value="" class="node"selected="selected">------请选择------</option>`
		if (sceneSn != "") {
			getHttp({
				url: "/scene/" + sceneSn,
				contentType: "x-www-form-urlencoded"
			}).then((res) => {
				console.log(res);
				res.nodeVos.forEach(function(Item) {
					console.log(Item.nodeSn);
					html1 += `<option value = "${Item.nodeSn}">${Item.nodeName}</option>`
								
				})
				document.getElementsByClassName('nodeName')[num].innerHTML = html1;
			}).catch(res => {
				document.getElementsByClassName('nodeName')[num].innerHTML = html1;
			})
		}
	})
	document.getElementsByClassName('nodeName')[num].onblur = (function() {
		// $("#nodeName").blur(function(){
		var sceneSn = document.getElementsByClassName('sceneID')[num].value //场景id 场景标识
		var nodeSn = document.getElementsByClassName('nodeName')[num].value //  节点标识
		console.log(nodeSn);
		let html2 = `<option value=""  class="property" selected="selected">----请选择----</option> 　`
		if (nodeSn != "") {
			getHttp({
				url: "/scene/" + sceneSn,
				contentType: "x-www-form-urlencoded"
			}).then((res) => {
				console.log(res);
				res.nodeVos.forEach(function(Item) {

					if (Item.nodeSn == nodeSn) {
						Item.nodeAttrList.forEach(function(Item1) {
							console.log(Item1.naName);
							html2 += `<option value = "${Item1.naId}">${Item1.naName} ----单位:${Item1.naUnit}</option>`;		
						})
					}

				})
				document.getElementsByClassName('nodeProperty')[num].innerHTML = html2;
			}).catch(res => {
				document.getElementsByClassName('nodeProperty')[num].innerHTML = html2;
			})
		}
	})
}




/*页面的保存*/
function buttonsave() {


	var naaType = $("input[type='radio'][ name='actionType']:checked").val() //警报类型，1为短信，2为email，3为站内信
	if (naaType == "1") {
		document.getElementById("email").disabled = true;
		var naaVal = $('#phonenum').val()
	} else if (naaType == "2") {
		document.getElementById("phonenum").disabled = true;
		var naaVal = $('#email').val() //发送报警对象
	}
	var naaContent = $('#alertInfo').val() //发送的报警内容
	var ntExpr = $('#deadTime').val() //失效时间 
	if (ntExpr != "") {
		var arr1 = ntExpr.split(" ");
		var sdate1 = arr1[0].split('-');
		var sdate2 = arr1[1].split(':');
		ntExpr = new Date(sdate1[0], sdate1[1] - 1, sdate1[2], sdate2[0], sdate2[1] - 1, sdate2[2], );
	}

	var ntName = $('#ntName').val() //触发器名称
	var ntInvl = $('#ntInvl').val() //触发器时间间隔

	var ntExec = $("input[type='radio'][ name='executed']:checked").val() //是否执行   可用标志，代表该触发器是否可用，0代表可用，1代表不可用
	var ntRept = $("input[type='radio'][ name='repeated']:checked").val() //是否重复执行 重用标志，代表是否重复执行，0代表重复，1代表不重复(触发一次后失效)
	var pass = true;

	var tzLink = $('.button-save');

	let nodeCondParams = [];

	for (var i = 0; i < numlength; i++) {

		if (pass == true) {

			var sceneSn = document.getElementsByClassName('sceneID')[i].value //场景id 场景标识
			var nodeSn = document.getElementsByClassName('nodeName')[i].value //  节点标识
			var naId = document.getElementsByClassName('nodeProperty')[i].value //节点属性
			var ncOp = document.getElementsByClassName('choseCondition')[i]
				.value //操作条件 触发器条件的操作符，1>，2>=，3<，4<=，5==，6新值，7冻结，8复活
			var ncVal = document.getElementsByClassName('boundaryValue')[i].value //边界值
			var ncFitTime = document.getElementsByClassName('detectionNumber')[i].value //符合条件该条件，需要连续满足操作结果的次数
			ncFitTime = Number(ncFitTime);

			let condition = {
				"naId": naId,
				"ncFitTime": ncFitTime,
				"ncOp": ncOp,
				"ncVal": ncVal,
				"nodeSn": nodeSn,
				"sceneSn": sceneSn,
				"ncId": ""
			}

			nodeCondParams.push(condition)
			if ((sceneSn == "") || (nodeSn == "") || (naId == "") || (ncOp == "") || (ncVal == "") || (ncFitTime ==
					"")) {
				alertCondition(); //提示没有输入添加信息
				tzLink.attr('href', 'javascript:void(0);'); //这种情况下禁止跳转，因为信息没有输入完整
				pass = false;
			}
		}

	}

	let nodeActAlertParam = {
		"naaContent": naaContent,
		"naaType": naaType,
		"naaVal": naaVal,
		"ntId": " "
	}

	let data = {
		"ntExec": ntExec,
		"ntExpr": ntExpr,
		"ntRept": ntRept,
		"ntInvl": ntInvl,
		"ntName": ntName,
		"nodeCondParams": nodeCondParams,
		"nodeActAlertParam": nodeActAlertParam
	}
	console.dir(data)


	if (pass == true && naaType == "2" && ($("#email").val() == "")) {
		alertEmail(); //提示没有输入邮箱号码
		tzLink.attr('href', 'javascript:void(0);');
		pass = false;
	} else if (pass == true && naaType == "1" && ($("#phonenum").val() == "")) {
		alertPhonenum(); //提示没有输入手机号码
		tzLink.attr('href', 'javascript:void(0);');
		pass = false;
	} else if (pass == true && naaContent == "") {
		alertInformation();
		tzLink.attr('href', 'javascript:void(0);');
		pass = false;
	} else if (pass == true && (ntInvl == "" || ntName == "")) {
		alertTrigger();
		tzLink.attr('href', 'javascript:void(0);');
		pass = false;
	}

	if (pass == true) {
		deletediv();
		// alert("保存" + ntId + "成功！");
		addAlert({
			url: "/rule",
			data: JSON.stringify(data)
		})
	}

}


function addAlert(data) {
	console.dir(data);
	postHttp(data).then(res => {
		console.log(res);
		promptModel({
			type: "modal-header-success",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>添加规则引擎页面',
			body: '添加规则成功'
		})
		setTimeout(function() {
			window.opener = null;
			window.close();
		}, 3000)
	}).catch(res => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>添加规则引擎页面',
			body: '添加规则失败'
		})
	})
}

function deletediv() {
	$("div").remove(".alertCondition");
	$("div").remove(".alertWay");
	$("div").remove(".alertEmail");
	$("div").remove(".alertPhonenum");
	$("div").remove(".alertInformation");
	$("div").remove(".alertTrigger");
}
/* 动态添加提示div */

function alertCondition() {
	deletediv();
	var alertName = $('<div></div>');
	alertName.attr('class', 'alertCondition');
	alertName.text('*报警条件填写不完整*');
	alertName.appendTo($('.add-condition-title'));
}

function alertEmail() {
	deletediv();
	var alertName = $('<div></div>');
	alertName.attr('class', 'alertEmail');
	alertName.text('*邮箱号未填写*');
	alertName.appendTo($('.alert-info-title'));
}

function alertPhonenum() {
	deletediv();
	var alertName = $('<div></div>');
	alertName.attr('class', 'alertPhonenum');
	alertName.text('*手机号码未填写*');
	alertName.appendTo($('.alert-info-title'));
}

function alertInformation() {
	deletediv();
	var alertName = $('<div></div>');
	alertName.attr('class', 'alertInformation');
	alertName.text('*报警内容信息未填写*');
	alertName.appendTo($('.alert-info-title'));
}


function alertTrigger() {
	deletediv();
	var alertName = $('<div></div>');
	alertName.attr('class', 'alertTrigger');
	alertName.text('*触发器信息未填写完整*');
	alertName.appendTo($('.alert-set-title'));
}


/*重置按钮*/
function reset() {
	location.reload(true);
}
