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
	<div>
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
										<input type="text" name="job_id" value=""
											lay-verType="msg" lay-verify="" placeholder="请输入任务编号"
											autocomplete="off" class="layui-input"/>
									</div>
									<label class="layui-form-mid">任务名称：</label>
									<div class="layui-input-inline" style="width:200px;">
										<input type="text" name="bean_name" value=""
											lay-verType="msg" lay-verify="" placeholder="请输入任务名称"
											autocomplete="off" class="layui-input"/>
									</div>
									<div class="layui-inline">
										<div class="layui-btn-group">
											<button class="layui-btn layui-btn-sm">
												<i class="layui-icon">&#xe615;</i>查询
											</button>
											<button class="layui-btn layui-btn-sm" type="button">
												<i class="layui-icon">&#xe654;</i>增加
											</button>
											<button class="layui-btn layui-btn-sm layui-btn-radius layui-btn-warm">
												<i class="layui-icon">&#xe652;</i>启动
											</button>
											<button class="layui-btn layui-btn-sm layui-btn-radius layui-btn-danger">
												<i class="layui-icon">&#xe651;</i>暂停
											</button>
											<button class="layui-btn layui-btn-sm layui-btn-normal" type="button">
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
							<div class="layui-btn-container">
								<button class="layui-btn layui-btn-sm" topTitle="编辑"><i class="layui-icon">&#xe642;</i>编辑</button>
								<button class="layui-btn layui-btn-sm"><i class="layui-icon">&#xe640;</i>删除</button>
								<button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="submit"><i class="layui-icon">&#xe652;</i>执行</button>
								<button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="submit"><i class="layui-icon">&#xe652;</i>启动</button>
								<button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="submit"><i class="layui-icon"></i>停止</button>
								<button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="submit"><i class="layui-icon">&#xe651;</i>暂停</button>
								<button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="submit"><i class="layui-icon">&#xe9aa;</i>恢复</button>
							</div>
							<table class="layui-table" lay-data="{height: 'full-200'"></table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/html" id="stateTpl">
	<input type="checkbox" name="status" lay-skin="switch" disabled lay-text="开启|关闭" {{ d.status == 1 ? 'checked' : '' }}>
</script>

<script type="text/html" id="barTools">
	
</script>

<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
<script type="text/javascript">
	layui.use('table', function(){
		var table = layui.table;
	});
</script>
</html>