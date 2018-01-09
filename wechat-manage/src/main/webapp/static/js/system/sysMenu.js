/**
 * 菜单管理
 */
var sysMenu = {
	id : "menuTable", // 表格id
	setItem : null, // 选中的条目
	table : null,
	layerIndex : -1,
	check : function() { // 检查是否选中
		var selected = $('#' + this.id).bootstrapTable('getSelections');
		if (selected.length == 0) {
			return false;
		} else {
			sysMenu.setItem = selected[0];
			return true;
		}
	},
	openAddDig : function() {// 点击添加管理员

	},
	openChangeDig : function() {// 点击修改按钮时
		if (this.check()) {
		}
	},
	openDeleleDig : function() {// 点击修改按钮时
		if (this.check()) {
		}
	},
	deleteClick : function() { // 删除
		if (this.check()) {
		}
	},
	enableClick : function() {// 启用
		if (this.check()) {
		}
	},
	disableClick : function() {// 禁用
		if (this.check()) {
		}
	},
	searchClick : function() { // 搜索角色

	},
	refreshClick : function() { // 刷新

	},
	resetSearch : function() {// 重置搜索条件

	}
};
/**
 * 菜单详情对话框
 */
var MenuInfoDlg = {
	showMenuSelectTree : function() { // 显示父级菜单选择的树

	}
}

/**
 * 新建菜单
 */
sysMenu.addSubmitClick = function() {

}

/**
 * 修改菜单
 */
sysMenu.editSubmitClick = function() {

}