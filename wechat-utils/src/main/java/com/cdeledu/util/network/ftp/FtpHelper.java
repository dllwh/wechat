package com.cdeledu.util.network.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: ftp工具类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年6月10日 下午10:12:45
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class FtpHelper {
	/** ----------------------------------------------------- Fields start */
	/** FTP 登录用户名 */
	private String userName;
	/** FTP 登录密码 */
	private String passWord;
	/** FTP 服务器地址IP地址 */
	private String host;
	/** FTP服务器 端口 */
	private int port;
	private static FTPClient ftpClient;

	/**
	 * ----------------------------------------------------- Fields end
	 * 
	 * @throws IOException
	 * @throws SocketException
	 */

	public FtpHelper(String userName, String passWord, String host, int port) throws IOException {
		this.userName = userName;
		this.passWord = passWord;
		this.host = host;
		this.port = port;
		getFtpClient();
	}

	/**
	 * @throws IOException
	 * @throws SocketException
	 * @方法:连接ftp服务器
	 * @创建人:独泪了无痕
	 */
	private void login() throws IOException {
		ftpClient = new FTPClient();
		ftpClient.connect(host, port);// 连接FTP服务器
		ftpClient.login(userName, passWord);// 登录
		int reply = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			logout();
		}
	}

	/**
	 * @throws IOException
	 * @方法:关闭连接 server
	 * @创建人:独泪了无痕
	 */
	private void logout() throws IOException {
		if (ftpClient != null) {
			ftpClient.disconnect();
		}
	}

	/**
	 * @方法:获取连接FTP客户端
	 * @创建人:独泪了无痕
	 * @return
	 * @throws SocketException
	 * @throws IOException
	 */
	public FTPClient getFtpClient() throws IOException {
		if (ftpClient == null) {
			synchronized (ftpClient) {
				login();
			}
		}
		return ftpClient;
	}

	/**
	 * @方法: 向FTP服务器上传文件
	 * @创建人:独泪了无痕
	 * @param basePath
	 *            FTP服务器基础目录
	 * @param filePath
	 *            FTP服务器文件存放路径。文件的路径为basePath+filePath
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @return 成功返回true，否则返回false
	 * @param input
	 *            输入流
	 * @throws IOException
	 * @throws SocketException
	 */
	public boolean uploadFile(String basePath, String filePath, String filename,
			InputStream input) {
		boolean result = false;
		try {
			// 切换到上传目录
			if (!ftpClient.changeWorkingDirectory(basePath + filePath)) {
				// 如果目录不存在创建目录
				String[] dirs = filePath.split("/");
				String tempPath = basePath;
				for (String dir : dirs) {
					if (null == dir || "".equals(dir))
						continue;
					tempPath += "/" + dir;
					if (!ftpClient.changeWorkingDirectory(tempPath)) { // 进不去目录，说明该目录不存在
						if (!ftpClient.makeDirectory(tempPath)) { // 创建目录
							// 如果创建文件目录失败，则返回
							System.out.println("创建文件目录" + tempPath + "失败");
							return result;
						} else {
							// 目录存在，则直接进入该目录
							ftpClient.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			// 设置上传文件的类型为二进制类型
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// 上传文件
			if (!ftpClient.storeFile(filename, input)) {
				return result;
			}
			ftpClient.logout();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(input);
			try {
				logout();
			} catch (IOException e) {
			}
		}
		return result;
	}

	/**
	 * 
	 * @方法:从FTP服务器下载文件
	 * @创建人:独泪了无痕
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @param fileName
	 *            要下载的文件名
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @return
	 */
	public boolean downloadFile(String remotePath, String fileName, String localPath) {
		boolean result = false;
		OutputStream is = null;
		try {
			ftpClient.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftpClient.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());

					is = new FileOutputStream(localFile);
					ftpClient.retrieveFile(ff.getName(), is);
					is.close();
				}
			}
			ftpClient.logout();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
			try {
				logout();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
