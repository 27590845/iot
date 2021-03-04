function getSceneList() {
	let data = {
		page: location.search.match(/page=(.*)/).split("&")[0],
		limit: 10,
	}
	
	http({
		url: "/scene/list",
		type: "GET",
		contentType: "application/x-www-form-urlencoded",
		data : data
	}).then((res)=>{
		
	}).catch((res)=>{
		console.error("获取场景列表失败")
	})
}

$(function(){
	// 侧边导航栏中样式的选择和点击效果【通用组件的功能，在base.js中进行定义，方便维护】
	let navinfo = {
		id: "page-nav",
		title: "页面导航",
		content: {
			'index.html': "数据中心",
			'scenelist.html': "场景列表"
		},
		type: 'scene',
		tag: "glyphicon glyphicon-tags"
	}
	broadside(navinfo, location)
	
	// 路径导航
	trajectoryUnCode(location.pathname, location.search)
	
	// 进行网络请求
	
})
