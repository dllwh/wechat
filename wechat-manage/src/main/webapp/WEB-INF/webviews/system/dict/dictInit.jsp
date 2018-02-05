<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<title>数据字典</title>
</head>
<body>

</body>

<script type="text/javascript">
	var DictController = {
		id : "dictTable", // 表格id
		setItem : null, // 选中的条目
		table : null,
		layerIndex : -1,
		check : function() { // 检查是否选中
			var selected = $('#' + this.id).bootstrapTable('getSelections');
			if (selected == undefined || selected == "" || selected == 'null'
					|| selected == 'undefined') {
				// "您没有选中任何数据项！"
				return false;
			}
			if (selected.length > 1) {
				return false;
			} else {
				DictController.setItem = selected[0];
				return true;
			}
		},
		openAddDig : function() {// 点击添加

		},
		openChangeDig : function() {// 点击修改按钮时
			if (this.check()) {
			}
		},
		delClickFun : function() {// 点击删除按钮
			if (this.check()) {
			}
		},
		searchClick : function() { // 搜索

		},
		resetSearch : function() {// 重置搜索条件

		}
	};
</script>
</html>