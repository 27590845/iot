/**
 * 节点信息的获取并展示
 */
function showNodeInfo() {
	// 展示内容
	let nodeInfoShow = {
		"sceneSn": "",
		"nodeName": "",
		"nodeSn": "",
		"nodeMap": "暂无节点映射",
		"nodeDesc": "暂无节点描述信息"
	}
	getHttp({
		url: "/scene/" + sceneSn
	}).then(res => {
		// 获取指定的node
		res.nodeVos.some(function(item) {
			if (nodeSn === item.nodeSn) {
				nodeInfo = item;
				return true;
			}
			return false;
		})
		// 基本信息展示
		for (let item of Object.entries(nodeInfoShow)) {
			$("[name=" + item[0] + "Info]").html(nodeInfo[item[0]] || item[1])
		}

		nodeAttrInfo = nodeInfo.nodeAttrList;
		// 属性列表展示
		createNodeAttrList();
		// 侧边导航栏中样式的选择和点击效果【通用组件的功能，在base.js中进行定义，方便维护】
		let navinfo = {
			id: "page-nav",
			title: "节点列表",
			content: [],
			type: 'node',
			tag: "glyphicon glyphicon-menu-hamburger"
		}
		res.nodeVos.forEach(function(item) {
			navinfo.content.push({
				url: item.nodeSn,
				title: item.nodeName,
				type: "_self"
			})
		})
		broadside(navinfo, location)

		// 获取历史数据
		getHistValue()
	}).catch(res => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点信息',
			body: '节点信息获取失败'
		})
	})
}
/**
 * 生成节点属性列表【从后台中获取到相关的nodesn的所有属性的时候，用html进行展示】
 */
function createNodeAttrList() {
	if (nodeAttrInfo.length <= 0) {
		$("#node-attr-list").hide()
		$("#node-attr-info").hide()
		$("#node-attr-list-prompt").show()
		return;
	}

	$("#node-attr-list").html("")
	$("#node-attr-list").show()
	$("#node-attr-info").show()
	$("#node-attr-list-prompt").hide()
	let table = `
		<div class="node-attr-list-body">
		</div>`
	if (nodeAttrInfo.length != 0) {
		table =
			`
			<div class="node-attr-list-body">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>属性名称</th>
							<th>属性标识</th>
							<th>属性映射</th>
							<th>属性符号</th>
							<th>属性单位</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>`
		nodeAttrInfo.forEach(function(item, index) {
			if (0 === index) {
				showNodeAttrInfo({
					naId: item.naId,
					naName: item.naName,
					naKey: item.naKey
				})
			}
			table +=
				`
					<tr>
						<td>${item.naName}</td>
						<td>${item.naKey}</td>
						<td>${item.naMap || "暂无"}</td>
						<td>${item.naSym}</td>
						<td>${item.naUnit}</td>
						<td>
							<button type="button" class="btn btn-xs btn-primary info-node-attr" data-naid="${item.naId}" data-naname="${item.naName}" data-nakey="${item.naKey}">查看</button>
							<button type="button" class="btn btn-xs btn-warning update-node-attr"  data-naid="${item.naId}" data-naname="${item.naName}" data-nakey="${item.naKey}">编辑</button>
							<button type="button" class="btn btn-xs btn-danger delete-node-attr"  data-naid="${item.naId}" data-naname="${item.naName}" data-nakey="${item.naKey}">删除</button>
						</td>
					</tr>
				`
		})
		table += `
					</tbody>
				</table>
			</div>
		</div>`
	}

	$("#node-attr-list").append(table)

	// 节点属性的操作
	// 更新
	$('.update-node-attr').on('click', nodeBtn)
	// 查看
	$('.info-node-attr').on('click', nodeBtn)
	// 删除
	$('.delete-node-attr').on('click', nodeBtn)

}
/**
 * 查看节点属性的详细信息【点击事件】
 * @param {Object} data 节点的属性信息 
 */
