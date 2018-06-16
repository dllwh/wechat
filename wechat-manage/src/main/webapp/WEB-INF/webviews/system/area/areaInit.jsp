<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/bootstrap.jsp"%>
<link href="${_currConText }/static/css/common.css?v=4.1.0" rel="stylesheet">
<link href="${_currConText }/static/css/sample/animate.css" rel="stylesheet">
<link href="${_currConText }/static/css/sample/style.css?v=4.1.0" rel="stylesheet">
<title>${_currProject }---行政区域</title>
<style type="text/css">
.no-padding {
	padding: 2px;
}

div#zTreeRightMenuContainer {
	position: absolute;
	visibility: hidden;
	top: 0;
	background-color: #555;
	text-align: left;
	padding: 2px;
}

div#rMenu ul {
	margin: 0px;
	padding: 0px;
}

div#rMenu ul li {
	font-size: 12px;
	line-height: 20px;
	margin: 0px;
	padding: 0px;
	cursor: pointer;
	list-style: none outside none; 
	background-color: #DFDFDF;
	border-bottom: 1px solid #fff;
	padding-left: 3px;
}

div#rMenu ul li:hover {
	background: #eee;
}

div#zTreeRightMenuContainer a {
	cursor: pointer;
	list-style: none outside none;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<%@ include file="/WEB-INF/webviews/system/area/areaList.jsp"%>
	</div>
	
	<%-- 右键菜单--%>
	<div id="zTreeRightMenuContainer">
		<ul class="dropdown-menu" role="menu" level="0">
			<li id="expandSon">
				<a tabindex="-1" onclick="treeController.expandSon()">展开子节点</a>
			</li>
			<li id="collapse">
				<a tabindex="-1" onclick="treeController.collapse()">折叠子节点</a>
			</li>
			<li id="collapseAll">
				<a tabindex="-1" onclick="treeController.collapseAll()">全部折叠(C)</a>
			</li>
			<li class="divider"></li>
			<li>
				<a tabindex="-1" onclick="treeController.refreshNode()">刷新(R)</a>
			</li>
			<li class="divider"></li>
			<li id="addTreeNode">
				<a tabindex="-1" onclick="treeController.addTreeNode()">
					<i class="fa fa-plus"></i>&nbsp;新增(N)
				</a>
			</li>
			<li id="removeTreeNode">
				<a tabindex="-1" onclick="treeController.removeTreeNode()">
					<i class="fa fa-trash-o"></i>&nbsp;删除(D)
				</a>
			</li>
			<li id="editTreeNode">
				<a tabindex="-1" onclick="treeController.editTreeNode()"> 
					<i class="fa fa-pencil-square-o"></i>&nbsp;编辑(M)
				</a>
			</li>
			<li id="contentTreeNode">
				<a tabindex="-1" onclick="treeController.contentTreeNode()">
					<i class="fa fa-eye"></i>&nbsp;查看(V)
				</a>
			</li>
		</ul>
	</div>
	
	<%-- 添加节点窗口 --%>
	<div class="modal fade bs-example-modal-md" id="addTreeNodeMode" tabindex="-1" role="dialog" 
		aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">新增区域</h4>
				</div>
				<div class="modal-body">
					<form method="post" id="createNodeForm" role="form"
						class="form form-horizontal responsive">
						
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="sysAreaController.addSubmitClick()">
						保存
					</button>
					<button type="button" class="btn btn-white" data-dismiss="modal">
						关闭
					</button>
				</div>
			</div>
		</div>
	</div>
	
	<%-- 编辑节点窗口 --%>
	<div class="modal fade bs-example-modal-md" id="editTreeNodeMode" tabindex="-1" role="dialog" 
		aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">编辑区域</h4>
				</div>
				<div class="modal-body">
					<form method="post" id="editNodeForm" role="form"
						class="form form-horizontal responsive">
						
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="sysAreaController.addSubmitClick()">
						保存
					</button>
					<button type="button" class="btn btn-white" data-dismiss="modal">
						关闭
					</button>
				</div>
			</div>
		</div>
	</div>
	
