<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/ace.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/assets.jsp"%>
<link rel="stylesheet" href="${_currConText }/plug-in/font/css/font-awesome.min.css" />
<link rel="stylesheet" href="${_currConText }/static/css/payment.css"/>  
<title>支付配置</title>
</head>
<c:set var="details" value="${_currConText }/system/payment/config/details.shtml?ownerId="/>
<body>
	<div class="margin clearfix">
		<div class="Configure_style">
			<div class="manner">
				<div class="title_name">商城用户支付配置</div>
				<div class="info clearfix">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th width="150px">支付名称</th>
								<th width="150px">状态</th>
								<th>描述</th>
								<th width="100px">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${paymentConfigList }" var="item">
								<tr>
									<td>${item.name}</td>
									<td>
										<label>
											<input name="form-field-radio-${item.id}" type="radio" class="ace"
												<c:if test="${item.ifVisible eq 1}">checked</c:if>
											 	onclick="enable(this,'${item.id}')">
											<span class="lbl">启用</span>
										</label>
										<label>
											<input name="form-field-radio-${item.id}" type="radio"class="ace"
												<c:if test="${item.ifVisible eq 0}">checked</c:if>
												onclick="closes(this,'${item.id}')">
											<span class="lbl">关闭</span>
										</label>
									</td>
									<td>
										<c:choose>
											<c:when test="${fn:length(item.remark) gt 60}">
												${fn:substring(item.remark, 0, 60)}...
											</c:when>
											<c:otherwise>
												${item.remark}
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<a title="在线支付配置" href="${details}${item.id}">配置</a>
									</td>
								</tr>
							</c:forEach>							
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	function enable(ojb,id){
		var checkedState = $(ojb).attr('checked');//记录当前选中状态 
		
		layer.confirm('确认要开启吗？',
			function(index){
				layer.msg('开启成功!',{
					icon:1,time:1000
				});
			},function(index){
				
			}
		);
	}
	function closes(ojb,id){
		var checkedState = $(ojb).attr('checked');//记录当前选中状态 
		layer.confirm('确认要关闭该支付功能吗？',
			function(index){
				layer.msg('已关闭!',{icon:1,time:1000});
			},
			function(index){
					
			}
		);
	}
</script>
</html>