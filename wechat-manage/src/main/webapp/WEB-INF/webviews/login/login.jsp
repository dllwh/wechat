<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<html>
<head>
<title>${_currProject }</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->
<link rel="stylesheet" href="${_currConText }/plug-in/login/css/supersized.css">
<link rel="stylesheet" href="${_currConText }/plug-in/login/css/login.css">
<link href="${_currConText }/plug-in/login/css/bootstrap.min.css" rel="stylesheet">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="${_currConText }/plug-in/login/js/html5.js"></script>
<![endif]-->
<script src="${_currConText }/plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${_currConText }/plug-in/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${_currConText }/plug-in/login/js/tooltips.js"></script>
<script type="text/javascript" src="${_currConText }/plug-in/login/js/login.js"></script>
<script type="text/javascript">
	// 判断如果当前页面不为主框架，则将主框架进行跳转
	var tagert_URL = "<%=request.getContextPath()%>/loginController.shtml?doLogin";
	if (self != top) {
		top.location.href = tagert_URL;
	}
</script>
</head>
<body>
<div class="page-container">
	<div class="main_box">
		<div class="login_box">
			<div class="login_logo">
				<label><span>${_currProject }登录</span></label> 
			</div>
		
			<div class="login_form">
				<form action="loginController.shtml?doLogin" id="login_form" method="post"
					check="loginController.shtml?checkuser" >
					<div class="form-group">
						<label for="userName" class="t">账  号</label>
						<input id="userName" name="userName" type="text" 
							class="userName form-control x319 in" autocomplete="off">
					</div>
					<div class="form-group">
						<label for="password" class="t">密  码</label> 
						<input id="password" value="" name="password" type="password" 
						class="password form-control x319 in">
					</div>
					<div class="form-group">
						<label for="captcha" class="t">验证码</label>
						<input id="imageCaptcha" name="imageCaptcha" value="" type="text" 
							class="form-control x164 in">
						<img id="captcha_img" alt="点击更换" title="看不清？换一张试试" 
							src="${_currConText }/plug-in/login/images/imageCaptcha.jsp" 
							onclick="javascript:loadimage();" class="m">
					</div>
					<div class="form-group space">
						<label class="t"></label>　　　
						<button type="button"  id="submit_btn" 
						class="btn btn-primary btn-lg">&nbsp;登&nbsp;录&nbsp; </button>
						<input type="reset" value="&nbsp;重&nbsp;置&nbsp;" class="btn btn-default btn-lg">
					</div>
				</form>
			</div>
		</div>
		<div class="bottom">Copyright &copy; 2016 - 2017 </div>
	</div>
</div>

<!-- Javascript -->

<script src="${_currConText }/plug-in/login/js/supersized.3.2.7.min.js"></script>
<script src="${_currConText }/plug-in/login/js/supersized-init.js"></script>
<script src="${_currConText }/plug-in/login/js/scripts.js"></script>
<script src="${_currConText }/plug-in/login/js/jquery-jrumble.js"></script>
</body>
</html>