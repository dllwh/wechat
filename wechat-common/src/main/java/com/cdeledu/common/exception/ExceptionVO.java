package com.cdeledu.common.exception;

import java.io.Serializable;

public class ExceptionVO implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	/** 异常编号 */
	private String id;
	/** 异常摘要信息 */
	private String info;
	/** 异常排查建议 */
	private String suggest;

	/** ----------------------------------------------------- Fields end */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

}
