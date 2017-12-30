<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<title>Glyphicons字体图标</title>
<%@ include file="/WEB-INF/webviews/common/context/bootstrap.jsp"%>
<link rel="stylesheet" href="${_currConText }/static/css/sample/animate.css" />
<link rel="stylesheet" href="${_currConText }/static/css/sample/style.css" />
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-3">
				<div class="ibox float-e-margins">
					<h2>Glyphicons 字体图标</h2>
					包括250多个来自 Glyphicon Halflings 的字体图标。Glyphicons Halflings
					一般是收费的，但是他们的作者允许 Bootstrap 免费使用。为了表示感谢，希望你在使用时尽量为 Glyphicons
					添加一个友情链接。
				</div>
			</div>
			<div class="col-sm-9">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							所有图标 
							<small class="m-l-sm">所有图标集合 - 
								<a target="_blank" href="http://glyphicons.com/">
									Glyphicons
								</a>
							</small>
						</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> 
								<i class="fa fa-chevron-up"></i>
							</a> 
							<a class="close-link">
								<i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content icons-box"></div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
</html>