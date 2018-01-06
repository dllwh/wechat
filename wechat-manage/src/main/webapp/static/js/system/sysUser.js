/**
 * 系统管理--用户管理的单例对象
 */
var sysUser = {
	id : "managerTable",// 表格id
	seItem : null, // 选中的条目
	table : null,
	layerIndex : -1,
	check : function() { // 检查是否选中
		var selected = $('#' + this.id).bootstrapTable('getSelections');
		if (selected.length == 0) {
			return false;
		} else {
			sysUser.seItem = selected[0];
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

	},
	roleAssign : function() {// 点击角色分配
		if (this.check()) {
		}
	},
	importClick : function() {// 导入

	},
	exportClick : function() {// 导出

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
 * 删除用户
 */
sysUser.deleteClick = function() {
	if (this.check()) {
	}
};

/**
 * 冻结用户账户
 * 
 * @param userId
 */
sysUser.freezeAccountClick = function() {
	if (this.check()) {
	}
};

/**
 * 解除冻结用户账户
 * 
 * @param userId
 */
sysUser.unfreezeClick = function() {
	if (this.check()) {
	}
}

/**
 * 重置密码
 */
sysUser.resetPwdClick = function() {
	if (this.check()) {
	}
};

/**
 * 启用
 */
sysUser.enableUserClick = function() {
	if (this.check()) {
	}
}

/**
 * 禁用
 */
sysUser.disableUserClick = function() {
	if (this.check()) {
	}
};

/**
 * 用户审核
 */
sysUser.auditClickFun = function() {
	if (this.check()) {
	}
}

/**
 * 锁定过户
 */
sysUser.lockClick = function() {
	if (this.check()) {
	}
}

/**
 * 解锁用户
 */
sysUser.unlockClick = function() {
	if (this.check()) {
	}
};