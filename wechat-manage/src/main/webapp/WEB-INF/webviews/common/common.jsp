<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<%-- 自定义标签:字典接口 --%>
<%-- <%@ taglib prefix="dict" tagdir="/WEB-INF/tags/dict" %> --%>
<%-- 自定义标签:登录信息 --%>
<%@ taglib prefix="login" tagdir="/WEB-INF/tags/login"%>
<%@ taglib prefix="echarts" tagdir="/WEB-INF/tags/echarts"%>
<%-- 自定义标签:常用工具类 --%>
<%@ taglib prefix="utilHelper" tagdir="/WEB-INF/tags/utilHelper"%>
<%-- 自定义标签:页面工具类 --%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>
<!-- 自定义标签:easyui  -->
<%@ taglib prefix="easyui" uri="/easyui-tags"%>


<%-- 这3句每个页面必须添加 --%>
<meta http-equiv="content-type" content="text/html;chartset=UTF-8">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<%-- 这4句是避免页面缓存（需要时添加） --%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="expires" content="wed, 26 feb 1997 08:21:57 gmt">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%-- 当前Context --%>
<c:set var="_currConText" value="<%=request.getContextPath() %>" />
<%-- 项目名称--%>
<c:set var="_currProject" value="综合信息后台管理系统" />
<%-- 网页标题图标  --%>
<link rel='icon' href='${_currConText }/static/image/common/webSite.ico' type='image/x-ico' />