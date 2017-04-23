/**
 * 扩展javsScript工具函数
 */
var dllwh = dllwh || {};
dllwh.data = dllwh.data || {};// 用于存放临时的数据或者对象

/**
 * js获取项目根路径
 */
dllwh.getRootPath = function() {
	// 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	// 获取主机地址，如： http://localhost:8083
	var localhostPaht = curWwwPath.substring(0, pos);
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
}

/**
 * 判断是否为空 不是一个空对象，或者未定义，或者不等于空字符串
 */
dllwh.isNull = function(exp) {
	if (exp !== null && typeof (exp) !== undefined && exp !== '')
		return false;
	return true;
}

// 时间设置
dllwh.currentTime = function() {
	var d = new Date(), str = '';
	str += d.getFullYear() + '年';
	str += d.getMonth() + 1 + '月';
	str += d.getDate() + '日';
	str += d.getHours() + '时';
	str += d.getMinutes() + '分';
	str += d.getSeconds() + '秒';
	return str;
}