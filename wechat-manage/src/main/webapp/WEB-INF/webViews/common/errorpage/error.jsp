<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误提示</title>
</head>
<body>
	<%
		Exception e = (Exception) request.getAttribute("ex");
	%>
	<H2>
		错误异常:
		<%=e.getClass().getSimpleName()%></H2>
	<hr />
	<P>错误描述：</P>
	<%=e.getMessage()%>
	<P>错误信息：</P>
	<%
		e.printStackTrace(new java.io.PrintWriter(out));
	%>
</body>
</html>