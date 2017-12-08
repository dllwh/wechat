package com.cdeledu.core.tag.system;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 用与停止页面继续加载的标签
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月8日 下午3:36:24
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class StopPage extends TagSupport {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */

	public int doEndTag() throws JspException {
		return SKIP_PAGE;// 让服务器不要处理剩余的页面
	}
}
