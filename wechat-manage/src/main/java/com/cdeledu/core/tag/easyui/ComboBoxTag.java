package com.cdeledu.core.tag.easyui;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 下拉选择框标签
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月8日 下午3:42:03
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ComboBoxTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [公共方法] */
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspTagException {
		return EVAL_PAGE;
	}
	/** ----------------------------------------------- [公共方法] */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
