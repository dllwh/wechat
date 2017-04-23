<%@ tag import="com.cdeledu.util.apache.lang.DateUtilHelper"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="var" type="java.lang.String" required="true"
	description="返回结果"%>
<%@ attribute name="date" type="java.util.Date" required="true"
	description="时间格式"%>
<%@ attribute name="pattern" type="java.lang.String" required="true"
	description="时间格式"%>
<%
	String result = "";
	try {
		result = DateUtilHelper.formatDate(date, pattern);
	} catch (Exception ex) {
	ex.printStackTrace();
	}
	jspContext.setAttribute(var, result, PageContext.REQUEST_SCOPE);
%>
