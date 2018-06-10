package com.cdeledu.util.network.ftp;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: sftp工具类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年6月10日 下午10:12:29
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SftpHelper {
	/** ----------------------------------------------------- Fields start */
	/** SFTP 登录用户名 */
	private String userName;
	/** SFTP 登录密码 */
	private String passWord;
	/** 私钥 */
	private String privateKey;
	/** SFTP 服务器地址IP地址 */
	private String host;
	/** SFTP 端口 */
	private int port;

	/** ----------------------------------------------------- Fields end */
	public SftpHelper(String userName, String passWord, String host, int port) {
		this.userName = userName;
		this.passWord = passWord;
		this.host = host;
		this.port = port;
	}

	/**
	 * @方法:连接sftp服务器
	 * @创建人:独泪了无痕
	 */
	public void login() {

	}

	/**
	 * @方法:关闭连接 server
	 * @创建人:独泪了无痕
	 */
	public void logout() {

	}

	/**
	 * @方法:将数据上传到sftp作为文件。文件完整路径=basePath+directory
	 * @创建人:独泪了无痕
	 * @param basePath
	 *            服务器的基础路径
	 * @param directory
	 *            上传到该目录
	 * @param sftpFileName
	 *            sftp端文件名
	 * @return 成功返回true，否则返回false
	 */
	public boolean upload(String basePath, String directory, String sftpFileName) {
		boolean result = false;
		return result;
	}

	/**
	 * @方法:下载文件
	 * @创建人:独泪了无痕
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件名
	 */
	public void download(String directory, String downloadFile) {

	}

	/**
	 * @方法:删除文件
	 * @创建人:独泪了无痕
	 * @param directory
	 *            要删除文件所在目录
	 * @param deleteFile
	 *            要删除的文件
	 * @return 成功返回true，否则返回false
	 */
	public boolean delete(String directory, String deleteFile) {
		boolean result = false;
		return result;
	}

	/**
	 * @方法:列出目录下的文件
	 * @创建人:独泪了无痕
	 * @param directory
	 *            要列出的目录
	 */
	public void getFileLists(String directory) {

	}
}
