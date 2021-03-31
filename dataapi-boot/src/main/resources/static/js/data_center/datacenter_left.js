//设备在线情况模块
var sbdata;
ajaxsbdata();
(function() {
	var myChart = echarts.init(document.querySelector(".panel .chart"));
	var option = {
		color: ["#0286ff", "#ffd302", "#fb5274"],
		tooltip: {
			trigger: 'item'
		},
		legend: {
			orient: 'horizontal',
			bottom: "bottom",
			itemGap: 40,
			textStyle: {
				color: ["#0286ff", "#ffd302", "#fb5274"],
				fontSize: 14
			}
		},
		textStyle: {
			fontSize: 18
		},
		series: [{
			name: '设备在线情况',
			type: 'pie',
			radius: '60%',
			data: sbdata,
			emphasis: {
				itemStyle: {
					shadowBlur: 10,
					shadowOffsetX: 0,
					shadowColor: 'rgba(0, 0, 0, 0.5)'
				}
			}
		}]
	};
	myChart.setOption(option);
	window.addEventListener("resize", function() {
		myChart.resize();
	})
})();

function ajaxsbdata(){    //获取json数据
	$.ajax({
		url:"../../json/sbdata.json",
		type:"get",
		dataType:"json",		
		success:function(res){			
			sbdata = res;
			return sbdata;
		},
		error:function(sbdata){
			console.log(sbdata);
		},
		async:false   //处理同步异步问题，默认为ture为异步		
	});
}

//动态获取当前网关下节点
/* 这块也不好，似乎也是写死的 */
function setChange() {
	if (document.f.selfs.value == "0") {
		$("table").css("display","none");
	} else if (document.f.selfs.value == "1") {
		document.all.tb1.style.display = "block";
		$("table:not(#tb1)").css("display","none");
	} else if (document.f.selfs.value == "2") {
		document.all.tb2.style.display = "block";
		$("table:not(#tb2)").css("display","none");
	} else if (document.f.selfs.value == "3") {
		document.all.tb3.style.display = "block";
		$("table:not(#tb3)").css("display","none");
	} else if (document.f.selfs.value == "4") {
		document.all.tb4.style.display = "block";
		$("table:not(#tb4)").css("display","none");
	} else if (document.f.selfs.value == "5") {
		document.all.tb5.style.display = "block";
		$("table:not(#tb5)").css("display","none");
	}
}

/* 动态添加第一个网关及对应的节点信息*/
console.log(time.start);
var jldata;
ajaxjldata();
(function jlNode() {
	var tb = document.getElementById("tb1");
	for (var j = 0; j < jldata.length; j++) {
		var oTr = document.createElement("tr");
		for (var i = 0; i < jldata[j].node.length; i++) {
			var oTd = document.createElement("td");
			oTd.innerHTML = jldata[j].node[i];
			oTr.appendChild(oTd);
			tb.appendChild(oTr);
		}
	}
})();

function ajaxjldata(){    //获取json数据
	$.ajax({
		url:"../../json/jldata.json",
		type:"get",
		dataType:"json",		
		success:function(res){			
			jldata = res;
			return jldata;
		},
		error:function(jldata){
			console.log(jldata);
		},
		async:false   //处理同步异步问题，默认为ture为异步		
	});
}
console.log(time.end);
/* 动态添加第二个网关*/
var lkdata;
ajaxlkdata();
(function lkNode() {
	var tb = document.getElementById("tb2");
	for (var j = 0; j < lkdata.length; j++) {
		var oTr = document.createElement("tr");
		for (var i = 0; i < lkdata[j].node.length; i++) {
			var oTd = document.createElement("td");
			oTd.innerHTML = lkdata[j].node[i];
			oTr.appendChild(oTd);
			tb.appendChild(oTr);
		}
	}
})();

function ajaxlkdata(){    //获取json数据
	$.ajax({
		url:"../../json/lkdata.json",
		type:"get",
		dataType:"json",		
		success:function(res){			
			lkdata = res;
			return lkdata;
		},
		error:function(lkdata){
			console.log(lkdata);
		},
		async:false   //处理同步异步问题，默认为ture为异步		
	});
}

