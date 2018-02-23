package com.cdeledu.model.easyui;

import java.io.Serializable;
import java.util.List;

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
public class EasyUITreeNode implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	/** 绑定节点的标识值 */
	private Integer id;
	/** 绑定节点父节点的标识值 */
	private Integer pid;
	/** 显示的节点文本 */
	private String text;
	/** 节点状态，'open' 或 'closed'。 */
	private String state = "open";
	/** 显示的节点图标CSS类ID */
	private String iconCls;
	/** 该节点是否被选中 */
	private boolean checked;
	/** 绑定该节点的自定义属性参数 */
	private Object attributes;
	/** 子节点 */
	private List<EasyUITreeNode> children;

	/** ----------------------------------------------------- Fields end */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
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

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public List<EasyUITreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<EasyUITreeNode> children) {
		this.children = children;
	}
}
