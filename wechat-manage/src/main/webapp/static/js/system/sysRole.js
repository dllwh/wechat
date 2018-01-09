/**
 * 角色管理的单例
 */
var sysRole = {
	id : "roleTable", // 表格id
	setItem : null, // 选中的条目
	table : null,
	layerIndex : -1,
	check : function() { // 检查是否选中
		var selected = $('#' + this.id).bootstrapTable('getSelections');
		if (selected.length == 0) {
			return false;
		} else {
			sysRole.setItem = selected[0];
			return true;
		}
	},
	openAddDig : function() {// 点击添加

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
	searchClick : function() { // 搜索

	},
	assign : function() {// 权限配置

	},
	refreshClick : function() { // 刷新

	},
	resetSearch : function() {// 重置搜索条件

	}
};

/**
 * 提交修改
 */
sysRole.addSubmitClick = function() {

};
/**
 * 提交修改
 */
sysRole.editSubmit = function() {

};
