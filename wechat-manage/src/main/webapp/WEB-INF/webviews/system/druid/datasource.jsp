<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/bootstrap.jsp"%>
<link rel="stylesheet" href="${_currConText }/static/css/sample/animate.css" />
<link rel="stylesheet" href="${_currConText }/static/css/sample/style.css" />
<title>${_currProject }---数据库说明文档</title>
<style>

table {
	width: 100%; 
} 

.detail {
	border: 0;
	background-color: #C1D1A3;
	font-size: 12px;
	margin-bottom: 10px;
	color: #DDEDA5;
}
</style>
</head>
<body class="gray-bg">
	<div class="row border-bottom white-bg dashboard-header">
		<%-- 数据库表  --%>
		<table style="margin-bottom: 8px">
			<tbody>
				<tr>
					<td>[</td>
				</tr>
				<tr>
					<td><a href="#cms6">CMS表</a></td>
				</tr>
				<tr>
					<td>]</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="wrapper wrapper-content animated fadeInRight">
		<c:forEach begin="1" end="10" varStatus="varStatus">
			<a name="cms${varStatus.index}"></a>
			<div class="row">
				<div class="col-sm-12" >
					<div class="ibox">
						<div class="ibox-title">
							<h5>表名${varStatus.index}</h5>
							<div class="ibox-tools">
								<a class="collapse-link">
									<i class="fa fa-chevron-up"></i>
								</a>
								<a class="dropdown-toggle" data-toggle="dropdown">
									<i class="fa fa-wrench"></i>
								</a>
								<ul class="dropdown-menu dropdown-user">
									<li>
										<a>选项1</a>
									</li>
									<li>
										<a>选项2</a>
									</li>
								</ul>
							</div>
						</div>
						<div class="ibox-content">
							<table cellpadding="5" cellspacing="1" class="detail">
								<tbody>
									<tr>
										<td height="34" colspan="3" bgcolor="#458B74">
											<table class="col-sm-12"  border="0" cellspacing="1" cellpadding="1">
												<tbody>
													<tr>
														<td width="71%">
															说明：
															<span style="float: right;">
																<a href="javascript:scroll(0,0)">Top</a>
															</span>
														</td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
									<tr>
										<td class="col-sm-2" height="28">字段名</td>
										<td class="col-sm-5">说明描述</td>
										<td class="col-sm-5">具体参数</td>
									</tr>
									<tr height="24" bgcolor="#FFFFFF">
										<td><b></b></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td height="28" colspan="3">
											<b>索引：</b>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>	
	</div>
</body>
<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
</html>