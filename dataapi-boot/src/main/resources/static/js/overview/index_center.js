/**
 * 顶部时间
 */
function clock() {
	let timeDOM = document.getElementById("time");
	let dateDOM = document.getElementById("date")
	setInterval(() => {
		dateDOM.innerHTML = moment().format("YYYY[/]MM[/]DD  dddd");
		timeDOM.innerHTML = moment().format("HH[:]mm[:]ss");
	}, 1000);
}
/**
 * 高德天气情况API接口
 */
function weatherInfo() {
	let weatherIcon = function(s) {
		let result = "&#xe636;"
		if (s == "晴") {
			result = "&#xe60a;"
		} else if (s.includes("阴")) {
			result = "&#xe63c;"
		} else if (s.includes("云")) {
			result = "&#xe625;"
		} else if (s.includes("大雨")) {
			result = "&#xe62a;"
		} else if (s.includes("中雨")) {
			result = "&#xe642;"
		} else if (s.includes("雨")) {
			result = "&#xe63a;"
		} else if (s.includes("大雪")) {
			result = "&#xe62c;"
		} else if (s.includes("中雪")) {
			result = "&#xe641;"
		} else if (s.includes("雪")) {
			result = "&#xe639;"
		}
		return result;
	}
	$.ajax({
		url: "https://restapi.amap.com/v3/weather/weatherInfo",
		type: "get",
		data: {
			city: "140100",
			key: "aa992d1106a7a20162d712d1a6faa98c"
		},
		success: function(res) {
			if (res.status == "1") {
				let data = null || res.lives[0];
				if (data != null) {

					$("#weather-icon").html(weatherIcon(data.weather))
					$("#weather-info").html(`
					<p>${data.weather}</p>
					<p>${data.temperature} ℃</p>
					<p>${data.windpower} 级</p>
					`)
				} else {
					$("#weather").html(`
					<p>查询失败</p>
					`)
				}
			}
		},
		error: function(res) {
			console.log(res)
		}
	})
}
/**
 * 高德地图loca模式
 */
function mapInfo() {
	// 地图的中心点
	var centerCoordinate = [108.936351, 34.342571] // 西安[108.936351, 34.342571]、太原[112.609904,37.878333]
	// 创建地图
	var map = new AMap.Map('map', {
		zoom: 5, //初始化地图层级
		center: centerCoordinate, //初始化地图中心点 
		zooms: [3, 18],
		mapStyle: 'amap://styles/darkblue', //设置地图的显示样式
		pitch: 60, // 地图俯仰角度，有效范围 0 度- 83 度
		viewMode: '3D', // 地图模式
		labelzIndex: 130,
		skyColor: "#142132",
		defaultCursor: 'pointer',
		expandZoomRange: true,
	});
	// 中国边界线高亮
	var district = new AMap.DistrictSearch({
		level: "country",
		showbiz: false,
		extensions: "all",
	})
	// var object3Dlayer = new AMap.Object3DLayer({ zIndex: 1 });
	// map.add(object3Dlayer);
	// district.search("中国", function(status, result) {
	// 	console.dir(result)
	// 	var bounds = result.districtList[0].boundaries;
	// 	// rgba
	//        for (var i = 0; i < bounds.length; i++) {
	//            var line = new AMap.Object3D.ThinLine({
	//                path: bounds[i],
	//                color: '#eb2f06'
	//            });
	//            object3Dlayer.add(line)
	//        }
	// })

	var loca = new Loca.Container({
		map,
	});
	// 链接图层
	var linkLayer = new Loca.LinkLayer({
		zIndex: 20,
		opacity: 1,
		visible: true,
		zooms: [2, 22],
	});
	// 动画图层1
	var scatterLayer1 = new Loca.ScatterLayer({
		zIndex: 10,
		opacity: 1,
		visible: true,
		zooms: [2, 22],
	});
	// 动画图层2
	var scatterLayer2 = new Loca.ScatterLayer({
		zIndex: 10,
		opacity: 0.8,
		visible: true,
		zooms: [2, 22],
	});
	// 动画图层:中心点
	var scatterLayer3 = new Loca.ScatterLayer({
		zIndex: 10,
		opacity: 0.8,
		visible: true,
		zooms: [2, 22],
	});
	// 中心点
	var centerPoint = new Loca.GeoJSONSource({
		data: {
			'type': 'FeatureCollection',
			'features': [{
				'type': 'Feature',
				'geometry': {
					'type': 'Point',
					'coordinates': [108.936351, 34.342571],
				},
			}],
		},
	});
	scatterLayer3.setSource(centerPoint);
	scatterLayer3.setStyle({
		size: [50, 50],
		unit: 'px',
		animate: true,
		duration: 1000,
		texture: 'https://a.amap.com/Loca/static/loca-v2/demos/images/breath_red.png',
	});
	loca.add(scatterLayer3);

	$.ajax({
		url: "http://" + location.host + "/json/overview/center.json",
		type: "get",
		dataType: "json",
		success: function(res) {
			let points = [];
			let lines = [];
			res.data.forEach(function(data, index) {
				let item1 = {
					'type': 'Feature',
					'geometry': {
						'type': 'LineString',
						'coordinates': [centerCoordinate, data.coordinates],
					},
					'properties': data.properties
				}
				item1.properties.type = index % 2;
				lines.push(item1)

				let item2 = {
					'type': 'Feature',
					'geometry': {
						'type': 'Point',
						'coordinates': data.coordinates,
					},
					'properties': data.properties
				}
				item2.properties.type = index % 2;
				points.push(item2)

			})
			// 端点
			scatterLayer2.setSource(new Loca.GeoJSONSource({
				data: {
					'type': "FeatureCollection",
					'features': points
				},
			}));
			scatterLayer2.setStyle({
				size: [20, 20],
				unit: 'px',
				animate: true,
				duration: 1000,
				texture: 'https://a.amap.com/Loca/static/loca-v2/demos/images/blue.png',
			});
			loca.add(scatterLayer2);
			// 连接线
			linkLayer.setSource(new Loca.GeoJSONSource({
				data: {
					'type': "FeatureCollection",
					'features': lines
				},
			}));
			linkLayer.setStyle({
				lineColors: function(index, item) {
					return item.link.properties.type === 0 ? ['#ff6b81', '#ff4757'] : [
						'#eccc68',
						'#ffa502'
					];
				},
				height: function(index, item) {
					return item.distance / 3;
				},
				smoothSteps: function(index, item) {
					return 200;
				},
			});
			loca.add(linkLayer);
		},
		error: function(res) {

		}
	});

}
clock()
weatherInfo()
mapInfo()
