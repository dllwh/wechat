<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/layui.jsp"%>
<title>创建银行信息</title>
</head>
<body>
	<div class="layui-form" style="padding: 20px 30px 0 0;">
		<div class="layui-form-item">
			<label class="layui-form-label">银行名称</label>
			<div class="layui-input-block">
				<input type="text" name="name" lay-verify="required" autocomplete="off"
					placeholder="请输入银行名称" class="layui-input layui-col-xs12">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">图标地址</label>
			<div class="layui-input-block">
				<input type="text" name="iconPath" lay-verify="required" autocomplete="off"
					placeholder="请输入图标地址" class="layui-input layui-col-xs12">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">有效状态</label>
			<div class="layui-input-inline">
				<input type="radio" name="ifVisible" value="1" title="有效" >
				<input type="radio" name="ifVisible" value="0" title="无效">
			</div>
		</div>
		<div class="layui-form-item layui-hide">
			<input type="button" lay-submit lay-filter="bank-form-submit" 
				id="bank-form-submit" value="确认添加">
		</div>
	</div>

</body>
<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>

<script type="text/javascript">
	layui.use(['form'], function(){
		var $ = layui.$,form = layui.form;
		
		//监听提交
		form.on('submit(bank-form-submit)', function(data){
			
			var field = data.field; //获取提交的字段
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引  
			
			//提交 Ajax 成功后，关闭当前弹层并重载表格
			$.ajax({
				url : '${_currConText }/system/payment/config/saveBank.shtml',
				data:field,
				type:"POST",
				dataType:"json",
				success:function(result){
					if (result.success) {
						// parent.layui.table.reload('bankTable'); //重载表格
						parent.layer.close(index); //再执行关闭
					} else {
						dialogMsg(result.msg, "error");
					}
					
				}
			});
			return false; 
		});
	
	});
</script>
</html>