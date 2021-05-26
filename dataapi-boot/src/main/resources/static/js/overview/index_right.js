/**
 * 设备故障率
 */
function deviceFault() {
	$.ajax({
		url: "http://" + location.host + "/json/overview/right_first.json",
		dataType: "json",
		type: "get",
		success: function(res) {
			var deviceFaultChart = echarts.init(document.getElementById('device_fault'));
			var deviceFaultOption = {
				tooltip: {
					trigger: 'item'
				},
				legend: {
					top: '5%',
					left: 'center',
					textStyle: {
						color: "white"
					}
				},
				series: [{
					name: '设备故障率统计',
					type: 'pie',
					radius: ['40%', '70%'],
					avoidLabelOverlap: false,
					itemStyle: {
						borderRadius: 10,
						borderColor: '#fff',
						borderWidth: 2
					},
					label: {
						show: false,
						position: 'center'
					},
					emphasis: {
						label: {
							show: true,
							fontSize: '40',
							fontWeight: 'bold'
						}
					},
					labelLine: {
						show: false
					},
					data: res.data
				}],
				textStyle: {
					fontSize: 16
				}
			};
			deviceFaultChart.setOption(deviceFaultOption);
		},
		error: function(res) {

		},
	})
}
/**
 * 设备分布情况
 */
function deviceSpread() {
	$.ajax({
		url: "http://" + location.host + "/json/overview/right_second.json",
		dataType: "json",
		type: "get",
		success: function(res) {
			console.dir(res)
			var deviceSpreadChart = echarts.init(document.getElementById('device_spread'));
			var deviceSpreadOption = {
				tooltip: {
					trigger: 'axis',
					axisPointer: { // 坐标轴指示器，坐标轴触发有效
						type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				grid: {
					left: '10%',
					right: '10%',
					top: '10%',
					bottom: '10%',
					containLabel: true
				},
				xAxis: [{
					type: 'category',
					data: res.xAxis,
					axisTick: {
						alignWithLabel: true
					}
				}],
				yAxis: [{
					type: 'value'
				}],
				series: [{
					name: '设备分布情况',
					type: 'bar',
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
/**
 * 设备类型汇总
 */
function deviceType() {
	$.ajax({
		url: "http://" + location.host + "/json/overview/right_third.json",
		dataType: "json",
		type: "get",
		success: function(res) {
			var deviceTypeChart = echarts.init(document.getElementById('device_type'));
			var deviceTypeOption = {
				tooltip: {
					trigger: 'axis'
				},
				grid: {
					left: '5%',
					right: '5%',
					top: '10%',
					bottom: '10%',
					containLabel: true
				},
				xAxis: {
					type: 'category',
					data: res.xAxis
				},
				yAxis: {
					type: 'value'
				},
				series: [{
					data: res.data,
					type: 'line',
					smooth: true
				}],
				textStyle: {
					fontSize: 16,
					color: "#dfe4ea"
				}
			};
			deviceTypeChart.setOption(deviceTypeOption)

		},
		error: function(res) {

		},
	})
}

deviceFault()
deviceSpread()
deviceType()