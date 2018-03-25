
<%@tag
	import="com.cdeledu.util.openplatform.baidu.echarts.EchartsHelper"%>
<%@tag import="com.cdeledu.util.openplatform.baidu.echarts.ChartsData"%>
<%@tag import="com.cdeledu.util.openplatform.baidu.echarts.AxisEntity"%>
<%@tag import="java.util.Arrays"%>

<%@tag import="com.github.abel533.echarts.code.DataFilter"%>
<%@tag import="com.google.common.collect.Lists"%>

<%@tag import="java.util.List"%>
<%@tag import="com.github.abel533.echarts.Title"%>
<%@tag import="com.cdeledu.util.network.IpUtilHelper"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="var" type="java.lang.String" required="true"
	description="返回结果"%>
<%
	String result = "";
	try {
		Title title = new Title();
		title.text("未来一周气温变化");
		title.subtext("纯属虚构");
		List<String> xData = Lists.newArrayList();
		xData.add("周一");
		xData.add("周二");
		xData.add("周三");
		xData.add("周四");
		xData.add("周五");
		xData.add("周六");
		xData.add("周日");
		AxisEntity axisEntity = new AxisEntity();
		axisEntity.setxData(xData);
		axisEntity.setyAxisFormatter(" °C");
		axisEntity.setLineShowState(true);
		axisEntity.setDataFilter(DataFilter.average);
		List<ChartsData> chartDataList = Lists.newArrayList();

		ChartsData chartDataMap1 = new ChartsData();
		chartDataMap1.setName("最高气温");
		chartDataMap1.setData(Arrays.asList(new Object[] { 11, 11, 35, 13, 52, 33, 10 }));
		chartDataList.add(chartDataMap1);
		ChartsData chartDataMap2 = new ChartsData();
		chartDataMap2.setName("最低气温");
		chartDataMap2.setData(Arrays.asList(new Object[] { 1, 3, 2, 44, 3, 2, 0 }));
		chartDataList.add(chartDataMap2);
		result = EchartsHelper
				.getlineChart(title, true, true, true, axisEntity, chartDataList)
				.toString();
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	jspContext.setAttribute(var, result, PageContext.REQUEST_SCOPE);
%>
