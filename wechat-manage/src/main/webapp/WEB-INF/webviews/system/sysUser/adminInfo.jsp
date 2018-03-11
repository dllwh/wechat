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
<title>个人信息管理</title>
</head>
<c:set var="currentUser" value="${fns:getCurrenLoginUser() }" />
<body>
	<div class="clearfix">
		<div class="admin_info_style">
			<div class="admin_modify_style" id="Personal">
				<div class="type_title">管理员信息</div>
				<div class="xinxi">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">
							用户名：
						</label>
						<div class="col-sm-9">
							<c:if test="${not empty currentUser.realName }">
								<c:set var="userName" value="${currentUser.realName }" />
							</c:if>
							<c:if test="${empty currentUser.realName }">
								<c:set var="userName" value="${currentUser.userName }" />
							</c:if>
							<input type="text" name="用户名" value="${userName}" 
								class="col-xs-6 text_info" disabled="disabled" />
								&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="change_Password()" 
								class="btn btn-warning btn-xs">修改密码</a>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">
							性别：
						</label>
						<div class="col-sm-9">
							<span class="sex">男</span>
							<div class="add_sex">
								<label>
									<input name="form-field-radio" type="radio" class="ace" 
										checked="checked" /> 
									<span class="lbl">保密</span>
								</label>
								&nbsp;&nbsp;
								<label>
									<input name="form-field-radio" type="radio" class="ace" /> 
									<span class="lbl">男</span>
								</label>
								&nbsp;&nbsp; 
								<label>
									<input name="form-field-radio" type="radio" class="ace" /> 
									<span class="lbl">女</span>
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">
							年龄： 
						</label>
						<div class="col-sm-9">
							<input type="text" name="年龄" value="24"
								class="col-xs-7 text_info" disabled="disabled" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">
							移动电话： 
						</label>
						<div class="col-sm-9">
							<input type="text" name="移动电话" disabled="disabled"
								value="${currentUser.mobile }" class="col-xs-8 text_info"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">
							电子邮箱：
						</label>
						<div class="col-sm-9">
							<input type="text" name="电子邮箱" disabled="disabled" 
								value="${currentUser.email }" class="col-xs-8 text_info" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">
							QQ： 
						</label>
						<div class="col-sm-9">
							<input type="text" name="QQ" disabled="disabled"
								value="1349310440" class="col-xs-8 text_info" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">
							权限： 
						</label>
						<div class="col-sm-9">
							<c:forEach items="${fns:getRoleList()}" var="userRole">
								<span>${userRole.roleName }</span>
							</c:forEach>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">注册时间： </label>
						<div class="col-sm-9">
							<span><fmt:formatDate value="${currentUser.createTime}"
									pattern="yyyy-MM-dd hh:mm" /></span>
						</div>
					</div>
					<div class="Button_operation clearfix">
						<button onclick="modify();" class="btn btn-danger radius"
							type="submit">修改信息</button>
						<button onclick="cancel();" class="btn btn-success radius"
							type="button">修改取消</button>
						<button onclick="save_info();" class="btn btn-success radius"
							type="button">保存修改</button>
					</div>
				</div>
			</div>
			<div class="recording_style">
				<div class="type_title">管理员登陆记录</div>
				<div class="recording_list">
					<table class="table table-border table-bordered table-bg table-hover table-sort" id="userLoginLogTable">
						<thead>
							<tr class="text-c">
								<th width="25"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
								<th width="80">ID</th>
								<th width="100">类型</th>
								<th>内容</th>
								<th width="17%">登陆地点</th>
								<th width="10%">用户名</th>
								<th width="120">客户端IP</th>
								<th width="150">时间</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><label><input type="checkbox" class="ace"><span class="lbl"></span></label></td>
								<td>15686</td>
								<td>1</td>
								<td>登录成功!</td>
								<td>江苏南京</td>
								<td>admin</td>
								<td>61.233.7.80</td>
								<td>2014-6-11 11:11:42</td>      
							</tr>
							<tr>
								<td><label><input type="checkbox" class="ace"><span class="lbl"></span></label></td>
								<td>15686</td>
								<td>1</td>
								<td>登录成功!</td>
								<td>江苏南京</td>
								<td>admin</td>
								<td>61.233.7.80</td>
								<td>2014-6-11 11:11:42</td>      
							</tr>
							<tr>
								<td><label><input type="checkbox" class="ace"><span class="lbl"></span></label></td>
								<td>15686</td>
								<td>1</td>
								<td>登录成功!</td>
								<td>江苏南京</td>
								<td>admin</td>
								<td>61.233.7.80</td>
								<td>2014-6-11 11:11:42</td>      
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!--修改密码样式-->
	<div class="change_Pass_style" id="change_Pass">
		<ul class="xg_style">
			<li>
				<label class="label_name">原&nbsp;&nbsp;密&nbsp;码</label>
				<input name="原密码" type="password" class="" id="password" />
			</li>
			<li>
				<label class="label_name">新&nbsp;&nbsp;密&nbsp;码</label>
				<input name="新密码" type="password" class="" id="Nes_pas" />
			</li>
			<li>
				<label class="label_name">确认密码</label>
				<input name="再次确认密码" type="password" class="" id="c_mew_pas" />
			</li>
		</ul>
	</div>
	<script type="text/javascript">
		$(function(){
			//初始化宽度、高度    
			$(".admin_modify_style").height($(window).height());
			$(".recording_style").width($(window).width() - 400);
			//当文档窗口发生改变时 触发  
			$(window).resize(function() {
				$(".admin_modify_style").height($(window).height());
				$(".recording_style").width($(window).width() - 400);
			});
		
			$('#userLoginLogTable th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});	
		});
		
		// 修改按钮点击事件
		function modify() {
			$('.text_info').attr("disabled", false);
			$('.text_info').addClass("add");
			$('#Personal').find('.xinxi').addClass("hover");
			$('#Personal').find('.btn-info').css({
				'display' : 'block'
			});
			$('#Personal').find('.btn-success').css({
				'display' : 'block'
			});
		};
		
		// 取消按钮点击事件
		function cancel (){
			$('#Personal').find('.xinxi').removeClass("hover");
			$('#Personal').find('.text_info').removeClass("add").attr("disabled", true);
			$('#Personal').find('.btn-info').css({
				'display' : 'none'
			});
			$('#Personal').find('.btn-success').css({
				'display' : 'none'
			});
		}
		
		// 保存按钮点击事件
		function save_info() {
			var num = 0;
			var str = "";
			$(".xinxi input[type$='text']").each(
					function(n) {
						if ($(this).val() == "") {
							layer.alert(str += "" + $(this).attr("name")
									+ "不能为空！\r\n", {
								title : '提示框',
								icon : 0,
							});
							num++;
							return false;
						}
					});
			if (num > 0) {
				return false;
			} else {
				$.ajax({
					type : "post",
					url: '${_currConText }/loginController/updateUser.shtml',
					dataType : "json",
					success : function(result) {
						if (result.success) {
							layer.alert('修改成功！', {
								title : '提示框',
								icon : 1,
							});
							cancel();
						} else {
							layer.alert(result.msg, {
								title : '提示框',
								icon : 2,
							});
						}	
					}
				});
			}
		};
		
		//修改密码
		function change_Password() {
			layer.open({
				type : 1,
				title : '修改密码',
				area : [ '300px', '300px' ],
				shadeClose : true,
				content : $('#change_Pass'),
				btn : [ '确认修改' ],
				yes : function(index, layero) {
					if ($("#password").val() == "") {
						dialogAlert("原密码不能为空!", "warn");
						return false;
					}
					if ($("#Nes_pas").val() == "") {
						dialogAlert("新密码不能为空!", "warn");
						return false;
					}

					if ($("#c_mew_pas").val() == "") {
						dialogAlert("确认新密码不能为空!", "warn");
						return false;
					}
					if (!$("#c_mew_pas").val
							|| $("#c_mew_pas").val() != $("#Nes_pas").val()) {
						dialogAlert("密码不一致!", "warn");
						return false;
					} else {
						$.ajax({
							url : "${_currConText }/loginController/resetPwd.shtml",
							type : "POST",
							data : {
								oldPassWord : $("#password").val(),
								newPassWord : $("#Nes_pas").val()
							},
							success : function(data) {
								var result = eval("("+data+")");
								if (result.success) {
									
									layer.msg('修改成功！2秒后将重新登录', {
										title : '提示框',
										icon: 1,
										time: 2000 //2秒关闭（如果不配置，默认是3秒）
									}, function(){
										location.href="${_currConText }/loginController.shtml?doLogout";
									});
									layer.close(index);
								} else {
									layer.alert(result.msg, {
										title : '提示框',
										icon : 0,
									});
								}
							}
						});
					}
				}
			});
		}
	</script>
</body>
</html>