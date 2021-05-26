/**
 * 定义一个表单校验器
 */
function bindValidate() {
	$("#nodeInfo").bootstrapValidator({
		message: '输入的值是非法的', //默认提示信息
		feedbackIcons: { //提示图标
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			nodeNames: { // 与input等html元素的name值相同
				validators: {
					notEmpty: {
						message: '节点名称不能为空'
					}
				}
			}
		},
		submitHandler: function(validator, form, submitButton) {
			alert("submit");
		}
	})
}
/**
 * 节点基本信息的填充
 */
function showNodeInfo() {
	// 展示内容
	let nodeInfoShow = {
		"nodeSn": "",
		"nodeMap": "暂无",
	}
	for (let item of Object.entries(nodeInfoShow)) {
		$("[name='" + item[0] + "']").val(nodeInfo[item[0]] || item[1])
	}
	// 因为nodeName和Jquery中的nodeName冲突，所以单独拿出来写
	$("[name='nodeNames']").val(nodeInfo.nodeName);
	$("[name='nodeDesc']").html(nodeInfo.nodeDesc || "暂无节点介绍信息");

	$("#update-nodeinfo").on("click", nodeBtn);
}
/**
 * 更新节点基本信息
 */
function updateNodeInfo() {
	$("#nodeInfo").data('bootstrapValidator').validate();
	if (!$("#nodeInfo").data('bootstrapValidator').isValid()) {
		return;
	}
	let data = new FormData($("#nodeInfo")[0])
	let nodeInfoShow = {
		"nodeMap": "", // 节点映射
		"nodeDesc": "", // 节点描述信息
	}
	for (let item of Object.entries(nodeInfoShow)) {
		nodeInfoShow[item[0]] = data.get(item[0]) || "";
	}
	// nodeName特殊
	nodeInfoShow.nodeName = data.get("nodeNames");
	putHttp({
		"url": `/node/${sceneSn}/${nodeSn}`,
		"data": JSON.stringify(nodeInfoShow)
	}).then(res => {
		$("update-node").attr("disabled", true);
		promptModel({
			type: "modal-header-success",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点更新',
			body: '节点信息更新成功'
		})
	}).catch(_ => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点更新',
			body: '节点信息更新失败'
		})
	})
}
/**
 * 节点属性的填充
 */
function showNodeAttr() {
	$("#add-node-attr").on("click", nodeBtn);
	let nodeAttr = nodeInfo.nodeAttrList;
	if (nodeAttr.length <= 0) {
		$("#node-attr-list").hide()
		$("#node-attr-list-prompt").show()
		return;
	}
	$("#node-attr-list").show()
	$("#node-attr-list-prompt").hide()
	// 展示内容
	let tbody = "";
	for (let item of nodeAttr) {
		let customize = false;
		let select = "<option></option>";
		for (let item1 of nodeAttrStd) {
			select += "<option value='" + item1.nasId + "' " + ((item.naSym == item1.nasSym) ? "selected" : "") + ">" +
				item1.nasDesc + "</option>";
		}
		if (select.search("selected") == -1) {
			customize = true;
			select += "<option value='-1' selected>自定义属性</option>";
		} else {
			select += "<option value='-1'>自定义属性</option>";
		}
		// tr要记录nakey，更新过程中使用
		tbody +=
			`
				<tr data-nakey="${item.naKey}">
					<td>
						<select name="type">
							${select}
						</select>
					</td>
					<td>
						<input type="text" name="naName" value="${item.naName}" placeholder="请输入属性名称" />
					</td>
					<td>
						<input type="text" name="naKey" value="${item.naKey}" placeholder="请输入属性标识" />
					</td>
					<td>
						<input type="text" name="naMap" value="${item.naMap || '暂无'}" size="10" placeholder="请输入属性映射" />
					</td>
					<td>
						<input type="text" name="naSym" value="${item.naSym}" ${customize ? "" : "readonly"} size="10" placeholder="请输入属性符号" />
					</td>
					<td>
						<input type="text" name="naUnit" value="${item.naUnit}" ${customize ? "" : "readonly"} size="10" placeholder="请输入属性单位" />
					</td>
					<td>
						<button type="button" class="btn btn-xs btn-success update-node-attr" disabled>更新</button>
						<button type="button" class="btn btn-xs btn-danger delete-node-attr">删除</button>
					</td>
				</tr>
		`
	}
	$("#node-attr-list tbody").html(tbody);
	$("select").on("change", nodeBtn) // select下拉框的事件
	$('tr input').on("change", nodeBtn); // input框中的内容进行更新
	$(".update-node-attr").on("click", nodeBtn); // 更新按钮
	$(".delete-node-attr").on("click", nodeBtn); // 删除按钮
}
/**
 * 添加节点属性HTML
 */
