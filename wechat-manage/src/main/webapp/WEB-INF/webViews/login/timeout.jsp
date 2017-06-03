<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webViews/common/common.jsp"%>
<html>
<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="plug-in/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="plug-in/login/js/login.js"></script>
<script type="text/javascript">
	// 判断如果当前页面不为主框架，则将主框架进行跳转
	var tagert_URL = "<%=request.getContextPath()%>/loginController.shtml?doLogin";
	if (self == top) {
		window.location.href = tagert_URL;
	} else {
		top.location.href = tagert_URL;
	}
</script>
<body>
	<h2>您当前没有登录或登录超时，请重新登录！</h2>
</body>
</html>