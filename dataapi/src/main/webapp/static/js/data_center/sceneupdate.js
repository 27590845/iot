/**
 * 填充表单
 */
function fillForm() {
	let sceneInfo = {
		sceneSn: "暂无信息",
		sceneName: "暂无信息",
		sceneMap: "",
		sceneDesc: "",
		sceneLoc: "暂无信息",
		sceneLng: "暂无信息",
		sceneLat: "暂无信息",
		sceneEl: "暂无信息",
		createTime: "暂无信息"
	};

	getHttp({
		url: "/scene/" + sceneSn
	}).then(res => {
		// 基本信息
		$("#sceneSn").val(res.sceneSn || sceneInfo.sceneSn)
		$("#sceneName").val(res.sceneName || sceneInfo.sceneName)
		$("#sceneMap").val(res.sceneMap || sceneInfo.sceneMap)
		$("#sceneDesc").html(res.sceneDesc || sceneInfo.sceneDesc)
		$("#createTime").val(dateTimeFormat(res.createTime || sceneInfo.createTime))
		// 使用协议和使用场景在sceneSn中进行获取
		// <!-- sceneSn第十二位通信协议 -->
		// <!-- sceneSn第十一位使用场景 -->
		let commCode = "<option></option>"
		for (let item of Object.entries(communicate)) {
			commCode += `<option value='${item[0]}' ${item[0] == sceneSn.split("")[11] ? "selected" : ""}>${item[1]}</option>`
		}
		$("#commCode").html(commCode)
		let usageCode = "<option></option>"
		for (let item of Object.entries(usage)) {
			usageCode += `<option value='${item[0]}' ${item[0] == sceneSn.split("")[10] ? "selected" : ""}>${item[1]}</option>`
		}
		$("#usageCode").html(usageCode)
		// 位置信息


		// 表单验证器
		bindValidate()


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
			sceneSn: { // 与input等html元素的name值相同
				validators: {
					notEmpty: {
						message: '场景名称不能为空'
					},
					onblurevent: function(...param) {
						console.dir(param)
					}
				}
			},
			protocol: {
				validators: {
					notEmpty: {
						message: '请选择通信协议'
					}
				}
			},
			useScene: {
				validators: {
					notEmpty: {
						message: '请选择使用场景'
					}
				}
			},
		},
		submitHandler: function(validator, form, submitButton) {
			alert("submit");
		}
	})
	// 添加失去焦点事件
	$(".form-control").on("blur", function() {
		$("#sceneInfo").data('bootstrapValidator').validateField(this.name)
	})
	
	// sceneUpdateBtn更新事件
	$("#sceneUpdateBtn").on("click", function() {
		$("#sceneInfo").data('bootstrapValidator').validate();
		$(this).button("reset")
		if (!$("#sceneInfo").data('bootstrapValidator').isValid()) {
			console.log("校验失败")
			return;
		}
		console.log("校验成功")
		// 进行ajax请求，提交表单内容
		let formInfo = decodeURIComponent($("#sceneInfo").serialize(), true) // 获取表单内容并进行解码
		let data = {}
		formInfo.split("&").forEach(function(item) {
			data[item.split("=")[0]] = item.split("=")[1]
		})
		putHttp({
			url: "/scene/" + sceneSn,
			data: JSON.stringify(data)
		}).then(res => {
			promptModel({
				type: "modal-header-success",
				title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>更新场景',
				body: '更新场景成功'
			})
		}).catch(res => {
			promptModel({
				type: "modal-header-danger",
				title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>更新场景',
				body: '更新场景失败'
			})
		})
	})
}

// 使用场景
let usage = null;
// 通信协议
let communicate = null;
// 场景网关号
let sceneObj = new RegExp(getSearchKey("scenesn").self + "=(.*)");
let sceneSn = location.search.match(sceneObj)[1].split("&")[0]
$(function() {
	// 路径导航初始化
	trajectoryUnCode(location.pathname, location.search)

	// 获取基本信息【使用场景和通信协议】
	getHttp({
		url: "/communicate"
	}).then(res => {
		communicate = res
		getHttp({
			url: "/usage"
		}).then(res => {
			usage = res;
			// 信息填充
			fillForm()
		})
	}).catch(res => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>更新场景',
			body: '获取信息失败'
		})
	})
	
})
