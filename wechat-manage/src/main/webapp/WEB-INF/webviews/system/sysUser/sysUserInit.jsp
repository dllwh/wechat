<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/ace.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/assets.jsp"%>
<link rel="stylesheet" href="${_currConText }/plug-in/font/css/font-awesome.min.css" />

<script src="${_currConText }/plug-in/tools/lrtk.js"></script>

<title>管理员用户管理</title>
</head>
<body>
	<div class="page-content clearfix">
		<div class="administrator">
			<div class="d_Confirm_Order_style">
				<input type="hidden" id="seachRole" value="0"/>
				<!-- 数据查询 -->
				<div class="search_style">
					<div class="title_names">搜索查询</div>
					<ul class="search_content clearfix">
						<li>
							<label class="l_f">管理员名称</label>
							<input type="text" class="text_add" placeholder="管理员名称"  style="width:400px"/>
						</li>
						<li>
							<label class="l_f">添加时间</label>
							<input class="inline laydate-icon" id="start" style="margin-left:10px;">
						</li>
						<li style="width:90px;">
							<button type="button" class="btn_search">
								<i class="fa fa-search"></i>查询
							</button>
						</li>
					</ul>
				</div>
				<!--操作-->
				<div class="border clearfix">
					<span class="l_f">
						 <a id="administrator_add" class="btn btn-warning">
						 	<i class="fa fa-plus"></i> 添加管理员
						 </a>
						 <a class="btn btn-danger">
						 	<i class="fa fa-trash"></i> 批量删除
						 </a>
					</span>
					 <span class="r_f">共：<b>6</b>人</span>
				</div>
				<!--管理员列表-->
				<div class="clearfix administrator_style" id="administrator">
					<div class="left_style">
						<div id="scrollsidebar" class="left_Treeview">
							<div class="show_btn" id="rightArrow">
								<span></span>
							</div>
							<div class="widget-box side_content">
								<div class="side_title">
									<a title="隐藏" class="close_btn"><span></span></a>
								</div>
								<div class="side_list">
									<div class="widget-header header-color-green2">
										<h4 class="lighter smaller">管理员分类列表</h4>
									</div>
									<div class="widget-body">
										<ul class="b_P_Sort_list">
											<li>
												<i class="fa fa-users green"></i>
												<a onclick="seachByRole('0')">
													全部管理员（${homePage:countUserTotal()}）
												</a>
											</li>
											<c:forEach items="${fns:countRoleUser()}" var="roleUser">
												<li>
													<i class="fa fa-users orange"></i> 
													<a onclick="seachByRole('${roleUser.roleId }')">
														${roleUser.roleName }（${roleUser.userCount }）
													</a>
												</li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="table_menu_list"  id="testIframe">
						<table class="table table-striped table-bordered table-hover" id="sample_table">
							<thead>
								<tr>
									<th width="25px">
										<label>
											<input type="checkbox" class="ace">
											<span class="lbl"></span>
										</label>
									</th>
									<th width="80px">登录名</th>
									<th width="100px">角色</th>				
									<th width="160px">加入时间</th>
									<th width="70px">有效状态</th>                
									<th width="70px">审核状态</th>                
									<th width="70px">允许登陆</th>                
									<th width="70px">锁定状态</th>                
									<th width="200px">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach begin="1" end="10">
									<tr>
										<td>
											<label>
												<input type="checkbox" class="ace">
												<span class="lbl"></span>
											</label>
										</td>
										<td>admin</td>
										<td>超级管理员</td>
										<td>2017-10-19 16:20:29</td>
										<td class="td-status">
											<span class="label label-success radius">已启用</span>
										</td>
										<td class="td-status">
											<span class="label label-danger radius">未审核</span>
										</td>
										<td class="td-status">
											<span class="label label-success radius">允许</span>
										</td>
										<td class="td-status">
											<span class="label label-success radius">未锁定</span>
										</td>
										<td class="td-manage">
											<a style="text-decoration:none" class="btn btn-xs " 
												title="启用" onclick="sysUserController.enableClick()">
												<i class="fa fa-close bigger-120"></i>
											</a>   
											<a title="停用" onclick="sysUserController.disableClick()" 
												class="btn btn-xs btn-success">
												<i class="fa fa-check  bigger-120"></i>
											</a>   
        									<a title="编辑" onclick="sysUserController.openChangeDig()" 
        										class="btn btn-xs btn-info" >
        										<i class="fa fa-edit bigger-120"></i>
        									</a>      
        									<a title="删除" onclick="sysUserController.delClickFun()" 
        										class="btn btn-xs btn-warning" >
        										<i class="fa fa-trash  bigger-120"></i>
        									</a>
        									<a title="重置密码" onclick="sysUserController.resetPwdFun()" 
        										class="btn btn-xs btn-danger" >
        										<i class="fa fa-history bigger-120"></i>
        									</a>
        									<a title="锁定" onclick="sysUserController.lockClick()"
        										class="btn btn-xs btn-danger" >
        										<i class="fa fa-pause bigger-120"></i>
        									</a>
        									<a title="解锁" onclick="sysUserController.unlockClick()"
        										class="btn btn-xs btn-warning" >
        										<i class="fa fa-play bigger-120"></i>
        									</a>
        									<a title="禁止登录" onclick="sysUserController.freezeAccountClick()"
        										class="btn btn-xs btn-danger" >
        										<i class="fa fa-pencil bigger-120"></i>
        									</a>
        									<a title="允许登录" onclick="sysUserController.unfreezeClick()"
        										class="btn btn-xs btn-warning" >
        										<i class="fa fa-pencil bigger-120"></i>
        									</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		 <!--添加管理员-->
		<div id="add_administrator_style" class="add_menber" style="display:none">
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
<script type="text/javascript">
	
	//初始化宽度、高度  
	$(".widget-box").height($(window).height()-215); 
	$(".table_menu_list").width($(window).width()-260);
	$(".table_menu_list").height($(window).height()-215);
	//当文档窗口发生改变时 触发  
	$(window).resize(function(){
		$(".widget-box").height($(window).height()-215);
		$(".table_menu_list").width($(window).width()-260);
		$(".table_menu_list").height($(window).height()-215);
	});
	
	laydate({
		elem: '#start',
		event: 'focus' 
	});
	
	//调用函数，初始化表格  
	initTable(); 
	
	//当点击查询按钮的时候执行  
	$(".btn_search").bind("click", initTable);
	
	$(function() { 
		$("#administrator").fix({
			float : 'left',
			//minStatue : true,
			skin : 'green',	
			durationTime :false,
			spacingw:50, //设置隐藏时的距离
			spacingh:270 //设置显示时间距
		});
	});
	
	//字数限制
	function checkLength(which) {
		var maxChars = 100; //
		if(which.value.length > maxChars){
			layer.open({
				icon:2,
				title:'提示框',
				content:'您输入的字数超过限制!',	
			});
			// 超过限制的字数了就将 文本框中的内容按规定的字数 截取
			which.value = which.value.substring(0,maxChars);
			return false;
		} else {
			var curr = maxChars - which.value.length; //250 减去 当前输入的
			document.getElementById("sy").innerHTML = curr.toString();
			return true;
		}
	};
	
	function initTable(){
		var seachRole =  $("#seachRole").val();
	}
	
	function seachByRole(key){
		if(dllwh.isNullOrEmpty(key)){
			$("#seachRole").val(0);	
		} else {
			$("#seachRole").val(key);
		}
	}
	
	/*添加管理员*/
	$('#administrator_add').on('click', function(){
		layer.open({
		type: 1,
		title:'添加管理员',
		area: ['700px',''],
		shadeClose: false,
		content: $('#add_administrator_style'),
		
		});
	});
	

	/**
	 * 用户详情对话框（可用于添加和修改对话框）
	 */
	var sysUserInfoDlg = {
		userInfoData : {},
		showRoleSelectTree : function() {// 角色选择的树

		},
		showMenuSelectTree : function() {// 菜单选择的树

		},
		showInfoDeptSelectTree : function() {// 显示用户详情部门选择的树

		},
		validatePwd : function() { // 验证两个密码是否一致

		},
		validate : function() { // 验证数据是否为空

		}
	}
	
	var sysUserController = {
		id : "managerTable",// 表格id
		setItem : null, // 选中的条目
		table : null,
		layerIndex : -1,
		check : function() { // 检查是否选中
			var selected = $('#' + this.id).bootstrapTable('getSelections');
			if (selected == undefined || selected == "" || selected == 'null' || selected == 'undefined') {
				// "您没有选中任何数据项！
				return false;
			}
			if (selected.length > 1) {
				return false;
			} else {
				sysUser.setItem = selected[0];
				return true;
			}
		},
		openAddDig : function() {// 点击添加
		},
		openChangeDig : function() {// 点击修改按钮时
			if (this.check()) {
			}
		},
		delClickFun : function() {// 点击删除按钮时
			if (this.check()) {
				// $.messager.alert('警告提示', '不能删除当前登录用户！', 'warning');
				// $.messager.alert('警告提示', '不能删除超级管理员用户！', 'warning');
				// $.messager.alert('询问提示', '确认要删除所选用户吗?', 'warning');
			}
		},
		viewClick : function() {// 查看

		},
		resetPwdFun : function() {// 重置密码
			if (this.check()) {
			}
		},
		enableClick : function() {// 启用
			if (this.check()) {
				layer.confirm('确认要启用吗？',function(index){
					layer.msg('已启用!',{icon: 6,time:1000});
				});
			}
		},
		disableClick : function() {// 禁用
			if (this.check()) {
				layer.confirm('确认要停用吗？',function(index){
					layer.msg('已停用!',{icon: 5,time:1000});
				});
			}
		},
		freezeAccountClick : function() { // 冻结用户账户
			if (this.check()) {
			}
		},
		unfreezeClick : function() {// 解除冻结用户账户
			if (this.check()) {
			}
		},
		lockClick : function() {// 锁定用户
			if (this.check()) {
			}
		},
		unlockClick : function() {// 解除锁定
			if (this.check()) {
			}
		},
		roleAssignClick : function() {// 点击角色分配
			if (this.check()) {
			}
		},
		importClick : function() {// 导入

		},
		exportClick : function() {// 导出

		},
		searchClick : function() { // 搜索

		},
		resetSearch : function() {// 重置搜索条件

		}
	}
	
	/**
	 * 创建
	 */
	sysUserController.addSubmitClick = function() {

	};

	/**
	 * 更新
	 */
	sysUserController.editSubmitClick = function() {

	};
</script>
</html>