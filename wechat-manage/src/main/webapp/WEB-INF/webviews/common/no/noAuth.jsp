<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<style type="text/css">
	body {
		background-image: url(${_currConText }/static/image/cloud.jpg);
		background-position: center !important; font-family : "微软雅黑";
		font-weight:;
		font: normal 100% Helvetica, Arial, sans-serif 900;
		font-family: "微软雅黑";
	}
	
	* {
		margin: 0px;
		padding: 0px;
	}
	
	.container {
		margin-left: auto;
		width: 100%;
		margin-right: auto;
		text-align: center;
		margin-top: 100px;
	}
	
	.tip {
		width: 28vw;
		margin: auto
	}
	
	.tip span {
		color: #f30810;
		font-size: 2vw;
		text-align: left;
		font-weight: bolder;
	}
	</style>
<title>权限不足</title>
</head>
<body>
	<div class="container">
		<div>
			<img src="${_currConText }/static/image/noAuth.gif">
		</div>
		<div class="tip">
			<div>
				<span>用户权限不足，请联系管理员</span>
			</div>
		</div>
	</div>
</body>


</html>