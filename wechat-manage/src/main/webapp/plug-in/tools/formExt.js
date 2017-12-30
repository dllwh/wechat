/**
 * 
 */
$.SaveForm = function(options) {
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

$.RemoveForm = function(options) {
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

$.SetForm = function(options) {
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

$.ConfirmForm = function(options) {
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

$.ConfirmAjax = function(options) {
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