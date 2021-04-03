/**
 * 绑定点击复制事件
 * @param {String} name 选择器，用来选择对应的元素即可
 */
function copySn(name) {
	$(name).click(function() {
		let successElement = document.createElement("div"); // 提示框
		successElement.classList.add("alert") // 添加bootstrap提示框的class类
		// 支持点击复制 document.execCommand运行相关功能的命令函数
		if (!document.execCommand("copy")) {
			successElement.classList.add("alert-danger")
			successElement.innerText = "自动复制失败，请手动复制"
			$(name).onclick = null;
		} else {
			// 不支持点击复制
			successElement.classList.add("alert-success")
			successElement.innerText = "复制成功"
		}
		// 显示一定的时间就消失
		document.getElementsByTagName("body")[0].appendChild(successElement)
		setTimeout(function() {
			document.getElementsByTagName("body")[0].removeChild(successElement)
		}, 3000)
	})
}
/**
 * 根据sceneSn删除场景
 * @param {Object} data sceneSn号
 */
function deleteScene(data) {
	deleteHttp({
		url: "/scene/" + data,
		contentType: "x-www-form-urlencoded"
	}).then((res) => {
		location.reload(false)
	}).catch(res => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>场景删除提醒',
			body: '删除失败'
		})
	})
}
/**
 * 更新节点属性id删除某个场景下某个节点的某个属性
 * @param {String} url 删除的URL地址
 */
function deleteNodeAttr(url) {
	deleteHttp({
		url: url
	}).then(res => {
		location.reload(false)
	}).catch(_ => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点删除',
			body: `节点删除失败`
		})
	})
}
/**
 * 场景中相关按钮的点击事件
 */
function sceneListBtn() {
	// 获取scenesn
	let scenesn = this.dataset.hasOwnProperty("scenesn") ? this.dataset.scenesn : null;
	// 获取nodesn
	let nodesn = this.dataset.hasOwnProperty("nodesn") ? this.dataset.nodesn : null;
	// 获取scenename
	let scenename = this.dataset.hasOwnProperty("scenename") ? this.dataset.scenename : null;
	// 获取nodename
	let nodename = this.dataset.hasOwnProperty("nodename") ? this.dataset.nodename : null;

	// 将本页面的状态存入localstorage中【如果是搜索内容和场景列表2选1】
	let searchObj = getSearchKey("search");
	let searchObjKey = searchObj ? searchObj.self : "search";
	let itemKey = new RegExp(searchObjKey).test(location.search) ? searchObjKey : location.pathname.match(
		/.*\/(.*)\.html/)[1];
	localStorage.setItem(itemKey, location.search)

	// 获取历史路径
	let search = "?" + trajectoryEnCode(location.pathname, location.search)
	if (this.id === "add-scene") { // 添加的按钮
		window.open("./sceneadd.html" + search, "_blank")
		return;
	}
	// 携带scenesn
	search += "&" + getSearchKey("scenesn").self + "=" + scenesn;
	if (/add-node/.test(this.className)) { // 添加节点按钮
		window.open("./nodeadd.html" + search, "_blank")
	} else if (/view-scene/.test(this.className)) { // 可视化按钮
		window.open("/master/grafana/" + scenesn, "_blank")
		return;
	} else if (/info-scene/.test(this.className)) { // 场景信息按钮
		window.open("./sceneinfo.html" + search, "_blank")
		return;
	} else if (/update-scene/.test(this.className)) { // 场景编辑按钮
		window.open("./sceneupdate.html" + search, "_blank")
		return;
	} else if (/delete-scene/.test(this.className)) { // 场景删除按钮
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>场景删除提醒',
			body: `确定要删除"${scenename}"(${scenesn})此场景吗?`,
			footer: '<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button><button id="deleteCommit" type="button" class="btn btn-danger">删除</button>'
		})
		$("#deleteCommit").click(function() {
			var $btn = $(this).button('loading')
			$btn.button('reset')
			deleteScene(scenesn)
		})
		return;
	}
	// 携带nodesn
	search += "&" + getSearchKey("nodesn").self + "=" + nodesn
	if (/info-node/.test(this.className)) { // 节点信息按钮
		window.open("./nodeinfo.html" + search, "_blank")
		return;
	} else if (/update-node/.test(this.className)) { // 节点更新按钮
		window.open("./nodeupdate.html" + search, "_blank")
		return;
	} else if (/delete-node/.test(this.className)) { // 节点删除按钮
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点删除提醒',
			body: `确定要删除"${nodename}"(${scenesn} / ${nodesn})此节点吗?`,
			footer: '<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button><button id="deleteNodeAttrCommit" type="button" class="btn btn-danger">删除</button>'
		})
		$("#deleteNodeAttrCommit").click(function() {
			let url = "/node/" + scenesn + "/" + nodesn;
			deleteNodeAttr(url)
		})
		return;
	}
}
/**
 * 生成场景列表【从后台中获取到相关的scene列表之后，用html进行展示】
 * @param {Array} data 展示的数据列表 
 */
