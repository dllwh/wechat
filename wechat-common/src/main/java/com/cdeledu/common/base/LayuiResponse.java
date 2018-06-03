package com.cdeledu.common.base;

import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Layui返回结果
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年5月20日 下午8:28:36
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class LayuiResponse {
	/** 状态，表示请求成功 */
	private Integer code = 0;
	/** 返回的信息 */
	private String msg;
	/** 表示数据库中总共多少条数据 */
	private Integer count;
	/** 返回的list列表 */
	private List<?> data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "LayuiResponse [code=" + code + ", msg=" + msg + ", count=" + count + ", data="
				+ data + "]";
	}
}
