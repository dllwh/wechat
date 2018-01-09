/**
 * 数据字典
 */
var Dict = {
	id : "DictTable", // 表格id
	setItem : null, // 选中的条目
	table : null,
	layerIndex : -1,
	check : function() { // 检查是否选中
		var selected = $('#' + this.id).bootstrapTable('getSelections');
		if (selected.length == 0) {
			return false;
		} else {
			Dict.setItem = selected[0];
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