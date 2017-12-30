<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/easyUI.jsp"%>
<link rel="stylesheet" href="${_currConText }/plug-in/assets/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${_currConText }/plug-in/font/css/font-awesome.min.css" />
<link rel="stylesheet" href="${_currConText }/static/css/sample/style.css" />
<title>行政区域</title>
<style>
.no-padding {
	padding: 2px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-2 no-padding">
				<div class="panel panel-default">
					<div class="panel-heading">区域目录</div>
					<div class="panel-body" id="treePanel" style="overflow: auto;">
						<ul id="areaTree"></ul>
					</div>
				</div>
			</div>
			<div class="col-sm-10 no-padding">
				<div class="panel panel-default">
					<div class="panel-heading">区域信息</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-6 form-inline pull-left">
								<div class="form-group">
									<input class="form-control" placeholder="请输入查询关键字" style="width: 220px;" />
								</div>
								<div class="form-group">
									<a class="btn btn-primary" onclick="searchClick()">
										<i class="fa fa-search"></i>&nbsp;查询
									</a>
								</div>
							</div>
							<div class="col-md-6">
								<div class="btn-toolbar pull-right">
									<div class="btn-group">
										<a class="btn btn-default" onclick="refreshClick();">
											<i class="fa fa-refresh"></i>&nbsp;刷新
										</a>
										<a class="btn btn-default" onclick="addClick()">
											<i class="fa fa-plus"></i>&nbsp;新增
										</a>
										<a class="btn btn-default" onclick="editClick()">
											<i class="fa fa-pencil-square-o icon-white"></i>&nbsp;编辑
										</a>
										<a class="btn btn-default red" onclick="enableClick()">
											<i class="fa fa-check"></i>&nbsp;启用
										</a>
										<a class="btn btn-default" onclick="disableClick()">
											<i class="fa fa-close"></i>&nbsp;禁用
										</a>
										<a class="btn btn-danger" onclick="deleteClick()">
											<i class="fa fa-trash-o"></i>&nbsp;删除
										</a>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<table id="dataGrid"
									class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">
												<label>
													<input type="checkbox" class="ace" />
													<span class="lbl"></span>
												</label>
											</th>
											<th>区域代码</th>
											<th class="hidden-480">区域名称</th>
											<th>层级</th>
											<th class="hidden-480">可用</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${dict:getProvinceArea()}" var="sysArea">
											<tr>
												<td></td>
												<td>${sysArea.areaCode}</td>
												<td>${sysArea.areaName}</td>
												<td>
													<c:choose>
														<c:when test="${sysArea.areaLevel eq 0}">
															<span class="label label-primary">国家</span>
														</c:when>
														<c:when test="${sysArea.areaLevel eq 1}">
															<span class="label label-info">省级行政区</span>
														</c:when>
														<c:when test="${sysArea.areaLevel eq 2}">
															<span class="label label-success">地市</span>
														</c:when>
														<c:when test="${sysArea.areaLevel eq 3}">
															<span class="label label-warning">区县</span>
														</c:when>
														<c:when test="${sysArea.areaLevel eq 4}">
															<span class="label label-danger">乡</span>
														</c:when>
														<c:when test="${sysArea.areaLevel eq 5}">
															<span class="label label-inverse">镇</span>
														</c:when>
														<c:otherwise>
															<span class="label">-</span>
														</c:otherwise>
													</c:choose>
												</td>
												<td></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	
	$(function() {
		initialPage();
		getTree();
		getGrid();
	});
	
	function initialPage(){
		$("#treePanel").css('height', $(window).height()-54);
		$(window).resize(function() {
			$("#treePanel").css('height', $(window).height()-54);
			// $("#dataGrid").height($(window).height() - 108);
		});
	}
	
	function getTree(){
		$('#areaTree').tree({
			animate : true, // 定义节点在展开或折叠的时候是否显示动画效果。
			lines : true,// 定义是否显示树控件上的虚线。
			dnd : false,// 定义是否启用拖拽功能。
			onDblClick : function(node){// 在用户双击一个节点的时候触发。
				
			}, onClick : function(node){// 在用户点击一个节点的时候触发。
				var leaf = $('#areaTree').tree('isLeaf', node.target);
				if(leaf){//如果为子节点
					
				} else {
					
				}
			}, onContextMenu : function(e, node){
				// 在右键点击节点的时候触发
				e.preventDefault();
			}
		});
	}
	
	function getGrid(){
		
	} 
	
	function searchClick(){
		alert('searchClick');
	}
	
	function refreshClick(){
		alert('refreshClick');
	}
	
	function addClick(){
		alert("addClick");
	}
	
	function editClick(){
		alert("editClick");
	}
	
	function deleteClick(){
		alert("deleteClick");
	}
	
	function disableClick(){
		alert("disableClick");
	}
	
	function enableClick(){
		alert("enableClick");
	}
</script>

</html>