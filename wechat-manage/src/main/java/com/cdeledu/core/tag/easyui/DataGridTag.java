package com.cdeledu.core.tag.easyui;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: DataGridTag
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月8日 下午3:14:58
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="">TODO(连接内容简介)</a>
 */
public class DataGridTag extends TagSupport {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */
	@Override
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;// 让服务器继续执行页面
	}
	/** ----------------------------------------------- [公共方法] */
	/** ----------------------------------------------- [公共方法] */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
