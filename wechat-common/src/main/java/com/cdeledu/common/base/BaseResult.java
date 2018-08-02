package com.cdeledu.common.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @类描述: BaseResult 统一返回结果类
 * 
 *       <pre>
 *       // 请求异常
 *       return Result.error("我是错误提示");
 *       // 请求成功
 *       return Result.success();
 *       // 请求成功并返回数据
 *       return Result.success().add("list", list);
 *       </pre>
 * 
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年5月20日 下午5:24:11
 * @版本: V2.0
 * @since: JDK 1.7
 */
public class BaseResult implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 返回状态码，200：成功；999999：失败 */
	private Integer code;
	/** 返回信息 */
	private String message;
	/** 返回数据 */
	private Map<String, Object> data = new HashMap<String, Object>();

	public static BaseResult success() {
		BaseResult result = new BaseResult();
		result.setCode(200);
		result.setMessage("操作成功");
		return result;
	}

	public static BaseResult error(String msg) {
		BaseResult result = new BaseResult();
		result.setCode(999999);
		if (StringUtils.isEmpty(msg)) {
			result.setMessage("操作失败");
		} else {
			result.setMessage(msg);
		}
		return result;
	}

	// 链式操作返回信息
	public BaseResult add(String key, Object value) {
		this.getData().put(key, value);
		return this;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
