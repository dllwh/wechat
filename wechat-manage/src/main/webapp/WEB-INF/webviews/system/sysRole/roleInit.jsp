<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@ include file="/WEB-INF/webviews/common/include/assets.jsp"%>
<title>角色管理</title>
</head>
<body>
	<div class="topPanel">
		<div class="toolbar">
			<div class="btn-group">
				<a class="btn btn-primary" onclick="$.reload()"><span
					class="glyphicon glyphicon-refresh"></span></a>
			</div>
			<div class="btn-group">
				<a id="NF-add" authorize="yes" class="btn btn-primary dropdown-text"
					onclick="btn_add()"><i class="fa fa-plus"></i>新建角色</a>
			</div>
			<div class="operate">
				<ul class="nav nav-pills">
					<li class="first">已选中<span>1</span>项
					</li>
					<li><a id="NF-edit" authorize="yes" onclick="btn_edit()"><i
							class="fa fa-pencil-square-o"></i>修改角色</a></li>
					<li><a id="NF-delete" authorize="yes" onclick="btn_delete()"><i
							class="fa fa-trash-o"></i>删除角色</a></li>
					<li><a id="NF-Details" authorize="yes" onclick="btn_details()"><i
							class="fa fa-search-plus"></i>查看角色</a></li>
				</ul>
				<a href="javascript:;" class="close"></a>
			</div>
			<!--         <script>$('.toolbar').authorizeButton()</script> -->
		</div>
		<div class="search">
			<table>
				<tr>
					<td>
						<div class="input-group">
							<input id="txt_keyword" type="text" class="form-control"
								placeholder="角色名称/角色编号" style="width: 200px;"> <span
								class="input-group-btn">
								<button id="btn_search" type="button" class="btn  btn-primary">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="gridPanel">
		<table id="gridList"></table>
	</div>

</body>
</html>