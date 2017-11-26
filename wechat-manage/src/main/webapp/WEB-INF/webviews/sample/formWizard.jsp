<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<title>后台主题UI框架 - 表单向导</title>
<link rel="stylesheet" href="${_currConText }/plug-in/assets/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${_currConText }/plug-in/steps/jquery.steps.css"/>
<link rel="stylesheet" href="${_currConText }/static/css/sample/animate.css" />
<link rel="stylesheet" href="${_currConText }/static/css/sample/style.css" />
<script type="text/javascript"
	src="${_currConText }/plug-in/jquery/jquery-1.10.2.min.js"></script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-4">
				<div class="jumbotron">
					<h1>表单向导</h1>
					<p>Smart UI 部件允许您快速创建表单向导接口。</p>
					<p>
						<a href="http://www.jquery-steps.com/GettingStarted"
							target="_blank" class="btn btn-primary btn-lg" role="button">
							了解 jQuery Steps
						</a>
					</p>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="ibox">
					<div class="ibox-title">
						<h5>基础表单向导</h5>
					</div>
					<div class="ibox-content">
						<p>这是一个简单的表单向导示例 </p>
						<div id="wizard" class="wizard clearfix">
							<h1>第一步</h1>
							<div class="step-content">
								<div class="text-center">
									<p>这是第一步的内容</p>
								</div>
							</div>
							<h1>第二步</h1>
							<div class="step-content">
								<div class="text-center m-t-md">
									<p>这是第二步的内容</p>
								</div>
							</div>
							<h1>第三步</h1>
							<div class="step-content">
								<div class="text-center m-t-md">
									<p>这是第三步的内容</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="${_currConText }/plug-in/steps/jquery.steps.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#wizard").steps({
			enableFinishButton: false,// 隐藏完成按钮
			enableCancelButton: false,// 隐藏取消按钮
			// stepsOrientation: "vertical",// 垂直显示
			enablePagination: true
		});
	});
</script>
</html>