/* 动态添加第三个网关*/
var zldata;
ajaxzldata();
(function zlNode() {
	var tb = document.getElementById("tb3");
	for (var j = 0; j < zldata.length; j++) {
		var oTr = document.createElement("tr");
		for (var i = 0; i < zldata[j].node.length; i++) {
			var oTd = document.createElement("td");
			oTd.innerHTML = zldata[j].node[i];
			oTr.appendChild(oTd);
			tb.appendChild(oTr);
		}
	}
})();

function ajaxzldata(){    //获取json数据
	$.ajax({
		url:"../../json/zldata.json",
		type:"get",
		dataType:"json",		
		success:function(res){			
			zldata = res;
			return zldata;
		},
		error:function(zldata){
			console.log(zldata);
		},
		async:false   //处理同步异步问题，默认为ture为异步		
	});
}

/* 动态添加第四个网关*/
var kfjdata;
ajaxkfjdata();
(function kfjNode() {
	var tb = document.getElementById("tb4");
	for (var j = 0; j < kfjdata.length; j++) {
		var oTr = document.createElement("tr");
		for (var i = 0; i < kfjdata[j].node.length; i++) {
			var oTd = document.createElement("td");
			oTd.innerHTML = kfjdata[j].node[i];
			oTr.appendChild(oTd);
			tb.appendChild(oTr);
		}
	}
})();

function ajaxkfjdata(){    //获取json数据
	$.ajax({
		url:"../../json/kfjdata.json",
		type:"get",
		dataType:"json",		
		success:function(res){			
			kfjdata = res;
			return kfjdata;
		},
		error:function(kfjdata){
			console.log(kfjdata);
		},
		async:false   //处理同步异步问题，默认为ture为异步		
	});
}

/* 动态添加第五个网关*/
var mmdata;
ajaxmmdata();
(function mmNode() {
	var tb = document.getElementById("tb5");
	for (var j = 0; j < mmdata.length; j++) {
		var oTr = document.createElement("tr");
		for (var i = 0; i < mmdata[j].node.length; i++) {
			var oTd = document.createElement("td");
			oTd.innerHTML = mmdata[j].node[i];
			oTr.appendChild(oTd);
			tb.appendChild(oTr);
		}
	}
})();

function ajaxmmdata(){    //获取json数据
	$.ajax({
		url:"../../json/mmdata.json",
		type:"get",
		dataType:"json",		
		success:function(res){			
			mmdata = res;
			return mmdata;
		},
		error:function(mmdata){
			console.log(mmdata);
		},
		async:false   //处理同步异步问题，默认为ture为异步		
	});
}
//节点属性值统计模块
//动态向select添加option
var arr;
ajaxarrdata();
var arr0 = [];
arr0[0] = "请选择节点";

var arr1 = [];
arr1[0] = "请选择节点";
var sy1 = 1;  //索引
for(var k=1 ; k<=jldata.length ; k++){
	arr1[sy1] = jldata[k-1].node[1];
	sy1++;
}

var arr2 = [];
arr2[0] = "请选择节点";
var sy2 = 1;
for(var k=1 ; k<=lkdata.length ; k++){
	arr2[sy2] = lkdata[k-1].node[1];
	sy2++;
}

var arr3 = [];
arr3[0] = "请选择节点";
var sy3 = 1;
for(var k=1 ; k<=zldata.length ; k++){
	arr3[sy3] = zldata[k-1].node[1];
	sy3++;
}

var arr4 = [];
arr4[0] = "请选择节点";
var sy4 = 1;
for(var k=1 ; k<=kfjdata.length ; k++){
	arr4[sy4] = kfjdata[k-1].node[1];
	sy4++;
}

var arr5 = [];
arr5[0] = "请选择节点";
var sy5 = 1;
for(var k=1 ; k<=mmdata.length ; k++){
	arr5[sy5] = mmdata[k-1].node[1];
	sy5++;
}

