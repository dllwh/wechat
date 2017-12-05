<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<title>ECharts，一个纯 Javascript 的图表库</title>

<link rel="stylesheet" href="${_currConText }/plug-in/assets/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${_currConText }/plug-in/font/css/font-awesome.min.css" />
<link rel="stylesheet" href="${_currConText }/static/css/sample/animate.css" />
<link rel="stylesheet" href="${_currConText }/static/css/sample/style.css" />
<script type="text/javascript" src="${_currConText }/plug-in/jquery/jquery-1.10.2.min.js"></script>
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
	<%-- 示例 --%>
	<script type="text/javascript" src="${_currConText }/plug-in/echarts3/echarts.min.js"></script>
	<!-- 自定义js -->
	<script src="${_currConText }/plug-in/tools/ExtJavascript.js?v=1.0.0"></script>
	<script src="${_currConText }/plug-in/tools/content.js?v=1.0.0"></script>
</body>
</html>