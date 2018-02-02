/**
 * 
 */
var setting = {
	view : {// 表示tree的显示状态
		dblClickExpand : false,// 双击节点时，是否自动展开父节点的标识
		showLine : true,// 是否显示节点之间的连线
		showIcon : true,// 是否显示节点的图标
		showTitle : true,// 是否显示节点的 title 提示信息
		selectedMulti : true,// 设置是否允许同时选中多个节点
		txtSelectedEnable : true
	// 是否允许可以选择 zTree DOM 内的文本
	},
	data : {// 表示tree的数据格式
		simpleData : {
			enable : true, // 使用简单数据模式
			idKey : "id",// 节点数据中保存唯一标识的属性名称
			pIdKey : "pId",// 节点数据中保存其父节点唯一标识的属性名称
			rootPId : "" // 用于修正根节点父节点数据 默认值：null
		}
	},
	check : {// 表示tree的节点在点击时的相关设置
		enable : true,// 设置 zTree 的节点上是否显示 checkbox / radio
		chkStyle : "checkbox",// 勾选框类型(checkbox 或 radio）
		chkboxType : {
			"Y" : "p",
			"N" : "s"
		}
	},
	callback : {
		beforeClick : beforeClick,
		onDblClick : onDblClick, // 定义节点双击事件回调函数
		beforeRename : beforeRenameClick,
		onClick : onClickFun,// 定义节点单击事件回调函数
		beforeRightClick : beforeRightClick,
		onRightClick : onRightClickFun,// 定义节点右键单击事件回调函数
		onCheck : onCheckFun
	// 定义节点复选框选中或取消选中事件的回调函数
	},
	async : {
		enable : true, // 设置启用异步加载
		type : "get", // 异步加载类型:post和get
		contentType : "application/json", // 定义ajax提交参数的参数类型，一般为json格式
		url : "",
		autoParam : [// 定义提交时参数的名称，=号前面标识节点属性

		]
	}
};

/**
 * 用于捕获 勾选 或 取消勾选 之前的事件回调函数，并且根据返回值确定是否允许 勾选 或 取消勾选
 * 
 * @param treeId
 *            对应Ztree的treeId
 * @param treeNode
 *            被点击的节点JSON数据对象
 * @return false： 将不会改变勾选状态
 */
function beforeClick(treeId, treeNode) {
	return treeNode.check != false;
}

function beforeRenameClick(treeId, treeNode) {

}

function onDblClick(treeId, treeNode) {

}
/**
 * 点击之前进行判断当前节点是否为父节点，若不是则进行提示
 * 
 * @param treeId
 *            对应Ztree的treeId
 * @param treeNode
 *            被点击的节点JSON数据对象
 * @return true： 选中当前节点 false：不选中
 */
function beforeClickFun(treeId, treeNode) {
	if (treeNode.isParent) {// 如果不是叶子结点，结束
		return false;
	} else {
		return true;
	}
}

/**
 * 点击之前进行判断当前节点是否为父节点，若不是则进行提示
 * 
 * @param treeId
 *            对应Ztree的treeId
 * @param treeNode
 *            被点击的节点JSON数据对象
 * @return true： 选中当前节点 false：不选中
 */
function beforeRightClick(treeId, treeNode) {
	if (treeNode.isParent) {// 如果不是叶子结点，结束
		return false;
	} else {
		return true;
	}
}

/**
 * 点击展开的父节点时触发的事件：判断当前节点是否处于折叠状态，若是则不予处理，否则进行折叠
 * 
 * @param treeId
 *            对应zTree的treeId
 * @param treeNode
 *            要折叠的父节点JSON数据对象
 * @return true-进行折叠， false-不折叠
 */
function beforeCollapse(treeId, treeNode) {
	return treeNode.collapse !== false;
}

/**
 * 点击折叠的父节点时触发的事件：判断当前节点是否处于展开状态，则进行折叠，否则不予处理
 * 
 * @param treeId
 *            对应zTree的treeId
 * @param treeNode
 *            要展开的父节点JSON数据对象
 * @return true-进行展开， false-不展开
 */
function beforeExpand(treeId, treeNode) {
	return treeNode.expand !== false;
}

/**
 * 用于捕获节点被删除之前的事件回调函数，并且根据返回值确定是否允许删除操作
 * 
 * @param treeId
 *            对应zTree的treeId
 * @param treeNode
 *            将要删除的节点 JSON 数据对象
 * @return true-删除节点， false-不删除节点
 */
function beforeRemove(treeId, treeNode) {
	return false;
}

/**
 * 用于捕获节点被点击的事件回调函数
 * 
 * @param event
 *            标准的 js event 对象
 * @param treeId
 *            对应zTree的treeId
 * @param treeNode
 *            将要删除的节点 JSON 数据对象
 * @return true-删除节点， false-不删除节点
 */
function onClickFun(event, treeId, treeNode) {

}

/**
 * 用于捕获节点鼠标右键点击之后的事件回调函数
 * 
 * @param event
 *            标准的 js event 对象
 * @param treeId
 *            对应zTree的treeId
 * @param treeNode
 *            将要删除的节点 JSON 数据对象
 * @return true-删除节点， false-不删除节点
 */
function onRightClickFun(event, treeId, treeNode) {

}
/**
 * 用于捕获节点被勾选 或 取消勾选的事件回调函数
 * 
 * @param event
 *            标准的 js event 对象
 * @param treeId
 *            对应zTree的treeId
 * @param treeNode
 *            被勾选 或 取消勾选的节点 JSON 数据对象
 */
function onCheckFun(event, treeId, treeNode) {

}

/**
 * 根据节点类型，控制右键操作菜单哪些可用哪些不可用
 */
function showRMenu(type, x, y) {

}

/**
 * 新增节点
 */
function addTreeNode() {

}

/**
 * 编辑节点
 */
function editTreeNode() {

}

/**
 * 删除节点
 */
function removeTreeNode() {

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