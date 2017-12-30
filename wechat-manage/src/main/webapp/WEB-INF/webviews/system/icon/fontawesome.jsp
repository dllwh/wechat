<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/bootstrap.jsp"%>
<title>Font Awesome 字体图标</title>
<link rel="stylesheet" href="${_currConText }/static/css/sample/animate.css" />
<link rel="stylesheet" href="${_currConText }/static/css/sample/style.css" />
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-3">
				<div class="ibox float-e-margins">
					<h2>Font Awesome</h2>
					字体图标的最佳集合。提供可伸缩矢量图标，可以立即进行定制大小、颜色、阴影，所有都可以用CSS样式来完成。
				</div>
			</div>
			<div class="col-sm-9">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							所有图标 
							<small class="m-l-sm">所有图标集合 - 
								<a href="http://fortawesome.github.io/Font-Awesome/icons/" target="_blank">
									Font Awesome
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
<!-- 自定义js -->
<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
</html>