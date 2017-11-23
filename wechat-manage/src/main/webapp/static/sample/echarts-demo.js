/**
 * 百度Echarts示例
 */

$(function() {
	var lineChart = echarts.init(document.getElementById("echarts-line-chart"));
	lineChart.showLoading({
		text: '正在努力的读取数据中...'
	});
	var lineoption = {
		"title" : {
			text : '未来一周气温变化1111'
		},
		"tooltip" : {
			trigger : 'axis'
		},
		legend : {
			data : [ '最高气温', '最低气温' ]
		},
		toolbox : {
			show : true,
			orient: 'vertical',
			feature : {
				mark : {
					show : false
				},
				dataView : {
					show : false,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true,
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			data : [ '周一', '周二', '周三', '周四', '周五', '周六', '周日' ]
		} ],
		yAxis : [ {
			type : 'value',
			axisLabel : {
				formatter : '{value} °C'
			}
		} ],
		series : [ {
			name : '最高气温',
			type : 'line',
			data : [ 11, 11, 15, 13, 12, 13, 10 ],
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
			markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}
		}, {
			name : '最低气温',
			type : 'line',
			data : [ 1, -2, 2, 5, 3, 2, 0 ],
			markPoint : {
				data : [ {
					name : '周最低',
					value : -2,
					xAxis : 1,
					yAxis : -1.5
				} ]
			},
			markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}
		} ]
	};
	lineChart.setOption(lineoption);
	lineChart.hideLoading();
	$(window).resize(lineChart.resize);

	var barChart = echarts.init(document.getElementById("echarts-bar-chart"));

	var baroption = {
		title : {
			text : '某地区蒸发量和降水量'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '蒸发量', '降水量' ]
		},
		toolbox : {
			show : true,
			orient: 'vertical',
			feature : {
				mark : {
					show : false
				},
				dataView : {
					show : false,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true,
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			data : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月',
					'10月', '11月', '12月' ]
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [
				{
					name : '蒸发量',
					type : 'bar',
					data : [ 2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2,
							32.6, 20.0, 6.4, 3.3 ],
					markPoint : {
						data : [ {
							type : 'max',
							name : '最大值'
						}, {
							type : 'min',
							name : '最小值'
						} ]
					},
					markLine : {
						data : [ {
							type : 'average',
							name : '平均值'
						} ]
					}
				},
				{
					name : '降水量',
					type : 'bar',
					data : [ 2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2,
							48.7, 18.8, 6.0, 2.3 ],
					markPoint : {
						data : [ {
							name : '年最高',
							value : 182.2,
							xAxis : 7,
							yAxis : 183,
							symbolSize : 18
						}, {
							name : '年最低',
							value : 2.3,
							xAxis : 11,
							yAxis : 3
						} ]
					},
					markLine : {
						data : [ {
							type : 'average',
							name : '平均值'
						} ]
					}
				} ]
	};
	barChart.setOption(baroption);
	window.onresize = barChart.resize;

	var mapChart = echarts.init(document.getElementById("echarts-map-chart"));
	var mapoption = {
		title : {
			text : 'iphone销量',
			subtext : '纯属虚构',
			x : 'center'
		},
		tooltip : {
			trigger : 'item'
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : [ 'iphone3', 'iphone4', 'iphone5' ]
		},
		dataRange : {
			min : 0,
			max : 2500,
			x : 'left',
			y : 'bottom',
			text : [ '高', '低' ], // 文本，默认为数值文本
			calculable : true
		},
		toolbox : {
			show : true,
			orient : 'vertical',
			x : 'right',
			y : 'center',
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		roamController : {
			show : true,
			x : 'right',
			mapTypeControl : {
				'china' : true
			}
		},
		series : [ {
			name : 'iphone3',
			type : 'map',
			mapType : 'china',
			roam : false,
			itemStyle : {
				normal : {
					label : {
						show : true
					}
				},
				emphasis : {
					label : {
						show : true
					}
				}
			},
			data : [ {
				name : '北京',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '天津',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '上海',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '重庆',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '河北',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '河南',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '云南',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '辽宁',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '黑龙江',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '湖南',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '安徽',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '山东',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '新疆',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '江苏',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '浙江',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '江西',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '湖北',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '广西',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '甘肃',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '山西',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '内蒙古',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '陕西',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '吉林',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '福建',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '贵州',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '广东',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '青海',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '西藏',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '四川',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '宁夏',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '海南',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '台湾',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '香港',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '澳门',
				value : Math.round(Math.random() * 1000)
			} ]
		}, {
			name : 'iphone4',
			type : 'map',
			mapType : 'china',
			itemStyle : {
				normal : {
					label : {
						show : true
					}
				},
				emphasis : {
					label : {
						show : true
					}
				}
			},
			data : [ {
				name : '北京',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '天津',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '上海',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '重庆',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '河北',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '安徽',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '新疆',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '浙江',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '江西',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '山西',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '内蒙古',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '吉林',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '福建',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '广东',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '西藏',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '四川',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '宁夏',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '香港',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '澳门',
				value : Math.round(Math.random() * 1000)
			} ]
		}, {
			name : 'iphone5',
			type : 'map',
			mapType : 'china',
			itemStyle : {
				normal : {
					label : {
						show : true
					}
				},
				emphasis : {
					label : {
						show : true
					}
				}
			},
			data : [ {
				name : '北京',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '天津',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '上海',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '广东',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '台湾',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '香港',
				value : Math.round(Math.random() * 1000)
			}, {
				name : '澳门',
				value : Math.round(Math.random() * 1000)
			} ]
		} ]
	};
	mapChart.setOption(mapoption);
	$(window).resize(mapChart.resize);
});