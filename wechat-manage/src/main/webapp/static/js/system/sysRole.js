/**
 * 角色管理的单例
 */
var sysRole = {
	id : "roleTable", // 表格id
	seItem : null, // 选中的条目
	table : null,
	layerIndex : -1,
	check : function() { // 检查是否选中
		var selected = $('#' + this.id).bootstrapTable('getSelections');
		if (selected.length == 0) {
			return false;
		} else {
			sysRole.seItem = selected[0];
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
	validate : function() {// 验证数据是否为空

	},
	searchClick : function() { // 搜索

	},
	refreshClick : function() { // 刷新

	},
	resetSearch : function() {// 重置搜索条件

	}
};

/**
 * 提交修改
 */
sysRole.editSubmit = function() {

};

/**
 * 删除角色
 */
sysRole.deleteClick = function() {

};

/**
 * 权限配置
 */
sysRole.assign = function() {

};

/**
 * 禁用
 */
sysRole.disableClick = function() {

};

/**
 * 启用
 */
sysRole.enableClick = function() {

};
