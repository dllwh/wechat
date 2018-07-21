<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/layui.jsp"%>
<title>当前在线用户</title>
</head>
<body data-spy="scroll">
	<blockquote class="layui-elem-quote layui-quote-nm layui-text" style="margin-top: 15px;color: #01AAED; "> 
		<p>这里是在线已经登录的<code>有效</code>Session，不能等同于当前在线用户，来源于Redis。</p>
	</blockquote>
	<div>
		<div class="layui-fluid">
			<div class="layui-row layui-col-space1">
				<div class="layui-col-md12">
					<div class="layui-row grid-demo">
						<div class="layui-col-md12">
							<div class="layui-btn-group opTable">
								<button class="layui-btn layui-btn-sm layui-btn-danger" data-type="forceLogout">
									<i class="layui-icon">&#xe640;</i>批量踢出
								</button>
								<button class="layui-btn layui-btn-sm" data-type="refresh">
									<i class="layui-icon">&#xe669;</i>刷新
								</button>
							</div>
							<table class="layui-table" lay-filter=""
								lay-data="{
									id : 'onlineUserTable',
									height: 'full-200', 
									cellMinWidth: 80, 
									page: true, limit:30, 
									url:'${_currConText}/roleView.shtml?getList'}">
								<thead>
									<tr>
										<th lay-data="{type:'checkbox'}">ID</th>
										<th lay-data="{field:'SessionID', width:200, sort: true}">SessionID</th>
										<th lay-data="{field:'userName',sort: true}">帐号</th>
										<th lay-data="{field:'startTimestamp',sort: true}">创建回话</th>
										<th lay-data="{field:'lastAccessTime', sort: true}">回话最后活动</th>
										<th lay-data="{field:'operater', sort: true}">操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
<script type="text/javascript">
	layui.use('table', function(){
		var table = layui.table;
		
		var $ = layui.$, active = {
			forceLogout: function(){
				var checkStatus = table.checkStatus('onlineUserTable'),
				data = checkStatus.data; //得到选中的数据

				if(data.length > 0 ){
					layer.confirm('是否确定踢出当前用户吗？', function(index){
						obj.del();
						layer.close(index);
					});
				} else {
					layer.msg('请选择数据');
				}
			},
			refresh: function(){
				table.reload('onlineUserTable', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
				});
			}
		};
		$('.opTable .layui-btn').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
</script>
</html>