<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<head>
<%@ include file="/WEB-INF/webviews/common/include/assets.jsp"%>
<link rel="stylesheet" href="plug-in/font/css/font-awesome.min.css" />


<script src="plug-in/tools/lrtk.js"></script>

<title>管理员用户管理</title>
</head>
<body>
	<div class="page-content clearfix">
		<div class="administrator">
			<div class="d_Confirm_Order_style">
				<!-- 数据查询 -->
				<div class="search_style">
					<div class="title_names">搜索查询</div>
					<ul class="search_content clearfix">
						<li>
							<label class="l_f">管理员名称</label>
							<input type="text" class="text_add" placeholder=""  style="width:400px"/>
						</li>
						<li>
							<label class="l_f">添加时间</label>
							<input class="inline" id="start" style="margin-left:10px;">
						</li>
						<li style="width:90px;">
							<button type="button" class="btn_search">
								<i class="fa fa-search"></i>查询
							</button>
						</li>
					</ul>
				</div>
				<!--操作-->
				<div class="border clearfix">
					<span class="l_f">
						 <a id="administrator_add" class="btn btn-warning">
						 	<i class="fa fa-plus"></i> 添加管理员
						 </a>
						 <a class="btn btn-danger">
						 	<i class="fa fa-trash"></i> 批量删除
						 </a>
					</span>
					 <span class="r_f">共：<b>6</b>人</span>
				</div>
				<!--管理员列表-->
				<div class="clearfix administrator_style" id="administrator">
					<div class="left_style">
						<div id="scrollsidebar" class="left_Treeview">
							<div class="show_btn" id="rightArrow">
								<span></span>
							</div>
							<div class="widget-box side_content">
								<div class="side_title">
									<a title="隐藏" class="close_btn"><span></span></a>
								</div>
								<div class="side_list">
									<div class="widget-header header-color-green2">
										<h4 class="lighter smaller">管理员分类列表</h4>
									</div>
									<div class="widget-body">
										<ul class="b_P_Sort_list">
											<li>
												<i class="fa fa-users green"></i>
												<a href="#">全部管理员（6）</a>
											</li>
											<li>
												<i class="fa fa-users orange"></i>
												<a>超级管理员（1）</a>
											</li>
											<li>
												<i class="fa fa-users orange"></i> 
												<a >普通管理员（5）</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="table_menu_list"  id="testIframe">
						<table class="table table-striped table-bordered table-hover" id="sample_table">
							<thead>
								<tr>
									<th width="25px">
										<label>
											<input type="checkbox" class="ace">
											<span class="lbl"></span>
										</label>
									</th>
									<th width="80px">登录名</th>
									<th width="100px">角色</th>				
									<th width="160px">加入时间</th>
									<th width="70px">有效状态</th>                
									<th width="70px">审核状态</th>                
									<th width="70px">允许登陆</th>                
									<th width="70px">锁定状态</th>                
									<th width="200px">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach begin="1" end="10">
									<tr>
										<td>
											<label>
												<input type="checkbox" class="ace">
												<span class="lbl"></span>
											</label>
										</td>
										<td>admin</td>
										<td>超级管理员</td>
										<td>2017-10-19 16:20:29</td>
										<td class="td-status">
											<span class="label label-success radius">已启用</span>
										</td>
										<td class="td-status">
											<span class="label label-success radius">已审核</span>
										</td>
										<td class="td-status">
											<span class="label label-success radius">允许</span>
										</td>
										<td class="td-status">
											<span class="label label-success radius">未锁定</span>
										</td>
										<td class="td-manage">
											<a style="text-decoration:none" class="btn btn-xs " 
												title="启用" onclick="sysUsereEnable()">
												<i class="fa fa-close bigger-120"></i></a>
											</a>   
											<a title="停用" onclick="sysUsereDisable()" 
												class="btn btn-xs btn-success">
												<i class="fa fa-check  bigger-120"></i>
											</a>   
        									<a title="编辑" onclick="editClickFun()" 
        										class="btn btn-xs btn-info" >
        										<i class="fa fa-edit bigger-120"></i>
        									</a>      
        									<a title="删除" onclick="delClickFun()" 
        										class="btn btn-xs btn-warning" >
        										<i class="fa fa-trash  bigger-120"></i>
        									</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		 <!--添加管理员-->
		<div id="add_administrator_style" class="add_menber" style="display:none">
		
		</div>
	</div>
</body>
<script type="text/javascript">
	
$(function() { 
	$("#administrator").fix({
		float : 'left',
		//minStatue : true,
		skin : 'green',	
		durationTime :false,
		spacingw:50,//设置隐藏时的距离
	    spacingh:270,//设置显示时间距
	});
});

	//初始化宽度、高度  
	$(".widget-box").height($(window).height()-215); 
	$(".table_menu_list").width($(window).width()-260);
	$(".table_menu_list").height($(window).height()-215);
	//当文档窗口发生改变时 触发  
	$(window).resize(function(){
		$(".widget-box").height($(window).height()-215);
		$(".table_menu_list").width($(window).width()-260);
		$(".table_menu_list").height($(window).height()-215);
	});
	laydate({
	    elem: '#start',
	    event: 'focus' 
	});
	
	
	//字数限制
	function checkLength(which) {
		var maxChars = 100; //
		if(which.value.length > maxChars){
		   layer.open({
		   icon:2,
		   title:'提示框',
		   content:'您输入的字数超过限制!',	
	    });
			// 超过限制的字数了就将 文本框中的内容按规定的字数 截取
			which.value = which.value.substring(0,maxChars);
			return false;
		}else{
			var curr = maxChars - which.value.length; //250 减去 当前输入的
			document.getElementById("sy").innerHTML = curr.toString();
			return true;
		}
	};
	/*添加管理员*/
	$('#administrator_add').on('click', function(){
		layer.open({
	    type: 1,
		title:'添加管理员',
		area: ['700px',''],
		shadeClose: false,
		content: $('#add_administrator_style'),
		
		});
	});
	
	
	/*用户-停用*/
	function sysUsereDisable(){
		layer.confirm('确认要停用吗？',function(index){
			layer.msg('已停用!',{icon: 5,time:1000});
		});
	}
	
	/*用户-启用*/
	function sysUsereEnable(){
		layer.confirm('确认要启用吗？',function(index){
			layer.msg('已启用!',{icon: 6,time:1000});
		});
	}
	
	/*用户-编辑*/
	function editClickFun(){
		
	}
	
	/*用户-删除*/
	function delClickFun(){
		layer.confirm('确认要删除吗？',function(index){
			layer.msg('已删除!',{icon:1,time:1000});
		});
	}
</script>
</html>