<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head  lang="en">
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<title>${_currProject}</title>
<%@ include file="/WEB-INF/webviews/common/context/assets.jsp"%>
<script type="text/javascript" src="${_currConText }/plug-in/tools/ExtJavascript.js"></script>
<script type="text/javascript">	

	$(function() {
		var cid = $('#nav_list> li>.submenu');
		cid.each(function(i) {
			$(this).attr('id', "Sort_link_" + i);

		});
		
		$.each($(".submenu"), function() {
			var $aobjs = $(this).children("li");
			var rowCount = $aobjs.size();
			var divHeigth = $(this).height();
			$aobjs.height(divHeigth / rowCount);
		});
		
		//初始化宽度、高度    
		$("#main-container").height($(window).height() - 76);
		$("#iframe").height($(window).height() - 140);
	
		$(".sidebar").height($(window).height() - 99);
		var thisHeight = $("#nav_list").height($(window).outerHeight() - 173);
		$(".submenu").height();
		$("#nav_list").children(".submenu").css("height",thisHeight);
	
		//当文档窗口发生改变时 触发  
		$(window).resize(function() {
			$("#main-container").height($(window).height() - 76);
			$("#iframe").height($(window).height() - 140);
			$(".sidebar").height($(window).height() - 99);
			var thisHeight = $("#nav_list").height($(window).outerHeight() - 173);
			$(".submenu").height();
			$("#nav_list").children(".submenu").css("height", thisHeight);
		});
		
		$(".iframeurl").click(function() {
			var cid = $(this).attr("name");
			var cname = $(this).attr("title");
			$("#iframe").attr("src", cid).ready();
			$("#Bcrumbs").attr("href", cid).ready();
			$(".Current_page a").attr('href',cid).ready();
			$(".Current_page").attr('name', cid);
			$(".Current_page").html(cname).css({
				"color" : "#333333",
				"cursor" : "default"
			}).ready();
			$("#parentIframe").html('<span class="parentIframe iframeurl"> </span>').css("display", "none").ready();
			$("#parentIfour").html('').css("display", "none").ready();
		});
	});

	/*********************点击事件*********************/

	$(document).ready(function() {
		$('#nav_list').find('li.home').click(function() {
			$('#nav_list').find('li.home').removeClass('active');
			$(this).addClass('active');
		});

		
		setInterval(function() {
			$('#time').html(dllwh.currentTime)
		}, 1000);

		$('#Exit_system').on('click', function() {
			layer.confirm('是否确定退出系统？', {
				btn : [ '是', '否' ],//按钮
				icon : 2,
			}, function() {
				location.href="loginController.shtml?doLogout";
			});
		});
	})
