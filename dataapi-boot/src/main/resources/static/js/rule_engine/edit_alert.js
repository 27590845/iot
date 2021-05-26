var numlength = 0; //从ntId中读取到的 nodeCondParams的个数
var numlength1 = 0; //编辑完成后（编辑中可以增加 删除） 最终nodeCondParams的个数
var naaId;  
let ntIdObj = new RegExp(getSearchKey("ntid").self + "=(.*)");
let ntId = location.search.match(ntIdObj)[1].split("&")[0]
console.log(ntId);
  
// 添加一条关联条件
function addCondition() {
	numlength1++;
	console.log(numlength1);

	var conditions = $("#conditions");

	if ($(".condition", conditions).length > 15) {
		return;
	}
	// 新增一个条件
	var condition = $("#template1 .condition").clone(true).show();
	// 添加到条件域
	conditions.append(condition);
	buttonsave();
	// nodeunit();
	buttonremove(condition);
	showNode(numlength1);
}

// 绑定删除和更新按钮
function bindCondition(condition, ncid) {
	// 绑定删除
	$(".remove", condition).click(function() {
		numlength1--;
		console.log(numlength1);
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点更新',
			body: `确定要删除` + ncid + `此属性吗?`,
			footer: '<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button><button id="deleteNodeAttrCommit" type="button" class="btn btn-danger">删除</button>'
		})
		$("#deleteNodeAttrCommit").click(function() {
			deleteAlert(ncid);
			condition.remove();
		})
		return;
	});



	// 绑定更新
	$(".update", condition).click(function() {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点更新',
			body: `确定要更新` + ncid + `此属性吗?`,
			footer: '<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button><button id="deleteNodeAttrCommit1" type="button" class="btn btn-danger">确认</button>'
		})
		$("#deleteNodeAttrCommit1").click(function() {
			let NodeCondParam = [];
			//更新所需要的数据
			for (var i = 0; i < numlength1; i++) {
				if (document.getElementsByClassName('data-ncid')[i].value == ncid) {
					var sceneSn = document.getElementsByClassName('sceneID')[i].value
					var nodeSn = document.getElementsByClassName('nodeName')[i].id //  节点标识
					var naId = document.getElementsByClassName('nodeProperty')[i].id //节点属性
					naId = Number(naId);
					var ncOp = document.getElementsByClassName('choseCondition')[i]
						.value //操作条件 触发器条件的操作符，1>，2>=，3<，4<=，5==，6新值，7冻结，8复活
					var ncVal = document.getElementsByClassName('boundaryValue')[i].value //边界值
					var ncFitTime = document.getElementsByClassName('detectionNumber')[i]
						.value //符合条件该条件，需要连续满足操作结果的次数
					ncFitTime = Number(ncFitTime);

					NodeCondParam = {
						"naId": naId,
						"ncFitTime": ncFitTime,
						"ncOp": ncOp,
						"ncVal": ncVal,
						"nodeSn": nodeSn,
						"sceneSn": sceneSn,
					}
				}
			}
			updateOneAlert({
				url: "/rule/nc/" + ncid,
				contentType: "application/json",
				data: JSON.stringify(NodeCondParam)
			})
		})
		return;
	});
}
$(function() {
	// 路径导航初始化
	trajectoryUnCode(location.pathname, location.search)

	var Information = function(naaType, naaVal, naaContent, naaId, ntId, ntExec, ntExpr, ntRept, ntInvl,
		ntName) {
		this.naaType = naaType;
		this.naaVal = naaVal;
		this.naaContent = naaContent;
		this.naaId = naaId;
		this.ntId = ntId;
		this.ntExec = ntExec;
		this.ntExpr = ntExpr;
		this.ntRept = ntRept;
		this.ntInvl = ntInvl;
		this.ntName = ntName;
	}
	var Information1 = function(ncFitTime, ncOp, ncVal, nodeSn, sceneSn, naId, ntId, ncId, nodeName, naName,
		sceneName) {
		this.ncFitTime = ncFitTime;
		this.ncOp = ncOp;
		this.ncVal = ncVal;
		this.nodeSn = nodeSn;
		this.sceneSn = sceneSn;
		this.naId = naId;
		this.ntId = ntId;
		this.ncId = ncId;
		this.nodeName = nodeName;
		this.naName = naName;
		this.sceneName = sceneName;
	}

	var informations = [];
	var informations1 = [];


	getHttp({
		url: `/rule/${ntId}`
	}).then(res => {
		data = res;
		console.dir(data);
		informations.push(new Information(data.nodeActAlertParam
			.naaType, data.nodeActAlertParam.naaVal, data
			.nodeActAlertParam.naaContent, data
			.nodeActAlertParam.naaId, data
			.nodeActAlertParam.ntId, data.ntExec, data.ntExpr, data.ntRept, data.ntInvl, data
			.ntName));
		for (var i = 0; i < data.nodeCondParams.length; i++) {
			informations1.push(new Information1(data.nodeCondParams[i]
				.ncFitTime, data.nodeCondParams[i]
				.ncOp, data.nodeCondParams[i]
				.ncVal, data.nodeCondParams[i]
				.nodeSn, data.nodeCondParams[i]
				.sceneSn, data.nodeCondParams[i]
				.naId, data.nodeCondParams[i]
				.ntId, data.nodeCondParams[i]
				.ncId, data.nodeCondParams[i]
				.nodeName, data.nodeCondParams[i]
				.naName, data.nodeCondParams[i]
				.sceneName));
		}

		//将取到的数据对号入座
		var obj = informations[0];
		if (obj["naaType"] == "1") {
			document.getElementById("email").disabled = true;
			document.getElementById("phonenum").disabled = false;
			document.getElementById("phonenum").value = obj["naaVal"];
		} else if (obj["naaType"] == "2") {
			document.getElementById("phonenum").disabled = true;
			document.getElementById("email").disabled = false;
			document.getElementById("email").value = obj["naaVal"]; //填充报警联系号码
		} else if (naaType == "3") {
			document.getElementById("phonenum").disabled = true;
			document.getElementById("email").disabled = true;
		}
		naaId = obj["naaId"];

		var radioObj = document.getElementsByName("actionType");
		for (var i = 0; i < radioObj.length; i++) {
			if (radioObj[i].value == obj["naaType"]) {
				radioObj[i].checked = true; //设置报警方式选中	
			}
		}
		var radioObj1 = document.getElementsByName("executed");
		for (var i = 0; i < radioObj1.length; i++) {
			if (radioObj1[i].value == obj["ntExec"]) {
				radioObj1[i].checked = true; //设置是否执行选中
			}

		}
		var radioObj2 = document.getElementsByName("repeated");
		for (var i = 0; i < radioObj2.length; i++) {
			if (radioObj2[i].value == obj["ntRept"]) {
				radioObj2[i].checked = true; //设置是否重复选中
			}

		}

		document.getElementById("alertInfo").value = obj["naaContent"]; //报警内容
		document.getElementById("ntName").value = obj["ntName"]; //触发器名称
		document.getElementById("ntInvl").value = obj["ntInvl"]; //触发器时间间隔

		if (obj["ntExpr"] == null) {
			document.getElementById("deadTime").value = null; //失效时间
		} else {
			$("#deadTime").val(dateTimeFormat(obj.ntExpr));
		}

		var numlength = informations1.length;
		numlength1 = numlength - 1;

		// 对每一行数据进行对应填充，同时绑定该行的更新和删除按钮
		for (var i = 0; i < informations1.length; i++) {
			var ob = informations1[i];
			console.log(ob["ncId"]);

			var conditions = $("#conditions");
			var condition = $("#template .condition").clone(true).show();
			conditions.append(condition);
			
			document.getElementsByClassName('sceneID')[i].value = ob["sceneSn"]; //场景id 场景标识
			document.getElementsByClassName("nodeName")[i].value = ob["nodeName"]; //节点标识
			let naNameUnit = ob["naName"];
			if ( ob["naName"] == "电流" ||  ob["naName"] == "电流1" ||  ob["naName"]==
				"电流2" ||  ob["naName"] == "电流3")
				naNameUnit += ` ----单位:A`;
				
			else if ( ob["naName"] == "电压" ||  ob["naName"]== "电压1" || ob["naName"] ==
				"电压2" || ob["naName"] ==
				"电压3")
				naNameUnit += ` ----单位:V`;
			
			else if ( ob["naName"]== "温度" || ob["naName"]== "温度1" ||  ob["naName"] ==
				"温度2" ||  ob["naName"] ==
				"温度3")
				naNameUnit += ` ----单位:℃`;
			
			else if ( ob["naName"] == "湿度" ||  ob["naName"]== "湿度1" ||  ob["naName"] ==
				"湿度2" ||  ob["naName"] ==
				"湿度3")
				naNameUnit += ` ----单位:%`;
			
			else if ( ob["naName"] == "压力" ||  ob["naName"] == "压力1" ||  ob["naName"] ==
				"压力2" ||  ob["naName"] ==
				"压力3")
				naNameUnit += ` ----单位:pm`;
				
			else if ( ob["naName"] == "重量" )
				naNameUnit += ` ----单位:吨`;
			document.getElementsByClassName("nodeProperty")[i].value = naNameUnit; //节点属性
			document.getElementsByClassName("nodeName")[i].id = ob["nodeSn"]; //节点标识
			document.getElementsByClassName("nodeProperty")[i].id = ob["naId"]; //节点属性
			document.getElementsByClassName("choseCondition")[i].value = ob["ncOp"]; //节点操作
			document.getElementsByClassName("boundaryValue")[i].value = ob["ncVal"]; //边界值
			document.getElementsByClassName("detectionNumber")[i].value = ob["ncFitTime"]; //操作次数
			document.getElementsByClassName("data-ncid")[i].value = ob["ncId"]; //操作次数

			bindCondition(condition, ob["ncId"]);

		}

		// 设置报警方式唯一可选
		$(document).click(function() {
			var naaType = $("input[type='radio'][ name='actionType']:checked")
				.val() //警报类型，1为短信，2为email，3为站内信
			if (naaType == "1") {
				document.getElementById("email").value = "";
				document.getElementById("email").disabled = true;
				document.getElementById("phonenum").disabled = false;
			} else if (naaType == "2") {
				document.getElementById("phonenum").value = "";
				document.getElementById("phonenum").disabled = true;
				document.getElementById("email").disabled = false;
			} else if (naaType == "3") {
				document.getElementById("email").value = "";
				document.getElementById("phonenum").value = "";
				document.getElementById("phonenum").disabled = true;
				document.getElementById("email").disabled = true;
			}
		});


	}).catch(_ => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>报警信息',
			body: '报警信息获取失败'
		})
	})

})


