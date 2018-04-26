package com.cdeledu.model.rbac;

import java.util.Iterator;
import java.util.List;

import com.cdeledu.common.base.DataEntity;
import com.google.common.collect.Lists;

/**
 * @类描述: 菜单表实体类
 * @创建者: 独泪了无痕
 * @创建时间: 2015年8月22日 下午5:16:57
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SysMenu extends DataEntity {

	private static final long serialVersionUID = 1L;
	/** 菜单名称 */
	private String menuName;
	// 菜单链接地址
	private String menuUrl;
	/** 类型。0:目录，默认值；1：菜单；2：按钮；3：窗体；4：链接*/
	private Integer type;
	/** 父菜单编号 */
	private Integer parentCode;
	/** 父级菜单名称 */
	private String parentName;
	/** 菜单图标样式 */
	private String iconClass;
	/** tree属性 */
	private Boolean open = false;
	/** 子节点的集合 */
	private List<SysMenu> childrenList;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public List<SysMenu> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<SysMenu> childrenList) {
		this.childrenList = childrenList;
	}

	@Override
	public String toString() {
		return super.toString() + "\n\r SysMenu [menuName=" + menuName + ", menuUrl=" + menuUrl
				+ ", type=" + type + ", parentCode=" + parentCode + ", parentName=" + parentName
				+ ", iconClass=" + iconClass + ", open=" + open + ", childrenList=" + childrenList
				+ "]";
	}

	/**
	 * @方法描述 : 得到子节点列表
	 * @param nodeList
	 * @param parentId
	 * @return
	 */
	public List<SysMenu> findChildNodes(List<SysMenu> nodeList, Integer parentId) {
		if (nodeList == null && parentId == null) {
			return Lists.newArrayList();
		}
		for (Iterator<SysMenu> iterator = nodeList.iterator(); iterator.hasNext();) {
			SysMenu node = iterator.next();
			// 根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (node.getParentCode() != -1 && parentId.equals(node.getParentCode())) {
				nodeList.add(node);
			}
		}
		return nodeList;
	}
}
