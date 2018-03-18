package com.cdeledu.common.plugs.bootstrap;

import java.io.Serializable;
import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: jQuery “树插件”
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年1月23日 上午9:55:39
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="www.treejs.cn/v3/main.php#_zTreeInfo">zTree -- jQuery 树插件</a>
 */
public class ZtreeNode implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 节点的标识属性 */
	private Integer id;
	/** 节点parentId属性，命名规则同id */
	private Integer pId;
	/** 节点显示的文本 */
	private String name;
	/** 节点是否勾选 */
	private boolean checked;
	/** 节点的 checkbox / radio 是否禁用;true :节点禁用;false:节点可以使用 */
	private boolean chkDisabled = false;
	/** 设置节点是否隐藏 checkbox / radio */
	private boolean nocheck = false;
	/** 节点是否展开 */
	private boolean open = false;
	/** 节点的图标 */
	private String icon;
	/** 节点展开式的图标 */
	private String iconOpen;
	/** 节点折叠时的图标 */
	private String iconClose;
	/** 判断 treeNode 节点是否被隐藏 ;true 表示被隐藏;false 表示被显示 */
	private boolean isHidden = false;
	/** 是否为父节点 */
	private boolean IsParent = false;
	/** 得到该节点所有孩子节点，直接下级，若要得到所有下属层级节点，需要自己写递归得到 */
	private List<ZtreeNode> children;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
	public boolean isNocheck() {
		return nocheck;
	}
	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIconOpen() {
		return iconOpen;
	}
	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}
	public String getIconClose() {
		return iconClose;
	}
	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}
	public boolean getIsHidden() {
		return isHidden;
	}
	public void setIsHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}
	public boolean getIsParent() {
		return IsParent;
	}
	public void setIsParent(boolean isParent) {
		IsParent = isParent;
	}
	public List<ZtreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<ZtreeNode> children) {
		this.children = children;
	}
	
}
