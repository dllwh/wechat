/**
 * 基于 bootstrapTable 的扩展
 */
$.fn.bootstrapTableEx = function(opt) {
	var defaults = {
		toolbar:'',
		url : '',
		dataField : "rows",
		method : 'post',
		dataType : 'json',
		selectItemName : 'id',
		smartDisplay : false,
		method: 'get',                      // 请求方式（*）
		toolbar: '',       					// 工具按钮用哪个容器
		iconsPrefix:'glyphicon',            // 定义字体库 ('Glyphicon' or 'fa' for FontAwesome)
		striped: true,                      // 是否显示行间隔色
		cache: false,                       // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		sortable: false,                    // 是否启用排序
		queryParamsType: '',
		queryParams: queryParams,			// 传递参数（*）
		sidePagination: "server",           // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber: 1,                      // 初始化加载第一页，默认第一页
		pageSize: 10,                       // 每页的记录行数（*）
		pageList: [10, 20, 30, 40, 50],     // 可供选择的每页的行数（*）
		strictSearch: true,
		showHeader:true,					// 是否显示列头
		showFooter:false,					// 是否显示列脚
		showColumns: true,                  // 是否显示所有的列
		showRefresh: true,                  // 是否显示刷新按钮
		minimumCountColumns: 2,             // 最少允许的列数
		clickToSelect: true,                // 是否启用点击选中行
		cardView: false,                    // 是否显示详细视图
		detailView: false,                  // 是否显示父子表
		pagination: true,                   // 是否显示分页（*）
		paginationPreText:"上一页",			// 指定分页条中上一页按钮的图标或文字
		paginationNextText:"下一页",			// 指定分页条中下一页按钮的图标或文字
		clickToSelect:true,					// 设置true 将在点击行时，自动选择rediobox 和 checkbox
		singleSelect:true,					// 设置True 将禁止多选
		maintainSelected:false,				// 设置为 true 在点击分页按钮或搜索按钮时，将记住checkbox的选择项
		columns : []
	}
	var option = $.extend({}, defaults, opt);
	$(this).bootstrapTable(option);
}


$.fn.fileinputExt = function(opt) {
	var defaults = {
		uploadUrl : "",//上传的地址
		language : "zh", //设置语言
		uploadAsync : true, //异步上传
		showCaption : true, //是否显示标题
		showUpload : true, //是否显示上传按钮
		showRemove : true, //是否显示移除按钮
		showPreview : true, //是否显示预览按钮
		showCancel:true,//是否显示取消按钮
		showClose:true,//是否显示关闭按钮
		browseClass : "btn btn-primary", //按钮样式 
		dropZoneEnabled : false, //是否显示拖拽区域
		minImageWidth: null, //图片的最小宽度
		minImageHeight: null,//图片的最小高度
		maxImageWidth: null,//图片的最大宽度
		maxImageHeight: null,//图片的最大高度
		maxFileSize : 20480, //单位为kb，如果为0表示不限制文件大小
		minFileSize : 0,// 单位为kb，上传文件的最小大小值
		allowedPreviewTypes : null,
		// allowedFileTypes: ['image','html','text','video','audio','flash','object'],//文件允许的类型
		allowedFileTypes: null,
		// allowedFileExtensions : [ "xls", "xlsx", "ppt", "pptx", "doc", "docx" ], //接收的文件后缀
		allowedFileExtensions : null, //接收的文件后缀
		maxFileCount : 10, // 表示允许同时上传的最大文件个数
		enctype: 'multipart/form-data',
		validateInitialCount:true,
		previewFileIcon : '<i class="glyphicon glyphicon-file"></i>',
		msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！"
	}
	var option = $.extend({}, defaults, opt);
	$(this).fileinput(option);
}

/**
 * 获取查询的参数 
 */
function queryParams(params) {
	//这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	var temp = {   
		rows: params.limit,                         //页面大小
		page: (params.offset / params.limit) + 1,   //页码
		sort: params.sort,      //排序列名  
		sortOrder: params.order //排位命令（desc，asc） 
	};
	return temp;
}