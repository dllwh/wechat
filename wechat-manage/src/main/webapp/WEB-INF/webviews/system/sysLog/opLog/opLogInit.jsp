<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/layui.jsp"%>
<title>操作日志</title>
</head>
<body>

	<div class="seacherTools">
		<span>操作者账号</span>
		<div class="layui-inline">
			<input class="layui-input" name="id" id="userCode" autocomplete="off">
		</div>
		<button class="layui-btn" data-type="reload">搜索</button>
	</div>

	<table class="layui-hide admin_modify_style" id="opLogTable" lay-filter="opLogTableFilter"></table>

<script type="text/html" id="operateBar">
	<a class="layui-btn  layui-btn-warm layui-btn-xs" lay-event="detail">查看</a>
</script>
	<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
<script>
	var table = layui.table,
		form = layui.form;
	var loading = layer.msg("数据加载中…………");
	
	
	var tableOptions  = {
		url : '${_currConText }/sysLog/opLog.shtml?list',
		elem : '#opLogTable',
		cellMinWidth: 60,
		id: 'opLogTable', //生成 Layui table 的标识 id，必须提供
		height:window.innerHeight-70,
		page : {
			layout : [ 'limit', 'count','prev', 'page', 'next', 'skip','refresh' ],
			prev:'上一页',
			next:'下一页',
			first:'首页',
			last:'尾页',
			groups : 10// 连续出现的页码个数
		},
		method : "", // 接口http请求类型，默认：get
		limit : 15,// 每页显示的条数，默认：10
		request : {
			pageName: 'page', //页码的参数名称，默认：page
			limitName: 'pageSize'// 每页数据量的参数名，默认：limit
		},
		done: function(res, curr, count){
			// res : 接口返回的信息
			// curr : 当前页码
			// count : 数据总量
			layer.close(loading);
		},
		cols : [ [
			{ type:'numbers',fixed:'left'}, // 序号列
			{ field : 'userCode', title : '操作',unresize:true,fixed:'left'},
			{ field : 'ipAddress', title : 'IP地址',fixed:'left',minWidth:120},
			{ field : 'opType', title : '日志类型',fixed:'left',minWidth:60},
			{ field : 'params', title : '请求参数',minWidth:1300,edit:'text'},
			{ field : 'opResult', title : '操作结果',minWidth:550}, 
			{ field : 'time', title : '响应时间'}, 
			{ field : 'browser', title : '浏览器'}, 
			{ field : 'tableName',title : '日志操作表'}, 
			{ field : 'createTime',title : '记录时间'}, 
	    	{ fixed: 'right', width:80, align:'center', toolbar: '#operateBar'}
		] ]
	};
	
	layui.use('table', function() {
		table.render(tableOptions);
		
		//监听工具条
		table.on('tool(opLogTableFilter)', function(obj) {
			var data = obj.data;
			if (obj.event === 'detail') {
				layer.alert(JSON.stringify(data));
			}
		});
		
		var $ = layui.$, active = {
			reload: function(){
				//执行重载
				table.reload("opLogTable",{
					where: { //设定异步数据接口的额外参数，任意设
						userCode:  $('#userCode').val()
					},
					page: {
						curr: 1 //重新从第 1 页开始
					}
				});
			}
		};
		
		$('.seacherTools .layui-btn').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
</script>
</body>
</html>