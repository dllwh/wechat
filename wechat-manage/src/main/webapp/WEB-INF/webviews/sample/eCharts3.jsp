<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<title>ECharts，一个纯 Javascript 的图表库</title>

<link rel="stylesheet" href="${_currConText }/plug-in/assets/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${_currConText }/plug-in/font/css/font-awesome.min.css" />
<link rel="stylesheet" href="${_currConText }/static/css/sample/animate.css" />
<link rel="stylesheet" href="${_currConText }/static/css/sample/style.css" />

<script src="https://cdn.bootcss.com/echarts/3.7.1/echarts.min.js"></script>

</head>
<body class="gray-bg">
	<%-- 简介 --%>
	<div class="row border-bottom white-bg dashboard-header">
		<div>
			<p>
				ECharts，是百度EFE团队推出的一个纯 Javascript 的图表库，
				可以流畅的运行在 PC 和移动设备上，底层依赖轻量级的 Canvas 类库 ZRender，
				提供直观，生动，可交互，可高度个性化定制的数据可视化图表。
				ECharts 3 中更是加入了更多丰富的交互功能以及更多的可视化效果，并且对移动端做了深度的优化
			</p>
			<pre>
&lt;!--引入百度地图--&gt;
&lt;!--建议使用自己的ak，避免出现问题--&gt;
&lt;script type="text/javascript" src="http://api.map.baidu.com/getscript?v=2.0&amp;ak=ObQAHaA2vmgzruRgw43QWoAhXhuzgOVD&amp;services=&amp;t=20170207140543"&gt;&lt;/script&gt;</pre>
			<hr>
			<p>
				更多示例 <a href="http://echarts.baidu.com/examples.html" target="_blank">请前往官网查看 。</a>
			</p>
		</div>
	</div>
	
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12" >
				<div class="ibox">
					<div class="ibox-title">
						<h5>折线图</h5>
						<div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a> 
							<a class="close-link">
								<i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="echarts" id="echarts-line-chart"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">

		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('echarts-line-chart'));

		// 指定图表的配置项和数据
		var option = {
			title : {
				text : 'Topic'   
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '内存使用情况', '当前数量' ]
			},
			toolbox : {
				show : true,
				feature : {
					dataView : {
						readOnly : false
					},
					restore : {},
					saveAsImage : {}
				}
			},
			dataZoom : {
				show : false,
				start : 0,
				end : 100
			},
			xAxis : [ {
				type : 'category',
				boundaryGap : false,
				data : (function() {
					var now = new Date();
					var res = [];
					var len = 20;
					while (len--) {
						res.unshift(now.toLocaleTimeString()
								.replace(/^\D*/, ''));
						now = new Date(now - 2000);
					}
					return res;
				})()
			} ],
			yAxis : [ {
				type : 'value',
				scale : true,
				name : '内存使用情况',
				max : 20,
				min : 0,
				boundaryGap : [ 0.2, 0.2 ]
			}, {
				type : 'value',
				scale : true,
				name : '当前数量',
				min : 0,
				boundaryGap : [ 0.2, 0.2 ]
			} ],
			series : [
					{
						name : '当前数量',
						type : 'line',
						yAxisIndex : 1,
						itemStyle : {
							normal : {
								color : '#ffd700',
								lineStyle : {
									color : '#ffd700'
								}
							}
						},
						data : (function() {
							var res = [];
							var len = 20;
							while (len--) {
								res.push(null);
							}
							return res;
						})()
					},
					{
						name : '内存使用情况',
						type : 'line',
						smooth : true,
						// itemStyle areaStyle 成为面积图的关键。
						itemStyle : {
							normal : {
								color : '#0099ff',
								areaStyle : {
									type : 'default'
								},
								lineStyle : {
									color : '#0099ff'
								}
							}
						},
						areaStyle : {// 实现蓝白渐变色
							normal : {
								color : new echarts.graphic.LinearGradient(0,
										0, 0, 1, [ {
											offset : 0,
											color : 'rgb(0, 153, 255)'
										}, {
											offset : 1,
											color : 'rgb(255,255,255)'
										} ])
							}
						},
						data : (function() {
							var res = [];
							var len = 0;
							while (len < 20) {
								res.push(null);
								len++;
							}
							return res;
						})()
					} ]
		};
		setInterval(function() {
			axisData = (new Date()).toLocaleTimeString().replace(/^\D*/, '');

			var data0 = option.series[0].data;
			var data1 = option.series[1].data;
			data0.shift();
			data0.push(Math.round(Math.random() * 100));
			data1.shift();
			data1.push(Math.round(Math.random() * 20));

			option.xAxis[0].data.shift();
			option.xAxis[0].data.push(axisData);

			myChart.setOption(option);
		}, 1000);
	</script>
	
	
	<%-- 示例 --%>
	<script type="text/javascript" src="${_currConText }/plug-in/echarts3/echarts.min.js"></script>
	<!-- 自定义js -->
	<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
</body>
</html>