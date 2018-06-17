<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/layui.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/bootstrap.jsp"%>
<link href="${_currConText }/static/css/sample/style.css?v=4.1.0" rel="stylesheet">
<title>文件管理</title>
<style type="text/css">
/* imgTpl */
#imgTpl {
	position: absolute;
	border: 1px solid #ccc;
	background: #333;
	padding: 2px;
	display: none;
	color: #fff;
}
</style>
</head>
<body>

	<fieldset class="layui-elem-field site-demo-button"
		style="margin-top: 30px; border: 0">
		<div class="layui-btn-group demoTable">
			<button type="button" class="layui-btn layui-btn-sm" id="uploadFile">
				<i class="layui-icon"></i>上传压缩文件
			</button>
		</div>
	</fieldset>
	<table class="layui-table" lay-filter="attachmentTableFilter" id="attachmentTable"
		lay-skin="row" lay-size="md">
	</table>
</body>

<script type="text/html" id="imgTpl">
	<a class="imgTpl">
		<img src="{{ d.path }}/{{ d.name }}" width="20" height="20">
	</a>
</script>

<script type="text/html" id="operateBar">
	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
	<a class="layui-btn layui-btn-xs" data-id="{{ d.id }}" lay-event="open">审核</a>
	<a class="layui-btn layui-btn-xs" data-id="{{ d.id }}" id="download{{ d.id }}" lay-event="download">下载</a>
	<button class="layui-btn layui-btn-xs layui-btn-danger" id="{{ d.id }}" lay-event="delete">删除</button>
</script>

<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>

<script type="text/javascript">
	var table = layui.table;
	
	var tableOptions  = {
		elem : '#attachmentTable',
		page : true,
		url : '${_currConText }/attachment/index.shtml?getList',
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
		text: {
			none: '无法获取更多数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
		},
		cols : [ [
			{
				field : 'id',
				title : '编号',
				width : 60,
				fixed:'left'
			}, {
				field : 'path',
				title : '路径',
				templet : '#imgTpl',
				event: 'preview',
				width : '5%'
			}, {
				field : 'state',
				title : '状态',
				width : '5%',
				templet : function(rowData){
					if(rowData.type == 0){
						return '<span class="layui-badge">未审核</span>';
					}else if(rowData.type == 1){
						return '<span class="layui-badge">已审核</span>';
					}else if(rowData.type == 2){
						return '<span class="layui-badge">审核未通过</span>';
					}else if(rowData.type == -1){
						return '<span class="layui-badge">已删除</span>';
					}else {
						return '<span class="layui-badge">待审核</span>';
					}
				}
			}, {
				field : 'type',
				title : '类型',
				width : '10%',
				templet : function(d){
					if(d.type == 1){
						return "音视频文件";
					} else if(d.type == 2){
						return "文档文件";
					} else if(d.type == 3){
						return "压缩文件";
					} else if(d.type == 4){
						return "图形文件";
					} else {
						return "--";
					}
				}
			}, {
				field : 'size',
				title : '大小',
				width : 100,
				templet : function(rowData){
					if(dllwh.isNullOrEmpty(rowData.size)){
						return 0;
					} else {
						return rowData.size;
					}
				}
			}, {
				field : 'format',
				title : '格式',
				width : 100
			}, {
				field : 'uploadUser',
				title : '上传id',
				width : '5%'
			}, {
				field : 'uploadIp',
				title : '上传IP',
				width : '10%'
			}, {
				field : 'uploadTime',
				title : '上传时间',
				templet : function(rowData){
					return dllwh.genStrDateTime(rowData.uploadTime);
				},
				width : '10%'
			}, {
				field : 'auditUser',
				title : '审核者',
				width : '5%'
			}, {
				field : 'auditTime',
				title : '审核时间',
				templet : '<div>{{dllwh.genStrDateTime(d.auditTime) }}</div>',
				width : '10%'
			}, {
				field : 'downloaded',
				title : '已下载',
				width : '5%',
				templet : function(rowData){
					if(dllwh.isNullOrEmpty(rowData.downloaded)){
						return 0;
					} else {
						return rowData.downloaded;
					}
				}
			}, {
				field : 'operate',
				title : '操作',
				toolbar : '#operateBar',
				fixed:'right'
			} 
		] ]
	};

	layui.use('upload', function() {
		var $ = layui.jquery
		,upload = layui.upload;
		upload.render({
			elem: '#uploadFile'
		});
	});
	layui.use('table', function() {
		table.render(tableOptions);

		//监听工具条
		table.on('tool(attachmentTableFilter)', function(obj) {
			var data = obj.data;

			if (obj.event === 'preview') {
				
			} else if (obj.event === 'detail') {
				
			} else if (obj.event === 'open') {
				var id = $(this).attr('data-id');
				layer.msg('文件审核', {
					time : 20000,
					btn : [ '仁慈通过', '残忍拒绝', '再想想' ],
					yes : function(index, layero) {
						dialogMsg("仁慈通过");
					},
					btn2 : function(index, layero) {
						dialogMsg("残忍拒绝");
					}
				});
			} else if (obj.event === 'download') {
				var data_id = $(this).attr('data-id');
				var id = $(this).attr('id');
				var download = document.getElementById(id);
				layer.msg("点击了下载");
			} else if (obj.event === 'delete') {
				layer.confirm('确定要删除?', function(index) {
					layer.close(index);
				});
			}
		});

	});

	$(function() {
		var x = 10;
		var y = 20;
		
		$(".imgTpl").mouseover(function(e) {
			var tooltip = "<div id='tooltip'><img src='"+ this.href +"' alt='预览图' height='200'/>"
					+ "<\/div>"; //创建 div 元素
			$("body").append(tooltip); //把它追加到文档中             
			$("#imgTpl").css({
				"top" : (e.pageY + y) + "px",
				"left" : (e.pageX + x) + "px"
			}).show("fast"); //设置x坐标和y坐标，并且显示
		}).mouseout(function() {
			$("#imgTpl").remove(); //移除 
		}).mousemove(function(e) {
			$("#imgTpl").css({
				"top" : (e.pageY + y) + "px",
				"left" : (e.pageX + x) + "px"
			});
		});
	});
</script>
</html>