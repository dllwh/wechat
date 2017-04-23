<%--获取当前用户登录IP --%>
<%@tag import="com.cdeledu.util.network.IpUtilHelper"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="var" type="java.lang.String" required="true"
	description="返回结果"%>
<%
	String result = IpUtilHelper.getClientIP(request);
	jspContext.setAttribute(var, result, PageContext.REQUEST_SCOPE);
%>
