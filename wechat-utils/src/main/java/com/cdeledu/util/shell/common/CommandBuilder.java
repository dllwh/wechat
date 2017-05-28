package com.cdeledu.util.shell.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.common.constant.ConstantHelper;

/**
 * @类描述: java shell命令工具类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年5月28日 下午4:41:11
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class CommandBuilder {
	/** ----------------------------------------------------- Fields start */
	/** 编码格式 */
	protected static String charset = "";
	/** 系统换行符 */
	protected static String lineseparator = System.getProperty("line.separator");

	/** ----------------------------------------------------- Fields end */

	static {
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("win")) {
			charset = ConstantHelper.GBK.name();
		} else if (osName.contains("linux")) {
			charset = ConstantHelper.UTF_8.name();
		} else if (osName.contains("mac")) {
			charset = ConstantHelper.UTF_8.name();
		}
	}

	/**
	 * @方法描述:
	 * 
	 *        <pre>
	 * 判断是否正常执行shell脚本，如果正常结束，Process的waitFor()方法返回0
	 * (不需登录)
	 *        </pre>
	 * 
	 * @param shellCommand
	 *            要运行的脚本
	 * @return
	 * @throws Exception
	 */
	public static CommandResult isSuccessExec(String shellCommand) {
		if (StringUtils.isBlank(shellCommand)) {
			return new CommandResult(-1, "", "empty command");
		}

		int exitValue = -1;// 运行状态，0标识正常
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(shellCommand);
			exitValue = process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CommandResult(exitValue, ScriptOpCode.getErrorMsg(exitValue));
	}

	/**
	 * @方法描述: 执行命令
	 * @param shellCommand
	 *            要运行的脚本
	 * @throws Exception
	 */
	public static void execCmd(String shellCommand) throws Exception {
		if (StringUtils.isNotBlank(shellCommand)) {
			Runtime.getRuntime().exec(shellCommand);
		}
	}

	/**
	 * @方法描述: 执行命令并获取返回数据
	 * @param shellCommand
	 *            要运行的脚本
	 * @param needResponse
	 *            是否需要返回结果
	 * @throws Exception
	 */
	public static CommandResult execCmd(String shellCommand, final boolean needResponse) {
		if (StringUtils.isBlank(shellCommand)) {
			return new CommandResult(-1, "", "empty command");
		}
		int result = -1;
		final StringBuffer successMsg = new StringBuffer();
		final StringBuffer errorMsg = new StringBuffer();
		Process process = null;
		final BufferedReader successResult;
		final BufferedReader errorResult;

		try {
			process = Runtime.getRuntime().exec(shellCommand);

			if (process != null) { // 没有执行
				successResult = new BufferedReader(
						new InputStreamReader(process.getInputStream(), charset));
				errorResult = new BufferedReader(
						new InputStreamReader(process.getErrorStream(), charset));

				// 启动两个线程,解决process.waitFor()阻塞问题
				new Thread(new Runnable() {
					/** 标准输出 */
					public void run() {
						if (needResponse) {
							try {
								String iLine;

								while (successResult != null
										&& (iLine = successResult.readLine()) != null) {
									successMsg.append(iLine).append(lineseparator);
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}

				}).start();
				new Thread(new Runnable() {
					/** 错误输出 */
					public void run() {
						if (needResponse) {
							try {
								String eLine;

								while (errorResult != null
										&& (eLine = errorResult.readLine()) != null) {
									errorMsg.append(eLine).append(lineseparator);
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}

				}).start();

				result = process.waitFor();

				if (errorResult != null) {
					errorResult.close();
				}
				if (successResult != null) {
					successResult.close();
				}
			}

		} catch (Exception ioe) {
			ioe.printStackTrace();
		} finally {
			if (process != null) {
				process.destroy();
			}
		}
		return new CommandResult(result,
				StringUtils.isBlank(successMsg) ? "" : successMsg.toString(),
				StringUtils.isBlank(errorMsg) ? "" : errorMsg.toString());
	}
}
