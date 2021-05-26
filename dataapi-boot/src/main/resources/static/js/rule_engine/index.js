/**
 * @param {String} ntid
 * @param {Number} ntexec
 * @param {Node} node
 */
function switchTrigger(ntid, ntexec, node) {
	let data = {
		"ntExec": ntexec,
		"ntId": ntid
	}
	patchHttp({
		url: "/rule/" + ntid,
		data: JSON.stringify(data)
	}).then(res => {
		if (res.success) {
			node.dataset.ntexec = ntexec;
		} else {
			promptModel({
				type: "modal-header-danger",
				title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>更新失败提醒',
				body: '更新失败'
			})
		}
	}).catch(res => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>更新失败提醒',
			body: '更新失败'
		})
	})
}
/**
 * 删除整个触发器
 * @param {Object} ntid
 */
function deleteTrigger(ntid) {
	deleteHttp({
		url: "/rule/" + ntid
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
 * 场景中相关按钮的点击事件
 */
function triggerListBtn() {
	// 获取ntid
	let ntid = this.dataset.hasOwnProperty("ntid") ? this.dataset.ntid : null;
	// 获取ntname
	let ntname = this.dataset.hasOwnProperty("ntname") ? this.dataset.ntname : null;
	// 获取ntexec
	let ntexec = this.dataset.hasOwnProperty("ntexec") ? this.dataset.ntexec : null;

	// 将本页面的状态存入localstorage中【如果是搜索内容和场景列表2选1】
	let searchObj = getSearchKey("search");
	let searchObjKey = searchObj ? searchObj.self : "search";
	let itemKey = new RegExp(searchObjKey).test(location.search) ? searchObjKey : location.pathname.match(
		/.*\/(.*)\.html/)[1];
	localStorage.setItem(itemKey, location.search)

	// 获取历史路径
	let search = "?" + trajectoryEnCode(location.pathname, location.search)
	if (this.id === "add-trigger") { // 添加规则引擎按钮
		window.open("./add_alert.html" + search, "_blank")
		return;
	}
	// 携带ntid
	search += "&" + getSearchKey("ntid").self + "=" + ntid;
	if (/info-trigger/.test(this.className)) { // 规则引擎信息按钮
		window.open("./edit_alert.html" + search, "_blank")
		return;
	} else if (/delete-trigger/.test(this.className)) { // 规则引擎删除按钮
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>触发器删除提醒',
			body: `确定要删除"${ntname}"(${ntid})此触发器吗?`,
			footer: '<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button><button id="deleteCommit" type="button" class="btn btn-danger">删除</button>'
		})
		$("#deleteCommit").click(function() {
			var $btn = $(this).button('loading')
			$btn.button('reset')
			deleteTrigger(ntid)
		})
	} else if (/switch-trigger/.test(this.className)) { // 规则引擎开关按钮
		ntexec = ntexec == 0 ? "1" : "0";
		console.dir({ntexec})
		switchTrigger(ntid, ntexec, this)
	}
}
/**
 * 触发器列表展示
 * @param {Object} data
 */
function createTriggerList(data) {
	$("#trigger-list").html("");
	data.forEach(function(triggerItem) {
		// 是否过期进行判断
		let tag = triggerItem.ntExpr == null ? true : triggerItem.ntExpr > new Date().getTime() ? true : false;
		// 展示的HTML
		let html =
			`
		<div class="trigger-item" style="--color: ${tag ? 'green' : '#c0392b'}; --content: '${tag ? "有效" : "过期"}'">
		`
		// 头部内容
		html +=
			`
		<div class="trigger-header">
			<div class="row-info">
				<div class="trigger-name col-title" title="${triggerItem.ntName}">
					${triggerItem.ntName}
				</div>
		
				<div class="scene-sn col-title">
					到期时间:${triggerItem.ntExpr == null ? "永久" : dateTimeFormat(triggerItem.ntExpr)}
				</div>
			</div>
		
			<div class="row-btn">
				<button type="button" class="btn btn-primary info-trigger"
					data-ntid="${triggerItem.ntId}">详情
					<span class="glyphicon glyphicon-forward"></span>
				</button>
				<button type="button" class="btn btn-default delete-trigger"
					data-ntid="${triggerItem.ntId}" data-ntname="${triggerItem.ntName}">删除
					<span class="glyphicon glyphicon-trash"></span>
				</button>
			</div>
		</div>
		
		<div class="trigger-body">
		`

		// 主体内容
		// 触发条件
		let conditions = `
			<section class="trigger-conditions">暂无触发条件</section>
		`
		if (0 != triggerItem.nodeCondParams.length) {
			conditions = `<section class="trigger-conditions">`
			triggerItem.nodeCondParams.forEach(function(nodeCondParamItem) {
				conditions += `
				<div class="condition-item" title="${nodeCondParamItem.naName} ${ncOp.get(nodeCondParamItem.ncOp - 0)} ${nodeCondParamItem.ncVal}">
					<p>${nodeCondParamItem.sceneName}</p>
					<p>${nodeCondParamItem.nodeName} - ${nodeCondParamItem.naName}</p>
					<p class="condtion-content">
						${ncOp.get(nodeCondParamItem.ncOp - 0)} ${nodeCondParamItem.ncVal}
					</p>
				</div>`
			});
			conditions += "</section>"
		}
		html += conditions;
		// 开启状态
		html += `
			<section class="trigger-exec">
				<span></span>
				<p class="switch-trigger" data-ntid="${triggerItem.ntId}" data-ntexec="${triggerItem.ntExec}">
					<i></i>
				</p>
				<span></span>
			</section>
		`
		// 触发报警信息
		html += `
			<section class="trigger-alert">
				<div>
					<span>报警方式：</span>
					<p>${naaType.get(triggerItem.nodeActAlertParam.naaType - 0)} - ${triggerItem.nodeActAlertParam.naaVal}</p>
				</div>
				<div>
					<span>报警内容：</span>
					<p>${triggerItem.nodeActAlertParam.naaContent}</p>
				</div>
			</section>
		`
		// 闭合信息
		html +=
			`
			</div>
		</div>
		`
		$("#trigger-list").append(html)
	})

	// 规则操作
	// 查看
	$('.info-trigger').on('click', triggerListBtn)
	// 删除
	$('.delete-trigger').on('click', triggerListBtn)
	// 删除
	$('.switch-trigger').on('click', triggerListBtn)
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
 * /rule_engine/index.html的入口方法
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
		url: "/rule/list?" + data,
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
		createTriggerList(res.data);
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
			body: '获取规则列表失败'
		})
	})

}

let naaType = new Map();
naaType.set(1, "短信");
naaType.set(2, "站内信");
naaType.set(3, "邮件");
let ncOp = new Map();
ncOp.set(1, "大于");
ncOp.set(2, "大于等于");
ncOp.set(3, "小于");
ncOp.set(4, "小于等于");
ncOp.set(5, "等于");
ncOp.set(6, "新值");
ncOp.set(7, "冻结");
ncOp.set(8, "复活");


$(function() {
	// 入口函数
	entrance();

	// 路径导航
	trajectoryUnCode(location.pathname, location.search)

	// 添加场景按钮点击事件
	$('#add-trigger').on('click', triggerListBtn)
})
