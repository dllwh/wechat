/**
 * 设置字体
 */
function setFontCss(treeId, treeNode) {
	if (treeNode.id == 0) { // 根节点
		return {
			color : "#333",
			"font-weight" : "bold"
		};
	} else if (treeNode.isParent == true) {// 父节点
		return {
			color : "#333",
			"font-weight" : "normal",
			'font-weight' : 'bold'
		};
	} else { // 叶子节点
		return {
			"font-weight" : "normal"
		};
	}
}

/**
 * 操作树节点
 * 
 * @param event
 *            标准的JS Event对象
 */
function checkNode(event) {
	var getZTreeObj = $.fn.zTree.getZTreeObj(event.data.treeId);
	var type = event.data.type;// 获取类型
	var nodes = getZTreeObj.getSelectedNodes();// 获取选中的节点

	switch (type) {
	case "checkAllNodes": // 全选
		getZTreeObj.checkAllNodes(true);
		break;
	case "cleanAllNodes": // 清除
		getZTreeObj.checkAllNodes(false);
		break;
	case "expandAll": // 展开全部
		getZTreeObj.expandAll(true);
		break;
	case "collapseAll": // 折叠全部
		getZTreeObj.expandAll(false);
		break;
	case "expandNode":// 展开单个父节点
		break;
	case "collapseNode":// 折叠单个父节点
		break;
	}
}