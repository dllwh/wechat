package com.cdeledu.common.result;

import com.cdeledu.common.base.BaseResult;
import com.cdeledu.common.baseInterface.ReturnCode;

/**
 * @类描述: CmsResult 后台管理系统返回结果类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年5月20日 下午5:37:54
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class CmsResult extends BaseResult {

	private static final long serialVersionUID = 1L;

	public CmsResult(ReturnCode returnCode) {
		super(returnCode.getCode(), returnCode.getMessage());
	}

	public CmsResult(ReturnCode returnCode, Object data) {
		super(returnCode.getCode(), returnCode.getMessage(), data);
	}

	public CmsResult(Integer code, String message) {
		super(code, message);
	}

}
