<%@tag import="com.cdeledu.util.ShiroHelper"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="var" type="java.lang.String" required="true"
	description="返回结果"%>
<%
	jspContext.setAttribute(var, ShiroHelper.getCurrentUserName(),
			PageContext.REQUEST_SCOPE);
%>

