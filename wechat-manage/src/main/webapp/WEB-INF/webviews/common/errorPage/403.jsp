<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<title>禁止访问提示页面</title>

<style type="text/css">
html,body,div,h1,h2,h3,h4,h5,h6,a,img {
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

body,
p,
h1,
h2,
h3,
h4,
h5,
h6 {
	font-family: 'Lato', sans-serif;
}

body {
	background: url(${_currConText}/static/image/403_bg.jpg) no-repeat;
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
	font-size: 4em;
	color: #fff;
	font-weight: 500;
	margin-top: .8em;
	font-family: 'Pinyon Script', cursive;
	letter-spacing: 3px;
}

.w3-main {
	width: 53%;
	margin: 3em auto;
	-webkit-box-shadow: -5px 20px 30px 8px rgba(0, 0, 0, 0.45);
	-moz-box-shadow: -5px 20px 30px 8px rgba(0, 0, 0, 0.45);
	box-shadow: -5px 20px 30px 8px rgba(0, 0, 0, 0.45);
	background: rgba(87, 175, 150, 0.29);
}

.agile-info {
	padding: 3em 0;
}

.agile-info h2 {
	font-size: 14em;
	color: #fff;
	line-height: 1;
	letter-spacing: 2px;
	font-weight: 300;
	text-shadow: 0 2px 2px rgba(0, 0, 0, 0.6);
}

.agile-info h3 {
	font-size: 3em;
	color: #fff;
	letter-spacing: 3px;
	text-shadow: 0 2px 2px rgba(0, 0, 0, 0.6);
}

.agile-info p {
	font-size: .9em;
	color: #fff;
	text-transform: capitalize;
	letter-spacing: 5px;
	margin: 1em 0 4em;
	font-weight: 300;
}


.agile-info a {
	font-size: 1em;
	text-transform: uppercase;
	color: #fff;
	padding: .7em 1.8em;
	letter-spacing: 2px;
	display: inline-block;
	background: #E91E63;
	transition: 0.5s all;
	-webkit-transition: 0.5s all;
	-o-transition: 0.5s all;
	-moz-transition: 0.5s all;
	-ms-transition: 0.5s all;
}

.agile-info a:hover {
	background: #efbf31;
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
		<h1>forbidden error page</h1>
	</div>
	<div class="w3-main">
		<div class="agile-info">
			<h2>403</h2>
			<h3>SORRY</h3>
			<p>The Page You're Looking for Was forbidden</p>
			<a href="${_currConText}/${fns:getloginPageUrl()}">
				<i class="fa fa-angle-double-left" aria-hidden="true"></i>
				回到首页
			</a>
		</div>
	</div>
</body>
</html>