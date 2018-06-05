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

.laytable-cell-1-picurl { /*最后的pic为字段的field*/
	height: 100%;
	max-width: 100%;
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

<script type="text/html" id="imgTpl">
	<img style="display: inline-block; width: 100%; height: 100%;" src= {{ d.url }}>
</script>
<script type="text/html" id="createdTpl">
	{{#  
		var fn = function(value){
   			return dllwh.genStrDateTime(value);
		}; 
	}}
	{{ fn(d.createdAt) }}
</script>
<script type="text/html" id="publishedTpl">
	{{#  
		var fn = function(value){
			return dllwh.genStrDateTime(value);
		}; 
	}}
	{{ fn(d.publishedAt) }}
</script>

<script type="text/javascript">
	layui.use(['element', 'layer'], function(){
	
		var table = layui.table;
		
		table.render({
			elem : '#test',
			page : true,
			url : '${_currConText }/pictureController/meiziPicture.shtml?getGanSharekData',
			page : {
				layout : [ 'prev', 'next' ],
				prev:'上一页',
				next:'下一页',
				limit : 5,// 每页显示的条数
				limits:[5, 10, 15] // 每页条数的选择项。
			},
			request : {
				pageName: 'page', //页码的参数名称，默认：page
				limitName: 'rows'// 每页数据量的参数名，默认：limit
			},
			done: function(res, curr, count){
				console.log(res.msg); 
			},
			text: {
			    none: '无法获取更多数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
			},
			cols : [ [
				{
					field : 'id',
					width : '15%',
					title : '图片标识'
				}, {
					field : 'picurl',
					title : '图片预览',
					width : '15%',
					templet: '#imgTpl'
				}, {
					field : 'url',
					event : 'preview',
					style : 'cursor: pointer;',
					title : '地址'
				}, {
					field : 'createdAt',
					width : '15%',
					title : '上传时间',
					templet: '#createdTpl'
				}, {
					field : 'publishedAt',
					width : '15%',
					title : '发布时间',
					templet: '#publishedTpl'
				}, ] ]
		});

		//监听单元格事件
		table.on('tool(operateEvent)', function(obj) {
			var data = obj.data;
			if (obj.event === 'preview') {
				layer.prompt({
					formType : 2,
					title : '预览',
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
