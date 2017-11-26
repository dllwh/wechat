/**
 * 
 */
var $parentNode = window.parent.document;
function $childNode(name) {
	return window.frames[name]
}

// 折叠ibox
$('.collapse-link').click(function() {
	var ibox = $(this).closest('div.ibox');
	var button = $(this).find('i');
	var content = ibox.find('div.ibox-content');
	content.slideToggle(200);
	button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
	ibox.toggleClass('').toggleClass('border-bottom');
	setTimeout(function() {
		ibox.resize();
		ibox.find('[id^=map-]').resize();
	}, 50);
});

// 关闭ibox
$('.close-link').click(function() {
	var content = $(this).closest('div.ibox');
	content.remove();
});

// 判断当前页面是否在iframe中
if (top == this) {
	var gohome = '<div class="gohome"><a class="animated bounceInUp" href="'
			+ dllwh.getRootPath()
			+ '/loginController.shtml?doLogin" title="返回首页"><i class="fa fa-home"></i></a></div>';
	$('body').append(gohome);
}
