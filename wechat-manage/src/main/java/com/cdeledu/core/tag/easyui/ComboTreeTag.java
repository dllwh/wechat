package com.cdeledu.core.tag.easyui;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 下拉树形菜单
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月8日 下午3:42:42
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ComboTreeTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields start */
	protected String id;// ID
	protected String url;// 远程数据
	protected String name;// 控件名称
	protected String width;// 宽度
	protected String value;// 控件值
	protected boolean multiple = false;// 是否多选

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [公共方法] */
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(end().toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	/** ----------------------------------------------- [公共方法] */

	/** ----------------------------------------------- [私有方法] */
	private StringBuffer end() {
		StringBuffer sb = new StringBuffer();
		return sb;
	}
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
