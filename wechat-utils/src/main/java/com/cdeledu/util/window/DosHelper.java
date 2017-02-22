package com.cdeledu.util.window;

/**
 * @类描述: DOS操作封装辅助类 DosHelper
 * 
 *       <pre>
 * 本辅助类主要是用来方便实现DOS操作。
 * DOS操作在自定义安装数据库脚本、运行特殊命令、后台注册控件、操作Windows Service等方面都有用到
 *       </pre>
 * 
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月11日 下午4:56:22
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class DosHelper {
	/**
	 * @方法描述: 后台执行DOS文件
	 * @param fileName
	 *            文件名(包含路径)
	 * @param argument
	 *            运行参数
	 * @param hidden
	 *            是否隐藏窗口
	 */
	public void runDos(String fileName, String argument, boolean hidden){
		
	}

	/**
	 * @方法描述: 运行指定DOS命令行
	 * @param fileName
	 *            命令
	 * @param argument
	 *            命令行参数
	 * @param hidden
	 *            是否隐藏窗口
	 * @param confirm
	 *            写入命令行的确认信息
	 * @return
	 */
	public String executeCMD(String fileName, String argument, boolean hidden, String confirm){
		return null;
	}

	/**
	 * @方法描述: 同步方式执行ＤＯＳ命令
	 * @param command
	 */
	public void executeCommandSync(Object command){
		
	}

	/**
	 * @方法描述: 异步方式执行ＤＯＳ命令
	 * @param command
	 */
	public void ExecuteCommandAsync(Object command){
		
	}
}
