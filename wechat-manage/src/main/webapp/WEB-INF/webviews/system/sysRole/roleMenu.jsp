<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/bootstrap.jsp"%>
<link href="${_currConText }/static/css/common.css?v=4.1.0" rel="stylesheet">
<link href="${_currConText }/static/css/sample/animate.css" rel="stylesheet">
<link href="${_currConText }/static/css/sample/style.css?v=4.1.0" rel="stylesheet">
<title>${_currProject }---角色授权</title>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-xs-12">
				<div id="roleMenu" class="ztree"></div>
				<button id="checkAllNodes" type="button" class="btn btn-warning">全选</button>
				<button id="cleanAllNodes" type="button" class="btn btn-warning">清除</button>
			</div>
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/webviews/common/context/easyUI.jsp"%>
<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
<script type="text/javascript">
	var ztree,getZTreeObj;
	var strIds = [];
	var setting = {
		view: {//表示tree的显示状态
			dblClickExpand: false,// 双击节点时，是否自动展开父节点的标识
			showLine: true,// 是否显示节点之间的连线
			showIcon: true,// 是否显示节点的图标
			showTitle:true,// 是否显示节点的 title 提示信息
			selectedMulti: true,// 设置是否允许同时选中多个节点
			txtSelectedEnable: true //是否允许可以选择 zTree DOM 内的文本
		},
		data: {//表示tree的数据格式
			simpleData: {
				enable:true, //使用简单数据模式
				idKey: "id",//节点数据中保存唯一标识的属性名称
				pIdKey: "pId",//节点数据中保存其父节点唯一标识的属性名称
				rootPId: "" //用于修正根节点父节点数据 默认值：null
			}
		},
		check : {//表示tree的节点在点击时的相关设置
			enable: true,// 设置 zTree 的节点上是否显示 checkbox / radio
			chkStyle: "checkbox",// 勾选框类型(checkbox 或 radio）
			chkboxType: { "Y": "p", "N": "s" } 
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				if (treeNode.isParent) {// 如果不是叶子结点，结束
					return false;
				} else {
					return true;
				}
			}
		}
	};
	
	$(function(){
		$.ajax({
			type : "post",
			url: '${_currConText }/roleView/roleAccessConfig.shtml?roleMenuList&roleCode=${roleCode}',
			dataType : "json",
			success : function(jsonData) {
				if (jsonData != null) {
					ztree = $.fn.zTree.init($("#roleMenu"), setting, jsonData);
					ztree.expandAll(true);
					getZTreeObj = $.fn.zTree.getZTreeObj("roleMenu");
				}
			}
		});
	})

	function getCheckedAll(roleCode) {
		var nodeList = getZTreeObj.getCheckedNodes(true);
		for (var i = 0; i < nodeList.length; i++) {
			strIds.push(nodeList[i].id);
		}
		
		var ids = strIds.join(",");
		
		$.ajax( {
			url : "${_currConText }/roleOperate/updateMenuByRole.shtml",
			type : "POST",
			data : { 
				roleId:roleCode,
				ids : ids
			},
			success : function(result) {
				if(result.success){
					parent.layer.closeAll();
				} else{
					dialogMsg(result.msg, "error");
				}
			}
		});
	}

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
		}
	}
	
	$("#checkAllNodes").bind("click", {treeId:"roleMenu",type : "checkAllNodes"}, checkNode);
	$("#cleanAllNodes").bind("click",{treeId:"roleMenu",type : "cleanAllNodes"},checkNode);
</script>
</html>