package com.cdeledu.util.network.ftp;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: sftp工具类
 * 
 *       <pre>
 *  sftp是Secure File Transfer Protocol的缩写，安全文件传送协议。
 *       </pre>
 * 
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年6月10日 下午10:12:29
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SftpHelper {
	/** ----------------------------------------------------- Fields start */
	private final static Log logger = LogFactory.getLog(SftpHelper.class);
	/** SFTP 登录用户名 */
	private String sftpUserName;
	/** SFTP 登录密码 */
	private String sftpPassword;
	/** SFTP 服务器地址IP地址 */
	private String sftpHost;
	/** SFTP 端口 */
	private int sftpPort;
	private JSch jsch;
	/** 密钥文件路径 */
	private String privateKey;
	/** 密钥口令 */
	private String passphrase;
	private static ChannelSftp sftpClient;
	private Session sshSession;
	private Channel channel;
	public int conFTPTryCount = 0;
	public int conFTPTryInterval = 1;
	public Boolean isConnected = false;

	/** ----------------------------------------------------- Fields end */

	/**
	 * 构造基于密码认证的sftp对象
	 * 
	 * @throws JSchException
	 * @throws SftpException
	 */
	public SftpHelper(String sftpUserName, String sftpPassword, String sftpHost, int sftpPort)
			throws SftpException, JSchException {
		this.sftpUserName = sftpUserName;
		this.sftpPassword = sftpPassword;
		this.sftpHost = sftpHost;
		this.sftpPort = sftpPort;
		getSftpConnect();
	}

	/**
	 * 构造基于秘钥认证的sftp对象
	 * 
	 * @throws JSchException
	 * @throws SftpException
	 */
	public SftpHelper(String sftpUserName, String sftpPassword, String sftpHost, int sftpPort,
			String privateKey, String passphrase) throws SftpException, JSchException {
		this.sftpUserName = sftpUserName;
		this.sftpPassword = sftpPassword;
		this.sftpHost = sftpHost;
		this.sftpPort = sftpPort;
		this.privateKey = privateKey;
		this.passphrase = passphrase;
		getSftpConnect();
	}

	/**
	 * @方法:连接sftp服务器
	 * @创建人:独泪了无痕
	 * @throws SftpException,JSchException
	 * @throws JSchException
	 */
	private ChannelSftp getSftpConnect() throws SftpException, JSchException {
		jsch = new JSch();

		if (sftpClient == null) {
			synchronized (SftpHelper.class) {
				if (StringUtils.isNoneBlank(privateKey)) {
					// 使用密钥验证方式，密钥可以使有口令的密钥，也可以是没有口令的密钥
					if (StringUtils.isNoneBlank(passphrase)) {
						jsch.addIdentity(privateKey, passphrase);
					} else {
						jsch.addIdentity(privateKey);
					}
				}
				// 根据用户名、主机ip、端口连接SFTP服务器,获取一个Session对象
				sshSession = jsch.getSession(sftpUserName, sftpHost, sftpPort);
				if (StringUtils.isNoneBlank(sftpPassword)) {
					// 设置密码
					sshSession.setPassword(sftpPassword);
				}
				Properties sshConfig = new Properties();
				sshConfig.put("StrictHostKeyChecking", "no");

				sshSession.setConfig(sshConfig);
				sshSession.setServerAliveInterval(92000);

				// 登陆SFTP服务器
				sshSession.connect();
				if (!sshSession.isConnected()) {
					logger.info("未连接到FTP，用户名或密码错误。");
					logger.info("FTP的IP地址可能错误，请正确配置。");
					logger.info("FTP的端口错误,请正确配置。");
				}
				// 参数sftp指明要打开的连接是sftp连接
				channel = sshSession.openChannel("sftp");
				channel.connect();

				sftpClient = (ChannelSftp) channel;
			}

		}
		return sftpClient;
	}

	/**
	 * @方法:关闭协议-sftp协议.(关闭会导致连接池异常，因此不建议用户自定义关闭)
	 * @创建人:独泪了无痕
	 */
	private void logout() {
		if (sftpClient != null) {
			if (sftpClient.isConnected()) {
				sftpClient.disconnect();
				// sftp.exit();
				// sftp.quit();
			}
		}
		if (sshSession != null) {
			if (sshSession.isConnected()) {
				sshSession.disconnect();
			}
		}

		if (channel != null) {
			if (channel.isConnected()) {
				channel.disconnect();
			}
		}
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
	 * @throws SftpException
	 * @throws JSchException
	 */
	public boolean upload(final String srcFile, final String basePath, final String directory,
			final String sftpFileName) throws SftpException, JSchException {
		boolean result = false;
		if (StringUtils.isNoneEmpty(basePath)) {
			try {
				sftpClient.cd(basePath);
				if (StringUtils.isNotBlank(directory)) {
					sftpClient.cd(directory);
				}
				// 目录不存在，则创建文件夹
				// mkdirs(basePath + "/" + directory);
				if (StringUtils.isNotBlank(sftpFileName)) {
					sftpClient.put(srcFile, sftpFileName);
				} else {
					sftpClient.put(srcFile);
				}
				result = true;
			} finally {
				logout();
			}
		}
		return result;
	}

	/**
	 * @方法:下载文件
	 * @创建人:独泪了无痕
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件名
	 * @param saveFile
	 *            存在本地的路径
	 * @throws JSchException
	 * @throws SftpException
	 * @throws IOException
	 */
	public void download(final String directory, final String downloadFile, final String saveFile)
			throws SftpException, JSchException, IOException {
		File file = new File(saveFile);
		if (StringUtils.isNotBlank(directory)) { // 下载目录不为空
			try {
				if (!file.exists()) {
					File parentFile = file.getParentFile();
					if (!parentFile.exists()) {
						parentFile.mkdirs();
					}
					file.createNewFile();
				}
				// 目录不存在，则创建文件夹
				sftpClient.cd(directory);
				sftpClient.get(downloadFile, saveFile);
				// IOUtils.toByteArray(sftp.get(downloadFile));
			} finally {
				logout();
			}
		}
	}

	/**
	 * @方法:删除文件
	 * @创建人:独泪了无痕
	 * @param directory
	 *            要删除文件所在目录
	 * @param deleteFile
	 *            要删除的文件
	 * @return 成功返回true，否则返回false
	 * @throws SftpException
	 */
	public boolean delete(final String directory, final String deleteFile) throws SftpException {
		boolean result = false;
		if (StringUtils.isNotBlank(directory) && StringUtils.isNotBlank(deleteFile)) {
			try {
				sftpClient.cd(directory);
				sftpClient.rm(deleteFile);
			} finally {
				logout();
			}
		}
		return result;
	}

	/**
	 * @方法描述 : 删除文件夹-sftp协议.如果文件夹有内容，则会抛出异常
	 * @param directory
	 *            文件夹路径
	 * @param recursion
	 *            递归删除
	 * @throws Exception
	 */
	public boolean rmDir(final String directory, final boolean recursion) throws Exception {
		boolean result = false;
		try {
			if (!recursion) {

			} else {
				exeRmRec(directory);
			}
		} finally {
			logout();
		}
		return result;
	}

	/**
	 * @方法描述 : 递归删除执行
	 * @param directory
	 *            文件路径
	 * @throws SftpException
	 */
	@SuppressWarnings("unchecked")
	public void exeRmRec(final String directory) throws SftpException {
		try {
			Vector<LsEntry> vector = sftpClient.ls(directory);
			if (vector.size() == 1) { // 文件，直接删除
				sftpClient.rm(directory);
			} else if (vector.size() == 2) { // 空文件夹，直接删除
				sftpClient.rmdir(directory);
			} else {
				String fileName;
				// 删除文件夹下所有文件
				for (LsEntry entry : vector) {
					fileName = entry.getFilename();
					if (".".equals(fileName) || "..".equals(fileName)) {
						continue;
					} else {
						exeRmRec(directory + "/" + fileName);
					}
				}
			}
		} finally {
			logout();
		}

	}

	/**
	 * @方法:列出目录下的文件
	 * @创建人:独泪了无痕
	 * @param directory
	 *            要列出的目录
	 */
	@SuppressWarnings("unchecked")
	public void getFileLists(String directory) {
		if (StringUtils.isBlank(directory)) {

		}
		if (directory.endsWith("/")) {
			directory = directory + "/";
		}
		try {
			Vector<LsEntry> vector = sftpClient.ls(directory);
			String fileName;
			for (LsEntry entry : vector) {
				fileName = entry.getFilename();
				if (fileName.equals(".") || fileName.equals("..")) {
					continue;
				}
				SftpATTRS attrs = entry.getAttrs();
				System.out.println(fileName);
				System.out.println(attrs.isDir()); // 是否是文件夹
				System.out.println(attrs.isFifo());
				System.out.println(attrs.getSize());// 文件大小
				System.out.println(attrs.getATime());// 创建时间
				System.out.println(attrs.getMTime());// 最后更新时间
				System.out.println("--------------");
			}

		} catch (SftpException e) {
			e.printStackTrace();
		} finally {
			logout();
		}
	}

	/**
	 * @方法描述 : 根据路径创建文件夹
	 * @param dir
	 *            路径 必须是 /xxx/xxx/xxx/ 不能就单独一个/
	 * @return 是否创建成功
	 * @throws Exception
	 */
	public boolean mkdir(final String dir) throws Exception {
		boolean result = false;
		try {
			if (StringUtils.isBlank(dir)) {
				return false;
			}
			String md = dir.replaceAll("\\\\", "/");
			if (md.indexOf("/") != 0 || md.length() == 1) {
				mkdirs(md);
			}
		} finally {
			logout();
		}
		return result;
	}

	/**
	 * 
	 * @方法描述 : 递归创建文件夹
	 * @param dir
	 *            路径
	 * @return 是否创建成功
	 * @throws SftpException
	 */
	public boolean mkdirs(final String dir) throws SftpException {
		boolean result = false;
		try {

		} finally {
			logout();
		}
		return result;
	}

	/**
	 * @方法描述 : 判断文件夹是否存在
	 * @param dir
	 *            文件夹路径， /xxx/xxx/
	 * @return
	 */
	public boolean isDirExist(final String dir) {
		boolean result = false;
		try {
			Vector<?> vector = sftpClient.ls(dir);
			if (null == vector) {
				result = false;
			} else {
				result = true;
			}
		} catch (SftpException e) {
			e.printStackTrace();
		} finally {
			logout();
		}
		return result;
	}
}