function addNodeAttrHTML() {
	$("#node-attr-list").show()
	$("#node-attr-list-prompt").hide()
	let select = "<option></option>";
	for (let item1 of nodeAttrStd) {
		select += "<option value='" + item1.nasId + "' >" + item1.nasDesc + "</option>";
	}
	select += "<option value='-1'>自定义属性</option>";
	let tr =
		`
			<tr>
				<td>
					<select>
						${select}
					</select>
				</td>
				<td>
					<input type="text" name="naName" placeholder="请输入属性名称" disabled />
				</td>
				<td>
					<input type="text" name="naKey" placeholder="请输入属性标识" disabled />
				</td>
				<td>
					<input type="text" name="naMap" size="10" placeholder="请输入属性映射" disabled />
				</td>
				<td>
					<input type="text" name="naSym" readonly="true" size="10" placeholder="请输入属性符号" disabled />
				</td>
				<td>
					<input type="text" name="naUnit" readonly="true" size="10" placeholder="请输入属性单位" disabled />
				</td>
				<td>
					<button type="button" id="save" class="btn btn-xs btn-success save-node-attr" disabled >保存</button>
					<button type="button" class="btn btn-xs btn-danger delete-node-attr">删除</button>
				</td>
			</tr>
	`
	$("#node-attr-list tbody").append(tr)
	$("select").on("change", nodeBtn)
	$("tr input").on("change", nodeBtn)
	$(".save-node-attr").on("click", nodeBtn)
	$(".delete-node-attr").on("click", nodeBtn);
}
/**
 * 保存节点属性
 * @param {Object} 删除的一行tr{Jquery对象}
 */
function saveNodeAttr(tr) {
	// 更新节点属性成功后
	let nodeAttrs = []
	let nodeAttrItem = {}
	let inputs = tr.find("input").toArray();
	inputs.forEach(function(item) {
		nodeAttrItem[item.name] = item.value
	})
	nodeAttrs.push(nodeAttrItem)
	postHttp({
		url: `/nodeAttr/${sceneSn}/${nodeSn}`,
		data: JSON.stringify(nodeAttrs)
	}).then(res => {
		promptModel({
			type: "modal-header-success",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点信息',
			body: '节点属性新增成功'
		})

		// 保存功能之后的内容
		let btn = $(tr.find("button")[0])
		btn.html("更新")
		btn.removeClass("save-node-attr")
		btn.addClass("update-node-attr")
		btn.prop("disabled", true)

		tr.data("nakey", nodeAttrItem.naKey);

	}).catch(_ => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点信息',
			body: '节点属性新增失败，请稍后再试'
		})
	})

}
/**
 * 删除节点属性
 * @param {Object} 删除的一行tr{Jquery对象}
 */
function deleteNodeAttr(tr) {
	// 判断类型，更新请求后台删除节点；否则不需要请求后台删除节点
	let naKey = tr[0].dataset.hasOwnProperty("nakey") ? tr[0].dataset.nakey : ""
	if ("" === naKey) {
		promptModel({
			type: "modal-header-success",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点信息',
			body: '节点属性删除成功'
		})
		tr.remove()
		if ($("#node-attr-list table tbody tr").length <= 0) {
			$("#node-attr-list").hide()
			$("#node-attr-list-prompt").show()
		}
		return;
	}

	deleteHttp({
		url: `/nodeAttr/${sceneSn}/${nodeSn}/${naKey}`
	}).then(res => {
		promptModel({
			type: "modal-header-success",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点信息',
			body: '节点属性删除成功'
		})
		tr.remove()
		if ($("#node-attr-list table tbody tr").length <= 0) {
			$("#node-attr-list").hide()
			$("#node-attr-list-prompt").show()
		}
	}).catch(_ => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点信息',
			body: '节点属性删除失败，请稍后再试'
		})
	})





}
/**
 * 更新节点属性
 * @param {Object} 更新的一行tr{Jquery对象}
 */
function updateNodeAttr(tr) {
	let naKey = tr[0].dataset.hasOwnProperty("nakey") ? tr[0].dataset.nakey : "";
	// 更新节点属性成功后
	let nodeAttrItem = {}
	let inputs = tr.find("input").toArray();
	inputs.forEach(function(item) {
		nodeAttrItem[item.name] = item.value
	})

	putHttp({
		url: `/nodeAttr/${sceneSn}/${nodeSn}/${naKey}`,
		data: JSON.stringify(nodeAttrItem)
	}).then(res => {
		// 同时要更新nakay的值
		tr[0].dataset.nakey = nodeAttrItem.naKey;
		promptModel({
			type: "modal-header-success",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点更新',
			body: '节点属性更新成功'
		})
		let btn = $(tr.find("button")[0]);
		btn.prop("disabled", true)
	}).catch(_ => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点更新',
			body: '节点属性更新失败'
		})
	})

}
/**
 * 节点属性中节点类型的选择
 * @param {Object} element 点击的SELECT标签{Jquery}
 */
