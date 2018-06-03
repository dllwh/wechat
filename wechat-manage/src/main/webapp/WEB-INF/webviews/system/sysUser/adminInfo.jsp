<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%--
<%@ include file="/WEB-INF/webviews/common/context/ace.jsp"%>
 --%>
<%@ include file="/WEB-INF/webviews/common/context/bootstrap.jsp"%>
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
							<span>${currentUser.userName}</span>
								&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" onclick="change_Password()" class="btn btn-warning btn-xs">修改密码</a>
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
									<input name="sex" type="radio" class="ace" checked="checked" /> 
									<span class="lbl">保密</span>
								</label>
								&nbsp;&nbsp;
								<label>
									<input name="sex" type="radio" class="ace" /> 
									<span class="lbl">男</span>
								</label>
								&nbsp;&nbsp; 
								<label>
									<input name="sex" type="radio" class="ace" /> 
									<span class="lbl">女</span>
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">
							真实姓名： 
						</label>
						<div class="col-sm-9">
							<input type="text" name="realName"  title="真实姓名" value="${currentUser.realName }" class="col-xs-8 text_info"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">
							昵称： 
						</label>
						<div class="col-sm-9">
							<input type="text" name="nickName" title="昵称" value="${currentUser.nickName }" class="col-xs-8 text_info"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">
							移动电话： 
						</label>
						<div class="col-sm-9">
							<input type="text" name="mobile" title="移动电话" value="${currentUser.mobile }" class="col-xs-8 text_info"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">
							电子邮箱：
						</label>
						<div class="col-sm-9">
							<input type="text" name="email" title="电子邮箱" value="${currentUser.email }" class="col-xs-8 text_info" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">
							个性签名： 
						</label>
						<div class="col-sm-9">
							<span>${currentUser.signature }</span>
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
						<button onclick="modify();" class="btn btn-danger radius" type="submit">修改信息</button>
						<button onclick="cancel();" class="btn btn-success radius" type="button">修改取消</button>
						<button onclick="save_info();" class="btn btn-success radius" type="button">保存修改</button>
					</div>
				</div>
			</div>
			<div class="recording_style">
				<div class="type_title">管理员登陆记录</div>
				<div class="recording_list">
					<table id="userLoginLogTable" 
							class="table table-striped table-bordered table-hover">
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
	<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
	<script type="text/javascript">
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
		
		$(function(){
			initTable();
		})
		
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
			
			$('#change_Pass').css({
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
							layer.alert(str += "" + $(this).attr("title")
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
					data : {
						realName : $(".xinxi input[name='realName']").val(),
						nickName : $(".xinxi input[name='nickName']").val(),
						mobile : $(".xinxi input[name='mobile']").val(),
						email : $(".xinxi input[name='email']").val(),
						random: Math.random()
					},
					success : function(result) {
						if (result.success) {
							layer.msg('修改成功！2秒后将重新登录', {
								title : '提示框',
								icon: 1,
								time: 2000 //2秒关闭（如果不配置，默认是3秒）
							}, function(){
								location.href="${_currConText }/loginController.shtml?doLogout";
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
				cancel : function(){
					cancel();
				},
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
		
		function initTable(){
			//先销毁表格  
			$("#userLoginLogTable").bootstrapTable('destroy');  
			
			$("#userLoginLogTable").bootstrapTableEx({
				iconsPrefix:'FontAwesome', 
				showRefresh:false,
				showColumns:false,
				pageSize: 15,                       // 每页的记录行数（*）
				pageList: [15],     // 可供选择的每页的行数（*）
				url :"${_currConText}/sysUserView/adminInfo.shtml?getUserLoginLog",
				columns : [{  
					title: '序号',  
					align: "center",
					formatter: function (value, row, index) {  
						var pageSize = $("#userLoginLogTable").bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
						var pageNumber = $("#userLoginLogTable").bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
						return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
					}  
				},{
					field : 'ipAddress',
					align: "center",
					title : '客户端IP'
				}, {
					field : 'loginStatus',
					width:"8%",
					title : '登录状态',
					formatter : function(value,rowData,rowIndex){
						if(value == 1){
							return "<span class='label label-info radius'>成功</span>";
						} else {
							return "<span class='label label-danger radius'>失败</span>";
						}
					}
				},{
					field: 'browser',
					align: "center",
					sortable: true, // 开启排序功能
					title: '登录浏览器'
				},{
					field: 'createTime',
					title: '时间',
					formatter : function(value,rowData,rowIndex){
						return dllwh.genStrDateTime(value);
					}
				},{
					field: 'logContent',
					title: '日志内容'
				}]
			});
		}
		
		// 获取查询的参数
		function queryParams(params) {
			var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
				rows : params.pageSize, //页面大小
				page : params.pageNumber,
				sortName: params.sortName,
				sortOrder: params.sortOrder
			};
			return temp;
		}
	</script>
</body>
</html>