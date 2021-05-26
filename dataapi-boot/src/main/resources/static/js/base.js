/**
 * 功能：判断用户是否是登录状态
 */
function isLogin() {
	return true;
}
/**
 * 导航路径之来源编码
 * @param {String} pathname location.pathname字段的值
 * @param {String} search location.search字段的值
 * @return {String} path 拼接后的来源字符串
 */
function trajectoryEnCode(pathname, search, side = false) {
	let path = "";
	// 历史路径
	let historyObj = getSearchKey("route");
	let historyObjKey = historyObj ? historyObj.self : "trajectory"; // 获取key【统一维护】
	// 搜索信息
	let searchObj = getSearchKey("search");
	let searchObjKey = searchObj ? searchObj.self : "search";

	// 当前位置的粗略定位
	let present = pathname.match(/.*\/(.*)\.html/)[1]

	/* 侧边导航栏只需要返回index即可 */
	if (side) {
		return historyObjKey + "=index";
	}
	/* 不是侧边导航栏返回历史路径和当前位置 */
	// 解析历史路径
	search.split("&").forEach(function(item) {
		if (new RegExp(historyObjKey).test(item)) {
			path = historyObjKey + "=" + item.match(new RegExp(historyObjKey + "=(.*)"))[1];
			return true;
		} else if (new RegExp(searchObjKey).test(item)) { // 当前位置的精确定位
			present = searchObjKey;
		}
		return false;
	})
	path += "" === path ? historyObjKey + "=" + present : "S" + present;
	// 路径中的内容进行去重
	path = Array.from(new Set(path.split("S"))).join("S")
	return path;
}
/**
 * 导航路径之来源解码
 * @param {String} pathname location.pathname字段的值
 * @param {String} search location.search字段的值
 */
function trajectoryUnCode(pathname, search) {
	console.log("路径")
	// 获取该导航下的所有页面信息
	let classification = getClassification(pathname.match(/.*\/(.*)\/.*html/)[1]);
	// 当前位置的粗略定位
	let present = pathname.match(/.*\/(.*)\.html/)[1];
	// 历史路径获取 + 当前位置的精确定位
	let trajectory = []; // 存放路径值的数组
	search.split("&").forEach(function(item) {
		let historyObj = getSearchKey("route");
		let historyObjKey = historyObj ? historyObj.self : "trajectory"; // 获取key【统一维护】

		let searchObj = getSearchKey("search");
		let searchObjKey = searchObj ? searchObj.self : "search";
		if (new RegExp(historyObjKey).test(item)) { // 历史路径获取
			let path = item.match(new RegExp(historyObjKey + "=(.*)"))[1];
			trajectory = path.split("S");
		} else if (new RegExp(searchObjKey).test(item)) { // 当前位置的精确定位
			present = searchObjKey;
		}
	})
	// 如果历史路径中有当前位置，进行消除
	trajectory.indexOf(present) == -1 ? "" : trajectory.splice(trajectory.indexOf(present), 1)
	// 生成路径导航
	let innerHTML = "<li>当前位置：</li>"
	trajectory.forEach(function(item) {
		let search =
			innerHTML += "<li>" + "<a href='" + classification[item].url + (localStorage.getItem(item) ?
				localStorage.getItem(
					item) : "") + "'>" + classification[item].name + "</a>";
	})
	innerHTML += "<li class='active'>" + classification[present].name + "</li>"
	$("#nav-path").html(innerHTML);
}
/**
 * 侧边导航栏的设置 【使用前提是必须有相关的标签】
 * @param {Object} navinfo 导航栏的标题和内容
 * @param {Object} loca window.locations 
 */
function broadside(navinfo, loca) {
	// navinfo的格式：
	// let navinfo = {
	//  id: "", //html标签的id的属性值
	// 	title: "页面导航", // 侧边栏的题目
	// 	content: { // 侧边栏的内容
	// 		'index.html': "数据中心",
	// 		'scenelist.html': "场景列表"
	// 	},
	// 	type: 'scene' | 'node', //侧边栏的类型, 默认是scene
	//  tag: "glyphicon glyphicon-tags" // 相关标签的样式
	// }
	// 判断选中的内容
	let present = ""
	switch (navinfo.type) {
		case 'node':
			let nodeObj = new RegExp(getSearchKey("nodesn").self + "=(.*)&?")
			present = loca.search.match(nodeObj)[1]; // 当前的位置
			break;
		default:
			present = loca.pathname.match(/.*\/(.*)$/)[1]; // 当前的位置
	}
	// 侧边栏的构建
	let nav = $("#" + navinfo.id)
	let content = ""
	console.dir(navinfo.content)
	for (let item of navinfo.content) {
		content +=
			`<button type="button" class="list-group-item ${ present == item.url ? "disabled" : "" }" data-item="${item.url}"  data-type="${item.type}">${item.title}</button>`
	}
	nav.html(`
	<header class="panel-heading">
		<span class="${navinfo.tag}"></span>
			${navinfo.title}
	</header>
	<section class="panel-body list-group">
		${content}
	</section>`)

	let search = trajectoryEnCode(loca.pathname, loca.search)
	$(".list-group button").on("click", function() {
		if (this.classList.contains("disabled")) {
			return;
		}
		switch (navinfo.type) {
			// 节点
			case "node":
				let url = location.href.replace(new RegExp(getSearchKey("nodesn").self + "=.*&?"), getSearchKey(
					"nodesn").self + "=" + this.dataset.item)
				if (this.dataset.type == "_blank") {
					window.open(url, "_blank")
				} else {
					window.open(url, "_self")
				}
				break;
			case "scene":
				// 进行判断，如果是index.html，不添加参数
				window.open(this.dataset.item, this.dataset.type);
				break;
			default:
			

		}
	})
}
/**
 * 根据关键字确定key
 * @param {String} content 添加key的关键字
 * @return {Object} key-key1的对应对象或null
 */