function AddOptions(dltObj, arrObj) {
	dltObj.innerHTML = "";
	var arrLocation = arrObj;
	for (var i = 0; i < arrLocation.length; i++) {
		var opt = document.createElement("option");
		dltObj.add(opt);
		opt.value = i;
		opt.text = arrLocation[i];
	}
}

function init() {
	AddOptions(dltGateway, eval('arr[0].value'));
	AddOptions(dltNode, eval('arr' + dltGateway.selectedIndex));
}

function ajaxarrdata(){    //获取json数据
	$.ajax({
		url:"../../json/arrdata.json",
		type:"get",
		dataType:"json",		
		success:function(res){			
			arr = res;
			return arr;
		},
		error:function(arr){
			console.log(arr);
		},
		async:false   //处理同步异步问题，默认为ture为异步		
	});
}

//节点属性统计模块
var imgdata;
ajaximgdata();
function showImg() {
	(function() {
		//获取value值
		/* var myGateway = document.getElementById("dltGateway");
		var indexGateway = myGateway.selectedIndex;
		var valueGateway = myGateway.options[indexGateway].value;
		var myNode = document.getElementById("dltNode");
		var indexNode = myNode.selectedIndex;
		var valueNode = myNode.options[indexNode].value; */
		var valueGateway = $("#dltGateway").val();
		var valueNode = $("#dltNode").val();
		var newImgdata = [];
		var datavar = 0;
	/* 	for(var i=1 ; i<valueGateway.length ; i++){
			for(var j=1 ; j<valueNode.length; j++){
				for (var m = 0; m < imgdata.length; m++) {
					newImgdata[datavar] = imgdata[m].data;
					datavar++;
				}
			}
		} */
		if (valueGateway == 1 && valueNode == 1) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].jldata1;
				datavar++;
			}
		} else if (valueGateway == 1 && valueNode == 2) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].jldata2;
				datavar++;
			}
		} else if (valueGateway == 1 && valueNode == 3) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].jldata3;
				datavar++;
			}
		} else if (valueGateway == 1 && valueNode == 4) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].jldata4;
				datavar++;
			}
		}  else if (valueGateway == 1 && valueNode == 5) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].jldata5;
				datavar++;
			}
		} else if (valueGateway == 1 && valueNode == 6) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].jldata6;
				datavar++;
			}
		} else if (valueGateway == 2 && valueNode == 1) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].lkdata1;
				datavar++;
			}
		} else if (valueGateway == 2 && valueNode == 2) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].lkdata2;
				datavar++;
			}
		} else if (valueGateway == 2 && valueNode == 3) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].lkdata3;
				datavar++;
			}
		} else if (valueGateway == 3 && valueNode == 1) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].zldata1;
				datavar++;
			}
		} else if (valueGateway == 3 && valueNode == 2) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].zldata2;
				datavar++;
			}
		} else if (valueGateway == 3 && valueNode == 3) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].zldata3;
				datavar++;
			}
		}else if (valueGateway == 4 && valueNode == 1) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].kfdata1;
				datavar++;
			}
		} else if (valueGateway == 4 && valueNode == 2) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].kfdata2;
				datavar++;
			}
		} else if (valueGateway == 4 && valueNode == 3) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].kfdata3;
				datavar++;
			}
		} else if (valueGateway == 4 && valueNode == 4) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].kfdata4;
				datavar++;
			}
		} else if (valueGateway == 4 && valueNode == 5) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].kfdata5;
				datavar++;
			}
		} else if (valueGateway == 4 && valueNode == 6) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].kfdata6;
				datavar++;
			}
		} else if (valueGateway == 5 && valueNode == 1) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].tedata1;
				datavar++;
			}
		} else if (valueGateway == 5 && valueNode == 2) {
			for (var m = 0; m < imgdata.length; m++) {
				newImgdata[datavar] = imgdata[m].tedata1;
				datavar++;
			}
		} 
		var myChart = echarts.init(document.querySelector(".panel .chart2"));
		var XName = ["1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00", "12:00",
			"13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"
		]
		//让气泡图变成好看的线条，imgdata里有多少个对象，这就创建多少个这样的image
		var img = [];
		var indexImg = 0;
		for (var p = 0; p < imgdata.length; p++) {
			img[indexImg] =
				'image://data:image/png;base64,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';
			indexImg++;
		}
		var color = ['#00f8ff', '#00f15a', '#0696f9', '#dcf776'];

		//数据处理
		var datas = [];
		var myLine = []; //将json数组中的Line保存在这个数组里
		var indexbiao = 0; //定义一个索引下标
		for (var i = 0; i < imgdata.length; i++) {
			myLine[indexbiao] = imgdata[i].Line;
			indexbiao++;
		}
		myLine.map((item, index) => {
			datas.push({
				symbolSize: 100,
				symbol: img[index],
				name: item,
				type: "line",
				yAxisIndex: 1,
				data: newImgdata[index],
				itemStyle: {
					normal: {
						borderWidth: 5,
						color: color[index],
					}
				}
			})
		})

		var option = {
			grid: {
				left: '10%',
				top: '17%',
				bottom: '11%',
				right: '5%',
			},
			legend: {
				type: "scroll",
				data: imgdata.Line,
				top: "3%",
				itemWidth: 16,
				itemHeight: 12,
				pageIconColor: "#fff",
				pageIconInactiveColor: '#817E7E',
				pageButtonItemGap: 2,
				pageTextStyle: {
					color: "#fff"
				},
				textStyle: {
					color: "#00ffff",
					fontSize: 14
				},
			},
			tooltip: {
				show: true,
				trigger: "axis",
				axisPointer: {
					// 坐标轴指示器，坐标轴触发有效
					type: "line" // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			yAxis: [{
					type: 'value',
					position: 'right',
					splitLine: {
						show: false
					},
					axisLine: {
						show: false
					},
					axisTick: {
						show: false
					},
					axisLabel: {
						show: false
					}
				},
				{
					type: 'value',
					position: 'left',
					nameTextStyle: {
						color: '#00FFFF'
					},
					splitLine: {
						lineStyle: {
							type: 'dashed',
							color: 'rgba(135,140,147,0.8)'
						}
					},
					axisLine: {
						show: false
					},
					axisTick: {
						show: false
					},
					axisLabel: {
						formatter: '{value}',
						color: '#fff',
						fontSize: 14
					}
				},
			],
			xAxis: [{
					type: 'category',
					axisTick: {
						show: false
					},
					axisLine: {
						show: false,
						lineStyle: {
							color: '#6A989E',
						}
					},
					axisLabel: {
						inside: false,
						textStyle: {
							color: '#fff', // x轴颜色
							fontWeight: 'normal',
							fontSize: '14',
							lineHeight: 12
						}

					},
					data: XName,
				},
				{
					type: 'category',
					axisLine: {
						show: false
					},
					axisTick: {
						show: false
					},
					axisLabel: {
						show: false
					},
					splitArea: {
						show: false
					},
					splitLine: {
						show: false
					},
				},
			],
			series: datas,
		};
		//当点击legend选项时折线上的小图片会消失，为避免这种情况，可以采取以下初始化方法 使用svg
		//var myCharts = echarts.init(document.getElementById('AnalysisChartLine'), null, {renderer: 'svg'});
		//myCharts.clear();
		//myCharts.setOption(option)
		myChart.setOption(option);
		window.addEventListener("resize", function() {
			myChart.resize();
		})
	})();
}

function ajaximgdata(){    //获取json数据
	$.ajax({
		url:"../../json/imgdata.json",
		type:"get",
		dataType:"json",		
		success:function(res){			
			imgdata = res;
			return imgdata;
		},
		error:function(imgdata){
			console.log(imgdata);
		},
		async:false   //处理同步异步问题，默认为ture为异步		
	});
}
