<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<title>ECharts，一个纯 Javascript 的图表库</title>
<%@ include file="/WEB-INF/webviews/common/context/bootstrap.jsp"%>
<link rel="stylesheet" href="${_currConText }/static/css/sample/animate.css" />
<link rel="stylesheet" href="${_currConText }/static/css/sample/style.css" />
</head>
<body class="gray-bg">
	<%-- 简介 --%>
	<div class="row border-bottom white-bg dashboard-header">
		<div>
			<p>
				ECharts开源来自百度商业前端数据可视化团队，基于html5 Canvas，商业级数据图表，是一个纯Javascript图表库，
				可以流畅的运行在PC和移动设备上，兼容当前绝大部分浏览器（IE6/7/8/9/10/11，chrome，firefox，Safari等），
				提供直观，生动，可交互，可个性化定制的数据可视化图表。
				创新的拖拽重计算、数据视图、值域漫游等特性大大增强了用户体验，赋予了用户对数据进行挖掘、整合的能力。
			</p>
			<p>
				支持折线图（区域图）、柱状图（条状图）、散点图（气泡图）、K线图、饼图（环形图）、雷达图（填充雷达图）、和弦图、
				力导向布局图、地图、仪表盘、漏斗图、事件河流图等12类图表，
				同时提供标题，详情气泡、图例、值域、数据区域、时间轴、工具箱等7个可交互组件，支持多图表、组件的联动和混搭展现。
			</p>
			<p>
				ECharts2官网：<a href="http://echarts.baidu.com/echarts2/" target="_blank">http://echarts.baidu.com/</a>
			</p>
			<p>
				<echarts:getlineChart var="lineChart"/>
			</p>
		</div>
	</div>
	<%-- 示例 --%>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-6" >
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
			<div class="col-sm-6">
				<div class="ibox">
					<div class="ibox-title">
						<h5>柱状图</h5>
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
						<div class="echarts" id="echarts-bar-chart"></div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-6" >
				<div class="ibox">
					<div class="ibox-title">
						<h5>散点图</h5>
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
						<div class="echarts" id="echarts-scatter-chart"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="ibox">
					<div class="ibox-title">
						<h5>K线图</h5>
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
						<div class="echarts" id="echarts-k-chart"></div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-6" >
				<div class="ibox">
					<div class="ibox-title">
						<h5>饼状图</h5>
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
						<div class="echarts" id="echarts-pie-chart"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-6" >
				<div class="ibox">
					<div class="ibox-title">
						<h5>雷达图</h5>
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
						<div class="echarts" id="echarts-radar-chart"></div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-6">
				<div class="ibox">
					<div class="ibox-title">
						<h5>和弦图</h5>
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
						<div class="echarts" id="echarts-chord-chart"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-6" >
				<div class="ibox">
					<div class="ibox-title">
						<h5>仪表盘</h5>
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
						<div class="echarts" id="echarts-gauge-chart"></div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-6">
				<div class="ibox">
					<div class="ibox-title">
						<h5>漏斗图</h5>
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
						<div class="echarts" id="echarts-funnel-chart"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-6" >
				<div class="ibox">
					<div class="ibox-title">
						<h5>力导向布局图</h5>
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
						<div class="echarts" id="echarts-force-chart"></div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						<h5>中国地图</h5>
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
						<div style="height: 600px" id="echarts-map-chart"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
<%-- 	<script type="text/javascript" src="${_currConText }/plug-in/echarts3/echarts.min.js"></script> --%>
	<script type="text/javascript" src="${_currConText }/plug-in/echarts2/echarts-all.js"></script>
	<!-- 示例 -->
<%-- 	<script type="text/javascript" src="${_currConText }/static/sample/echarts-demo.js"></script> --%>
<!-- 自定义js -->
<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
	<script type="text/javascript">
		var lineChart = echarts.init(document.getElementById("echarts-line-chart"));
		lineChart.showLoading({
			text: '正在努力的读取数据中...'
		});
		var lineoption = ${lineChart};
		lineChart.setOption(lineoption);
		
		lineChart.hideLoading();
		$(window).resize(lineChart.resize);
	</script>
</body>
</html>