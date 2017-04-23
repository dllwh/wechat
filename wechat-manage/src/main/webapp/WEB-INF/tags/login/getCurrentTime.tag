<%--获取当前时间 --%>
<%@ tag import="com.cdeledu.util.apache.lang.DateUtilHelper"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="var" type="java.lang.String" required="true"
	description="返回结果"%>
<%@ attribute name="pattern" type="java.lang.String" required="true"
	description="时间格式"%>
<%
	String result = "";
	try {
		result = DateUtilHelper.getCurrentDate(pattern);
	} catch (Exception ex) {

	}
	jspContext.setAttribute(var, result, PageContext.REQUEST_SCOPE);
%>
