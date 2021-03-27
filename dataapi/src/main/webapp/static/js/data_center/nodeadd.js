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
 * 添加节点属性HTML
 */
function addNodeAttr() {
	if ($("#node-attr-list table tbody tr").length == 0) {
		$("#node-attr-list").show()
		$("#node-attr-list-prompt").hide()
	}
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
					<input type="text" name="naUnit" readonly="true" size="10" placeholder="请输入属性单位" disabled />
				</td>
				<td>
					<input type="text" name="naSym" readonly="true" size="10" placeholder="请输入属性符号" disabled />
				</td>
				<td>
					<button type="button" class="btn btn-xs btn-danger delete-node-attr">删除</button>
				</td>
			</tr>
	`
	$("#node-attr-list tbody").append(tr)
	$("select").on("change", nodeBtn)
	$(".delete-node-attr").on("click", nodeBtn);
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
	// 选择第一个
	if (selectedIndex < 0) {
		inputs.forEach(function(item) {
			item.value = "";
			item.setAttribute("disabled", "true")
		})
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
}
/**
 * 删除节点属性
 * @param {Object} 删除的一行tr{Jquery对象}
 */
function deleteNodeAttr(tr) {
	// 删除节点属性
	tr.remove()
	if ($("#node-attr-list table tbody tr").length == 0) {
		$("#node-attr-list").hide()
		$("#node-attr-list-prompt").show()
	}
}
/**
 * 获取节点信息
 * @return {Object} nodeInfoShow 节点的基本信息
 */
function getNodeInfo() {
	let nodeInfoShow = {
		"nodeMap": "", // 节点映射
		"nodeSn": "", // 节点Sn
		"nodeDesc": "", //节点描述信息
	};
	for(let item of Object.entries(nodeInfoShow)) {
		nodeInfoShow[item[0]] = $("[name="+ item[0] +"]").val() || "";
	}
	// nodeName特殊
	nodeInfoShow.nodeName = $("input[name=nodeNames]").val() || "";
	return nodeInfoShow;
}
/**
 * 获取节点属性信息
 * @return {Object} nodeAttrParams 节点属性属性
 */
function getNodeAttrInfo() {
	let nodeAttrParams = [];
	let nodeAttrs = [
		"naForm",
		"naKey",
		"naMap",
		"naName",
		"naSym",
		"naUnit"
	]
	let nodeAttrReq = ["naKey", "naName", "naSym", "naUnit"]; // 必要字段的检测
	let trs = $("#node-attr-list table tbody tr").toArray();
	trs.forEach(function(tr) {
		let tds = $(tr).find("td").toArray()
		let nodeAttrItem = {};
		tds.forEach(function(td) {
			let input = $(td).children("input");
			if (input.length > 0 && nodeAttrs.includes(input.attr("name"))) {
				// 检验字段是否为空
				if (nodeAttrReq.includes(input.attr("name")) && (input.val() === "")) {
					promptModel({
						type: "modal-header-danger",
						title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点属性',
						body: '节点属性信息不完整'
					})
					return null;
				}
				console.log("节点属性")
				nodeAttrItem[input.attr("name")] = input.val();
			}
		})
		nodeAttrParams.push(nodeAttrItem)
	})
	return nodeAttrParams;
}

/**
 * 保存新增的节点信息
 */
function saveNode() {
	$("#nodeInfo").data('bootstrapValidator').validate();
	if (!$("#nodeInfo").data('bootstrapValidator').isValid()) {
		return;
	}
	Object.assign(nodeInfo, getNodeInfo())
	let nodeAttrParams = getNodeAttrInfo()
	if ( null === nodeAttrParams ) { // 表示必要字段没有填写
		return;
	}
	nodeInfo.nodeAttrParams = nodeAttrParams;
	postHttp({
		url: '/node',
		data: JSON.stringify(nodeInfo)
	}).then(res => {
		promptModel({
			type: "modal-header-success",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点添加',
			body: '节点添加成功'
		})
		setTimeout(function() {
			window.opener = null;
			window.close()
		}, 3000)
	}).catch(_=>{
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点添加',
			body: '网络异常,节点添加失败。请稍后再试!'
		})
	})
	
	
}
/**
 * 按钮的相关事件
 */
function nodeBtn() {
	if (this.tagName === "SELECT") { // SELECT事件
		nodeAttrSelect($(this))
		return;
	} else if (this.id === "add-node") { // 保存节点
		saveNode()
	}
	// 操作节点属性
	let tr = $(this).parent().parent()
	if (this.id === "add-node-attr") { // 添加节点属性
		addNodeAttr()
		return;
	} else if (/delete-node-attr/.test(this.className)) { // 删除节点属性
		deleteNodeAttr(tr)
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
// nodeInfo的声明
let nodeInfo = {
	"nodeDesc": "", // 节点简介
	"nodeMap": "", // 节点映射
	"nodeName": "", // 节点名称
	"nodeSn": "", // 节点Sn
	"sceneSn": sceneSn // 场景Sn
};
let nodeAttrStd = null;
$("#node-attr-list").hide() // 默认不显示
$(function() {
	// 路径导航初始化
	trajectoryUnCode(location.pathname, location.search)

	// 使用表单校验器
	bindValidate()
	// 添加失去焦点事件
	$(".form-control").on("blur", function() {
		$("#nodeInfo").data('bootstrapValidator').validateField(this.name)
	})
	// 获取节点属性模版
	getHttp({
		url: "/nodeAttrStd/list?page=1&limit=10",
		contentType: "x-www-form-urlencoded"
	}).then(res => {
		nodeAttrStd = res.data;
		// 添加节点属性的事件
		$("#add-node-attr").on("click", nodeBtn);
		$("#add-node").on("click", nodeBtn);
	}).catch(res => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点信息',
			body: '节点信息获取失败'
		})
	})

})
