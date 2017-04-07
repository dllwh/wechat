<%@page import="java.net.InetAddress"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Java爬虫采集系统-cms</title>
</head>
<body>
	<%
		String ip = InetAddress.getLocalHost().getHostAddress().toString();
		out.println("Welcome!This is " + ip);
	%>
</body>
</html>