</script>	
</head>
<body>
	<div class="navbar navbar-default" id="navbar">
	<script type="text/javascript">
		try{
			ace.settings.check('navbar' , 'fixed');
		} catch (e){
			
		}
	</script>
		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="javascript:void();" class="navbar-brand">
					<small>					
						<img src="static/images/logo.png">
					</small>
				</a><!-- /.brand -->
				</div><!-- /.navbar-header -->
				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">	
						<li class="light-blue">
							<a data-toggle="dropdown" class="dropdown-toggle">
								<span  class="time">
									<em id="time"></em>
								</span>
								<span class="user-info">
									<c:choose>
										<c:when test="${not empty fns:getCurrenLoginUser().nickName}">
											<small>欢迎光临,</small>${fns:getCurrenLoginUser().nickName}
										</c:when>
										<c:when test="${not empty fns:getCurrenLoginUser().realName}">
											<small>欢迎光临,</small>${fns:getCurrenLoginUser().realName}
										</c:when>
										<c:otherwise>
											${fns:getCurrenLoginUser().userName}
										</c:otherwise>
									</c:choose>
								</span>
								<i class="icon-caret-down"></i>
							</a>
							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="javascript:void(0)" id="Exit_system">
										<i class="icon-off"></i>退出
									</a>
								</li>
							</ul>
						</li>
						<li class="purple">
							<a data-toggle="dropdown" class="dropdown-toggle" href="javascript:void(0)">
								<i class="icon-bell-alt"></i>
								<span class="badge badge-important">8</span>
							</a>
							<ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="icon-warning-sign"></i>8条通知
								</li>
								<li>
									<a href="#">
										查看所有通知
										<i class="icon-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{
					ace.settings.check('main-container' , 'fixed');
				} catch (e){
					
				}
			</script>
			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="javascript:void(0)">
					<span class="menu-text"></span>
				</a>
				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{
							ace.settings.check('sidebar' , 'fixed');
						} catch(e){
							
						}	
					</script>
					<div class="sidebar-shortcuts" id="sidebar-shortcuts">
						<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
							网站后台管理系统  
						</div>
						<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
							<span class="btn btn-success"></span>
							<span class="btn btn-info"></span>
							<span class="btn btn-warning"></span>
							<span class="btn btn-danger"></span>
						</div>
					</div><!-- #sidebar-shortcuts -->
					<ul class="nav nav-list" id="nav_list">
						<c:forEach items="${fns:getMenuList()}" var="menu">
							<c:choose>
								<c:when test="${menu.open}">
									<li>
										<a class="dropdown-toggle">
											<i class="${menu.iconClass }"></i>
											<span class="menu-text">${menu.menuName} </span>
											<b class="arrow icon-angle-down"></b>
										</a>
										<ul class="submenu">
											<c:forEach items="${menu.childrenList }" var="childrenMenu">
												<li class="home">
													<a name="${childrenMenu.menuUrl }" title="${childrenMenu.menuName }" class="iframeurl">
														<i class="${childrenMenu.iconClass }"></i>${childrenMenu.menuName }
													</a>
												</li>
											</c:forEach>
										</ul>
									</li>
								</c:when>
								<c:otherwise>
									<li class="home">
										<a name="${menu.menuUrl }" class="iframeurl">
											<i class="${menu.iconClass }"></i>
											<span class="menu-text"> ${menu.menuName} </span>
										</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
					<%-- /.nav-list --%>
					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" 
							data-icon2="icon-double-angle-right">
						</i>
					</div>
					<script type="text/javascript">
						try{
								ace.settings.check('sidebar' , 'collapsed');
						} catch(e){
						}
					</script>
				</div>
				<div class="main-content">
					<script type="text/javascript">
						try{
								ace.settings.check('breadcrumbs' , 'fixed');
						} catch(e){
							
						}
					</script>
					<div class="breadcrumbs" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="${fns:getloginPageUrl()}">首页</a>
							</li>
							<li class="active">
								<span class="Current_page iframeurl"></span>
							</li>
							<li class="active" id="parentIframe">
								<span class="parentIframe iframeurl"></span>
							</li>
							<li class="active" id="parentIfour">
								<span class="parentIfour iframeurl"></span>
							</li>
						</ul>
					</div>
					<iframe id="iframe" style="border:0; width:100%; background-color:#FFF;"
						name="iframe" frameborder="0" src="${fns:getHomePageUrl()}"></iframe>
					<!-- /.page-content -->
				</div><!-- /.main-content -->	
					<div class="ace-settings-container" id="ace-settings-container">
						<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
							<i class="icon-cog bigger-150"></i>
						</div>
					<div class="ace-settings-box" id="ace-settings-box">
					<div>
						<div class="pull-left">
							<select id="skin-colorpicker" class="hide">
								<option data-skin="default" value="#438EB9">#438EB9</option>
								<option data-skin="skin-1" value="#222A2D">#222A2D</option>
								<option data-skin="skin-2" value="#C6487E">#C6487E</option>
								<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
							</select>
						</div>
						<span>&nbsp; 选择皮肤</span>
					</div>
					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
						<label class="lbl" for="ace-settings-rtl">切换到左边</label>
					</div>
					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
						<label class="lbl" for="ace-settings-add-container">
							切换窄屏<b></b>
						</label>
					</div>
				</div>
			</div><!-- /#ace-settings-container -->		
		</div><!-- /.main-container-inner -->
	</div>
	<!--底部样式-->
	<div class="footer_style" id="footerstyle">  
		<p class="l_f">版权所有：独泪了无痕</p>
		<p class="r_f">地址：北京市海淀区知春路1号学院国际大厦   技术支持：XXXX</p>
	</div>
</body>
</html>