<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${_currProject }</title>
</head>
<script type="text/javascript"> 
var t=10;//设定跳转的时间 
setInterval("refer()",1000); //启动1秒定时 
function refer(){  
    if(t==0){ 
        location="${_currConText}/loginController.shtml?doLogin"; //#设定跳转的链接地址 
    } 
    document.getElementById('show').innerHTML=""+t+"秒后跳转"; // 显示倒计时 
    t--; // 计数器递减 
    //本文转自： 
} 
</script> 
<body>
	<h2>您已长时间没有操作，需要重新登陆！</h2>
	<span id="show"></span> 
</body>
</html>