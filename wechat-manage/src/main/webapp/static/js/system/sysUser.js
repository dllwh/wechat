/**
 * 系统管理--用户管理的单例对象
 */
var sysUser = {
	id : "managerTable",// 表格id
	setItem : null, // 选中的条目
	table : null,
	layerIndex : -1,
	check : function() { // 检查是否选中
		var selected = $('#' + this.id).bootstrapTable('getSelections');
		if (selected.length == 0) {
			return false;
		} else {
			sysUser.setItem = selected[0];
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
	freezeAccountClick : function() { // 冻结用户账户
		if (this.check()) {
		}
	},
	unfreezeClick : function() {// 解除冻结用户账户
		if (this.check()) {
		}
	},
	lockClick : function() {// 锁定用户
		if (this.check()) {
		}
	},
	unlockClick : function() {// 解除锁定
		if (this.check()) {
		}
	},
	searchClick : function() { // 搜索

	},
	refreshClick : function() { // 刷新

	},
	resetSearch : function() {// 重置搜索条件

	},
	roleAssign : function() {// 点击角色分配
		if (this.check()) {
		}
	},
	importClick : function() {// 导入

	},
	exportClick : function() {// 导出

	},
	resetPwdClick : function() {// 重置密码
		if (this.check()) {
		}
	}
};

/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var sysUserInfoDlg = {
	userInfoData : {},
	showRoleSelectTree : function() {// 角色选择的树

	},
	showMenuSelectTree : function() {// 菜单选择的树

	},
	showInfoDeptSelectTree : function() {// 显示用户详情部门选择的树

	},
	validatePwd : function() { // 验证两个密码是否一致

	},
	validate : function() { // 验证数据是否为空

	}
}

/**
 * 创建
 */
sysUser.addSubmitClick = function() {

};

/**
 * 更新
 */
sysUser.editSubmitClick = function() {

};
/**
 * 重置密码
 */
sysUser.resetPwdFun = function() {

};