// 新添加一行数据 删除
function buttonremove(condition) {
	$(".buttonremove", condition).click(function() {
		numlength1--;
		console.log(numlength1);
		condition.remove();
		return;
	});
}
// 新添加一行数据 保存
function buttonsave() {

	$(".buttonsave").click(function() {
		var pass = true;
		var tzLink = $('.buttonsave');

		var sceneSn = document.getElementsByClassName('sceneID')[numlength1].value //  节点标识
		var nodeSn = document.getElementsByClassName('nodeName')[numlength1].value //  节点标识
		var naId = document.getElementsByClassName('nodeProperty')[numlength1].value //节点属性
		var ncOp = document.getElementsByClassName('choseCondition')[numlength1]
			.value //操作条件 触发器条件的操作符，1>，2>=，3<，4<=，5==，6新值，7冻结，8复活
		var ncVal = document.getElementsByClassName('boundaryValue')[numlength1].value //边界值
		var ncFitTime = document.getElementsByClassName('detectionNumber')[numlength1]
			.value //符合条件该条件，需要连续满足操作结果的次数
		ncFitTime = Number(ncFitTime);
		// 点击后单位也会自动添加

		let nodeCondParams = {
			"naId": naId,
			"ncFitTime": ncFitTime,
			"ncOp": ncOp,
			"ncVal": ncVal,
			"nodeSn": nodeSn,
			"sceneSn": sceneSn,
			"ntId": ntId
		}

		if ((sceneSn == "") || (nodeSn == "") || (naId == "") || (ncOp == "") || (ncVal == "") || (ncFitTime ==
				"")) {
			alertCondition(); //提示没有输入添加信息
			tzLink.attr('href', 'javascript:void(0);'); //这种情况下禁止跳转，因为信息没有输入完整
			pass = false;
		}

		if (pass == true) {
			deletediv();

			saveAlert({
				url: "/rule/" + ntId,
				contentType: "application/json",
				data: JSON.stringify(nodeCondParams)
			})
		}
	})
}


