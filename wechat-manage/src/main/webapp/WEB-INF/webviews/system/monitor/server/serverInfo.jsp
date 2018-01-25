<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<title>系统监控</title>
<style type="text/css">
			@charset "utf-8";
			* {
				word-wrap: break-word
			}
			
			html,
			body,
			th,
			td {
				margin: 0;
				padding: 0
			}
			
			html,
			body {
				*position: static;
				height: 100%;
			}
			
			body,
			th,
			td {
				font-family: "Microsoft Yahei", "Hiragino Sans GB", "Helvetica Neue", Helvetica, tahoma, arial, Verdana, sans-serif, "WenQuanYi Micro Hei", "\5B8B\4F53";
				font-size: 12px;
				color: #333;
				-webkit-font-smoothing: antialiased;
				-moz-font-smoothing: antialiased
			}
			
			body {
				width: 100%;
				line-height: 1.6
			}
			
			table {
				width: 100%;
				empty-cells: show;
				background-color: transparent;
				border-collapse: collapse;
				border-spacing: 0
			}
			
			table th {
				text-align: left;
				font-weight: normal
			}
			/*带水平线*/
			
			.table th {
				font-weight: bold
			}
			
			.table th,
			.table td {
				padding: 8px;
				line-height: 20px
			}
			
			.table td {
				text-align: left
			}
			
			.table tbody+tbody {
				border-top: 2px solid #ddd
			}
			
			.table {
				background-color: #fff
			}
			/*带横向分割线*/
			
			.table-border {
				border-top: 1px solid #ddd
			}
			
			.table-border th,
			.table-border td {
				border-bottom: 1px solid #ddd
			}
			/*th带背景*/
			
			.table-bg thead th {
				background-color: #F5FAFE
			}
			/*带外边框*/
			
			.table-bordered {
				border: 1px solid #ddd;
				border-collapse: separate;
				*border-collapse: collapse;
				border-left: 0
			}
			
			.table-bordered th,
			.table-bordered td {
				border-left: 1px solid #ddd
			}
			
			.table-border.table-bordered {
				border-bottom: 0
			}
		</style>
	</head>

	<body>
		<div style="padding:20px;padding-top:20px;">
			<table class="table table-border table-bordered table-bg" style="margin-top:20px">
				<thead>
					<tr>
						<th colspan="2" scope="col">服务器信息</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="200">服务器计算机名</th>
						<td><span id="lbServerName">${osName}</span></td>
					</tr>
					<tr>
						<td>服务器IP地址</td>
						<td>${hostIP}</td>
					</tr>
					<tr>
						<td>服务器操作系统 </td>
						<td>${osType }&nbsp;&nbsp;&nbsp;${osVersion }</td>
					</tr>
					<tr>
						<td>服务器的语言种类 </td>
						<td>${Language }</td>
					</tr>
					<tr>
						<td>服务器当前时间 </td>
						<td>${currentTime }</td>
					</tr>
					<tr>
						<td>服务器上次启动到现在已运行 </td>
						<td>${runningTime }</td>
					</tr>
					<tr>
						<td>CPU 总数 </td>
						<td></td>
					</tr>
					<tr>
						<td>CPU 类型 </td>
						<td></td>
					</tr>
					<tr>
						<td>虚拟内存 </td>
						<td></td>
					</tr>
					<tr>
						<td>当前程序占用内存 </td>
						<td>${JVMtotalMem}</td>
					</tr>
					<tr>
						<td>当前Session数量 </td>
						<td>8</td>
					</tr>
					<tr>
						<td>当前SessionID </td>
						<td>${SessionID }</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>