<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/layui.jsp"%>
<title>调度任务</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-row layui-col-space1">
			<!--  -->
			<div class="layui-col-md12">
				<blockquote class="layui-elem-quote" style="padding: 0.5px  5px ">
					<div class="layui-form-query">
						<form class="layui-form" id="query_form">
							<div class="layui-form-item">
								<label class="layui-form-mid">任务编号：</label>
								<div class="layui-input-inline" style="width:200px;">
									<input type="text" name="job_id" placeholder="请输入任务编号"
										autocomplete="off" class="layui-input"/>
								</div>
								<label class="layui-form-mid">任务名称：</label>
								<div class="layui-input-inline" style="width:200px;">
									<input type="text" name="bean_name" placeholder="请输入任务名称"
										autocomplete="off" class="layui-input"/>
								</div>
								<div class="layui-inline">
									<div class="layui-btn-group taskTableButton">
										<button data-type="query" type="button"
											class="layui-btn layui-btn-sm">
											<i class="layui-icon">&#xe615;</i>查询
										</button>
										<shiro:hasPermission name="scheduledController/createJob.shtml">
											<button class="layui-btn layui-btn-sm" 
												type="button" data-type="add">
												<i class="layui-icon">&#xe654;</i>增加
											</button>
										</shiro:hasPermission>
										<shiro:hasPermission name="scheduledController/startJobs.shtml">
											<button data-type="startJobs" type="button"
												class="layui-btn layui-btn-sm layui-btn-warm">
												<i class="layui-icon">&#xe652;</i>启动全部
											</button>
										</shiro:hasPermission>
										<shiro:hasPermission name="scheduledController/shutdownJobs.shtml">
											<button data-type="shutdownJobs" type="button"
												class="layui-btn layui-btn-sm layui-btn-danger">
												<i class="layui-icon">&#xe651;</i>暂停全部
											</button>
										</shiro:hasPermission>
										<button data-type="refresh" type="button"
											class="layui-btn layui-btn-sm layui-btn-normal">
											<i class="layui-icon">&#xe669;</i>刷新
										</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</blockquote>
			</div>
			<!-- 数据展示区域 -->
			<div class="layui-col-md12">
				<div class="layui-row grid-demo">
					<div class="layui-col-md12">
						<div class="layui-btn-container taskTableButton">
							<shiro:hasPermission name="scheduledController/runJob.shtml">
								<button class="layui-btn layui-btn-sm layui-btn-warm" data-type="runJob">
									<i class="layui-icon">&#xe652;</i>执行
								</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="scheduledController/startJob.shtml">
								<button class="layui-btn layui-btn-sm layui-btn-warm" data-type="startJob">
									<i class="layui-icon">&#xe652;</i>启动
								</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="scheduledController/shutdownJob.shtml">
								<button class="layui-btn layui-btn-sm layui-btn-warm" data-type="shutdownJob">
									<i class="layui-icon"></i>停止
								</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="scheduledController/pauseJob.shtml">
								<button class="layui-btn layui-btn-sm layui-btn-warm" data-type="pauseJob">
									<i class="layui-icon">&#xe651;</i>暂停
								</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="scheduledController/resumeJob.shtml">
								<button class="layui-btn layui-btn-sm layui-btn-warm" data-type="resumeJob">
									<i class="layui-icon">&#xe9aa;</i>恢复
								</button>
							</shiro:hasPermission>
							<button class="layui-btn layui-btn-sm layui-btn-danger">
								<i class="layui-icon">&#xe60a;</i>日志列表
							</button>
						</div>
						<table class="layui-table" id="taskTable" lay-filter="taskTableFilter"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/html" id="stateTpl">
	{{#  if(d.status == 0){ }}
		<span style="color: #F581B1;">未运行</span>
	{{#  } else { }}
		{{ d.status }}
	{{#  } }}
</script>

<script type="text/html" id="barTools">
	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
<shiro:hasPermission name="scheduledController/updateJob.shtml">
	<a class="layui-btn layui-btn-xs" lay-event="edit"><i class="layui-icon">&#xe642;</i>编辑</a>
</shiro:hasPermission>
<shiro:hasPermission name="scheduledController/deleteJob.shtml">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
</shiro:hasPermission>
</script>

<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
<script type="text/javascript">
	layui.use('table', function(){
		var table = layui.table;
		
		table.render({
			elem: '#taskTable',
			id: 'taskTable',
			url : '${_currConText}/scheduledController.shtml?getList',
			page : {
				layout : [ 'limit', 'count','prev', 'page', 'next', 'skip','refresh' ],
				prev:'上一页',
				next:'下一页',
				first:'首页',
				last:'尾页',
				groups : 10// 连续出现的页码个数
			},
			cellMinWidth: 80 ,
			request : {
				pageName: 'page', //页码的参数名称，默认：page
				limitName: 'rows'// 每页数据量的参数名，默认：limit
			},
			cols: [[
				{checkbox: true, fixed: true},
				{field:'id', width:'10%',title: '任务ID'},
				{field:'beanName', title: 'bean名称'},
				{field:'methodName', title: '方法名称'},
				{field:'cronExpression',width:'30%',title: 'cron表达式'},
				{field:'status', width:'10%',title: '状态',toolbar : '#stateTpl'},
				{field:'bar',width:'25%',toolbar : '#barTools', fixed: 'right'}
			]]
		});
		
		// 监听工具条
		table.on('tool(taskTableFilter)', function(obj){
			var data = obj.data;
			if(obj.event === 'detail'){
				layer.msg('ID：'+ data.id + ' 的查看操作');
			} else if(obj.event === 'del'){
				layer.confirm('真的删除行么', function(index){
					obj.del();
					layer.close(index);
				});
			} else if(obj.event === 'edit'){
				var index = layui.layer.open({
					title : "修改",
					type : 2,
					content : "${_currConText}/scheduledController/updateJob.shtml?taskEdit",
					success : function(layero, index){
						layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
							tips: 3
						});
					}
				})
				//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
				$(window).resize(function(){
					layui.layer.full(index);
				})
				layui.layer.full(index);
			}
		});
		
		
		var getCheck = function(id){
			var checkStatus = table.checkStatus(id), data = checkStatus.data;
			if (data.length <= 0) {
				dialogAlert("您没有选中任何操作数据！", "warn");
				return false;
			} else {
				return true;
			}
		}
		
		var $ = layui.$, active = {
			query : function(){
				table.reload('taskTable', {
					page: {
						curr: 1 //重新从第 1 页开始
					},
					where :{
						id:$("#query_form input[name='job_id']").val(),
						beanName:$("#query_form input[name='bean_name']").val()
					}
				});
			},
			add : function(){
				var index = layui.layer.open({
					title : "新增",
					type : 2,
					content : "${_currConText}/scheduledController/createJob.shtml?taskAdd",
					success : function(layero, index){
						layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
							tips: 3
						});
					}
				})
				//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
				$(window).resize(function(){
					layui.layer.full(index);
				})
				layui.layer.full(index);
			},
			runJob:function(){
				if(getCheck('taskTable')){
					
				}
			},
			startJob :function(){
				if(getCheck('taskTable')){
					
				}
			},
			shutdownJob :function(){
				if(getCheck('taskTable')){
					
				}
			},
			pauseJob :function(){
				if(getCheck('taskTable')){
					
				}
			},
			resumeJob :function(){
				if(getCheck('taskTable')){
					
				}
			},
			startJobs: function(){
				var index = layer.msg('任务启动中，请稍候',{icon: 16,time:false,shade:0.8});
				setTimeout(function(){
					layer.close(index);
					layer.msg("任务启动成功");
				},2000);
			},
			shutdownJobs: function(){
				var index = layer.msg('任务正在暂定，请稍候',{icon: 16,time:false,shade:0.8});
				setTimeout(function(){
					layer.close(index);
					layer.msg("任务暂停成功");
				},2000);
			},
			refresh: function(){
				table.reload('taskTable', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
				});
			}
		};
		
		$('.taskTableButton .layui-btn').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
</script>
</html>
