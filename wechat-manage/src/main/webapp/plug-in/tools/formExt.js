/**
 * 
 */
$.fn.saveForm = function(options) {
	var defaults = {
		url : "",
		param : {},
		type : "post",
		dataType : "json",
		contentType : 'application/json',
		success : null,
		close : true
	};

	var options = $.extend(defaults, options);
	window.setTimeout(function() {
		$.ajax({
			url : options.url,
			data : JSON.stringify(options.param),
			type : options.type,
			dataType : options.dataType,
			contentType : options.contentType,
			success : function(data) {
				options.success(data);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				if(textStatus=="parsererror"){
					// 注：登录超时,请稍后重新登录.
				} else if(textStatus=="error") {
					// 请求超时，请稍候重试...
				} else {
					alert(errorThrown);
				}
			},
			beforeSend : function() {

			},
			complete : function() {

			}
		}, 500);
	});
}

$.fn.removeForm = function(options) {
	var defaults = {
		msg : "注：您确定要删除吗？该操作将无法恢复",
		url : "",
		param : [],
		type : "post",
		dataType : "json",
		contentType : 'application/json',
		success : null
	};
	var options = $.extend(defaults, options);
	window.setTimeout(function() {
		$.ajax({
			url : options.url,
			data : JSON.stringify(options.param),
			type : options.type,
			dataType : options.dataType,
			contentType : options.contentType,
			success : function(data) {
				options.success(data);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				if(textStatus=="parsererror"){
					// 注：登录超时,请稍后重新登录.
				} else if(textStatus=="error") {
					// 请求超时，请稍候重试...
				} else {
					alert(errorThrown);
				}
			},
			beforeSend : function() {

			},
			complete : function() {

			}
		}, 500);
	});
}

$.fn.setForm = function(options) {
	var defaults = {
		url : "",
		param : [],
		type : "post",
		dataType : "json",
		contentType : 'application/json',
		success : null
	};
	var options = $.extend(defaults, options);
	window.setTimeout(function() {
		$.ajax({
			url : options.url,
			data : JSON.stringify(options.param),
			type : options.type,
			dataType : options.dataType,
			contentType : options.contentType,
			success : function(data) {
				options.success(data);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				if(textStatus=="parsererror"){
					// 注：登录超时,请稍后重新登录.
				} else if(textStatus=="error") {
					// 请求超时，请稍候重试...
				} else {
					alert(errorThrown);
				}
			},
			beforeSend : function() {

			},
			complete : function() {

			}
		}, 500);
	});
}

$.fn.confirmForm = function(options) {
	onfirmForm = function(options) {
		var defaults = {
			msg : "您确定要保存当前数据项修改操作吗？",
			url : "",
			param : {},
			type : "post",
			dataType : "json",
			contentType : 'application/json',
			success : null,
			close : true
		};
	}
	var options = $.extend(defaults, options);
	$.SaveForm(options);
}

$.fn.confirmAjax = function(options) {
	var defaults = {
		msg : "您确定要保存当前操作结果吗？",
		url : "",
		param : {},
		type : "post",
		dataType : "json",
		contentType : options.contentType,
		success : null,
		close : true
	};

	var options = $.extend(defaults, options);
	window.setTimeout(function() {
		$.ajax({
			url : options.url,
			data : JSON.stringify(options.param),
			type : options.type,
			dataType : options.dataType,
			contentType : options.contentType,
			success : function(data) {
				options.success(data);
				alert();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(textStatus);
			},
			beforeSend : function() {

			},
			complete : function() {

			}
		}, 500);
	});
}

/**
 * 通过调用表单元素原有的DOM方法，将表单恢复到初始状态
 * 
 * eg:
 * 	$("#fromID").resetForm();
 */
$.fn.resetForm = function() {
	return this.each(function() {
		if (typeof this.reset == 'function'
				|| (typeof this.reset == 'object' && !this.reset.nodeType)) {
			this.reset();
		}
	});
}

/**
 * 清除表单元素
 * 
 * 该方法将所有的文本（text）输入字段、密码（password）输入字段和文本区域（textarea）字段置空
 * 清除任何select元素中的选定，以及将所有的单选（radio）按钮和多选（checkbox）按钮重置为非选定状态
 */
$.clearForm = function() {

}