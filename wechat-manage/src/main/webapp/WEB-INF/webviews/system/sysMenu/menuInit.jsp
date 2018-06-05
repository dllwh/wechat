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
										<div class="form-group center">
											<div class="row">
												<div class="form-group col-sm-3">
												<label for="menuName">菜单名称</label>
												<input type="text" placeholder="输入菜单名称" id="sMenuName" class="form-control">
											</div>
											<div class="form-group col-sm-2">
												<label for="menuType">菜单类型</label>
												<select class="form-control" id="sMenuType" name="menuType">
													<option value="">请选择</option>
													<option value="0">目录</option>
													<option value="1">菜单</option>
													<option value="2">按钮</option>
												</select>
											</div>
											<div class="form-group col-sm-3" id="paraentMenu"></div>
											<div class="form-group col-sm-3">
												<label for="isVisible">是否有效</label>
												<select class="form-control" id="sVisible" name="isVisible">
													<option value="">请选择</option>
													<option value="0">无效</option>
													<option value="1">有效</option>
												</select>
											</div>
											</div>
										</div>
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
												<button type="button" class="btn btn-warning editOperate"
													onclick="sysMenuController.editDisable()">
													<span class="fa fa-warning"> 禁止编辑</span>
												</button>
												<button type="button" class="btn btn-danger delOperate"
													onclick="sysMenuController.deleteClick()">
													<span class="fa fa-warning"> 禁止删除</span>
												</button>
												<button type="button" class="btn btn-warning visibleButton"
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
	
	<%-- 添加窗口 --%>
	<div class="modal fade bs-example-modal-lg" id="addMenuMode" tabindex="-1" role="dialog" aria-hidden="true"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">菜单创建</h4>
				</div>
				<div class="modal-body">
					<form method="post" id="createMenuForm" role="form"
						class="form form-horizontal responsive">
						<div class="form-group">
							<label for="content" class="control-label col-xs-12 col-sm-2">
								菜单类型
							</label>
							<div class="col-xs-12 col-sm-8">
								<label class="radio-inline">
									<input type="radio" name="type" value="0"/> 目录
								</label>
								<label class="radio-inline">
									<input type="radio" name="type" value="1" checked/> 
									菜单
								</label>
								<label class="radio-inline">
									<input type="radio" name="type" value="2"/> 按钮
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="content" class="control-label col-xs-12 col-sm-2">
								上级菜单
							</label>
							<div class="col-xs-12 col-sm-8">
								<select name="parentCode" class="easyui-combotree" id="parentCode"
									style="width:200px;" data-options="url:'${_currConText}/menuView.shtml?menuTreeList'">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="menuName" class="control-label col-xs-12 col-sm-2">
								标题
							</label>
							<div class="col-xs-12 col-sm-8">
								<input type="text" class="form-control" name="menuName" 
									placeholder="请输入标题" aria-required="true" aria-invalid="true" 
									class="error" autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="menuUrl" class="control-label col-xs-12 col-sm-2">
								规则URL
							</label>
							<div class="col-xs-12 col-sm-8">
								<input type="text" class="form-control" name="menuUrl" 
									placeholder="请输入url" aria-required="true" aria-invalid="true" 
									class="error" autocomplete="off">
								<span class="help-block m-b-none text-danger">
									<i class="fa fa-info-circle"></i>
									<strong>只能包含英文、/、.等字符,并且以.shtml结尾</strong> 
								</span>
							</div>
						</div>
						<div class="form-group">
							<label for="sequence" class="control-label col-xs-12 col-sm-2">
								权重
							</label>
							<div class="col-xs-12 col-sm-8">
								<input type="text" class="form-control easyui-numberspinner" 
									name="sequence" required="required" value="1"
									data-options="min:0,max:100"  
									placeholder="请输入排序号" aria-required="true" aria-invalid="true" 
									class="error" autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="iconClass" class="control-label col-xs-12 col-sm-2">
								图标
							</label>
							<div class="col-xs-12 col-sm-8">
								<div class="input-group m-b">
									<input type="text" class="form-control" name="iconClass" 
									placeholder="请输入图标" aria-required="true" aria-invalid="true" 
									class="error" autocomplete="off"  value="fa fa-leaf" />
									<span class="input-group-btn">
										<button type="button" class="btn btn-primary" disabled>搜索图标</button> 
									</span>
								</div>
								<code style="margin-top: 4px; display: block;">获取图标：http://fontawesome.io/icons/</code>
							</div>
						</div>
						<div class="form-group">
							<label for="iconClass" class="control-label col-xs-12 col-sm-2">
								备注
							</label>
							<div class="col-xs-12 col-sm-8">
									<textarea name="remark" class="form-control"></textarea>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="sysMenuController.addSubmitClick()">
						保存
					</button>
					<button type="button" class="btn btn-white" data-dismiss="modal">
						关闭
					</button>
				</div>
			</div>
		</div>
	</div>
	
	<%-- 编辑窗口 --%>
	<div class="modal fade bs-example-modal-lg" id="editMenuMode" tabindex="-1" role="dialog" aria-hidden="true"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">菜单更新</h4>
				</div>
				<div class="modal-body">
					<input id="editParentCode" type="hidden"/>
					<input id="editMenuType" type="hidden"/>
					<form method="post" id="editMenuForm" role="form"
						class="form form-horizontal responsive">
							<input type="hidden" name="id">
						<div class="form-group">
							<label for="content" class="control-label col-xs-12 col-sm-2">
								菜单类型
							</label>
							<div class="col-xs-12 col-sm-8">
								<label class="radio-inline">
									<input type="radio" name="type" value="0"/> 目录
								</label>
								<label class="radio-inline">
									<input type="radio" name="type" value="1" checked/> 
									菜单
								</label>
								<label class="radio-inline">
									<input type="radio" name="type" value="2"/> 按钮
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="content" class="control-label col-xs-12 col-sm-2">
								上级菜单
							</label>
							<div class="col-xs-12 col-sm-8">
								<select name="parentCode" class="easyui-combotree" id="mParentCode"
									style="width:200px;" data-options="url:'${_currConText}/menuView.shtml?menuTreeList'">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="menuName" class="control-label col-xs-12 col-sm-2">
								标题
							</label>
							<div class="col-xs-12 col-sm-8">
								<input type="text" class="form-control" name="menuName" 
									placeholder="请输入标题" aria-required="true" aria-invalid="true" 
									class="error" autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="menuUrl" class="control-label col-xs-12 col-sm-2">
								规则URL
							</label>
							<div class="col-xs-12 col-sm-8">
								<input type="text" class="form-control" name="menuUrl" 
									placeholder="请输入url" aria-required="true" aria-invalid="true" 
									class="error" autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="sequence" class="control-label col-xs-12 col-sm-2">
								权重
							</label>
							<div class="col-xs-12 col-sm-8">
								<input type="text" class="form-control easyui-numberspinner" 
									name="sequence" required="required" value="1"
									data-options="min:0,max:100"  
									placeholder="请输入排序号" aria-required="true" aria-invalid="true" 
									class="error" autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="iconClass" class="control-label col-xs-12 col-sm-2">
								图标
							</label>
							<div class="col-xs-12 col-sm-8">
								<div class="input-group m-b">
									<input type="text" class="form-control" name="iconClass" 
									placeholder="请输入图标" aria-required="true" aria-invalid="true" 
									class="error" autocomplete="off"  value="fa fa-dot" />
									<span class="input-group-btn">
										<button type="button" class="btn btn-primary" disabled>搜索图标</button> 
									</span>
								</div>
								<code style="margin-top: 4px; display: block;">获取图标：http://fontawesome.io/icons/</code>
							</div>
						</div>
						<div class="form-group">
							<label for="iconClass" class="control-label col-xs-12 col-sm-2">
								备注
							</label>
							<div class="col-xs-12 col-sm-8">
									<textarea name="remark" class="form-control"></textarea>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="sysMenuController.editSubmitClick()">
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
		
		
		$("#addMenuMode input[name=type]").each(function(){
			$(this).click(function(){
				var discount = $(this).val();
				$("#parentCode").combotree("clear");
				if(discount == 0){ //目录
					$(".form-group").show();
					$("[name='menuUrl']").parent().parent().hide();
				}
				
				if(discount == 1){ // 菜单
					$(".form-group").show();
				}
				
				if(discount == 2){// 按钮
					$(".form-group").show();
					$("[name='sequence']").parent().parent().parent().hide();
					$("[name='iconClass']").parent().parent().parent().hide();
				}
			});
		});
		
		$("#editMenuMode input[name=type]").each(function(){
			$(this).click(function(){
				var discount = $(this).val();
			
				if($("#editMenuType").val() != discount){
					$("#mParentCode").combotree("clear");
				} else {
					$("#mParentCode").combotree("reload");
					var obj = $("#editParentCode").val();
					if(dllwh.isNotNullOrEmpty(obj)){
						$("#mParentCode").combotree("setValue", obj);	
					}
				}
				
				if(discount == 0){ //目录
					$(".form-group").show();
					$("[name='menuUrl']").parent().parent().hide();
				}
				
				if(discount == 1){ // 菜单
					$(".form-group").show();
				}
				
				if(discount == 2){// 按钮
					$(".form-group").show();
					$("[name='sequence']").parent().parent().parent().hide();
					$("[name='iconClass']").parent().parent().parent().hide();
				}
			});
		});
		
		$("select[name=menuType]").each(function(){
			$(this).change(function(){
				var discount = $(this).val();
				if(discount == 0){ // 目录
					$("#paraentMenu").html("");	
				}
				if(discount == 1){ // 目录
					var a = '<label for="isParentCode">父目录</label>';
					a +='<select class="form-control" name="parentCode" id="sParentCode">';
					a +='<option value="">请选择</option>';
					a +='<c:forEach items="${fns:menuList(0)}" var="menu">';
					a +='<option value="${menu.id }">${menu.menuName}</option>		';
					a +='</c:forEach>';
					a +='</select>';
					$("#paraentMenu").html(a);	
				} 
				
				if(discount == 2){// 菜单 
					var a = '<label for="isParentCode">父菜单</label>';
					a +='<select class="form-control" name="parentCode" id="sParentCode">';
					a +='<option value="">请选择</option>';
					a +='<c:forEach items="${fns:menuList(1)}" var="menu">';
					a +='<option value="${menu.id }">${menu.menuName}</option>		';
					a +='</c:forEach>';
					a +='</select>';
					$("#paraentMenu").html(a);
				}
				
			});
		});
		
		$('#parentCode').combotree({  
			onBeforeSelect: function(node) {  
				var mType = $("#addMenuMode input[type='radio']:checked").val();
				var isLeaf = $(this).tree('isLeaf', node.target)
				if(isLeaf){
					if(mType == 2 ){
						if(node.id == -1){
							dialogAlert("不可选", "info");
							return false; 
						} else {
							return true;	
						}
					} else {
						return true; 
					}
				} else {
					if(mType == 2 ){
						dialogAlert("不可选", "info");
						return false; 	
					} else {
						return true; 
					}
				}
			}
		});
		
		$('#mParentCode').combotree({  
			onBeforeSelect: function(node) {  
				var mType = $("#editMenuMode input[type='radio']:checked").val();
				var isLeaf = $(this).tree('isLeaf', node.target)
				if(isLeaf){
					if(mType == 2 ){
						if(node.id == -1){
							dialogAlert("不可选", "info");
							return false; 
						} else {
							return true;	
						}
					} else {
						return true; 
					}
				} else {
					if(mType == 2 ){
						dialogAlert("不可选", "info");
						return false; 	
					} else {
						return true; 
					}
				}
			}
		});
		
		$("#createMenuForm").validate({
			rules : {
				menuName : {
					required : true,
					minlength:2,
					maxlength:16,
					isChina:true,
				},
				menuUrl : {
					required : true,
					actionMethod:true
				}
			},
			messages : {  
				menuName :{
					isChina:"只允许填入中文字符"
				}
			}
		});
		
		$("#editMenuForm").validate({
			rules : {
				menuName : {
					required : true,
					minlength:2,
					maxlength:16,
					isChina:true,
				},
				menuUrl : {
					required : true,
					actionMethod:true
				}
			},
			messages : {  
				menuName :{
					isChina:"只允许填入中文字符"
				}
			}
		});
		
	});
	
	function initButton(){
		$(".addBut").attr("disabled", false);
		$(".editBut").attr("disabled", false);
		$(".editOperate").attr("disabled", false);
		$(".delBut").attr("disabled", false);
		$(".delOperate").attr("disabled", false);
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
				visible:false,
				formatter : function(value,rowData,rowIndex){
					if(value == 1){
						return "<span class='label label-info radius'>允许</span>";
					} else {
						return "<span class='label label-danger radius'>不允许</span>";
					}
				}
			},{
				field: 'allowDelete',
				visible:false,
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
					$(".editOperate").html('<span class="fa fa-warning">允许编辑</span>');
				} else {
					$(".editBut").attr("disabled", false);
					$(".editOperate").html('<span class="fa fa-warning">禁止编辑</span>');
				}
				
				if(row.allowDelete == 0 ){// 不允许删除
					$(".delBut").attr("disabled", true);
					$(".delOperate").html('<span class="fa fa-warning">允许删除</span>');
				} else {
					$(".delBut").attr("disabled", false);
					$(".delOperate").html('<span class="fa fa-warning">禁止删除</span>');
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
			page : params.pageNumber,
			menuName:$("#sMenuName").val().trim(),
			isVisible:$("#sVisible").val().trim(),
			parentCode:$("#sParentCode").val(),
			type:$("#sMenuType").val().trim()
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
				dialogAlert("您没有选中任何数据项！", "warn");
				return false;
			}
			
			if (selected.length == 0) {
				return false;
			} else {
				this.setItem = selected[0];
				return true;
			}
		},
		openAddDig : function() {// 点击添加
			$("#createMenuForm")[0].reset();
			$('#addMenuMode').modal('show');
			$(".form-group").show();
			$("#parentCode").combotree("reload");
			$("#parentCode").combotree("clear");
		},
		openChangeDig : function() {// 点击修改按钮
			if (this.check()) {
				$("#editMenuForm").form("load", this.setItem);
				$("#editMenuForm input[name=id]").val(this.setItem.id);
				$("#editParentCode").val(this.setItem.parentCode);
				$("#editMenuType").val(this.setItem.type);
				
				$(".form-group").show();
				if(this.setItem.type == 0 ){// 目录
					$("#editMenuForm input[name='menuUrl']").parent().parent().hide();
				}
				
				if(this.setItem.type == 2){// 按钮
					$("#editMenuForm input[name='sequence']").parent().parent().parent().hide();
					$("#editMenuForm input[name='iconClass']").parent().parent().parent().hide();
				}
				
				$('#editMenuMode').modal('show');
			}
		},
		openDeleleDig : function() {// 点击删除按钮
			if (this.check()) {
				var id = this.setItem.id;
				
				dialogConfirm("您确认要删除该资源吗?", function() {
					$.ajax({
						url : "${_currConText }/sysMenuOperate/delMenu.shtml",
						type : "POST",
						data : {
							id : id,
							random:Math.random()
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
		isVisibleClick : function(){// 使用状态
			if(this.check()){
				var id = this.setItem.id;
				var ifVisible = this.setItem.ifVisible;
				if(ifVisible == 1){
					ifVisible = 0;
				} else {
					ifVisible = 1;
				}
				
				dialogConfirm("您确认要操作此数据吗?", function() {
					$.ajax({
						url : "${_currConText }/sysMenuOperate/updateMenu.shtml?visibleState",
						type : "POST",
						data : {
							id : id,
							ifVisible:ifVisible,
							random:Math.random()
						},
						success : function(result) {
							if (result.success) {
								dialogMsg("操作成功");
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
			if(this.check()){
				var id = this.setItem.id;
				var allowEdit = this.setItem.allowEdit;
				if(allowEdit == 1){
					allowEdit = 0;
				} else {
					allowEdit = 1;
				}
				
				dialogConfirm("您确认要操作此数据吗?", function() {
					$.ajax({
						url : "${_currConText }/sysMenuOperate/updateMenu.shtml?editDisable",
						type : "POST",
						data : {
							id : id,
							allowEdit:allowEdit,
							random:Math.random()
						},
						success : function(result) {
							if (result.success) {
								dialogMsg("操作成功");
								initTable();
							} else {
								dialogMsg(result.msg, "error");
							}
						}
					});
				});
			}
		},
		deleteClick : function(){// 删除状态
			if(this.check()){
				var id = this.setItem.id;
				var allowDelete = this.setItem.allowDelete;
				if(allowDelete == 1){
					allowDelete = 0;
				} else {
					allowDelete = 1;
				}
				
				dialogConfirm("您确认要操作此数据吗?", function() {
					$.ajax({
						url : "${_currConText }/sysMenuOperate/updateMenu.shtml?delDisable",
						type : "POST",
						data : {
							id : id,
							allowDelete:allowDelete,
							random:Math.random()
						},
						success : function(result) {
							if (result.success) {
								dialogMsg("操作成功");
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
			$("#sMenuName").val("");
			$("#sVisible").val("");
			$("#sParentCode").val("");
			$("#sMenuType").val("");
			$("#paraentMenu").html("");	
			initTable();
		},
		addSubmitClick : function() { // 新建菜单
			var mType = $("input[type='radio']:checked").val();
			$("#createMenuForm").ajaxSubmit({
				url:'${_currConText }/sysMenuOperate/createMenu.shtml',
				beforeSubmit : function(){
					var isValid = $("#createMenuForm").valid();
					if(mType == 0){
						
					}
					
					if(mType == 1){
						isValid = $("#createMenuForm").validate().element($("[name='menuUrl']"));
					}
					
					if(mType == 2){
						isValid = $("#createMenuForm").validate().element($("[name='menuUrl']"));
					} 
					return isValid;
				},
				success : function(result) {
					if (result.success) {
						dialogMsg("添加成功");
						initTable();
						initButton();
						$("#addMenuMode").modal('hide');
					} else {
						dialogMsg(result.msg, {
							icon : 2
						});
					}
				}
			});
		},
		editSubmitClick : function() { // 修改菜单
			var mType = $("input[type='radio']:checked").val();
			$("#editMenuForm").ajaxSubmit({
				url:'${_currConText }/sysMenuOperate/saveMenu.shtml',
				beforeSubmit : function(){
					var isValid = $("#editMenuForm").valid();
					if(isValid){
						if(mType == 0){
							
						}
						
						if(mType == 1){
							isValid = $("#editMenuForm").validate().element($("#editMenuForm [name='menuUrl']"));
						}
						
						if(mType == 2){
							isValid = $("#editMenuForm").validate().element($("[name='menuUrl']"));
						} 
					}
					return isValid;
				},
				success : function(result) {
					if (result.success) {
						dialogMsg("更新成功");
						initTable();
						initButton();
						$("#editMenuMode").modal('hide');
					} else {
						dialogMsg(result.msg, {
							icon : 2
						});
					}
				}
			});
		}
	};
</script>
</html>