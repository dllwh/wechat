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
	var defaultConfig = {
		uploadUrl : "",//上传的地址
		language : "zh", //设置语言
		enctype: 'multipart/form-data',
		uploadAsync : true, // false 同步上传，后台用数组接收，true 异步上传，每次上传一个file,会调用多次接口  
		showCaption : true, // 是否显示标题
		showUpload : true, // 是否显示上传按钮
		showRemove : true, // 是否显示移除按钮
		showPreview : true, // 是否显示预览按钮
		showCancel : true,// 是否显示取消按钮
		showClose : true,// 是否显示关闭按钮
		showBrowse : true,// 是否显示浏览按钮
		browseOnZoneClick: true,
		// deleteUrl:"",// 删除图片时的请求路径
		// deleteExtraData:"",// 删除图片时额外传入的参数
		uploadExtraData:{
			// 上传文件时额外传递的参数设置
		},
		uploadLabel: "上传",//设置上传按钮的汉字
		browseClass : "btn btn-primary", //按钮样式 
		dropZoneEnabled : false, //是否显示拖拽区域
		minImageWidth: null, //图片的最小宽度
		minImageHeight: null,//图片的最小高度
		maxImageWidth: null,//图片的最大宽度
		maxImageHeight: null,//图片的最大高度
		maxFileSize : 20*10*1024, //单位为kb，如果为0表示不限制文件大小
		minFileSize : 0,// 单位为kb，上传文件的最小大小值
		// previewFileType:"", // 预览文件类型
		// allowedFileTypes: ['image','html','text','video','audio','flash','object'],//文件允许的类型
		allowedFileExtensions : [ "xls", "xlsx", "ppt", "pptx", "doc", "docx"], //接收的文件后缀
		maxFileCount : 1, // 表示允许同时上传的最大文件个数
		validateInitialCount:true,
		overwriteInitial: true,
		removeFromPreviewOnError:true,// 当选择的文件不符合规则时,选择的文件不会出现在预览框中,只会显示错误信息   
		previewFileIcon : '<i class="fa fa-file"></i>',
		msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
		initialPreview : {},// 配置需要初始展示的图片字符串类型的数组 
		initialPreviewConfig:{},// 配置预览中的一些参数
		layoutTemplates :{
			actionDelete:'', //去除上传预览的缩略图中的删除图标
			actionUpload:'',//去除上传预览缩略图中的上传图片；
			actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
		},
		previewFileExtSettings : {
			'doc': function(ext) {
				return ext.match(/(doc|docx)$/i);
			},
			'xls' : function(ext) {
				return ext.match(/(xls|xlsx)$/i);
			},
			'ppt' : function(ext) {
				return ext.match(/(ppt|pptx)$/i);
			},
			'zip' : function(ext) {
				return ext.match(/(zip|rar|tar|gzip|gz|7z)$/i);
			},
			'htm' : function(ext) {
				return ext.match(/(php|js|css|htm|html)$/i);
			},
			'txt' : function(ext) {
				return ext.match(/(txt|ini|md)$/i);
			},
			'mov' : function(ext) {
				return ext.match(/(avi|mpg|mkv|mov|mp4|3gp|webm|wmv)$/i);
			},
			'mp3' : function(ext) {
				return ext.match(/(mp3|wav)$/i);
			}
		},
		previewFileIconSettings: { // configure your icon file extensions
			"doc"     :'<i class="fa fa-file-word-o text-primary"></i>',
			"docx"    :'<i class="fa fa-file-word-o text-primary"></i>',
			"xls"     :'<i class="fa fa-file-excel-o text-success"></i>',
			"xlsx"    :'<i class="fa fa-file-excel-o text-success"></i>',
			"ppt"     :'<i class="fa fa-file-powerpoint-o text-danger"></i>',
			"pptx"    :'<i class="fa fa-file-powerpoint-o text-danger"></i>',
			"jpg"     :'<i class="fa fa-file-photo-o text-warning"></i>',
			"pdf"     :'<i class="fa fa-file-pdf-o text-danger"></i>',
			"zip"     :'<i class="fa fa-file-archive-o text-muted"></i>',
			"rar"     :'<i class="fa fa-file-archive-o text-muted"></i>',
			'pdf'     :'<i class="fa fa-file-pdf-o text-danger"></i>',  
			'gif'     :'<i class="fa fa-file-photo-o text-muted"></i>',  
			'png'     :'<i class="fa fa-file-photo-o text-primary"></i>', 
			'htm'     :'<i class="fa fa-file-code-o text-info"></i>',
			'txt'     :'<i class="fa fa-file-text-o text-info"></i>',
			'mov'     :'<i class="fa fa-file-movie-o text-warning"></i>',
			'mp3'     :'<i class="fa fa-file-audio-o text-warning"></i>',
			"default" :"<i class='fa fa-file-o'></i>"
		}
	};
	
	
	var option = $.extend({}, defaultConfig, opt);
	
	$(this).fileinput(option);
	
	// 选择文件后处理事件
	$(this).on("filebatchselected", function(event, files) {
		if(dllwh.isExitsFunction('filebatchselected')){
			$(this).fileinput("upload");
		}
		console.log("选择文件后处理事件");
	});
	
	// 删除预处理（删除之前想要做什么事）
	$(this).on("filepredelete",function(event, key){
		if(dllwh.isExitsFunction('filepredelete')){
			
		}
		console.log("删除预处理（删除之前想要做什么事）");
	});
	
	
	// 删除后的处理（删除图片时想要做什么事）
	$(this).on("filedeleted",function(event, key){
		if(dllwh.isExitsFunction('filedeleted')){
			
		}
		console.log("删除后的处理（删除图片时想要做什么事）");
	});
	
	// 删除回调事件
	$(this).on("filesuccessremove",function(event, key){
		if(dllwh.isExitsFunction('filesuccessremove')){
			
		}
		console.log("删除回调事件");
	});
	
	// 上传之前
	$(this).on("filepreupload", function(event, data, previewId, index) {
		
	});
	
	// 异步上传错误结果处理
	$(this).on("fileerror", function(event, data, msg) {
		if(dllwh.isExitsFunction('fileError')){
			
		}
		
		console.log("异步上传错误结果处理");
	});
	
	// 上传失败的回调函数
	$(this).on("fileuploaded", function(event, data, previewId, index) {
		console.log("上传失败的回调函数");
	});
	
	$(this).on("fileuploaded", function(event, data, previewId, index) {
		// data: 这是一个数据对象(关联数组)发送以下信息 
		// files: 上传的文件信息    
		// response: ajax后台服务响应的内容
		// previewId:每个文件的标识符的母公司缩略图预览窗口中的div元素
		// index: 从零开始的索引文件中文件的堆栈
		if(dllwh.isExitsFunction('filePreupload')){
			
		}
		if (data == undefined) {
			alert("文件格式类型不正确"); 
			return;
		}
			// 提示用户Excel格式是否正常
			// 如果正常, 1.关闭弹出层;
			// 2.清空记录显示
			// $(this).fileinput("clear");//清空所有文件
			// 3.刷新主列表
		console.log("异步上传成功结果处理");
	});
	
	// 小图标上传成功后移除触发
	$(this).on("filesuccessremove",function(event, data){
		
	});
	
	// 同步上传成功结果处理
	$(this).on("filebatchuploadsuccess",function(event, data){
		if(dllwh.isExitsFunction('fileBatchuploadSuccess')){
			
		}
		console.log("同步上传成功结果处理");
	});
	
	// 同步上传错误结果处理
	$(this).on('filebatchuploaderror', function(event, data, msg) {
		if(dllwh.isExitsFunction('fileBatchuploadError')){
			
		}
		console.log("同步上传错误结果处理");
	});
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