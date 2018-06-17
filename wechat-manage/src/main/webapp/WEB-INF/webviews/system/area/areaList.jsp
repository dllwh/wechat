<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webviews/common/taglib.jsp"%>
<div class="row">
	<div class="col-sm-2 no-padding">
		<div class="panel panel-default">
			<div class="panel-heading">全国星行政区域管理</div>
			<div class="panel-body" id="treePanel" style="overflow: auto;">
				<ul id="areaTree" class="ztree"></ul>
			</div>
		</div>
	</div>
	<div class="col-sm-10 no-padding">
		<div class="panel panel-default">
			<div class="panel-heading">区域信息</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-7 form-inline pull-left">
						<div class="form-group">
							<input class="form-control" placeholder="请输入区域代码" 
							id="seacherCode"/>
							<input class="form-control" placeholder="请输入区域名称" 
							id="seacherName"/>
							<input id="parentCode" type="hidden"/>
						</div>
						<div class="form-group">
							<a class="btn btn-primary" onclick="sysAreaController.searchClick()">
								<i class="fa fa-search"></i>&nbsp;查询
							</a>
						</div>
					</div>
					<div class="col-md-5">
						<div class="btn-toolbar pull-right">
							<div class="btn-group">
								<a class="btn btn-default" onclick="sysAreaController.searchClick()">
									<i class="fa fa-search"></i>&nbsp;查询
								</a>
								<shiro:hasPermission name="sysAreaOperate/save.shtml">
									<a class="btn btn-success" onclick="sysAreaController.addClick()">
										<i class="fa fa-plus"></i>&nbsp;新增
									</a>
								</shiro:hasPermission>
								<shiro:hasPermission name="sysAreaOperate/update.shtml">
									<a class="btn btn-info" onclick="sysAreaController.editClick()">
										<i class="fa fa-pencil-square-o icon-white"></i>&nbsp;编辑
									</a>
								</shiro:hasPermission>
								<shiro:hasPermission name="sysAreaOperate/del.shtml">
									<a class="btn btn-danger" onclick="sysAreaController.deleteClick()">
										<i class="fa fa-trash-o"></i>&nbsp;删除
									</a>
								</shiro:hasPermission>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12" style="padding-top: 10px">
						<table id="areaTable"
							class="table table-striped table-bordered table-hover">
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>