function nodeAttrSelect(element) {
	// 获取所有的当前select标签的父元素<tr>
	let names = ["naSym", "naUnit"]; // 只读属性
	let selectedIndex = element[0].selectedIndex - 1;
	let tr = element.parent().parent();
	let inputs = tr.find("input").toArray();
	let btn = $(tr.find("button")[0]);
	// 选择第一个
	if (selectedIndex < 0) {
		inputs.forEach(function(item) {
			item.value = "";
			item.setAttribute("disabled", "true")
		})
		btn.prop("disabled", "true")
		return;
	}
	// 选择最后一个
	if (selectedIndex >= nodeAttrStd.length) {
		inputs.forEach(function(item) {
			item.value = "";
			item.removeAttribute("disabled")
			if (names.includes(item.name)) {
				item.removeAttribute("readonly")
			}
		})
		btn.removeAttr("disabled")
		return;
	}
	// 正常选择
	inputs.forEach(function(item) {
		item.removeAttribute("disabled")
		switch (item.name) {
			case "naSym":
				item.setAttribute("readonly", "true")
				item.value = nodeAttrStd[selectedIndex].nasSym
				break;
			case "naUnit":
				item.setAttribute("readonly", "true")
				item.value = nodeAttrStd[selectedIndex].nasUnit
				break;
			default:
				break;
		}
	})
	btn.removeAttr("disabled")
}
/**
 * 按钮的相关事件
 */
function nodeBtn() {
	// SELECT事件
	if (this.tagName === "SELECT") {
		nodeAttrSelect($(this))
		return;
	} else if (this.tagName === "INPUT") { // 输入框发生变化
		$(this).parent().parent().find("button")[0].removeAttribute("disabled")
	} else if (this.id === "add-node-attr") { // 进行节点属性的添加
		addNodeAttrHTML()
		return;
	} else if (this.id === "update-nodeinfo") {
		updateNodeInfo()
		return;
	}
	// 操作节点属性
	let tr = $(this).parent().parent()
	if (/update-node-attr/.test(this.className)) { // 更新节点属性
		updateNodeAttr(tr)
		return;
	} else if (/delete-node-attr/.test(this.className)) { // 删除节点属性
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点更新',
			body: `确定要删除此属性吗?`,
			footer: '<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button><button id="deleteNodeAttrCommit" type="button" class="btn btn-danger">删除</button>'
		})
		$("#deleteNodeAttrCommit").click(function() {
			deleteNodeAttr(tr)
		})
		return;
	} else if (/save-node-attr/.test(this.className)) { // 保存新增的节点属性
		saveNodeAttr(tr)
		return;
	}
	// 节点命令相关事件

}
/**
 * 本页面的全局变量
 */
// sceneSn的获取
let sceneObj = new RegExp(getSearchKey("scenesn").self + "=(.*)");
let sceneSn = location.search.match(sceneObj)[1].split("&")[0]
// nodeSn的获取
let nodeObj = new RegExp(getSearchKey("nodesn").self + "=(.*)");
let nodeSn = location.search.match(nodeObj)[1].split("&")[0]
// nodeInfo的声明
let nodeInfo = null;
let nodeAttrStd = null;
$(function() {
	// 路径导航初始化
	trajectoryUnCode(location.pathname, location.search)
	
	// 使用表单校验器
	bindValidate()
	// 添加失去焦点事件
	$(".form-control").on("blur", function() {
		$("#nodeInfo").data('bootstrapValidator').validateField(this.name)
	})
	$("#nodeInfo input").on("change", function() {
		$("#update-nodeinfo").removeAttr("disabled");
	})
	$("#nodeInfo textarea").on("change", function() {
		$("#update-nodeinfo").removeAttr("disabled");
	})
	// 获取节点属性模版
	getHttp({
		url: "/nodeAttrStd/list?page=1&limit=10",
		contentType: "x-www-form-urlencoded"
	}).then(res => {
		nodeAttrStd = res.data
		getHttp({
			url: "/node/" + sceneSn + "/" + nodeSn,
			contentType: "x-www-form-urlencoded"
		}).then(res => {
			nodeInfo = res;
			// 展示节点的基本信息
			showNodeInfo();
			console.log(sceneSn);
			// 展示节点属性信息
			showNodeAttr();
		})
	}).catch(res => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点信息',
			body: '节点信息获取失败'
		})
	})
})