function createSceneList(data) {
	$("#scene-list").html("");
	data.forEach(function(sceneItem) {
		// 展示的HTML
		let html =
			`
		<div class="scene-item" style="--color: green; --content: '在线' ">
		`
		// 头部内容
		html +=
			`
		<div class="scene-header">
			<div class="row-info">
				<div class="scene-name col-title" title="${sceneItem.sceneName}">
					${sceneItem.sceneName}
				</div>
					
				<div class="scene-sn col-title" title="点击复制">
					${sceneItem.sceneSn}
				</div>
			</div>
			
			<div class="row-btn">
				<button type="button" class="btn btn-info view-scene" data-scenesn="${sceneItem.sceneSn}">可视化
					<span class="glyphicon glyphicon-eye-open"></span>
				</button>
				<button type="button" class="btn btn-success add-node" data-scenesn="${sceneItem.sceneSn}">添加节点
					<span class="glyphicon glyphicon-plus-sign"></span>
				</button>
				<button type="button" class="btn btn-primary info-scene" data-scenesn="${sceneItem.sceneSn}">详情
					<span class="glyphicon glyphicon-forward"></span>
				</button>
				<button type="button" class="btn btn-warning update-scene" data-scenesn="${sceneItem.sceneSn}">编辑
					<span class="glyphicon glyphicon-edit"></span>
				</button>
				<button type="button" class="btn btn-default delete-scene" data-scenesn="${sceneItem.sceneSn}" data-scenename=${sceneItem.sceneName}>删除
					<span class="glyphicon glyphicon-trash"></span>
				</button>
			</div>
		</div>
		
		<div class="scene-body">
		`

		// 主体内容
		let table = "<div>暂无节点信息</div>"
		if (0 != sceneItem.nodeVos.length) {
			table =
				`
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>节点标识</th>
						<th>节点映射</th>
						<th>节点名称</th>
						<th>节点描述</th>
						<th>节点中传感器个数</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
			`
			sceneItem.nodeVos.forEach(function(nodeItem) {
				table +=
					`
							<tr>
								<td>${nodeItem.nodeSn}</td>
								<td>${nodeItem.nodeMap || "暂无"}</td>
								<td>${nodeItem.nodeName}</td>
								<td>${nodeItem.nodeDesc || "暂无简介"}</td>
								<td>${nodeItem.nodeAttrList.length}</td>
								<td>
									<button type="button" class="btn btn-xs btn-primary info-node" data-scenesn="${sceneItem.sceneSn}" data-nodesn="${nodeItem.nodeSn}" data-nodename="${nodeItem.nodeName}">查看</button>
									<button type="button" class="btn btn-xs btn-warning update-node" data-scenesn="${sceneItem.sceneSn}" data-nodesn="${nodeItem.nodeSn}" data-nodename="${nodeItem.nodeName}">编辑</button>
									<button type="button" class="btn btn-xs btn-danger delete-node" data-scenesn="${sceneItem.sceneSn}" data-nodesn="${nodeItem.nodeSn}" data-nodename="${nodeItem.nodeName}">删除</button>
								</td>
							</tr>
			`
			})
			table += `
					</tbody>
				</table>
				`
		}
		html += table;

		// 闭合信息
		html +=
			`
			</div>
		</div>
		`
		$("#scene-list").append(html)
	})

	// 场景中的操作
	//可视化
	$('.view-scene').on('click', sceneListBtn)
	// 添加节点
	$('.add-node').on('click', sceneListBtn)
	// 查看
	$('.info-scene').on('click', sceneListBtn)
	// 更新
	$('.update-scene').on('click', sceneListBtn)
	// 删除
	$('.delete-scene').on('click', sceneListBtn)

	// 节点中的操作
	// 更新
	$('.update-node').on('click', sceneListBtn)
	// 查看
	$('.info-node').on('click', sceneListBtn)
	// 删除
	$('.delete-node').on('click', sceneListBtn)

	// 调用点击事件
	copySn(".scene-sn")
}
/**
 * 控制分页按钮的特殊情况
 * @param {String} element 分页按钮的选择器
 */