// 新添加一行数据保存
function saveAlert(data) {
	postHttp(data).then(res => {
		promptModel({
			type: "modal-header-success",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>编辑规则引擎页面',
			body: '保存规则成功'
		})

		location.reload(true);
	}).catch(res => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>编辑规则引擎页面',
			body: '保存规则失败'
		})
	})
}

// 对一行数据进行删除
function deleteAlert(ncid) {
	console.log(ncid);
	deleteHttp({
		url: "/rule/nc/" + ncid,
		contentType: "x-www-form-urlencoded"
	}).then((res) => {
		console.log(ncid);
		location.reload(false);

	}).catch(res => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>报警节点删除提醒',
			body: '删除失败'
		})
	})
}


// 更新一行数据
function updateOneAlert(data) {
	patchHttp(data).then((res) => {
		console.log(data);
		location.reload(true);
	}).catch(res => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>报警节点更新提醒',
			body: '更新失败'
		})
	})
}
//将sceneID 与 nodeSn 与 naId 进行绑定
function showNode(num) {
		
		document.getElementsByClassName('sceneID')[num].onblur=(function(){
		   var sceneSn = document.getElementsByClassName('sceneID')[num].value //场景id 场景标识
		   console.log(sceneSn);
		   let html1=`<option value="" class="node"selected="selected">------请选择------</option>`
		   if(sceneSn!=""){
		   	getHttp({
		   		url: "/scene/" + sceneSn,
		   		contentType: "x-www-form-urlencoded"
		   	}).then((res) => {
		   		console.log(res);
		   		res.nodeVos.forEach(function(Item) {
		   			console.log(Item.nodeSn);	   			
		   			html1+=`<option value = "${Item.nodeSn}">${Item.nodeName}</option>`;
		   		})		
		   		document.getElementsByClassName('nodeName')[num].innerHTML = html1;	
		   	}).catch(res => {
		   		document.getElementsByClassName('nodeName')[num].innerHTML = html1;
		   	})
		   }
	   })
	   document.getElementsByClassName('nodeName')[num].onblur=(function(){
				   var sceneSn = document.getElementsByClassName('sceneID')[num].value //场景id 场景标识
				   var nodeSn = document.getElementsByClassName('nodeName')[num].value //  节点标识
				   console.log(nodeSn);
				   let html2=`<option value=""  class="property" selected="selected">----请选择----</option> 　`
				   if(nodeSn!=""){
				   	getHttp({
				   		url: "/scene/" + sceneSn,
				   		contentType: "x-www-form-urlencoded"
				   	}).then((res) => {
				   		console.log(res);
				   		res.nodeVos.forEach(function(Item) {
				   			
							if(Item.nodeSn == nodeSn){
								Item.nodeAttrList.forEach(function(Item1) {
									console.log(Item1.naName);
									html2 +=
										`<option value = "${Item1.naId}">${Item1.naName} ----单位:${Item1.naUnit}</option>`;	
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
// 对报警设置（包括是否执行，是否重复，失效时间,触发器名称，触发器时间间隔）的更新
function buttonupdate() {

	var pass = true;
	var tzLink = $('.button-update');

	var ntExpr = $('#deadTime').val() //失效时间 
	console.log(ntExpr);
	if (ntExpr != "") {
		var arr1 = ntExpr.split(" ");
		var sdate1 = arr1[0].split('-');
		var sdate2 = arr1[1].split(':');
		ntExpr = new Date(sdate1[0], sdate1[1] - 1, sdate1[2], sdate2[0], sdate2[1] - 1, sdate2[2], );
	} else {
		ntExpr = null;
	}
	var ntName = $('#ntName').val() //触发器名称
	var ntInvl = $('#ntInvl').val() //触发器时间间隔
	var ntExec = $("input[type='radio'][ name='executed']:checked").val() //是否执行   可用标志，代表该触发器是否可用，0代表可用，1代表不可用
	var ntRept = $("input[type='radio'][ name='repeated']:checked")
		.val() //是否重复执行 重用标志，代表是否重复执行，0代表重复，1代表不重复(触发一次后失效


	if (ntInvl == "" || ntName == "") {
		alertTrigger();
		tzLink.attr('href', 'javascript:void(0);');
		pass = false;
	}

	let data2 = {
		"ntDesc": "",
		"ntExec": ntExec,
		"ntExpr": ntExpr,
		"ntId": ntId,
		"ntInvl": ntInvl,
		"ntName": ntName,
		"ntRept": ntRept
	}
	if (pass == true) {
		patchHttp({
			url: "/rule/" + ntId,
			contentType: "application/json",
			data: JSON.stringify(data2)
		}).then((res) => {
			location.reload(true);
		}).catch(res => {
			promptModel({
				type: "modal-header-danger",
				title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>报警节点更新提醒',
				body: '更新失败'
			})
		})
	}
}


// 对报警通知（包括报警方式，联系方式，报警信息）的更新
function buttonupdateup() {
	var pass = true;
	var tzLink = $('.button-update');

	var naaType = $("input[type='radio'][ name='actionType']:checked")
		.val() //警报类型，1为短信，2为email，3为站内信
	if (naaType == "1") {
		var naaVal = $('#phonenum').val()
		if (naaVal == "" && pass==true) {
			alertPhonenum();
			tzLink.attr('href', 'javascript:void(0);');
			pass = false;
		}
	} else if (naaType == "2") {
		var naaVal = $('#email').val() //发送报警对象
		if (naaVal == ""  && pass==true) {
			alertEmail();
			tzLink.attr('href', 'javascript:void(0);');
			pass = false;
		}
	}
	var naaContent = $('#alertInfo').val() //发送的报警内容
	if (naaContent == "" && pass==true) {
		alertInformation();
		tzLink.attr('href', 'javascript:void(0);');
		pass = false;
	}

	let data1 = {
		"naaContent": naaContent,
		"naaType": naaType,
		"naaVal": naaVal,
		"ntId": ntId,
		"naaId": naaId,
	}
	if (pass == true) {
		patchHttp({
			url: "/rule/naa/" + naaId,
			contentType: "application/json",
			data: JSON.stringify(data1)
		}).then((res) => {
			location.reload(true);
		}).catch(res => {
			promptModel({
				type: "modal-header-danger",
				title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>报警节点更新提醒',
				body: '更新失败'
			})
		})
	}
}

// 对页面进行保存时，信息填的不完整 进行报警
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

function alertWay() {
	deletediv();
	var alertName = $('<div></div>');
	alertName.attr('class', 'alertWay');
	alertName.text('*报警方式未选择*');
	alertName.appendTo($('.alert-info-title'));
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