function showNodeAttrInfo(data) {
	$("[name='selectednodename']").html(data.naName)
	$("[name='selectednodesn']").html(data.naKey)
	// 绘制折线图
	if (nodeAttrEcharts) { // 只需要更新对应展示的传感器即可
		nodeAttrEcharts.changeSensor({
			name: data.naName,
			key: data.naKey
		})
		return;
	}
	let option = {
		tooltip: {
			show: true,
			trigger: "item", // 触发器
			confine: true, // tooltip限制在图表区域内
			renderMode: "html", // 渲染模式
			formatter: function(params) {
				console.dir(params)
				return `<span>时间：${params[0].name} <br> 数值：${params[0].value}</span>`
			}
		},
		legend: {
			show: false
		},
		dataZoom: [{ // 鼠标滚轮缩放
			type: "inside",
			xAxisIndex: [0],
			yAxisIndex: [0],
			filterMode: "none"
		}],
		xAxis: {
			type: 'category',
			data: [],
			name: "时间",
			axisLabel: {
				show: true,
				margin: 15
			},
			axisLine: { // 坐标轴线的设置
				show: true,
				symbol: ["none", "arrow"],
				symbolOffset: [0, 10]
			},
			axisTick: { // 坐标轴刻度
				alignWithLabel: true
			},
			axisPointer: { // 坐标指示器，添加上到，移动鼠标出现提示框tooltip
				show: true,
				label: {
					show: false
				}
			}
		},
		yAxis: {
			type: 'value',
			name: "数值",
			scale: true, // 设置成 true 后坐标刻度不会强制包含零刻度【只有type='value'的时候有效】
			axisLine: { // 坐标轴线的设置
				show: true,
				symbol: ["none", "arrow"],
				symbolOffset: [0, 10]
			},
		},
		series: [{
			name: data.naName,
			type: 'line',
			symbol: 'none',
			connectNulls: true, // 连接空数据
			data: []
		}]
	};
	nodeAttrEcharts = new Echarts("line-chart", option, data.naKey);
}
/**
 * 删除节点
 */
function deleteNode(info) {
	deleteHttp({
		url: "/node/" + sceneSn + "/" + nodeSn
	}).then((res) => {
		promptModel({
			type: "modal-header-success",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>属性删除',
			body: '删除节点属性成功'
		})
		setTimeout(function() {
			window.opener = null;
			window.close();
		}, 3000)
	}).catch(_ => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>异常提醒',
			body: '删除该节点属性失败'
		})
	})
}
/**
 * 删除节点属性
 * @param {Object} info 节点信息
 */
function deleteNodeAttr(info) {
	deleteHttp({
		url: "/nodeAttr/" + sceneSn + "/" + nodeSn + "/" + info.naKey
	}).then((res) => {
		location.reload(true)
	}).catch(_ => {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>异常提醒',
			body: '删除该节点属性失败'
		})
	})
}
/**
 * 节点属性列表相关按钮的点击事件
 */
function nodeBtn() {
	var $btn = $(this).button('loading')
	$btn.button('reset')

	// 将本页面的状态存入localstorage中【如果是搜索内容和场景列表2选1】
	let searchObj = getSearchKey("search");
	let searchObjKey = searchObj ? searchObj.self : "search";
	let itemKey = new RegExp(searchObjKey).test(location.search) ? searchObjKey : location.pathname.match(
		/.*\/(.*)\.html/)[
		1];
	localStorage.setItem(itemKey, location.search)

	// 获取历史路径
	let search = "?" + trajectoryEnCode(location.pathname, location.search)
	search += "&" + getSearchKey("scenesn").self + "=" + sceneSn;

	// 节点操作
	if ("add-node" === this.id) { // 节点属性信息按钮
		window.open("./nodeadd.html" + search, "_blank")
		return;
	} else if ("update-node" === this.id) {
		search += "&" + getSearchKey("nodesn").self + "=" + nodeSn;
		window.open("./nodeupdate.html" + search, "_blank")
		return;
	} else if ("delete-node" === this.id) {
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点删除提醒',
			body: `确定要删除该节点吗?`,
			footer: '<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button><button id="deleteCommit" type="button" class="btn btn-danger">删除</button>'
		})
		$("#deleteCommit").click(function() {
			var $btn = $(this).button('loading')
			$btn.button('reset')
			deleteNode()
		})
		return;
	}
	let info = {
		naId: this.dataset.naid,
		naName: this.dataset.naname,
		naKey: this.dataset.nakey
	}
	// 属性列表中的按钮
	if (/info-node-attr/.test(this.className)) { // 节点属性信息按钮
		showNodeAttrInfo(info)
		return;
	} else if (/update-node-attr/.test(this.className)) { // 节点属性更新按钮
		// 进行页面的跳转
		window.open("./nodeupdate.html" + search, "_blank")
		return;
	} else if (/delete-node-attr/.test(this.className)) { // 节点属性删除按钮
		promptModel({
			type: "modal-header-danger",
			title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>节点属性删除提醒',
			body: `确定要删除该节点属性吗?`,
			footer: '<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button><button id="deleteCommit" type="button" class="btn btn-danger">删除</button>'
		})
		$("#deleteCommit").click(function() {
			var $btn = $(this).button('loading')
			$btn.button('reset')
			deleteNodeAttr(info)
		})
		return;
	}
}
/**
 * 为节点属性中，时间下拉选择框的构建和点击事件的绑定
 */
