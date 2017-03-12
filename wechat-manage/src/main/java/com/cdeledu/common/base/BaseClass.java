package com.cdeledu.common.base;

import java.io.Serializable;

import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @类描述: 基类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月24日 下午12:57:08
 * @版本: V1.0
 * @since: JDK 1.7
 */
public abstract class BaseClass extends TagSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
}