</body>
<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
<script type="text/javascript">
	var setting = {
		view : {//表示tree的显示状态
			dblClickExpand : false,// 双击节点时，是否自动展开父节点的标识
			showLine : true,// 是否显示节点之间的连线
			showIcon : true,// 是否显示节点的图标
			showTitle : true,// 是否显示节点的 title 提示信息
			selectedMulti : false,// 设置是否允许同时选中多个节点
			txtSelectedEnable : true, //是否允许可以选择 zTree DOM 内的文本 
			fontCss : setFontCss
		},
		data : {//表示tree的数据格式
			simpleData : {
				enable : true, //使用简单数据模式
				idKey : "id",//节点数据中保存唯一标识的属性名称
				pIdKey : "pId",//节点数据中保存其父节点唯一标识的属性名称
				rootPId : "0" //用于修正根节点父节点数据 默认值：null
			}
		},
		check : {//表示tree的节点在点击时的相关设置
			enable : false,// 设置 zTree 的节点上是否显示 checkbox / radio
			chkStyle : "radio",// 勾选框类型(checkbox 或 radio）
			chkboxType : {
				"Y" : "p",
				"N" : "s"
			},
			radioType : "level"
		},
		callback : {//回调函数  
			onClick : function(e, treeId, treeNode) { //定义节点单击事件回调函数
				var node = zTree.getSelectedNodes()[0];
				if (node) {
					if(node.isParent){
						$("#seacherKey").val("");
						$("#parentCode").val(node.id);
						sysAreaController.searchClick();
					} else {
						
					}
				} else {
					dialogAlert("请选择要操作的节点", "warn");
				}
			},
			onRightClick : function(event, treeId, treeNode) {//定义节点右键单击事件回调函数
				if (treeNode && !treeNode.noR) { // 判断点击的是tree节点
					var top = $(window).scrollTop();
					// 选中tree节点
					zTree.selectNode(treeNode);
					if (treeNode.getParentNode()) {
						var isParent = treeNode.isParent;
						if(isParent){//非叶子节点
							treeController.showRMenu("firstNode", event.clientX, event.clientY+top);//处理位置，使用的是绝对位置
						} else {//叶子节点
							treeController.showRMenu("secondNode", event.clientX, event.clientY+top);
						}
					} else { 
						treeController.showRMenu("root", event.clientX, event.clientY+top);//根节点
					}
				} else { // 判断点击了tree的“空白”部分，即没有点击到tree节点上
					zTree.cancelSelectedNode();
				}
			},
			onAsyncSuccess : function(event, treeId, treeNode) {//异步加载成功后执行的方法
				dialogLoading(false);
			},
			onAsyncError : function(event, treeId, treeNode){ // 异步加载出现错误
				dialogMsg("数据加载失败，请重新刷新页面", "error");
			}
		},
		async : {
			enable : true,
			url : "${_currConText }/sysAreaView.shtml?getTreeList",
			dataType : 'json',
			autoParam : [ "id" ]
		},
	};

	$(function() {
		initialPage();
		getTree();
		getGrid();
	});
	
	function initialPage() {
		$("#treePanel").css('height', $(window).height() - 54);
		$(window).resize(function() {
			$("#treePanel").css('height', $(window).height() - 54);
			// $("#dataGrid").height($(window).height() - 108);
		});
	}

	function getGrid() {
		//先销毁表格  
		$("#areaTable").bootstrapTable('destroy');  
		
		$("#areaTable").bootstrapTableEx({
			toolbar: '#roleTableToolbar',
			url :"${_currConText}/sysAreaView.shtml?getList",
			showColumns: false, 
			showRefresh: false, 
			columns : [ { 
				field: "cb", 
				checkbox : true, 
				hidden:true
			}, {
				field : 'areaCode',
				title : '区域代码'
			}, {
				field : 'areaName',
				align: "center",
				title : '区域名称'
			}, {
				field : 'areaLevel',
				align: "center",
				title : '行政区别',
				formatter : function(value,rowData,rowIndex){
					if(value == 0){
						return "<span class='label label-primary'>国家</span>";
					} else if(value == 1){
						return "<span class='label label-info'>省级行政区</span>";
					} else if(value == 2){
						return "<span class='label label-success'>地市</span>";
					} else if(value == 3){
						return "<span class='label label-warning'>区县</span>";
					} else if(value == 4){
						return "<span class='label label-danger'>乡</span>";
					} else if(value == 5){
						return "<span class='label label-inverse'>镇</span>";
					} else {
						return "<span class='label'>---</span>";
					}
				}
			}, {
				field : 'shortName',
				title : '简称'
			}, {
				field : 'areaUrl',
				title : '访问地址'
			}],
			onClickRow : function(row){ // 当用户点击某一行的时候触发
				
			},
			onCheck: function (row) {
				
			},
			onUncheck : function(){
				var selected = $('#areaTable').bootstrapTable('getSelections');
				if (selected.length == 0) {
					initButton();
				}
			}
		});
	}

	function initButton(){
		
	}
	
	// 获取查询的参数
	function queryParams(params) {
		var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			rows : params.pageSize, //页面大小
			page : params.pageNumber,
			sortName: params.sortName,
			sortOrder: params.sortOrder,
			areaCode:$("#seacherCode").val(),
			areaName:$("#seacherName").val(),
			parentId:$("#parentCode").val()
		};
		return temp;
	}
	var zTree;
	function getTree() {
		$.ajax({
			type : "post",
			url : "${_currConText }/sysAreaView.shtml?getTreeList",
			dataType : "json",
			async : true,
			success : function(zNodes) {
				zTree = $.fn.zTree.init($("#areaTree"), setting, zNodes);
			}
		});
	}
	
	//鼠标按下事件   隐藏菜单
	function onBodyMouseDown(event) {
		if (!(event.target.id == "zTreeRightMenuContainer" || $(event.target)
				.parents("#zTreeRightMenuContainer").length > 0)) {
			$("#zTreeRightMenuContainer").css({
				"visibility" : "hidden"
			});
		}
	}

	/** 右键菜单 操作函数 */
	var treeController = {
		node:null,	
		check : function(){
			// var node = zTree.getSelectedNodes()[0];
			var selectNodes = zTree.getSelectedNodes();
			if (selectNodes) {
				node = zTree.getSelectedNodes()[0];
				return true;
			} else {
				dialogAlert("请选择要操作的节点", "warn");
				return false;
			}
		},
		showRMenu:function (type, x, y) {//显示右键菜单 
			$("#zTreeRightMenuContainer ul").show();
			if (type == "root") {
				
			} else if(type == "firstNode"){
				
			} else if(type == "secondNode"){
				
			}
			// 设置右键菜单的位置、可见
			$("#zTreeRightMenuContainer").css({
				"top" : y + "px",
				"left" : x + "px",
				"visibility" : "visible"
			}); 
			
			// 在当前页面绑定 鼠标事件
			$("body").bind("mousedown", onBodyMouseDown);
		},
		hideRMenu : function() {//隐藏右键菜单  
			// 设置右键菜单不可见
			$("#zTreeRightMenuContainer").css({
				"visibility" : "hidden"
			});  
			// 取消绑定
			$("body").unbind("mousedown", onBodyMouseDown);
		},
		addTreeNode : function(){ // 右键菜单 添加节点
			if(this.check()){
				this.hideRMenu();
				$("#createNodeForm")[0].reset();
				$('#addTreeNodeMode').modal('show');
			}
		},
		editTreeNode : function(){ // 右键菜单 编辑节点
			if(this.check()){
				this.hideRMenu();
				$('#editTreeNodeMode').modal('show');
			}
		},
		contentTreeNode : function(){ //右键菜单 内容节点
			if(this.check()){
				this.hideRMenu();
			}
		},
		removeTreeNode : function(){ //右键菜单 删除节点
			if(this.check()){
				this.hideRMenu();
				var id = node.getParentNode().id;
				// 一、判断该节点是否是文件夹节点，并且检查是否有子节点
				// 1、移除节点
				// 2、设置父节点为文件夹节点
				// 二、该节点为不是文件夹节点
				// 1、移除节点
				// 2、设置父节点为文件夹节点
				// 三、刷新节点数据
				refreshNode(id);
			}
		},
		refreshNode : function(id){ // 右键菜单 刷新节点
			if(this.check()){
				zTree.reAsyncChildNodes(node, "refresh");
				this.hideRMenu();
			}
		},
		refreshParentNode : function(id){ // 右键菜单 刷新父节点
			if(this.check()){
				var pNode = node.getParentNode();
				if (pNode) {
					refreshNode(pNode.id);
				} else {
					refreshNode("0");
				}
			}
		},
		expandSon : function(){ //右键菜单 展开所有子节点  
			if(this.check()){
				this.hideRMenu();
				var selectNodes = zTree.getSelectedNodes(); 
				zTree.expandNode(selectNodes[0], true, true, null);  
			}
		},
		collapse : function(){ //右键菜单 折叠子节点  
			if(this.check()){
				this.hideRMenu();
				var selectNodes = zTree.getSelectedNodes();
				zTree.expandNode(selectNodes[0], false, null, null);
			}
		},
		collapseAll : function(){ //右键菜单 全部折叠
			this.hideRMenu();
			zTree.expandAll(false);
		}
	}

	var sysAreaController = {
		id : "areaTable", //表格id
		setItem : null, //选中的条目
		check : function(){
			var selected = $('#' + this.id).bootstrapTable('getSelections');
			if (dllwh.isNullOrEmpty(selected)) {
				dialogAlert("您没有选中任何数据项！", "warn");
				return false;
			}
			if (selected.length > 1) {
				dialogAlert("请选择要操作的数据", "warn");
				return false;
			} else {
				sysAreaController.setItem = selected[0];
				return true;
			}
		},
		searchClick : function() { // 搜索
			getGrid();
		},
		addClick : function() { // 打开添加
			if(this.check()){
				
			}
		},
		addSubmitClick : function(){
			
		},
		editClick : function() { // 编辑窗口
			if(this.check()){
			}
		},
		deleteClick : function() {// 删除 
			if(this.check()){
				
			}
		}
	};
</script>
</html>