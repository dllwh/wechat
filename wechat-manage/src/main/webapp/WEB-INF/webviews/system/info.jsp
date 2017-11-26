<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<!--[if lt IE 9]>
	<script type="text/javascript" src="${_currConText }/static/js/html5.js"></script>
	<script type="text/javascript" src="${_currConText }/static/js/respond.min.js"></script>
	<script type="text/javascript" src="${_currConText }/static/js/PIE_IE678.js"></script>
	<![endif]-->
<link type="text/css" rel="stylesheet"
	href="${_currConText }/static/css/system/H-ui.css" />
<link type="text/css" rel="stylesheet"
	href="${_currConText }/static/css/system/H-ui.admin.css" />
<link type="text/css" rel="stylesheet"
	href="${_currConText }/static/css/font/font-awesome.min.css" />
<!--[if IE 7]>
	<link href="${_currConText }/static/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
	<![endif]-->
<title>${_currProject}</title>
</head>
<body>
	<div class="pd-20" style="padding-top: 20px;">
		<p class="f-20 text-success">
			欢迎使用综合信息 <span class="f-14">V1.0</span>管理系统！
		</p>
		<p>登录次数：18</p>
		<login:getUserIp var="userIp" />
		<login:getCurrentTime var="currentTime"
			pattern="yyyy年MM月dd日  HH时mm分ss秒" />

		<p>上次登录IP：${userIp} 上次登录时间：${currentTime }</p>

		<table class="table table-border table-bordered table-bg mt-20">
			<thead>
				<tr>
					<th colspan="2" scope="col">服务器信息</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th width="200">服务器计算机名</th>
					<td><span id="lbServerName"></span></td>
				</tr>
				<tr>
					<td>服务器IP地址</td>
					<td></td>
				</tr>
				<tr>
					<td>服务器域名</td>
					<td></td>
				</tr>
				<tr>
					<td>服务器端口</td>
					<td></td>
				</tr>
				<tr>
					<td>服务器IIS版本</td>
					<td></td>
				</tr>
				<tr>
					<td>本文件所在文件夹</td>
					<td></td>
				</tr>
				<tr>
					<td>服务器操作系统</td>
					<td></td>
				</tr>
				<tr>
					<td>系统所在文件夹</td>
					<td></td>
				</tr>
				<tr>
					<td>服务器脚本超时时间</td>
					<td></td>
				</tr>
				<tr>
					<td>服务器的语言种类</td>
					<td></td>
				</tr>
				<tr>
					<td>服务器当前时间</td>
					<td></td>
				</tr>
				<tr>
					<td>服务器IE版本</td>
					<td></td>
				</tr>
				<tr>
					<td>服务器上次启动到现在已运行</td>
					<td></td>
				</tr>
				<tr>
					<td>逻辑驱动器</td>
					<td></td>
				</tr>
				<tr>
					<td>CPU 总数</td>
					<td></td>
				</tr>
				<tr>
					<td>CPU 类型</td>
					<td></td>
				</tr>
				<tr>
					<td>虚拟内存</td>
					<td></td>
				</tr>
				<tr>
					<td>当前程序占用内存</td>
					<td></td>
				</tr>
				<tr>
					<td>当前Session数量</td>
					<td></td>
				</tr>
				<tr>
					<td>当前SessionID</td>
					<td></td>
				</tr>
				<tr>
					<td>当前系统用户名</td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
	<footer>
		<p>感谢jQuery、UEditor、My97DatePicker、Font Awesome、jquery.fileupload
		<br>Copyright &copy;2015 H-ui.admin v2.1.1 All Rights Reserved.<br>
	</footer>
</body>
</html>