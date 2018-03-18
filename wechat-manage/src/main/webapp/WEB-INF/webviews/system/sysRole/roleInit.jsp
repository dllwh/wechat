<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/bootstrap.jsp"%>
<link href="${_currConText }/static/css/common.css?v=4.1.0" rel="stylesheet">
<link href="${_currConText }/static/css/sample/animate.css" rel="stylesheet">
<link href="${_currConText }/static/css/sample/style.css?v=4.1.0" rel="stylesheet">
<head>
<title>角色管理</title>
</head>
<title>${_currProject }-角色管理</title>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-xs-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>角色管理</h5>
						<div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="row">
							<div class="col-sm-12 search_style">
								<div class="title_names">搜索查询</div>
								<div class="form-group">
									<div class="input-group">
										<input id="txtSearchKey" type="text" 
											class="input form-control" placeholder="输入角色名称">
										<span class="input-group-btn">
											<button id="btnSearch" class="btn btn-primary"
												type="button">
												<i class="fa fa-search"></i> 搜索
											</button>
										</span>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="hidden-xs" id="roleTableToolbar" role="group">
									<shiro:hasPermission name="roleOperate/addRole.shtml">
										<button type="button" class="btn btn-primary addBut" 
											onclick="RoleController.addClickFun()">
											<span class="fa fa-plus"> 创建</span>
										</button>
									</shiro:hasPermission>
									<button type="button" class="btn btn-success addBut"
										onclick="RoleController.showClickFun()">
										<span class="fa fa-eye"> 查看</span>
									</button>
									<shiro:hasPermission name="roleOperate/saveRole.shtml">
										<button type="button" class="btn btn-info opBut" 
											onclick="RoleController.editClickFun()">
											<span class="fa fa-pencil"> 修改</span>
										</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="roleOperate/delRole.shtml">
										<button type="button" class="btn btn-danger opBut"
											onclick="RoleController.delClickFun()">
											<span class="fa fa-trash-o"> 删除</span>
										</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="roleView/roleAccessConfig.shtml">
										<button data-toggle="dropdown" type="button"
											class="btn btn-info dropdown-toggle opBut">
											授权操作<span class="caret"></span>
										</button>
										<ul class="dropdown-menu">
											<li>
												<a onclick="RoleController.assignClick()">角色授权</a>
											</li>
											<li>
												<a onclick="RoleController.UserClickFun()">角色用户</a>
											</li>
										
											<li class="divider"></li>
											<li class="visibleButton"></li>
										</ul>
									</shiro:hasPermission>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<table id="roleTable" 
							class="table table-striped table-bordered table-hover">
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%-- 添加窗口 --%>
	<div class="modal fade bs-example-modal-md" id="addRoleMode" tabindex="-1" role="dialog" 
		aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">角色创建</h4>
				</div>
				<div class="modal-body">
					<form method="post" id="createForm" role="form"
						class="form form-horizontal responsive">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="roleName">角色名称：</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="roleName" 
									placeholder="角色名称" aria-required="true" aria-invalid="true"
									aria-invalid="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">角色代码：</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="roleCode" 
									placeholder="角色代码" aria-required="true" aria-invalid="true" 
									class="error">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">角色分类：</label>
							<div class="col-sm-9">
								<select class="form-control" name="categoryCode">
									<option value="0">业务角色</option>
									<option value="1">系统角色</option>
								</select>
							</div>
						</div>
						<div class="form-group draggable">
							<label class="col-sm-2 control-label">有效性：</label>
							<div class="col-sm-9">
								<label class="radio-inline">
									<input type="radio" value="1" name="ifVisible">有效
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<i class="fa fa-info-circle"></i>注：选中则启用该角色
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="sequence" class="control-label col-xs-12 col-sm-2">
								排序
							</label>
							<div class="col-xs-12 col-sm-9">
								<input type="number" value="1" name="sequence" min="1">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">角色描述：</label>
							<div class="col-sm-9">
								<textarea class="form-control" rows="3" name=remark></textarea>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="RoleController.addSubmitClick()">
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
	<div class="modal fade bs-example-modal-md" id="editRoleMode" tabindex="-1" role="dialog" 
		aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">角色信息更新</h4>
				</div>
				<div class="modal-body">
					<form method="post" id="editForm" role="form"
						class="form form-horizontal responsive">
						<input type="hidden" name="id">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="roleName">角色名称：</label>
							<div class="col-sm-6">
							<input type="text" class="form-control" name="roleName" 
								placeholder="角色名称" aria-invalid="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">角色代码：</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="roleCode" 
									placeholder="角色代码" aria-required="true" aria-invalid="true" 
									class="error">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">角色分类：</label>
							<div class="col-sm-9">
								<select class="form-control" name="categoryCode">
									<option value="0">业务角色</option>
									<option value="1">系统角色</option>
								</select>
							</div>
						</div>
						<div class="form-group draggable">
							<label class="col-sm-2 control-label">有效性：</label>
							<div class="col-sm-9">
								<label class="radio-inline">
									<input type="radio" value="1" name="ifVisible">有效
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<i class="fa fa-info-circle"></i>注：选中则启用该角色
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="sequence" class="control-label col-xs-12 col-sm-2">
								排序
							</label>
							<div class="col-xs-12 col-sm-9">
								<input type="number" value="1" name="sequence" min="1">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">角色描述：</label>
							<div class="col-sm-9">
								<textarea class="form-control" rows="3" name=remark></textarea>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="RoleController.editSubmit()">
						保存
					</button>
					<button type="button" class="btn btn-white" data-dismiss="modal">
						关闭
					</button>
				</div>
			</div>
		</div>
	</div>
	
	<%-- 查看窗口 --%>
	<div class="modal fade" id="showRoleMode" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">角色信息</h4>
				</div>
				<div class="modal-body">
					<table class="lms" style="width: 100%; height: 100%;">
						<tr>
							<th>角色名称</th><td><span id="s_roleName"></span></td>
							<th>角色代码</th><td><span id="s_roleCode"></span></td>
						</tr>
						<tr>
							<th>角色分类</th>
							<td><span id="s_categoryCode"></span></td>
							<th>使用状态</th>
							<td><span id="s_isVisible"></span></td>
						</tr>
						<tr>
							<th>是否允许编辑</th>
							<td><span id="s_allowEdit"></span></td>
							<th>是否允许删除</th>
							<td><span id="s_allowDelete"></span></td>
						</tr>
						<tr>
							<th>创建人</th>
							<td><span id="s_creator"></span></td>
							<th>创建日期</th>
							<td><span id="s_createTime"></span></td>
						</tr>
						<tr>
							<th>修改人</th>
							<td><span id="s_modifier"></span></td>
							<th>修改日期</th>
							<td><span id="s_updateTime"></span></td>
						</tr>
						<tr>
							<th>说明</th>
							<td colspan="3"><span id="s_remark"></span></td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">
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
	$(function() {
		//调用函数，初始化表格  
		initTable(); 
		//当点击查询按钮的时候执行  
		$("#btnSearch").bind("click", initTable); 
		
		initButton();
		
		$("#createForm").validate({
			rules : {
				roleName : {  
					required : true,
					minlength:4,
					maxlength:16,
					isChina:true
				},
				roleCode : {  
					required : true,
					minlength:2,
					maxlength:16,
					isEnglish:true,
					remote:function(){
					}
				}  
			},
			messages : {  
				roleName : {  
					required : "角色名称不能为空" 
				}, 
				roleCode : {  
					required : "角色代码不能为空" 
				}  
			}
		});
		
		$("#editForm").validate({
			rules : {
				roleName : {  
					required : true,
					minlength:4,
					maxlength:16,
					isChina:true
				},
				roleCode : {  
					required : true,
					minlength:2,
					maxlength:16,
					isEnglish:true,
					remote:function(){
					}
				}  
			},
			messages : {  
				roleName : {  
					required : "角色名称不能为空" 
				}, 
				roleCode : {  
					required : "角色代码不能为空" 
				}  
			}
		});
	});
	
	function initButton(){
		// 初始化  禁止操作
		$("#roleTableToolbar button").attr("disabled", true);
		$(".addBut").attr("disabled", false);
	}
	function initTable(){
		//先销毁表格  
		$("#roleTable").bootstrapTable('destroy');  
		
		$("#roleTable").bootstrapTableEx({
			toolbar: '#roleTableToolbar',
			url :"${_currConText}/roleView.shtml?getList",
			columns : [ { 
				field: "cb", 
				checkbox : true, 
				hidden:true
			}, {
				field : 'roleName',
				title : '角色名称'
			}, {
				field : 'roleCode',
				align: "center",
				title : '角色编码'
			}, {
				field : 'allowEdit',
				visible:false,
				width:"8%",
				title : '编辑状态',
				formatter : function(value,rowData,rowIndex){
					if(value == 1){
						return "<span class='label label-info radius'>允许</span>";
					} else {
						return "<span class='label label-danger radius'>不允许</span>";
					}
				}
			}, {
				field : 'allowDelete',
				width:"8%",
				visible:false,
				title : '删除状态',
				formatter : function(value,rowData,rowIndex){
					if(value == 1){
						return "<span class='label label-info radius'>允许</span>";
					} else {
						return "<span class='label label-danger radius'>不允许</span>";
					}
				}
			},{
				field: 'categoryCode',
				align: "center",
				width:"10%",
				sortable: true, // 开启排序功能
				title: '角色类型',
				formatter : function(value,rowData,rowIndex){
					if(value == 1){
						return "<span class='label label-success radius'>系统角色</span>";
					} else {
						return "<span class='label label-warning radius'>业务角色</span>";
					}
				}
			},{
				field: 'ifVisible',
				align: "center",
				sortable: true, // 开启排序功能
				title: '使用状态',
				formatter : function(value,rowData,rowIndex){
					if(value == 1){
						return "<span class='label label-info radius'>已启用</span>";
					} else {
						return "<span class='label label-danger radius'>禁用</span>";
					}
				}
			},{
				field: 'sequence',
				align: "center",
				sortable: true, // 开启排序功能
				title: '排序'
			},{
				field: 'createTime',
				title: '创建时间',
				visible:false,
				formatter : function(value,rowData,rowIndex){
					return dllwh.genStrDateTime(value);
				}
			},{
				field: 'updateTime',
				title: '最后修改时间',
				visible:true,
				formatter : function(value,rowData,rowIndex){
					return dllwh.genStrDateTime(value);
				}
			}],
			onClickRow : function(row){ // 当用户点击某一行的时候触发
				
			},
			onCheck: function (row) {
				if(row.ifVisible == 1){
					$(".visibleButton").html('<a onclick="RoleController.roleVisibleButton(0)">角色禁用</a>');
				} else {
					$(".visibleButton").html('<a onclick="RoleController.roleVisibleButton(1)">角色启用</a>');
				}
				
				if(row.id == 1 ){
					$('.opBut').attr('disabled',"true");
				}  else {
					$('.opBut').removeAttr("disabled");
				}
			},
			onUncheck : function(){
				var selected = $('#roleTable').bootstrapTable('getSelections');
				if (selected.length == 0) {
					initButton();
				}
			}
		});
	}
	// 获取查询的参数
	function queryParams(params) {
		var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			rows : params.pageSize, //页面大小
			page : params.pageNumber,
			sortName: params.sortName,
			sortOrder: params.sortOrder,
			roleName : $("#txtSearchKey").val().trim()
		};
		return temp;
	}
	
	// 赋予的参数
	function operateFormatter(value, row, index) {
	
	}

	/**
	 * 角色管理的单例
	 */
	var RoleController = {
		id : "roleTable", //表格id
		setItem : null, //选中的条目
		table : null,
		layerIndex : -1,
		check : function(){
			var selected = $('#' + this.id).bootstrapTable('getSelections');
			if (selected == undefined || selected == "" || selected == 'null' || selected == 'undefined') {
				dialogAlert("您没有选中任何数据项！", "warn");
				return false;
			}
			if (selected.length > 1) {
				dialogAlert("请选择要操作的数据", "warn");
				return false;
			} else {
				RoleController.setItem = selected[0];
				return true;
			}
		},
		addClickFun : function() {// 点击添加 
			$("#createForm")[0].reset();
			$('#addRoleMode').modal('show');
		},
		editClickFun : function() {// 点击更新
			if(RoleController.check()){
				$("#editForm").form("load",RoleController.setItem);
				$('#editRoleMode').modal('show');
			}
		},
		delClickFun : function() { // 删除
			if(RoleController.check()){
				var id = RoleController.setItem.id;
				dialogConfirm("您确认要删除该角色吗?", function() {
					$.ajax({
						url : "${_currConText }/roleOperate/delRole.shtml",
						type : "POST",
						data : {
							delId : id
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
		roleVisibleButton : function(visible) { // 启用、禁用
			if(dllwh.isNullOrEmpty(visible)){
				visible = 1;
			}
			
			if(RoleController.check()){
				var id = RoleController.setItem.id;
				dialogConfirm("您确认要操作该角色吗?", function() {
					$.ajax({
						url : "${_currConText }/roleOperate/roleVisibleButton.shtml",
						type : "POST",
						data : {
							id : id,
							visible:visible
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
		assignClick : function() {// 权限配置
			if(RoleController.check()){
				var roleCode = this.setItem.id;
				dialogOpen({
					title: '角色授权',
					width: '30%',
					height: '100%',
					scroll:true,
					maxmin: false,
					showCloseBtn:false,
					btn : [ '保存并提交','取消'],
					url: '${_currConText }/roleView/roleAccessConfig.shtml?roleMenu&roleCode='+roleCode,
					success: function(){
						
					},yes : function(layero, index){
						var newpsw = window[index.find('iframe')[0]['name']];
						newpsw.getCheckedAll(roleCode);
					}
				});
			}
		},
		UserClickFun : function() {// 角色用户关联
			if(RoleController.check()){
				var roleCode = this.setItem.id;
				dialogOpen({
					title: '角色用户关联',
					width: '100%',
					height: '90%',
					maxmin: false,
					scroll : true,
					showCloseBtn:false,
					btn : [ '关闭'],
					url: '${_currConText }/roleView/roleAccessConfig.shtml?roleUser&roleCode='+roleCode,
					success: function(){
						
					},yes : function(){
						layer.closeAll();
					}
				});
			}
		},
		searchClick : function(){// 搜索
			
		},
		resetSearch : function(){// 重置搜索
			
		}
	};
	
	/** 提交修改  */
	RoleController.addSubmitClick =function(){
		$("#createForm").ajaxSubmit({
			url : "${_currConText }/roleOperate/addRole.shtml",
			beforeSubmit:function(){
				return $("#createForm").valid();
			},
			success:function(result){
				if (result.success) {
					dialogMsg("添加成功");
					$('#addRoleMode').modal('hide')
					initTable();
				} else {
					dialogMsg(result.msg, "error");
				}
			}
		});
	}
	
	/**
	* 点击查看按钮时
	*/
	RoleController.showClickFun = function() {
			var selectContent = $("#roleTable").bootstrapTable('getSelections')[0];
			if (typeof (selectContent) == 'undefined') {
			dialogAlert("请选择要查看的数据", "warn");
			return false;
		}
		$("#s_roleName").text(selectContent.roleName);
		$("#s_roleCode").text(selectContent.roleCode);
		if (dllwh.isNotNullOrEmpty(selectContent.categoryCode)) {
			if (selectContent.categoryCode == 1) {
				$("#s_categoryCode").text("系统角色");
			} else {
				$("#s_categoryCode").text("业务角色");
			}
		} else {
			$("#s_categoryCode").text("-");
		}

		if (dllwh.isNotNullOrEmpty(selectContent.ifVisible)) {
			if (selectContent.ifVisible == 1) {
				$("#s_isVisible").text("允许");
			} else {
				$("#s_isVisible").text("禁止");
			}
		} else {
			$("#s_isVisible").text("-");
		}

		if (dllwh.isNotNullOrEmpty(selectContent.allowEdit)) {
			if (selectContent.allowEdit == 1) {
				$("#s_allowEdit").text("允许");
			} else {
				$("#s_allowEdit").text("禁止");
			}
		} else {
			$("#s_allowEdit").text("-");
		}

		if (dllwh.isNotNullOrEmpty(selectContent.allowDelete)) {
			if (selectContent.allowDelete == 1) {
				$("#s_allowDelete").text("允许");
			} else {
				$("#s_allowDelete").text("禁止");
			}
		} else {
			$("#s_allowDelete").text("-");
		}

		$("#s_creator").text(selectContent.creator);
		$("#s_createTime").text(dllwh.genStrDateTime(selectContent.createTime));
		$("#s_modifier").text(selectContent.modifier);
		$("#s_updateTime").text(dllwh.genStrDateTime(selectContent.updateTime));
		$("#s_remark").text(selectContent.remark);
		
		$('#showRoleMode').modal('show');
	}

	/**
	 * 点击修改按钮时
	 */
	RoleController.editSubmit = function(){
		$("#editForm").ajaxSubmit({
			url : "${_currConText }/roleOperate/saveRole.shtml",
			beforeSubmit:function(){
				return $("#editForm").valid();
			},
			success:function(result){
				if (result.success) {
					dialogMsg("更新成功");
					$('#editRoleMode').modal('hide')
					initTable();
				} else {
					dialogMsg(result.msg, "error");
				}
			}
		});
	}
</script>
</html>