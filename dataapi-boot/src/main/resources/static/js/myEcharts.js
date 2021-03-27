class Echarts {
	/**
	 * echarts对象的封装
	 * @param {Object} containerId echarts中init时候的div的id号
	 * @param {Object} option echarts中的option选项
	 * @param {String} nowSensor 当前选择的传感器类型
	 */
	constructor(containerId, option, nowSensor = null) {
		document.getElementById(containerId).innerHTML = ""; // 清空一下div
		this.container = null;			// echarts的对象
		this.containerId = containerId; // echarts中init时候的div的id号
		this.option = option;			// echarts中的option选项
		this.nowSensor = nowSensor;		// 当前选择的传感器类型
		this.xAxis = option.xAxis; 		// x轴，表示时间轴【数据刷新的时候使用】
		this.series = option.series;	// y轴，表示数值轴【数据刷新的时候使用】
		this.time = null;				// x轴内容，
		this.data = null;				// y轴内容，存放所有传感的数据，切换到的时候不用请求网络接口
		this.init();
	}
	/**
	 * 初始化echarts图表
	 */
	init() {
		this.container = echarts.init(document.getElementById(this.containerId)); // echarts的父类
		this.container.setOption(this.option); // 设置option选项
	}
	
	/**
	 * 展示历史数据
	 * @param {Array} xAxis x轴的内容，表示时间轴
	 * @param {Object} data 展示的数据 
	 */
	historyData(time, data) {
		this.time = time;
		this.data = data;
		this.xAxis.data = this.time;
		this.series[0].data = this.data[this.nowSensor]
		this.container.setOption({
			xAxis: this.xAxis,
			series: this.series
		})
	}
	
	/**
	 * 改变传感器的类型
	 * @param {Object} nowSensor 选中的传感器
	 */
	changeSensor(nowSensorInfo){
		this.nowSensor = nowSensorInfo.key;			// 当前选择的传感器类型
		this.series[0].name = nowSensorInfo.name; 	// 当前选择的传感器名称
		this.series[0].data = this.data[this.nowSensor];
		this.container.setOption({
			xAxis: this.xAxis,
			series: this.series
		})
		
	}
	
	/**
	 * 更新传感器数据
	 */
	updataValue() {
		
	}
	
}