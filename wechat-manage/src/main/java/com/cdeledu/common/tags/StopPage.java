package com.cdeledu.common.tags;

import javax.servlet.jsp.JspException;

import com.cdeledu.common.base.BaseClass;

/**
 * @类描述: 用与停止页面继续加载的标签
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月26日 下午4:14:02
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class StopPage extends BaseClass {
	private static final long serialVersionUID = 1L;

	public int doEndTag() throws JspException {
		return SKIP_PAGE;
	}

}
