<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="../../plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var browserversion = "";
		//IE8+浏览器
		if ($.browser.msie) {
			browserversion = "IE" + $.browser.version;
		}
		//谷歌浏览器
		if ($.browser.webkit) {
			browserversion = "Chrome" + $.browser.version;
		}
		//火狐浏览器
		if ($.browser.mozilla) {
			browserversion = "Mozilla Firefox" + $.browser.version;
		}
		//欧朋浏览器
		if ($.browser.opera) {
			browserversion = "Opera" + $.browser.version;
		}

		window.location.href = "loginController.shtml?doLogin";
	});
</script>
</head>
<body>

</body>
</html>