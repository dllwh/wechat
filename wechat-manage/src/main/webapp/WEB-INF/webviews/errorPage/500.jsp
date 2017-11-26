<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<title>HTTP-Internal Server Error</title>
<style type="text/css">
html, body, div, h1, h2, h3, h4, h5, h6, a, img {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}

a {
	text-decoration: none;
}

img {
	max-width: 100%;
}

body {
	font-family: 'Open Sans', sans-serif;
	background: url(${_currConText}/static/image/500_bg.jpg) no-repeat center 0px;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	background-size: cover;
	background-attachment: fixed;
}

.w3layouts-errortext {
	padding-top: 7em;
	text-align: center;
}

.w3layouts-errortext h2 {
	font-size: 6em;
	color: #fff;
	line-height: 1.8em;
	font-family: 'Share Tech Mono', monospace;
}

.w3layouts-errortext h2  span {
	background: #fff;
	color: #763c31;
	padding: .1em 0.3em;
	border: 15px solid #763c31;
	-webkit-box-shadow: 0px 0px 10px 1px #000;
	-moz-box-shadow: 0px 0px 10px 1px #000;
	box-shadow: 0px 0px 10px 1px #000;
}

.w3layouts-errortext h3 {
	font-size: 1.5em;
	color: #fff;
	font-weight: 600;
	border: 1px solid #fff;
	display: inline-block;
	padding: .8em 2em;
	margin: 2em 0 0;
}

.w3layouts-errortext h3 a {
	font-size: 0.6em;
	color: #fff;
	font-weight: 100;
	background: #542629;
	display: inline-block;
	padding: .5em 1em;
	margin-top: 1em;
	font-family: 'Share Tech Mono', monospace;
	-webkit-transition: .5s all;
	-moz-transition: .5s all;
	transition: .5s all;
}

.w3layouts-errortext h3 a:hover {
	color: #FFC107;
	background: transparent;
}

p.w3lstext {
	font-size: 0.9em;
	color: #fff;
	line-height: 2em;
	font-weight: 400;
	width: 65%;
	margin: 1.5em auto;
}
</style>
</head>
<body>
	<div class="agileits-main">
		<div class="agileinfo-row">

			<div class="w3layouts-errortext">
				<h2>
					<span>5</span> <span>0</span> <span>0</span>
				</h2>

				<p class="w3lstext"></p>
				<h3>
					Sorry! The page you are looking could not be access <br>
					<a href="${_currConText}/${fns:getloginPageUrl()}">回到首页</a>
				</h3>
			</div>
		</div>
	</div>
</body>
</html>