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
<title>${_currProject }---菜单管理</title>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-xs-2">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>菜单列表</h5>
						<div class="ibox-content">
							<ul id="menuTree" ></ul>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-xs-10">
				<div class="row">
					<div class="col-xs-12">
						<div class="ibox float-e-margins">
							<div class="ibox-content">
								<div class="row">
									<div class="col-sm-12 search_style">
										<div class="title_names">搜索查询</div>
										<form role="form" class="form-inline">
											<div class="form-group col-sm-3">
												<label for="menuName" class="sr-only">菜单名称</label>
												<input type="text" placeholder="输入菜单名称" id="sMenuName" 
													class="form-control">
											</div>
											<div class="form-group col-sm-3">
												<label for="menuUrl" class="sr-only">规则URL</label>
												<input type="text" placeholder="输入规则URL" id="sMenuUrl" 
													class="form-control">
											</div>
										</form>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12" style="padding-top: 5px;text-align: center;">
										<button id="btnSearch" class="btn btn-primary" type="button">
												<i class="fa fa-search"></i> 搜索
											</button>
											<button id="resetSearch" class="btn" type="reset">
												<i class="fa fa-refresh">&nbsp;</i> 重置
											</button>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="hidden-xs" id="menuTableToolbar" role="group">
											<button type="button" class="btn btn-primary addBut" 
												onclick="sysMenuController.openAddDig()">
												<span class="fa fa-plus"> 创建</span>
											</button>
											<shiro:hasPermission name="sysMenuOperate/saveMenu.shtml">
												<button type="button" class="btn btn-info editBut" 
													onclick="sysMenuController.openChangeDig()">
													<span class="fa fa-pencil"> 编辑</span>
												</button>
											</shiro:hasPermission>
											<shiro:hasPermission name="sysMenuOperate/delMenu.shtml">
												<button type="button" class="btn btn-danger delBut" 
													onclick="sysMenuController.openDeleleDig()">
													<span class="fa fa-trash"> 删除</span>
												</button>
											</shiro:hasPermission>
											<shiro:hasPermission name="sysMenuOperate/updateMenu.shtml">
												<button type="button" class="btn btn-warning editBut"
													onclick="sysMenuController.editDisable()">
													<span class="fa fa-warning"> 禁止编辑</span>
												</button>
												<button type="button" class="btn btn-danger delBut"
													onclick="sysMenuController.deleteClick()">
													<span class="fa fa-warning"> 禁止删除</span>
												</button>
												<button type="button" class="btn btn-warning visibleButton editBut"
													onclick="sysMenuController.isVisibleClick()">
												</button>
											</shiro:hasPermission>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="ibox float-e-margins">
						<div class="ibox-content">
							<table id="menuTable"
								class="table table-striped table-bordered table-hover">
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/webviews/common/context/easyUI.jsp"%>
<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
<script type="text/javascript">
	$(function(){
		//调用函数，初始化表格  
		initTable(); 
		//当点击查询按钮的时候执行  
		$("#btnSearch").bind("click", sysMenuController.searchClick);
		$("#resetSearch").bind("click", sysMenuController.resetSearch);
		initButton();
		
		$("#menuTree").tree({
			url:"${_currConText}/menuView.shtml?menuTreeList",
			animate : true, // 定义节点在展开或折叠的时候是否显示动画效果。
			lines : true,// 定义是否显示树控件上的虚线。
			dnd : false,// 定义是否启用拖拽功能。
			onClick : function(node){// 在用户点击一个节点的时候触发。
				var leaf = $(this).tree('isLeaf', node.target);
				var concept_id = node.attributes.concept_id;
				var tree_id=node.attributes.tree_id;
				var flag = false;
				if(leaf){//如果为子节点
					
				} else {//如果为父节点
					
				}
			},
			onContextMenu : function(e, node){// 在右键点击节点的时候触发
				e.preventDefault();
			},
			onLoadError:function(node, data){// 在数据加载失败的时候触发
				
            },
			onLoadSuccess:function(node, data){// 在数据加载成功以后触发。
				$('#menuTree').tree('expandAll');//展开所有的节点
            }
        });
	});
	
	function initButton(){
		$(".addBut").attr("disabled", false);
		$(".visibleButton").html('<span class="fa fa-warning">审核</span>');
	}
	
	function initTable(){
		//先销毁表格  
		$("#menuTable").bootstrapTable('destroy');  
		
		$("#menuTable").bootstrapTableEx({
			toolbar: '#menuTableToolbar',
			url :"${_currConText}/menuView.shtml?getList",
			columns : [ { 
				field: "cb", 
				checkbox : true, 
				hidden:true
			},{
				field: 'menuName',
				title: '菜单名称'
			},{
				field: 'iconClass',
				title: '图标',
				formatter : function(value,rowData,rowIndex){
					return '<i class="'+value+'"></i>';
				}
			},{
				field: 'menuUrl',
				title: '规则URL'
			},{
				field: 'type',
				title: '菜单类型',
				formatter : function(value,rowData,rowIndex){
					if(value == 0){
						return "<span class='label label-primary'>目录</span>";
					} else if(value == 1){
						return "<span class='label label-success'>菜单</span>";
					} else if(value == 2){
						return "<span class='label label-warning'>按钮</span>";
					} else {
						return "<span class='label label-success'>菜单</span>";
					}
				}
			},{
				field: 'sequence',
				title: '权重'
			},{
				field: 'allowEdit',
				title: '是否允许编辑',
				formatter : function(value,rowData,rowIndex){
					if(value == 1){
						return "<span class='label label-info radius'>允许</span>";
					} else {
						return "<span class='label label-danger radius'>不允许</span>";
					}
				}
			},{
				field: 'allowDelete',
				title: '是否允许删除',
				formatter : function(value,rowData,rowIndex){
					if(value == 1){
						return "<span class='label label-info radius'>允许</span>";
					} else {
						return "<span class='label label-danger radius'>不允许</span>";
					}
				}
			},{
				field: 'ifVisible',
				title: '是否有效',
				formatter : function(value,rowData,rowIndex){
					if(value == 1){
						return "<span class='label label-info radius'>已启用</span>";
					} else {
						return "<span class='label label-danger radius'>禁用</span>";
					}
				}
			},{
				field: 'createTime',
				title: '创建日期',
				visible:false,
				formatter : function(value,rowData,rowIndex){
					return dllwh.genStrDateTime(value);
				}
			},{
				field: 'updateTime',
				visible:false,
				title: '修改日期',
				formatter : function(value,rowData,rowIndex){
					return dllwh.genStrDateTime(value);
				}
			}
			],
			onCheck: function (row) {
				if(row.allowEdit == 0 ){// 不允许编辑
					$(".editBut").attr("disabled", true);
				} else {
					$(".editBut").attr("disabled", false);
				}
				
				if(row.allowDelete == 0 ){// 不允许删除
					$(".delBut").attr("disabled", true);
				} else {
					$(".delBut").attr("disabled", false);
				}
				if(row.ifVisible == 1 ){//
					$(".visibleButton").html('<span class="fa fa-warning">禁用</span>');
				} else {
					$(".visibleButton").html('<span class="fa fa-warning">启用</span>');
				}
			},
			onUncheck : function(){
				var selected = $('#menuTable').bootstrapTable('getSelections');
				if (selected.length == 0) {
					initButton();
				}

			}
		});
	}
	// 获取查询的参数
	function queryParams(params) {
		var temp = { 
			rows : params.pageSize, //页面大小
			page : params.pageNumber
		};
		return temp;
	}
	/**
	 * 菜单管理
	 */
	var sysMenuController = {
		id : "menuTable", // 表格id
		setItem : null, // 选中的条目
		table : null,
		layerIndex : -1,
		check : function() { // 检查是否选中
			var selected = $('#' + this.id).bootstrapTable('getSelections');
			if (selected == undefined || selected == "" || selected == 'null' || selected == 'undefined') {
				// "您没有选中任何数据项！
				return false;
			}
			
			if (selected.length == 0) {
				return false;
			} else {
				sysMenuController.setItem = selected[0];
				return true;
			}
		},
		openAddDig : function() {// 点击添加
	
		},
		openChangeDig : function() {// 点击修改按钮
			if (this.check()) {
			}
		},
		openDeleleDig : function() {// 点击删除按钮
			if (this.check()) {
			}
		},
		isVisibleClick : function(){// 使用状态
			if(this.check()){
				var id = this.setItem.id;
				var ifVisible = this.setItem.ifVisible;
				if(ifVisible == 1){
					ifVisible = 0;
				} else {
					ifVisible = 1
				}
				
				dialogConfirm("您确认要操作此数据吗?", function() {
					$.ajax({
						url : "${_currConText }/sysMenuOperate/updateMenu.shtml?visibleState",
						type : "POST",
						data : {
							id : id,
							ifVisible:ifVisible
						},
						success : function(result) {
							if (result.success) {
								dialogMsg("删除成功");
								initTable();
							} else {
								dialogMsg(result.msg, "error");
							}
						}
					});
				});
			}
		},
		editDisable : function(){// 编辑状态
			
		},
		deleteClick : function(){// 删除状态
			if(this.check()){
				var id = this.setItem.id;
				dialogConfirm("您确认要操作此数据吗?", function() {
					$.ajax({
						url : "${_currConText }/sysMenuOperate/updateMenu.shtml?delDisable",
						type : "POST",
						data : {
							id : id
						},
						success : function(result) {
							if (result.success) {
								dialogMsg("删除成功");
								initTable();
							} else {
								dialogMsg(result.msg, "error");
							}
						}
					});
				});
			}
		},
		searchClick : function() { // 搜索角色
			initTable();
		},
		resetSearch : function() { // 重置搜索条件
			$("#menuName").val("");
			$("#menuUrl").val("");
			initTable();
		}
	};
	
	/**
	 * 菜单详情对话框
	 */
	var MenuInfoDlg = {
		showMenuSelectTree : function() { // 显示父级菜单选择的树
	
		}
	}
	
	/**
	 * 新建菜单
	 */
	sysMenuController.addSubmitClick = function() {
	
	}
	
	/**
	 * 修改菜单
	 */
	sysMenuController.editSubmitClick = function() {
	
	}
</script>
</html>