function selectEvent() {
	let select = document.getElementById("node-time-select");
	let mintues = [5, 15, 60, 24*60, 3*24*60, 30*24*60, 3*30*24*60];
	mintues.forEach(function(item) {
		let option = document.createElement("option");
		let innerHtml = "";
		if (item < 60) {
			innerHtml = item + "分钟"
		} else if (item < 24*60) {
			innerHtml = (item/60) + "小时"
		} else if (item < 30*24*60) {
			innerHtml = (item/24/60) + "天"
		} else if (item < 12*30*24*60) {
			innerHtml = (item/30/24/60) + "月"
		} else {
			innerHtml = (item/12/30/24/60) + "年";
		}
		option.innerHTML = innerHtml;
		option.setAttribute("value", item);
		select.appendChild(option);
	})
	select.addEventListener("change", function() {
		console.dir(select)
		getHistValue(Date.now() - select.value)
	})
}
/**
 * 更新节点属性的echarts
 */
function updataNodeAttrValue() {

}
/**
 * 通过API接口获取到历史数据
 * @param {number} 开始时间的时间戳
 */
function getHistValue(startTime) {
	let url = `/scene/${sceneSn}/node/${nodeSn}`;
	if (typeof startTime === "undefined" || startTime === null) {
		return;
	}
	startTime = dateTimeFormat(startTime);
	let endTime = dateTimeFormat(Date.now());
	getHttp({
		url: `/scene/${sceneSn}/node/${nodeSn}?st=${startTime}&et=${endTime}`
	}).then(res => {
		let time = [];
		let sensors = {};
		nodeAttrInfo.forEach(item => {
			sensors[item.naKey] = []
		})
		console.dir(nodeAttrEcharts)
		// 获取传感器数据，让echarts直接使用
		// for (let index = res.length - 1; index >= 0; index--) { // 返回的数据是倒叙
		for (let index = 0; index < res.length; index++) { // 返回的数据是倒叙
			let item = res[index]
			time.push(dateTimeFormat(item.at));
			for (let [index, value] of Object.entries(item.data)) {
				sensors[index].push("0");
			}
			for (let [index, value] of Object.entries(item.data)) {
				sensors[index][sensors[index].length - 1] = value;
			}
		}
		if (nodeAttrEcharts) {
			console.dir(nodeAttrEcharts)
			nodeAttrEcharts.historyData(time, sensors);
		}

	}).catch(res => {

	})
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
// nodeAttrInfo的声明
let nodeAttrInfo = [];
// 绘制的折线图
let nodeAttrEcharts = null;
$(function() {
	// 路径导航初始化
	trajectoryUnCode(location.pathname, location.search)

	// 获取数据进行展示
	showNodeInfo()
	
	// 下拉菜单生成
	selectEvent()
	// 解决echarts动态变换
	window.onresize = function() {
		if (nodeAttrEcharts != null) {
			nodeAttrEcharts.container.resize()
		}
	}
	
	$("#add-node").on('click', nodeBtn)
	$("#update-node").on('click', nodeBtn)
	$("#delete-node").on('click', nodeBtn)
})
