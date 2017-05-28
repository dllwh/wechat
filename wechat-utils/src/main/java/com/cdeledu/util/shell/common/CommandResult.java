package com.cdeledu.util.shell.common;

/**
 * @类描述: 执行结果
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年5月28日 下午3:57:16
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class CommandResult {
	/** result of command **/
	public int result;
	/** success message of command result **/
	public String responseMsg;
	/** error message of command result **/
	public String errorMsg;

	public CommandResult() {
	}

	public CommandResult(int result, String responseMsg) {
		this.result = result;
		this.responseMsg = responseMsg;
	}

	public CommandResult(int result, String responseMsg, String errorMsg) {
		this.result = result;
		this.responseMsg = responseMsg;
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "CommandResult [result=" + result + ", responseMsg=" + responseMsg + ", errorMsg="
				+ errorMsg + "]";
	}
}
