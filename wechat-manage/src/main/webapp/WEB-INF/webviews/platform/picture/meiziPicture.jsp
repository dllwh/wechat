<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/layui.jsp"%>
<title>图片管理</title>
<style type="text/css">
body {
	overflow-y: scroll;
}
</style>
</head>
<body>
	<div class="layui-tab" lay-filter="filter">
		<ul class="layui-tab-title">
			<li class="layui-this">干货集中集</li>
			<li>用户管理2</li>
			<li>权限分配3</li>
			<li>商品管理4</li>
			<li>订单管理5</li>
		</ul>
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">
				<table class="layui-hide" id="test" lay-filter="operateEvent" ></table>
			</div>
			<div class="layui-tab-item"></div>
			<div class="layui-tab-item"></div>
			<div class="layui-tab-item"></div>
			<div class="layui-tab-item"></div>
		</div>
	</div>

	<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
</body>

<script type="text/javascript">
	layui.use(['element', 'layer'], function(){
	
		var table = layui.table;
		
		
		table.render({
			elem : '#test',
			page : true,
			url : '${_currConText }/pictureController/meiziPicture.shtml?getGanSharekData',
			cols : [ [
			    {
					field : 'id',
					width : '15%',
					fixed : 'left'
				}, {
					field : 'url',
					event : 'preview',
					style : 'cursor: pointer;',
					title : '请求方法'
				}, {
					field : 'createdAt',
					width : '10%'
				}, {
					field : 'publishedAt',
					width : '10%'
				}, ] ]
		});

		//监听单元格事件
		table.on('tool(operateEvent)', function(obj) {
			var data = obj.data;
			if (obj.event === 'preview') {
				layer.prompt({
					formType : 2,
					title : '修改 ID 为 [' + data.id + '] 的用户签名',
					value : data.sign
				}, function(value, index) {
					//这里一般是发送修改的Ajax请求
					layer.close(index);
				});
			}
		});
	});
</script>
</html>