function pageBtnSetting(element) {
	let lis = $(element + " li");
	let pages = $(element).bootstrapPaginator("getPages");
	if (pages['prev'] === pages['current']) {
		for (let i = 0; i < 2; i++) {
			lis[i].classList.remove("active")
			lis[i].classList.add("disabled")
		}
	}
	if (pages['next'] === pages['current']) {
		for (let i = 1; i < 3; i++) {
			lis[lis.length - i].classList.remove("active")
			lis[lis.length - i].classList.add("disabled")
		}
	}

}
/**
 * scenelist.html的入口方法
 */
function entrance() {
	var element = "#pageButton"; // 分页按钮的ul的id值
	var url = window.location.origin + window.location.pathname; // 基本地址
	// 判断地址中是否有page字段，有进行获取当前的page字段的值
	var search = window.location.search;
	// 检查page
	var currentPage = 1;
	let pageObj = getSearchKey("page");
	let pageObjKey = pageObj ? pageObj.self : "page";
	// 检查search
	let searchContent = null;
	let searchObj = getSearchKey("search");
	let searchObjKey = searchObj ? searchObj.self : "search";
	search.split("&").forEach(function(item) {
		if (new RegExp(pageObjKey).test(item)) {
			currentPage = item.match(new RegExp(pageObjKey + "=(\\d+)"))[1];
		} else if (new RegExp(searchObjKey).test(item) && item.match(new RegExp(searchObjKey + "=(.*)"))) {
			// 注意search=搜索内容&trajectory = ..|search这种情况的发生
			searchContent = decodeURIComponent(item.match(new RegExp(searchObjKey + "=(.*)"))[1])
			$("input[name='search']").val(searchContent)
		}
	})

	/**
	 * 进行ajax的数据请求【使用promise与后台进行交互】判断是否搜索或查看所有
	 */
	// 发送的参数
	let data = "page=" + currentPage + "&limit=10"
	let httpInfo = {
		url: "/scene/list?" + data,
		contentType: "application/x-www-form-urlencoded",
	}
	// 获取数据
	if (searchContent != null) {
		data += "&sceneSn=" + searchContent;
		httpInfo = {
			url: "/scene/search?" + data,
			contentType: "application/json",
		}
	}
	// 进行http请求
	getHttp(httpInfo).then((res) => {
		createSceneList(res.data);
		var options = {
			bootstrapMajorVersion: 3, // bootstrap的版本号
			alignment: 'center', // 控件对齐方式
			currentPage: res.page, // 当前页数
			numberOfPages: 5, // 显示按钮的数量
			totalPages: res.pageCount, // 总页数
			itemTexts: function(type, page, current) { // 分页按钮的样式
				switch (type) {
					case "first":
						return "首页";
					case "prev":
						return "上一页";
					case "next":
						return "下一页";
					case "last":
						return "末页";
					case "page":
						return page;
				}
			},
			shouldShowPage: true,
			// 返回的url
			pageUrl: function(type, page, current) {
				let searchStr = search.replace(new RegExp("&?\\??" + pageObjKey + "=\\d+"), "")
				if (/^\?/.test(searchStr)) { // 是否有多个参数【有多个参数就进行替换，只有一个参数进行重定义】
					return url + searchStr + "&" + pageObjKey + "=" + page
				}
				return url + "?" + pageObjKey + "=" + page
			}
			// 点击事件
			// onPageClicked: function(event1, event2, type, page) { }
		};
		$(element).bootstrapPaginator(options);
		pageBtnSetting(element);
	}).catch((res) => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>异常提醒',
			body: '获取场景列表失败'
		})
	})

}

$(function() {
	// 页面入口
	entrance()

	// 路径导航
	trajectoryUnCode(location.pathname, location.search)

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

	// 搜索按钮点击事件
	$("#scene-search-btn").on("click", function(element) {
		let search_value = $("input[name='search']").val().trim();
		/* 搜索内容为空 */
		if (0 === search_value.length) {
			alert("搜索内容不能为空")
			return;
		}

		// 检查search
		let searchObj = getSearchKey("search");
		let searchObjKey = searchObj ? searchObj.self : "search";

		if (!new RegExp(searchObjKey).test(location.search)) {
			localStorage.setItem(location.pathname.match(/.*\/(.*)\.html/)[1], location.search);
		}
		let search = "?search=" + search_value + "&";
		search += trajectoryEnCode(location.pathname, location.search);
		window.open("./scenelist.html" + search, "_self");
	})

	// 添加场景按钮点击事件
	$('#add-scene').on('click', sceneListBtn)


})