function getSearchKey(content) {
	// location中?后面中的key值
	var search_key = {
		"route": { // 表示浏览过的路径key对应关系
			"self": "trajectory",
			"search": "trajectory"
		},
		"search": { // 表示搜索内容的key
			"self": "search",
			"search": "search"
		},
		"page": { // 表示搜索内容的key
			"self": "page",
			"search": "page"
		},
		"scenesn": {
			"self": "scenesn",
			"search": "scenesn"
		},
		"nodesn": {
			"self": "nodesn",
			"search": "nodesn"
		},
		"ntid": {
			"self": "ntid",
			"search": "ntid"
		}
	}
	return search_key.hasOwnProperty(content) ? search_key[content] : null;

}
/**
 * 根据导航标记获取导航下的所有页面路径
 * @param {Object} tag 导航标记
 * @return {Object} classification 每个导航下的所有页面
 */
function getClassification(tag) {
	// 记录网站分类及相关信息
	let website_path = {
		"index": {
			"index": {
				"name": "首页",
				"url": "/html/index/index.html"
			},
		},
		"data_center": {
			"index": {
				"name": "数据中心",
				"url": "/html/data_center/index.html"
			},
			"scenelist": {
				"name": "场景列表",
				"url": "/html/data_center/scenelist.html"
			},
			"search": {
				"name": "搜索结果",
				"url": "/html/data_center/scenelist.html"
			},
			"sceneadd": {
				"name": "添加场景",
				"url": "/html/data_center/sceneadd.html"
			},
			"sceneinfo": {
				"name": "场景信息",
				"url": "/html/data_center/sceneinfo.html"
			},
			"sceneupdate": {
				"name": "更新场景",
				"url": "/html/data_center/sceneupdate.html"
			},
			"nodeadd": {
				"name": "新增节点",
				"url": "/html/data_center/nodeadd.html"
			},
			"nodeinfo": {
				"name": "节点信息",
				"url": "/html/data_center/nodeinfo.html"
			},
			"nodeupdate": {
				"name": "更新节点",
				"url": "/html/data_center/nodeupdate.html"
			}
		},
		"rule_engine": {
			"index": {
				"name": "规则引擎",
				"url": "/html/rule_engine/index.html"
			},
			"add_alert": {
				"name": "添加报警设置",
				"url": "/html/rule_engine/add_alert.html"
			},
			"edit_alert": {
				"name": "编辑报警信息",
				"url": "/html/rule_engine/edit_alert.html"
			}
		}
	}
	return website_path[tag]
}
/**
 * 时间格式化
 * @param {String/Number} timstamp 表示时间戳（13位)
 * @param {String} format 时间格式  
 */
function dateTimeFormat(timestamp, format = "yyyy-MM-dd hh:mm:ss") {
	let date = new Date(timestamp - 0);
	let dateObj = {
		yyyy: date.getFullYear() + "",
		MM: ("0" + (date.getMonth() + 1)).slice(-2),
		dd: ("0" + date.getDate()).slice(-2),
		hh: ("0" + date.getHours()).slice(-2),
		mm: ("0" + date.getMinutes()).slice(-2),
		ss: ("0" + date.getSeconds()).slice(-2)
	}
	for (let item of Object.entries(dateObj)) {
		format = format.replace(item[0], item[1])
	}
	return format;
}
/**
 * 提示信息函数
 * @param {Object} data 提示信息  
 */
function promptModel(data) {
	// let data = {
	//  type: "",
	// 	title: "",
	// 	body: "",
	//  footer: ""
	// }
	$('#promptModal').find(".modal-content").html(
		`
	<div class="modal-header ${data.type}">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title">
			${data.title}
		</h4>
	</div>
	<div class="modal-body">
		${data.body}
	</div>
	${data.hasOwnProperty('footer') ? '<div class="modal-footer">' + data.footer + '</div>' : ''}
	`
	)

	$('#promptModal').modal('show')
}
/**
 * 定义全局变量
 */
var nav = null; // 导航内容

// 页面加载时的前提条件
window.onload = function() {
	// let route = null; // 导航目录【加载页面的时候就获得】
	if ($("footer").length != 0) {
		$("footer").html("版权所有©西安电子科技大学工业互联网应用服务平台")
	}

	// 第一步：判断用户是否是登录状态
	if (!isLogin()) {
		// window.open(route["login"].path, "_self", null, true)
		return;
	}

	nav = new Nav("nav", "program-nav", "a")

	window.onscroll = nav.scroll;
}
