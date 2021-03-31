/**
 * 信息获取并进行填充
 */
function getInfoAndShow() {
	let sceneInfo = {
		sceneSn: "暂无信息",
		sceneName: "暂无信息",
		sceneMap: "暂无使用",
		sceneDesc: "暂无简介",
		sceneLoc: "暂无信息",
		sceneLng: "暂无信息",
		sceneLat: "暂无信息",
		sceneEl: "暂无信息",
	};
	
	getHttp({
		url: "/scene/" + sceneSn
	}).then(res=>{
		for (let item of Object.entries(sceneInfo)) {
			$("[name=" + item[0] + "Info]").html(res[item[0]] || item[1])
		}
		// 使用协议和使用场景在sceneSn中进行获取
		// <!-- sceneSn第十二位通信协议 -->
		// <!-- sceneSn第十一位使用场景 -->
		
		$("[name=createTimeInfo]").html(dateTimeFormat(res.createTime))
		$("[name=protocolInfo]").html(communicate[sceneSn.split("")[11]] || "获取中...")
		$("[name=useSceneInfo]").html(usage[sceneSn.split("")[10]] || "获取中...")
		
	})
}
/**
 * 解析url中的search中的内容，点击相关按钮的时候进行参数传递
 */
function infoBtn() {
	var $btn = $(this).button("loading")
	$btn.button("reset")
	
	// 保存当前页的信息
	localStorage.setItem(location.pathname.match(/.*\/(.*)\.html/)[1], location.search)
	
	// 添加scenesn
	let scenesnObj = getSearchKey("scenesn");
	let scenesnObjKey = scenesnObj.self;
	let search = "?" + location.search.match(new RegExp(scenesnObjKey + "=(.*)&?"))[0]
	if ("sceneUpdateBtn" === this.id) {
		// 历史路径的获取
		search += "&" + trajectoryEnCode(location.pathname, location.search)
		window.open("./sceneupdate.html" + search, "_blank")
		return;
	}
}
// 使用场景
let usage = null;
// 通信协议
let communicate = null;
// sceneSn的获取
let sceneObj = new RegExp(getSearchKey("scenesn").self + "=(.*)");
let sceneSn = location.search.match(sceneObj)[1].split("&")[0]
$(function() {
	//路径导航
	trajectoryUnCode(location.pathname, location.search)
	// 获取基本信息【使用场景和通信协议】
	getHttp({
		url: "/communicate"
	}).then(res=>{
		communicate = res
		getHttp({
			url: "/usage"
		}).then(res=>{
			usage = res;
			// 信息填充
			getInfoAndShow()
		})
	}).catch(_=>{
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>场景信息',
			body: '场景信息获取失败'
		})
	})
	// 编辑信息按钮的点击事件
	$("#sceneUpdateBtn").on("click", infoBtn)
	
	
})
