/**
 * 获取下拉框的内容，进行选项的填充
 */
function initPage() {
	// 进行页面的填充
	let commCode = "<option></option>"
	for (let item of Object.entries(communicate)) {
		commCode += `<option value='${item[0]}'>${item[1]}</option>`
	}
	$("#commCode").html(commCode)
	let usageCode = "<option></option>"
	for (let item of Object.entries(usage)) {
		usageCode += `<option value='${item[0]}'>${item[1]}</option>`
	}
	$("#usageCode").html(usageCode)

	// 使用表单校验器
	bindValidate()
	// 添加失去焦点事件
	$(".form-control").on("blur", function() {
		$("#sceneInfo").data('bootstrapValidator').validateField(this.name)
	})
	// 添加按钮点击事件
	$("#sceneAddBtn").on("click", function() {
		$("#sceneInfo").data('bootstrapValidator').validate();
		if (!$("#sceneInfo").data('bootstrapValidator').isValid()) {
			return;
		}
		// 进行ajax请求，提交表单内容
		let formInfo = decodeURIComponent($("#sceneInfo").serialize(), true) // 获取表单内容并进行解码
		let data = {}
		formInfo.split("&").forEach(function(item) {
			data[item.split("=")[0]] = item.split("=")[1].trim()
		})
		addScene({
			url: "/scene",
			data: JSON.stringify(data)
		})
	})

}
/**
 * 定义一个表单校验器
 */
function bindValidate() {
	$("#sceneInfo").bootstrapValidator({
		message: '输入的值是非法的', //默认提示信息
		feedbackIcons: { //提示图标
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			sceneName: { // 与input等html元素的name值相同
				validators: {
					notEmpty: {
						message: '场景名称不能为空'
					}
				}
			},
			commCode: {
				validators: {
					notEmpty: {
						message: '请选择通信协议'
					}
				}
			},
			usageCode: {
				validators: {
					notEmpty: {
						message: '请选择使用场景'
					}
				}
			},
		}
	})
}
/**
 * 添加场景的事件
 * @param {Object} data 添加场景的对象
 */
function addScene(data) {
	postHttp(data).then(res => {
		promptModel({
			type: "modal-header-success",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>添加场景页面',
			body: '添加场景成功'
		})
		setTimeout(function() {
			window.opener=null;
			window.close();
		}, 3000)
	}).catch(res => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>添加场景页面',
			body: '添加场景失败'
		})
	})
}


// 使用场景
let usage = null;
// 通信协议
let communicate = null;
$(function() {
	// 路径导航初始化
	trajectoryUnCode(location.pathname, location.search)

	getHttp({
		url: "/communicate"
	}).then(res => {
		communicate = res
		getHttp({
			url: "/usage"
		}).then(res => {
			usage = res;
			// 信息使用和事件添加
			initPage()
		})
	}).catch(_ => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>添加场景页面',
			body: '页面初始化失败'
		})
	})

})
