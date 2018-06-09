<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/layui.jsp"%>
<title>银行分类</title>
<style type="text/css">
	body{overflow-y: scroll;}
</style>
</head>
<body>
	<div class="layui-card">
		<shiro:hasPermission name="system/payment/config/saveBank.shtml">
			<div class="layui-card-header layuiadmin-card-header-auto">
				<button class="layui-btn addBank" data-type="add">添加</button>
			</div>
		</shiro:hasPermission>
		<div class="layui-card-body">
			<table id="bankTable" lay-filter="bankTable"></table>
			<script type="text/html" id="bankTableBar">
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">
				<i class="layui-icon layui-icon-log"></i>查看
			</a>
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">
				<i class="layui-icon layui-icon-delete"></i>删除
			</a>
		</script>
		<script type="text/html" id="enabledTpl">
			<input type="checkbox" name="ifVisible" value="{{d.id}}" lay-skin="switch" index
				lay-text="有效|无效" lay-filter="enabledTool" {{d.ifVisible == 1 ? 'checked' : ''}}>
		</script>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
	<script type="text/javascript">
	
		var table = layui.table;
		var loading = layer.msg("数据加载中…………");
		var form = layui.form;
		var tableOptions  = {
			url : '${_currConText }/system/payment/config/bankInit.shtml?getList',
			elem : '#bankTable',
			cellMinWidth: 60,
			id: 'opLogTable', //生成 Layui table 的标识 id，必须提供
			method : "", // 接口http请求类型，默认：get
			limit : 15,// 每页显示的条数，默认：10
			request : {
				pageName: 'page', //页码的参数名称，默认：page
				limitName: 'pageSize'// 每页数据量的参数名，默认：limit
			},
			done: function(res, curr, count){
				layer.close(loading);
			},
			text:"对不起，加载出现异常！",
			cols : [ [
				{ 
					type:'numbers',
					fixed:'left'
				},{ 
					field : 'name', 
					title : '银行名称', 
					edit: 'text'
				},{ 
					field : 'ifVisible', 
					title : '有效状态',
					templet: '#enabledTpl'
				},{ 
					field : 'iconPath',
					title : '图标'
				},{ 
					field : '操作',
					title : '记录时间',
					align:"center",
					fixed:"right",
					toolbar:"#bankTableBar"
				}
			] ]
		};
	
		layui.use(['table'], function(){
			table.render(tableOptions);
			
			//监听工具条
			table.on('tool(bankTable)', function(obj) {
				var data = obj.data; //获得当前行数据
				var layEvent = obj.event; //获得 lay-event 对应的值
				if("detail" == layEvent){
					layer.alert(JSON.stringify(data));
				} else if("del" == layEvent){
					layer.confirm("确定删除此条数据？", function (e) {
						$.ajax({
							url : "${_currConText }/system/payment/config/delBank.shtml",
							type : "POST",
							data : {
								id : data.id,
								ifEnabled:0,
								random:Math.random()
							},
							success : function(result) {
								if (result.success) {
									dialogMsg("操作成功");
									obj.del();
									layer.close(e)
								} else {
									dialogMsg(result.msg, "error");
								}
							}
						});
					});
				} 
			});
			
			//监听单元格编辑
			table.on('edit(bankTable)', function(obj){
				// console.log(obj.value); //得到修改后的值
				// console.log(obj.field); //当前编辑的字段名
				// console.log(obj.data); //所在行的所有相关数据
				if("name" == obj.field){
					$.ajax({
						url : "${_currConText }/system/payment/config/updateBank.shtml",
						type : "POST",
						data : {
							id :obj.data.id,
							name:obj.value,
							random:Math.random()
						},
						success : function(result) {
							if (result.success) {
								dialogMsg("操作成功");
							} else {
								dialogMsg(result.msg, "error");
							}
						}
					});
				} else if("iconPath" == obj.field){
					$.ajax({
						url : "${_currConText }/system/payment/config/updateBank.shtml",
						type : "POST",
						data : {
							id :obj.data.id,
							name:obj.value,
							random:Math.random()
						},
						success : function(result) {
							if (result.success) {
								dialogMsg("操作成功");
							} else {
								dialogMsg(result.msg, "error");
							}
						}
					});
				} 
			});
			
			//监听有效状态
			form.on('switch(enabledTool)', function(data){
				var ifVisible =1;
				if(data.elem.checked){
					ifVisible =1;
				} else {
					ifVisible = 0;
				}
				var id = data.value; 
				$.ajax({
					url : "${_currConText }/system/payment/config/updateBank.shtml",
					type : "POST",
					data : {
						id : id,
						ifVisible:ifVisible,
						random:Math.random()
					},
					success : function(result) {
						if (result.success) {
							dialogMsg("操作成功");
						} else {
							dialogMsg(result.msg, "error");
						}
					}
				});
			});
			
			var $ = layui.$, active = {
				add : function() {
					dialogOpen({
						title: '新建银行',
						width: '600px',
						height: '300px',
						maxmin: false,
						scroll : true,
						showCloseBtn:false,
						btn : [ '确定', '取消' ],
						url: '${_currConText }/system/payment/config/saveBank.shtml?createBank',
						success: function(){
							
						},yes : function(layero, index){
							//点击确认触发 iframe 内容中的按钮提交
							var submit = index.find('iframe').contents().find("#bank-form-submit");
							submit.click();
						}
					});
				}
			}
			$('.layui-btn.addBank').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
		});
	</script>
</body>
</html>