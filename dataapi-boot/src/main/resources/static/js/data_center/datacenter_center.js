//map模块
var mapdata;
ajaxmapdata();
(function() {
	var myChart = echarts.init(document.querySelector(".map .chart"));
	var outdata = [];
	var max = 6000,
		min = 10;
	var maxSize4Pin = 100,
		minSize4Pin = 20;
	for (var i = 0; i < mapdata.length; i++) {
		outdata.push({
			name: mapdata[i].name,
			value: mapdata[i].value
		})
	}
	var geoCoordMap = {};
	/*获取地图数据*/
	var mapFeatures = echarts.getMap('china').geoJson.features;
	//  console.log(mapFeatures)
	mapFeatures.forEach(function(v) {
		console.info(v)
		// 地区名称
		var name = v.properties.name;
		// 地区经纬度
		geoCoordMap[name] = v.properties.cp;
	});
	var convertData = function(outdata) {
		var res = [];
		for (var i = 0; i < outdata.length; i++) {
			var geoCoord = geoCoordMap[outdata[i].name];
			if (geoCoord) {
				res.push({
					name: outdata[i].name,
					value: geoCoord.concat(outdata[i].value),
				});
			}
		}
		return res;
	};

	var option = {
		opacity: 0.1,
		title: {
			text: "网关节点分布",
			x: 'center',
			top: 20,
			textStyle: {
				color: "#CDDEF8",
				fontSize: 24
			}
		},
		tooltip: {
			show: true,
			formatter: function(params) {
				if (params.value.length > 1) {
					return '&nbsp;&nbsp;' + params.name + '&nbsp;&nbsp;&nbsp;' + params.value[2] + '个&nbsp;&nbsp;';
				} else {
					return '&nbsp;&nbsp;' + params.name + '&nbsp;&nbsp;&nbsp;' + params.value + '个&nbsp;&nbsp;';
				}
			},
		},
		geo: {
			map: 'china',
			zoom: 1.1,
			show: true,
			roam: false,
			center: [104.97, 32.71],
			label: {
				emphasis: {
					show: false
				}
			},
			layoutSize: "100%",
			itemStyle: {
				normal: {
					borderColor: "rgba(83,217,255,0.6)",
					areaColor: "rgba(20, 41, 87,0.5)",
					borderWidth: 1,
					shadowColor: 'rgba(10,76,139,1)',
					shadowOffsetY: 0,
					shadowBlur: 60,
				}
			}
		},
		series: [{
			type: 'map',
			map: 'china',
			aspectScale: 0.75,
			zoom: 1.1,
			center: [104.97, 32.71],
			label: {
				normal: {
					show: false,
				},
				emphasis: {
					show: false,
				}
			},
			itemStyle: {
				normal: {
					areaColor: {
						x: 0,
						y: 0,
						x2: 0,
						y2: 1,
						colorStops: [{
							offset: 0,
							color: 'rgba(2,36,158,0.2)' // 0% 处的颜色
						}, {
							offset: 1,
							color: 'rgba(2,166,181,0.2)' // 100% 处的颜色
						}],
					},
					borderColor: '#215495',
					borderWidth: 0.5,
				},
				emphasis: {
					areaColor: {
						x: 0,
						y: 0,
						x2: 0,
						y2: 1,
						colorStops: [{
							offset: 0,
							color: 'rgba(2,36,158,0.2)' // 0% 处的颜色
						}, {
							offset: 1,
							color: 'rgba(2,166,181,0.2)' // 100% 处的颜色
						}],
					},
				}
			},
			data: outdata,
		}, {
			type: 'effectScatter',
			coordinateSystem: 'geo',
			/* zoom:1.2, */
			rippleEffect: {
				brushType: 'stroke'
			},
			showEffectOn: 'render',
			itemStyle: {
				normal: {
					color: {
						type: 'radial',
						x: 0.5,
						y: 0.5,
						r: 0.5,
						colorStops: [{
							offset: 0,
							color: 'rgba(5,80,151,0.2)'
						}, {
							offset: 0.8,
							color: 'rgba(5,80,151,0.8)'
						}, {
							offset: 1,
							color: 'rgba(0,108,255,0.7)'
						}],
						global: false // 缺省为 false
					},
				}
			},
			zoom: 1.2,
			label: {
				normal: {
					show: true,
					color: '#fff',
					position: 'inside',
					formatter: function(para) {
						return '{cnNum|' + para.data.value[2] + '}'
					},
					rich: {
						cnNum: {
							fontSize: 13,
							color: '#D4EEFF',
						}
					}
				},
			},
			symbol: 'circle',
			symbolSize: function(val) {
				if (val[2] === 0) {
					return 0;
				}
				var a = (maxSize4Pin - minSize4Pin) / (max - min);
				var b = maxSize4Pin - a * max;
				return a * val[2] + b * 1.2;
			},
			data: convertData(outdata),
			zlevel: 1,
		}]
	};
	myChart.setOption(option);
	window.addEventListener("resize", function() {
		myChart.resize();
	})
})();

function ajaxmapdata(){    //获取json数据
	$.ajax({
		url:"../../json/mapdata.json",
		type:"get",
		dataType:"json",		
		success:function(res){			
			mapdata = res;
			return mapdata;
		},
		error:function(mapdata){
			console.log(mapdata);
		},
		async:false   //处理同步异步问题，默认为ture为异步		
	});
}