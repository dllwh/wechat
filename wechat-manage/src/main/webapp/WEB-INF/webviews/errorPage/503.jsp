<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<title>HTTP 503 错误 – 服务不可用 (Service unavailable)</title>

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

html, body {
	font-size: 100%;
	font-family: 'Montserrat', sans-serif;
	background: url(${_currConText}/static/image/503_bg.jpg) no-repeat 0px 0px;
	background-size: cover;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	-ms-background-size: cover;
	background-attachment: fixed;
}

h1, h2, h3, h4, h5, h6, input, p, a, select {
	font-family: 'Montserrat', sans-serif;
	margin: 0;
}

ul, label {
	margin: 0;
	padding: 0;
}

body a:hover, body a {
	text-decoration: none;
}

input[type="submit"], a {
	transition: .5s ease-in;
	-webkit-transition: .5s ease-in;
	-moz-transition: .5s ease-in;
	-o-transition: .5s ease-in;
	-ms-transition: .5s ease-in;
}

.main {
	padding: 3em 0 1em;
	position: relative;
}

.main h1 {
	text-align: center;
	color: #34b3f5;
	font-size: 3em;
	text-transform: uppercase;
	letter-spacing: 2px;
	font-weight: bold;
}

.agileinfo_404_main {
	position: absolute;
	top: 5%;
	left: 0;
	width: 100%;
}

.w3_agile_main {
	text-align: center;
	margin: 3em 0 0;
}

.w3_agile_main h2 {
	font-size: 1.5em;
	color: #fff;
}

.agile_404 {
	position: relative;
}

.w3_agile_main h3 {
	font-size: 8em;
	color: #fff;
	font-family: 'Alfa Slab One', cursive;
}

.w3_agile_main h3 span {
	color: #f3a02f;
}

.agile_404_pos {
	position: absolute;
	top: 5.5%;
	left: 39%;
}

.w3_agile_main p {
	font-size: 14px;
	color: #f3a02f;
	text-transform: capitalize;
	margin: 1em 0 2em;
}
</style>
</head>
<body>
	<div class="main">
		<canvas id="myCanvas"></canvas>
		<div class="agileinfo_404_main">
			<h1>Service unavailable</h1>
			<div class="w3_agile_main">
				<h2>Service.... Could not find it</h2>
				<p>For some reason the page you requested could not be found on
					Our server.</p>
				<div class="agile_404 w3layouts">
					<div class="agile_404_pos">
						<h3>
							5<span>0</span>3<img src="${_currConText}/static/image/503_1.png" alt=" " />
						</h3>
					</div>
					<img src="${_currConText}/static/image/503_3.png" alt=" " class="w3l" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>