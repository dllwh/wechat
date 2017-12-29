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