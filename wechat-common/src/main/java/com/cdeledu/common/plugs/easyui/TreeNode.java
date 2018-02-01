package com.cdeledu.common.plugs.easyui;

import java.io.Serializable;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: EasyU Tree（树）
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2017年12月31日 下午11:34:29
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="http://www.jeasyui.com/">jQueryEasyUI树节点视图数据模型类</a>
 */
public class TreeNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	/** 绑定节点的标识值 */
	private String id;
	/** 绑定节点父节点的标识值 */
	private String pid;
	/** 显示的节点文本 */
	private String text;
	/** 节点状态，'open' 或 'closed'。 */
	private String state;
	/** 显示的节点图标CSS类ID */
	private String iconCls;
	/** 该节点是否被选中 */
	private boolean checked;

	/** ----------------------------------------------------- Fields end */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
