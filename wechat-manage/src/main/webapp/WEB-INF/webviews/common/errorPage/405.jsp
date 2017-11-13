<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<title>Method not allowed</title>
<style type="text/css">
html, body, div, h1, h2, h3, h4, h5, h6, img {
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
	padding-right: 0.1em;
}

body p, h1, h2, h3, h4, h5, h6 {
	font-family: 'Ropa Sans', sans-serif;
}

body {
	background: url(${_currConText}/static/image/405_bg.jpg) no-repeat;
	background-size: cover;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	-ms-background-size: cover;
	text-align: center;
	background-position: center;
	background-attachment: fixed;
}

.header h1 {
	font-size: 3em;
	text-transform: uppercase;
	letter-spacing: 8px;
	color: #fff;
	font-weight: 400;
	margin-top: 1.1em;
}

.w3-main {
	background: #fff;
	width: 53%;
	margin: 3em auto;
	-webkit-box-shadow: -2px 11px 32px -13px rgba(0, 0, 0, 0.45);
	-moz-box-shadow: -2px 11px 32px -13px rgba(0, 0, 0, 0.45);
	box-shadow: 20px 25px 41px -9px rgba(0, 0, 0, 0.45);
}

.agile-info {
	padding: 60px 0px;
}

.agile-info h2 {
	font-size: 12em;
	color: black;
	line-height: 1;
	font-weight: 300;
	letter-spacing: 20px;
}

.agile-info h3 {
	font-size: 2.5em;
	text-transform: uppercase;
	color: #000;
	font-weight: 500;
	letter-spacing: 12px;
}

.agile-info p {
	font-size: 1em;
	color: rgba(0, 0, 0, 0.71);
	text-transform: capitalize;
	letter-spacing: 1px;
	margin-bottom: 3em;
	margin-top: 2em;
}

.agile-info a {
	font-size: 1em;
	text-transform: uppercase;
	color: white;
	width: 18%;
	padding: 12px 0px;
	letter-spacing: 2px;
	display: inline-block;
	background: #962258;
	font-family: 'Ropa Sans', sans-serif;
	transition: 0.5s all;
	-webkit-transition: 0.5s all;
	-o-transition: 0.5s all;
	-moz-transition: 0.5s all;
	-ms-transition: 0.5s all;
}

.agile-info a:hover {
	background: #3859a0;
	transition: 0.5s all;
	-webkit-transition: 0.5s all;
	-o-transition: 0.5s all;
	-moz-transition: 0.5s all;
	-ms-transition: 0.5s all;
}
</style>
</head>
<body>
	<div class="header">
		<h1>Method not allowed</h1>
	</div>
	<div class="w3-main">
		<div class="agile-info">
			<h3>SORRY</h3>
			<h2>
				4<img src="${_currConText}/static/image/confused.gif" alt="image">5
			</h2>
			<p>
				The specified HTTP method is not allowed for the requested resource.
			</p>
			<a href="${_currConText}/${fns:getloginPageUrl()}">回到首页</a>
		</div>
	</div>
</body>
</html>