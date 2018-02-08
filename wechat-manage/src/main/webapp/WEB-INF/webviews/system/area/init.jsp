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
									<a class="btn btn-primary" onclick="sysAreaController.searchClick()">
										<i class="fa fa-search"></i>&nbsp;查询
									</a>
								</div>
							</div>
							<div class="col-md-6">
								<div class="btn-toolbar pull-right">
									<div class="btn-group">
										<a class="btn btn-default" onclick="sysAreaController.refreshClick();">
											<i class="fa fa-refresh"></i>&nbsp;刷新
										</a>
										<a class="btn btn-default" onclick="sysAreaController.addClick()">
											<i class="fa fa-plus"></i>&nbsp;新增
										</a>
										<a class="btn btn-default" onclick="sysAreaController.editClick()">
											<i class="fa fa-pencil-square-o icon-white"></i>&nbsp;编辑
										</a>
										<a class="btn btn-default red" onclick="sysAreaController.enableClick()">
											<i class="fa fa-check"></i>&nbsp;启用
										</a>
										<a class="btn btn-default" onclick="sysAreaController.disableClick()">
											<i class="fa fa-close"></i>&nbsp;禁用
										</a>
										<a class="btn btn-danger" onclick="sysAreaController.deleteClick()">
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
	var setting = {
		view: {//表示tree的显示状态
			dblClickExpand: false,// 双击节点时，是否自动展开父节点的标识
			showLine: true,// 是否显示节点之间的连线
			showIcon: true,// 是否显示节点的图标
			showTitle:true,// 是否显示节点的 title 提示信息
			selectedMulti: false,// 设置是否允许同时选中多个节点
			txtSelectedEnable: true //是否允许可以选择 zTree DOM 内的文本
		},
		data: {//表示tree的数据格式
			simpleData: {
				enable:true, //使用简单数据模式
				idKey: "id",//节点数据中保存唯一标识的属性名称
				pIdKey: "pId",//节点数据中保存其父节点唯一标识的属性名称
				rootPId: "" //用于修正根节点父节点数据 默认值：null
			}
		},
		check : {//表示tree的节点在点击时的相关设置
			enable: false,// 设置 zTree 的节点上是否显示 checkbox / radio
			chkStyle: "checkbox",// 勾选框类型(checkbox 或 radio）
			chkboxType: { "Y": "p", "N": "s" } 
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				if (treeNode.isParent) {// 如果不是叶子结点，结束
					alert(1);
					return false;
				} else {
					alert(2);
					return true;
				}
			}
		},async : {
			enable: true,
			type: "get",
			url: "${_currConText }/sysAreaView.shtml?getTreeList",
			autoParam: ["areaCode"]
		},
	};
	
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
	
	
	
	function getGrid(){
		
	}
	
	var ztree;
	function getTree(){
		ztree = $.fn.zTree.init($("#areaTree"), setting);
	}
	
	var sysAreaController = {
		data : {
			parentCode : '0'
		},
		load : function (){ // 加载数据
			
		},
		getArea : function(parentCode) { // 
			
		},
		searchClick : function (){
			alert('searchClick');
		},
		refreshClick : function (){
			
		},
		addClick : function (){
			
		},
		editClick : function (){
			
		},
		deleteClick : function (){
			
		},
		disableClick :function (){
			
		},
		enableClick : function (){
			
		},
		created : function() {
			
		},
		save : function() {// 新增区域
			
		},
		edit : function() {// 编辑区域
			
		},
		remove : function() {
			
		}
	};
</script>

</html>