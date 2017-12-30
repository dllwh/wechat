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
<style type="text/css">
.opBut {
}
</style>
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
										<button type="button" class="btn btn-primary" 
											onclick="Role.addClickFun()">
											<span class="fa fa-plus"> 创建</span>
										</button>
									</shiro:hasPermission>
									<button type="button" class="btn btn-success"
										onclick="Role.showClickFun()">
										<span class="fa fa-eye"> 查看</span>
									</button>
									<shiro:hasPermission name="roleOperate/saveRole.shtml">
										<button type="button" class="btn btn-info opBut" 
											onclick="Role.editClickFun()">
											<span class="fa fa-pencil"> 修改</span>
										</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="roleOperate/delRole.shtml">
										<button type="button" class="btn btn-danger opBut"
											onclick="Role.delClickFun()">
											<span class="fa fa-trash-o"> 删除</span>
										</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="roleOperate/updateMenuByRole.shtml">
										<button type="button" class="btn btn-info" 
											onclick="Role.assignClick()">
											<i class="fa fa-gavel"></i> 角色授权
										</button>
									</shiro:hasPermission>
									<button type="button" class="btn btn-info" 
										onclick="Role.UserClickFun()">
										<i class="fa fa-user"></i> 角色用户
									</button>
									<button type="button" class="btn btn-warning opBut" id="disableClickBtn"
										onclick="Role.disableClickFun()">
										<i class="fa fa-user"></i> 角色禁用
									</button>
									<button type="button" class="btn btn-warning" id="enableClickBtn"
										onclick="Role.enableClickFun()">
										<i class="fa fa-user"></i> 角色启用
									</button>
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
	<div class="modal fade" id="addRoleMode" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
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
							<label class="control-label" for="roleName">角色名称：</label>
							<input type="text" class="form-control" name="roleName" 
								placeholder="角色名称"
								aria-invalid="true">
						</div>
						<div class="form-group">
							<label class="control-label">角色代码：</label>
							<input type="text" class="form-control" name="roleCode" placeholder="角色代码"
							aria-required="true" aria-invalid="true" class="error">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="Role.addRole()">
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
	<div class="modal fade" id="editRoleMode" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
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
							<label class="control-label" for="roleName">角色名称：</label>
							<input type="text" class="form-control" name="roleName" 
								placeholder="角色名称"
								aria-invalid="true">
						</div>
						<div class="form-group">
							<label class="control-label">角色代码：</label>
							<input type="text" class="form-control" name="roleCode" placeholder="角色代码"
							aria-required="true" aria-invalid="true" class="error">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="Role.editRoleFun()">
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
					</table>
				</div>
				<div class="modal-footer">
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
	$(function() {
		//调用函数，初始化表格  
		initTable(); 
		//当点击查询按钮的时候执行  
		$("#btnSearch").bind("click", initTable); 
		
		$("#createForm").validate({
			errorElement : 'span', //用什么标签标记错误，默认是 label，可以改成 em。
			errorClass : 'help-block',   //指定错误提示的 css 类名，可以自定义错误提示的样式。
			focusInvalid : true,   //验证提示时，鼠标光标指向提示的input
			onkeyup:false,  
			focusCleanup:true,  
			success:"valid",
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
			},
			highlight : function(element) {//可以给未通过验证的元素加效果、闪烁等。
				$(element).closest('.form-group').addClass('has-error has-feedback');
			},
			success : function(label) {//要验证的元素通过验证后的动作
				var el=label.closest('.form-group').find("input");
				el.next().remove();//与errorPlacement相似
				el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
				label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
				label.remove();
			},
			errorPlacement : function(error, element) {//跟一个函数，可以自定义错误放到哪里
				element.next().remove();//删除显示图标
				element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
				element.closest('.form-group').append(error);//显示错误消息提示
			},
			submitHandler : function(form) {
			}
		});
		
		$("#editForm").validate({
			errorElement : 'span', //用什么标签标记错误，默认是 label，可以改成 em。
			errorClass : 'help-block',   //指定错误提示的 css 类名，可以自定义错误提示的样式。
			focusInvalid : true,   //验证提示时，鼠标光标指向提示的input
			onkeyup:false,  
			focusCleanup:true,  
			success:"valid",
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
			},
			highlight : function(element) {//可以给未通过验证的元素加效果、闪烁等。
				$(element).closest('.form-group').addClass('has-error has-feedback');
			},
			success : function(label) {//要验证的元素通过验证后的动作
				var el=label.closest('.form-group').find("input");
				el.next().remove();//与errorPlacement相似
				el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
				label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
				label.remove();
			},
			errorPlacement : function(error, element) {//跟一个函数，可以自定义错误放到哪里
				element.next().remove();//删除显示图标
				element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
				element.closest('.form-group').append(error);//显示错误消息提示
			},
			submitHandler : function(form) {
			}
		});
	});
	
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
				title : '角色编码'
			},{
				field: 'categoryCode',
				title: '角色类型',
				formatter : function(value,rowData,rowIndex){
					if(value == 1){
						return "<span class='label label-success radius'>系统角色</span>";
					} else {
						return "<span class='label label-warning radius'>业务角色</span>";
					}
				}
			},{
				field: 'isVisible',
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
				title: '排序'
			},{
				field: 'updateTime',
				title: '最后修改时间',
				formatter : function(value,rowData,rowIndex){
					return dllwh.genStrDateTime(value);
				}
			}],
			onLoadSuccess: function(){  // 加载成功时执行  
				
			},
			onLoadError: function(){  // 加载失败时执行
				
			},
			onClickRow : function(row){ // 当用户点击某一行的时候触发
				
			},
			onCheck: function (row) {
				if(row.categoryCode == 1 ){
					$('.opBut').attr('disabled',"true");
				}  else {
					$('.opBut').removeAttr("disabled");
				}
				
				if(row.isVisible == 1){
					$("#disableClickBtn").removeAttr("disabled");
					$("#enableClickBtn").attr('disabled',"true");
					if(row.categoryCode == 1 ){
						$("#disableClickBtn").attr('disabled',"true");
					}
				} else {
					$("#disableClickBtn").attr('disabled',"true");
					$("#enableClickBtn").removeAttr("disabled");
				}
			}
		});
	}
	// 获取查询的参数
	function queryParams(params) {
		var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			rows : params.pageSize, //页面大小
			page : params.pageNumber,
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
	var Role = {
		id : "roleTable", //表格id
		seItem : null, //选中的条目
		table : null,
		layerIndex : -1
	};

	/**
	 * 角色管理的单例
	 */
	var Role = {
		id : "roleTable", //表格id
		seItem : null, //选中的条目
		table : null,
		layerIndex : -1
	};

	/**
	 * 检查是否选中
	 */
	Role.check = function() {
		alert("检查是否选中");
	}

	/**
	 * 点击添加角色 
	 */
	Role.addClickFun = function() {
		$("#createForm")[0].reset();
		$('#addRoleMode').modal('show');
	}
	
	Role.addRole =function(){
		$("#createForm").ajaxSubmit({
			url : "${_currConText }/roleOperate/addRole.shtml",
			beforeSubmit:function(){
				return $("#createForm").valid();
			},
			success:function(result){
				// var result=eval("("+data+")");
				if (result.success) {
					dialogMsg("添加成功");
					$('#addRoleMode').modal('hide')
					initTable();
				} else {
					dialogMsg(result.msg, {icon : 2});
				}
			}
		});
	}
	
	/**
	* 点击查看按钮时
	*/
	Role.showClickFun = function() {
			var selectContent = $("#roleTable").bootstrapTable('getSelections')[0];
			if (typeof (selectContent) == 'undefined') {
			dialogAlert("请选择要查看的数据", "warn");
			return false;
		}
		// console.info(selectContent);
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

		if (dllwh.isNotNullOrEmpty(selectContent.isVisible)) {
			if (selectContent.isVisible == 1) {
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
		$('#showRoleMode').modal('show');
	}

	/**
	 * 点击修改按钮时
	 */
	Role.editClickFun = function() {
		var selectContent = $("#roleTable").bootstrapTable('getSelections')[0];
		if (typeof (selectContent) == 'undefined') {
			dialogAlert("请选择要编辑的数据", "warn");
			return false;
		}
		$("#editForm").form("load",selectContent);
		$('#editRoleMode').modal('show');
	}

	Role.editRoleFun = function(){
		$("#editForm").ajaxSubmit({
			url : "${_currConText }/roleOperate/saveRole.shtml",
			beforeSubmit:function(){
				return $("#editForm").valid();
			},
			success:function(result){
				// var result=eval("("+data+")");
				if (result.success) {
					dialogMsg("更新成功");
					$('#editRoleMode').modal('hide')
					initTable();
				} else {
					dialogMsg(result.msg, {icon : 2});
				}
			}
		});
	}
	/**
	 * 删除角色
	 */
	Role.delClickFun = function() {
		var selectContent = $("#roleTable").bootstrapTable('getSelections')[0];
		if (typeof (selectContent) == 'undefined') {
			dialogAlert("请选择要删除的数据", "warn");
			return false;
		}

		dialogConfirm("您确认要删除该角色吗?", function() {
			$.ajax({
				url : "${_currConText }/roleOperate/delRole.shtml",
				type : "POST",
				data : {
					delId : selectContent.id
				},
				success : function(result) {
					// var result=eval("("+data+")");
					if (result.success) {
						dialogMsg("删除成功");
						initTable();
					} else {
						dialogMsg(result.msg, {icon : 2});
					}
				}
			});
		});
	}

	/**
	 * 权限配置
	 */
	Role.assignClick = function() {
		var selectContent = $("#roleTable").bootstrapTable('getSelections')[0];
		if (typeof (selectContent) == 'undefined') {
			dialogAlert("请选择要操作的数据", "warn");
			return false;
		}
	}
	/**
	 * 角色用户
	 */
	Role.UserClickFun = function() {
		var selectContent = $("#roleTable").bootstrapTable('getSelections')[0];
		if (typeof (selectContent) == 'undefined') {
			dialogAlert("请选择要操作的数据", "warn");
			return false;
		}
	
		}

	/**
	 * 禁用
	 */
	Role.disableClickFun = function() {
		var selectContent = $("#roleTable").bootstrapTable('getSelections')[0];
		if (typeof (selectContent) == 'undefined') {
			dialogAlert("请选择要操作的数据", "warn");
			return false;
		}
		dialogConfirm("您确认要禁用该角色吗?", function() {
			$.ajax({
				url : "${_currConText }/roleOperate/disable.shtml",
				type : "POST",
				data : {
					id : selectContent.id
				},
				success : function(result) {
					// var result=eval("("+data+")");
					if (result.success) {
						dialogMsg("操作成功");
						initTable();
					} else {
						dialogMsg(result.msg, { icon : 2 });
					}
				}
			});
		});
	}

	/**
	 * 启用
	 */
	Role.enableClickFun = function() {
		var selectContent = $("#roleTable").bootstrapTable('getSelections')[0];
		if (typeof (selectContent) == 'undefined') {
			dialogAlert("请选择要操作的数据", "warn");
			return false;
		}
		dialogConfirm("您确认要启用该角色吗?", function() {
			$.ajax({
				url : "${_currConText }/roleOperate/enable.shtml",
				type : "POST",
				data : {
					id : selectContent.id
				},
				success : function(result) {
					// var result=eval("("+data+")");
					if (result.success) {
						dialogMsg("操作成功");
						initTable();
					} else {
						dialogMsg("操作失败。原因" + result.msg, { icon : 2 });
					}
				}
			});
		});
	}
	
</script>
</html>