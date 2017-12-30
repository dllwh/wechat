<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@ include file="/WEB-INF/webviews/common/context/assets.jsp"%>
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
								value="${currentUser.mobile }" class="col-xs-7 text_info"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">
							电子邮箱：
						</label>
						<div class="col-sm-9">
							<input type="text" name="电子邮箱" disabled="disabled" 
								value="${currentUser.email }" class="col-xs-7 text_info" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">
							QQ： 
						</label>
						<div class="col-sm-9">
							<input type="text" name="QQ" disabled="disabled"
								value="1349310440" class="col-xs-7 text_info" />
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
						<button onclick="save_info();" class="btn btn-success radius"
							type="button">保存修改</button>
					</div>
				</div>
			</div>
			<div class="recording_style">
				<div class="type_title">管理员登陆记录</div>
				<div class="recording_list">
					<table class="table table-border table-bordered table-bg table-hover table-sort" id="sample-table">
						<thead>
							<tr class="text-c">
								<th width="25">
									<label>
										<input type="checkbox" class="ace"/>
										<span class="lbl"></span>
									</label>
								</th>
								<th width="80">ID</th>
								<th width="100">类型</th>
								<th>内容</th>
								<th width="17%">登陆地点</th>
								<th width="120">客户端IP</th>
								<th width="150">时间</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<label>
										<input type="checkbox" class="ace"/>
										<span class="lbl"></span>
									</label>
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
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
		//按钮点击事件
		function modify() {
			$('.text_info').attr("disabled", false);
			$('.text_info').addClass("add");
			$('#Personal').find('.xinxi').addClass("hover");
			$('#Personal').find('.btn-success').css({
				'display' : 'block'
			});
		};
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
				layer.alert('修改成功！', {
					title : '提示框',
					icon : 1,
				});
				$('#Personal').find('.xinxi').removeClass("hover");
				$('#Personal').find('.text_info').removeClass("add").attr("disabled", true);
				$('#Personal').find('.btn-success').css({
					'display' : 'none'
				});
				layer.close(index);
			}
		};
		//初始化宽度、高度    
		$(".admin_modify_style").height($(window).height());
		$(".recording_style").width($(window).width() - 400);
		//当文档窗口发生改变时 触发  
		$(window).resize(function() {
			$(".admin_modify_style").height($(window).height());
			$(".recording_style").width($(window).width() - 400);
		});
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
						layer.alert('原密码不能为空!', {
							title : '提示框',
							icon : 0,

						});
						return false;
					}
					if ($("#Nes_pas").val() == "") {
						layer.alert('新密码不能为空!', {
							title : '提示框',
							icon : 0,

						});
						return false;
					}

					if ($("#c_mew_pas").val() == "") {
						layer.alert('确认新密码不能为空!', {
							title : '提示框',
							icon : 0,

						});
						return false;
					}
					if (!$("#c_mew_pas").val
							|| $("#c_mew_pas").val() != $("#Nes_pas").val()) {
						layer.alert('密码不一致!', {
							title : '提示框',
							icon : 0,

						});
						return false;
					} else {
						layer.alert('修改成功！', {
							title : '提示框',
							icon : 1,
						});
						layer.close(index);
					}
				}
			});
		}
	</script>
</body>
</html>