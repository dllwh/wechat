<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/layui.jsp"%>
<title>银行分类</title>
</head>
<body>

	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-card-header layuiadmin-card-header-auto">
				<button class="layui-btn layuiadmin-btn-tags" data-type="add">添加</button>
			</div>
			<div class="layui-card-body">
				<table id="LAY-app-content-tags" lay-filter="LAY-app-content-tags"></table>
				<script type="text/html" id="layuiadmin-app-cont-tagsbar">
					<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail"><i class="layui-icon layui-icon-log"></i>查看</a>
					<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
				</script>
				<script type="text/html" id="enabledTpl">
					<input type="checkbox" name="sex" value="{{d.ifEnabled}}" lay-skin="switch" lay-text="有效|无效" lay-filter="enabledTool" {{ d.ifEnabled == 1 ? 'checked' : '' }}>
				</script>
			</div>
		</div>
	</div>
		<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
<script type="text/javascript">

	var table = layui.table;
	var loading = layer.msg("数据加载中…………");
	var form = layui.form;
	var tableOptions  = {
		url : '${_currConText }/system/payment/config/bankInit.shtml?getList',
		elem : '#LAY-app-content-tags',
		cellMinWidth: 60,
		id: 'opLogTable', //生成 Layui table 的标识 id，必须提供
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
		text:"对不起，加载出现异常！",
		cols : [ [
			{ type:'numbers',fixed:'left'}, // 序号列
			{ field : 'name', title : '银行名称', edit: 'text'}, 
			{ field : 'ifEnabled', title : '有效状态',templet: '#enabledTpl'}, 
			{ field : 'iconPath',title : '图标'}, 
			{ field : '操作',title : '记录时间',align:"center",fixed:"right",toolbar:"#layuiadmin-app-cont-tagsbar"}
		] ]
	};

	layui.use(['table'], function(){
		table.render(tableOptions);
		
		//监听工具条
		table.on('tool(LAY-app-content-tags)', function(obj) {
			var data = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值
			if("detail" == layEvent){
			} else if("del" == layEvent){
				layer.confirm("确定删除此条数据？", function (e) {
					obj.del();
					layer.close(e)
				});
			} 
		});
		
		//监听单元格编辑
		table.on('edit(LAY-app-content-tags)', function(obj){
			console.log(obj.value); //得到修改后的值
			console.log(obj.field); //当前编辑的字段名
			console.log(obj.data); //所在行的所有相关数据
			if("name" == obj.field){
				
			} else if("iconPath" == obj.field){
				
			} 
		});
		
		//监听有效状态
		form.on('switch(enabledTool)', function(data){
			console.log(data.elem.checked); //开关是否开启，true或者false			
		});
		
		var $ = layui.$, active = {
			add : function() {
				layer.open({
					type : 2,
					title : '添加分类',
					content : 'tagsform.html',
					btn : [ '确定', '取消' ],
					yes : function(index, layero) {
						var othis = layero.find('iframe').contents().find("#layuiadmin-app-form-tags"); 
						var tags = othis.find('input[name="tags"]').val();

						if (!tags.replace(/\s/g, ''))
							return;

						table.reload('LAY-app-content-tags');
						layer.close(index);
					}
				});
			}
		}
		$('.layui-btn.layuiadmin-btn-tags').on('click', function() {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
</script>
</body>
</html>