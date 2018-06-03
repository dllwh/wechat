<%-- 让IE低版本浏览器兼容html5元素 --%>
<!--[if lt IE 9]> -->
<script src="${_currConText }/plug-in/assets/js/html5shiv.js"></script>
<script src="${_currConText }/plug-in/assets/js/respond.min.js"></script>
<!-- <![endif]-->

<!--[if !IE]> -->
<script src="${_currConText }/plug-in/jquery/jquery-1.9.1.min.js"></script>
<script src="${_currConText }/plug-in/jquery/jquery.form.js?v=2.77"></script>
<!-- <![endif]-->

<!--[if IE]>
<script src='${_currConText }/plug-in/jquery/jquery-1.10.2.min.js'></script>
<![endif]-->

<%-- 解决IE6png透明，请使用注释方式的方式加载，只有IE6的浏览器才会加载，避免增加其他浏览器的资源冗余 --%>
<!--[if IE 6]>
	<script type="text/javascript" src="${_currConText }/plug-in/assets/js/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('.pngfix,.icon');</script>
<![endif]--> 

<link href="${_currConText }/static/css/common.css?v=1.0" rel="stylesheet">

<script src="${_currConText }/plug-in/jquery-plugs/jquery.validation/jquery.validate.min.js?v=1.14.0"></script>
<script src="${_currConText }/plug-in/jquery-plugs/jquery.validation/messages_zh.min.js?v=1.14.0"></script>

<%-- zTree -- jQuery 树插件 --%>
<link rel="stylesheet" href="${_currConText }/plug-in/jquery-plugs/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${_currConText }/plug-in/jquery-plugs/zTree/js/jquery.ztree.all-3.5.min.js"></script>