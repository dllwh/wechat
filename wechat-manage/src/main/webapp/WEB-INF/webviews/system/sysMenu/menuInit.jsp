<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<title>菜单管理</title>
</head>
<body>菜单管理
</body>
<script type="text/javascript">
	/**
	 * 菜单管理
	 */
	var sysMenuController = {
		id : "menuTable", // 表格id
		setItem : null, // 选中的条目
		table : null,
		layerIndex : -1,
		check : function() { // 检查是否选中
			var selected = $('#' + this.id).bootstrapTable('getSelections');
			if (selected == undefined || selected == "" || selected == 'null' || selected == 'undefined') {
				// "您没有选中任何数据项！
				return false;
			}
			
			if (selected.length == 0) {
				return false;
			} else {
				sysMenuController.setItem = selected[0];
				return true;
			}
		},
		openAddDig : function() {// 点击添加
	
		},
		openChangeDig : function() {// 点击修改按钮
			if (this.check()) {
			}
		},
		openDeleleDig : function() {// 点击删除按钮
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
	sysMenuController.addSubmitClick = function() {
	
	}
	
	/**
	 * 修改菜单
	 */
	sysMenuController.editSubmitClick = function() {
	
	}
</script>
</html>