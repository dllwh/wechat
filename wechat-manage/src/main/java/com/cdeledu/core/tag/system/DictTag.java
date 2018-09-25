package com.cdeledu.core.tag.system;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 数据字典
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2017年12月31日 下午8:46:22
 * @版本: V0.0.1
 * @since: JDK 1.7
 */
public class DictTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private String id;
	/** 数据字典的值 */
	private String value;
	/** 数据字典的名称 */
	private String name;
	/** 是否仅仅显示 */
	private boolean onlyshow;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOnlyshow() {
		return onlyshow;
	}

	public void setOnlyshow(boolean onlyshow) {
		this.onlyshow = onlyshow;
	}

	public int doEndTag() throws JspException {
		return 0;
	}

	public int doStartTag() throws JspException {
		return doEndTag();
	}
}
