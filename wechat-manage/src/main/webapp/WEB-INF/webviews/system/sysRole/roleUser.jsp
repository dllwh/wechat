<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/bootstrap.jsp"%>
<link href="${_currConText }/static/css/common.css?v=4.1.0" rel="stylesheet">
<link href="${_currConText }/static/css/sample/animate.css" rel="stylesheet">
<link href="${_currConText }/static/css/sample/style.css?v=4.1.0" rel="stylesheet">
<title>${_currProject }---角色用户</title>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-xs-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<table id="roleUserTable" 
							class="table table-striped table-bordered table-hover">
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/webviews/common/context/easyUI.jsp"%>
	<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
</body>
<script type="text/javascript">
		$(function(){
			initTable();
		})
		
		function initTable(){
			//先销毁表格  
			$("#roleUserTable").bootstrapTable('destroy');
			
			$("#roleUserTable").bootstrapTableEx({
				showRefresh:false,
				showColumns: false,
				url :"${_currConText }/roleView/roleAccessConfig.shtml?roleUserList&roleId=${roleCode}",
				columns : [ { 
					field: "cb", 
					checkbox : true, 
					hidden:true
				},{
					field : 'id',
					title : '用户ID'
				},{
					field : 'userName',
					title : '用户名称'
				},{
					field : 'nickName',
					title : '昵称'
				},{
					field : 'realName',
					title : '真实姓名'
				}]
			});
		}
	</script>
</html>