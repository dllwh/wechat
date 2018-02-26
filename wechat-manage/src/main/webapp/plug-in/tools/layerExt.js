/**
 * 基于layer的封装
 */


var dialogOpen = function(opt){
	var defaults = {
		id : 'layerForm',
		title : '',
		width : '',
		height : '',
		url : null,
		scroll : false,
		data : {},
		maxmin : false,
		btn : [ '确定', '取消' ],
		success : function() {
		},
		yes : function() {
		}
	}
	
	var option = $.extend({}, defaults, opt);
	
	if(option.scroll){
		content = [opt.url]
	}else{
		content = [opt.url, 'no']
	}
	

	layer.open({
		id : option.id,
		type : 2,
		title : option.title,
		shadeClose : true,
		move : false,
		shade : 0.3,
		anim : -1,
		isOutAnim : false,
		resize : false,
		shadeClose : false,
		closeBtn : 2,
		area : [ option.width, option.height ],
		btn : option.btn,
		maxmin : option.maxmin,
		content : content,
		success : option.success,
		yes : option.yes
	}); 
}

/**
 * @方法描述 原始核心方法:页面层
 */
var dialogContentNoBtn = function(opt) {
	var defaults = {
		width : '',
		height : '',
		content : '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">你知道吗？亲！<br><br><br><br>我们此后的征途是星辰大海 ^_^</div>',
		data : {},
		btn : [ '确定', '取消' ],
		success : null,
		yes : null,
		shade : 0.8
	};

	var options = $.extend({}, defaults, opt);
	layer.open({
		type : 1,
		id : "i love Confidante",// 设定一个id，防止重复弹出
		title : false,// 不显示标题栏
		closeBtn : true,
		anim : -1,
		icon : options.icon,
		isOutAnim : false,
		shadeClose : false,
		shade : options.shade,
		area : options.area,
		move : false,
		shift : 5,
		btnAlign : 'c',
		moveType : 1,
		content : options.content,
		success : options.success,
		yes : options.yes
	});
}

/**
 * @方法描述 原始核心方法:Ajax获取
 */
var dialogAjax = function(opt) {
	var defaults = {
		title : '系统窗口',
		width : '',
		height : '',
		url : null,
		data : {},
		btn : [ '确定', '取消' ],
		success : null,
		yes : null
	};

	var options = $.extend({}, defaults, opt);
}

/**
 * 普通信息框
 * 
 * @param content
 *            内容
 * @param type
 *            内容 iconType
 */
var dialogAlert = function(content, iconType) {
	var msgType = {
		success : 1,
		error : 2,
		warn : 3,
		info : 7
	};
	if (dllwh.isNullOrEmpty(iconType)) {
		iconType = "info";
	}
	layer.alert(content, {
		icon : msgType[iconType],
		title : "系统提示",
		anim : -1,
		btnAlign : 'c',
		move : false,
		isOutAnim : false
	});
}

/**
 * 询问框
 * 
 * @param content
 * @param callBack
 *            “确认”回调事件
 */
var dialogConfirm = function(content, callBack) {
	layer.confirm(content, {
		area : '338px',
		icon : 7,
		anim : -1,
		isOutAnim : false,
		title : "系统提示",
		btn : [ '确认', '取消' ],
		btnAlign : 'c',
		move : false,
		yes : callBack
	});
}

/**
 * 提示框
 */
var dialogMsg = function(msg, type) {
	var msgType = {
		success : 1,
		error : 2,
		warn : 3,
		info : 7
	};
	if (dllwh.isNullOrEmpty(type)) {
		type = 'info';
	}
	layer.msg(msg, {
		move : false,
		icon : msgType[type],
		time : 3000
	});
}

/**
 * 关闭特定层
 */
var dialogClose = function() {
	var index = top.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
	top.layer.close(index); // 再执行关闭
}

/**
 * 关闭所有层
 */
var dialogCloseAll = function(opt) {
	if (dllwh.isNullOrEmpty(opt)) {
		layer.closeAll();// 疯狂模式，关闭所有层
	} else {
		layer.closeAll(opt);
	}
}
/**
 * 加载层
 */
var dialogLoading = function(flag) {
	if (flag) {
		layer.load(0, {
			shade : [ 0.1, '#fff' ],
			time : 2000
		});
	} else {
		dialogCloseAll('loading');
	}
}

var dialogFullScreen = function() {
}