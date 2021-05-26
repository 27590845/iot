/**
 * 设备状态
 */
function deviceStatus() {
	$.ajax({
		url: "http://" + location.host + "/json/overview/left_senond.json",
		dataType: "json",
		type: "get",
		success: function(res) {
			var deviceStatusChart = echarts.init(document.getElementById('device_status'));
			var deviceStatusOption = {
				tooltip: {
					trigger: 'item'
				},
				legend: {
					orient: 'vertical',
					top: "20",
					right: '20',
					textStyle: {
						color: "white"
					}
				},
				series: [{
					name: '设备在线状态',
					type: 'pie',
					radius: '50%',
					data: res.data,
					emphasis: {
						itemStyle: {
							shadowBlur: 10,
							shadowOffsetX: 0,
							shadowColor: 'rgba(0, 0, 0, 0.5)'
						}
					}
				}],
				textStyle: {
					fontSize: 16
				}
			};

			deviceStatusChart.setOption(deviceStatusOption);

		},
		error: function(res) {

		}
	})
}
/**
 * 设备上传量
 */
function deviceUpload() {
	$.ajax({
		url: "http://" + location.host + "/json/overview/left_third.json",
		dataType: "json",
		type: "get",
		success: function(res) {
			var deviceSpreadChart = echarts.init(document.getElementById('device_upload'));
			let xAxis = [];
			let now = Date.now();
			for(let i=0; i<7; i++) {
				xAxis.unshift(moment(now - i * 1000*60*60*24 ).format("YYYY[/]MM[/]DD"))
			}
			var deviceSpreadOption = {
				tooltip: {
					trigger: 'axis',
					axisPointer: { // 坐标轴指示器，坐标轴触发有效
						type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				grid: {
					left: '10%',
					right: '15%',
					top: '15%',
					bottom: '15%',
					containLabel: true
				},
				xAxis: [{
					type: 'category',
					name: "时间(天)",
					data: xAxis,
					axisLine: {
						"show": true,
						"symbol": ["none", "arrow"],
						"symbolOffset": [0 ,10],
						"lineStyle": {
							"color": "white"
						}
					},
					axisTick: {
						alignWithLabel: true
					}
				}],
				yAxis: [{
					type: 'value',
					name: "数据量(亿)",
					axisLine: {
						"show": true,
						"symbol": ["none", "arrow"],
						"symbolOffset": [0 ,10],
						"lineStyle": {
							"color": "white"
						}
					},
					splitLine: {
						show: false
					}
				}],
				series: [{
					name: '数据上传量',
					type: 'line',
					barWidth: '60%',
					data: res.data
				}],
				textStyle: {
					fontSize: 16,
					color: "white"
				}

			};
			deviceSpreadChart.setOption(deviceSpreadOption)

		},
		error: function(res) {

		},
	})
}

deviceStatus()
deviceUpload()