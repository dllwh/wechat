<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/assets.jsp"%>
<title>当前在线用户</title>
</head>
<body data-spy="scroll">
	<div class="container" style="padding-bottom: 15px;min-height: 300px;">
		<div class="row">
			<div class="col-md-12">
				<h2>当前在线用户</h2>
				<hr>
				<div class="well">
					<p>这里是在线已经登录的<code>有效</code>Session，不能等同于当前在线用户，来源于Redis。</p>
				</div>
				<hr>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>SessionID</th>
							<th>角色</th>
							<th>帐号</th>
							<th>创建回话</th>
							<th>回话最后活动</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>3c21bfda-384f-4bd1-b2c9-55eb94dbebb8</td>
							<td>管理员</td>
							<td>admin</td>
							<td>2017-12-04 11:57:26</td>
							<td></td>
							<td>有效</td>
							<td>
								<a>详情</a>
								<shiro:hasPermission name="sysUser/operate/changeSessionStatus.shtml">
									<a name="onlineState">
										<i class="icon-off"></i>退出
									</a>
								</shiro:hasPermission>
							</td>
						</tr>
						<tr>
							<td>d9c08711-8572-40a7-ae5b-a1354a8ef918</td>
							<td>管理员</td>
							<td>admin</td>
							<td>2017-12-04 11:57:26</td>
							<td></td>
							<td>已踢出</td>
							<td>
								<a>详情</a>
								<shiro:hasPermission name="sysUser/operate/changeSessionStatus.shtml">
									<a name="onlineState">
										<i class="icon-off"></i>激活
									</a>
								</shiro:hasPermission>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	$('a[name=onlineState]').on('click', function() {
		var self = $(this);
		var text = $.trim(self.text());
		var index = layer.confirm('是否确定'+text+'?', {
			btn : [ '是', '否' ],//按钮
			icon : 2,
		}, function() {
			changeSessionStatus();
			layer.close(index);
			
		});
	});
	
	/** 改变状态 */
	function changeSessionStatus(){
		
	}
</script>
</html>