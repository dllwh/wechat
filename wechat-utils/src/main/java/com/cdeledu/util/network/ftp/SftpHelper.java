package com.cdeledu.util.network.ftp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cdeledu.util.network.ftp.monitor.FileProgressMonitor;
import com.google.common.collect.Lists;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
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
 *       <p>
 *       1.所有的文件路径必须以'/'开头和结尾，否则路径最后一部分会被当做是文件名<br>
 *       2.JSch支持三种文件传输模式 <br>
 *       2.1 OVERWRITE 完全覆盖模式，这是JSch的默认文件传输模式，即如果目标文件已经存在，传输的文件将完全覆盖目标文件，产生新的文件
 *       <br>
 *       2.2 恢复模式，如果文件已经传输一部分，这时由于网络或其他任何原因导致文件传输中断，如果下一次传输相同的文件，则会从上一次中断的地方续传
 *       <br>
 *       2.3 追加模式，如果目标文件已存在，传输的文件将在目标文件后追加
 *       </p>
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
		init();
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
		init();
	}

	/**
	 * @方法:关闭协议-sftp协议.(关闭会导致连接池异常，因此不建议用户自定义关闭)
	 * @创建人:独泪了无痕
	 */
	private void logout() {
		if (sftpClient != null) {
			if (sftpClient.isConnected()) {
				sftpClient.disconnect();
			}
		}
	}

	/**
	 * @方法:路径
	 * @创建人:独泪了无痕
	 * @param srcPath
	 *            原路径. /xxx/xxx/xxx.yyy 或 X:/xxx/xxx/xxx.yy
	 * @return 第一个是路径（/xxx/xxx/）,第二个是文件名（xxx.yy）
	 */
	private static List<String> formatPath(final String srcPath) {
		List<String> list = new ArrayList<String>(2);
		String repSrc = srcPath.replaceAll("\\\\", "/");
		int firstP = repSrc.indexOf("/");
		int lastP = repSrc.lastIndexOf("/");
		String fileName = lastP < 0 ? repSrc : repSrc.substring(lastP + 1);
		String dir = firstP < 0 ? "" : repSrc.substring(firstP, lastP);
		dir = (dir.length() == 1 ? dir : (dir + "/"));
		list.add(dir);
		list.add(fileName);
		return list;
	}

	private void init() throws SftpException, JSchException {
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
	}

	private void getSftpSession() throws SftpException, JSchException {
		if (sshSession == null || !sshSession.isConnected()) {
			synchronized (Session.class) {
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
				sshSession.connect();
			}
		}
	}

	private void getSftpChannel() throws SftpException, JSchException {
		if (channel == null || channel.isClosed()) {
			synchronized (Channel.class) {
				getSftpSession();
				// 参数sftp指明要打开的连接是sftp连接
				channel = sshSession.openChannel("sftp");
				channel.connect();
				sftpClient = (ChannelSftp) channel;
			}
		}
	}

	/**
	 * @方法:连接sftp服务器
	 * @创建人:独泪了无痕
	 * @throws SftpException,JSchException
	 * @throws JSchException
	 */
	private ChannelSftp getSftpConnect() throws SftpException, JSchException {

		if (sftpClient == null) {
			synchronized (SftpHelper.class) {
				// 参数sftp指明要打开的连接是sftp连接
				getSftpChannel();
				channel.connect();
				sftpClient = (ChannelSftp) channel;
			}

		}
		return sftpClient;
	}

	/**
	 * @方法描述 : 判断文件夹是否存在
	 * @param dir
	 *            文件夹路径， /xxx/xxx/
	 * @return
	 * @throws SftpException
	 */
	private boolean isDirExist(final String dir) throws SftpException, JSchException {
		Vector<?> vector = getSftpConnect().ls(dir);
		if (null == vector) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @方法:退出
	 * @创建人:独泪了无痕
	 */
	public void exit() {
		if (sftpClient != null) {
			if (sftpClient.isConnected()) {
				sftpClient.disconnect();
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
	public boolean upload(final String srcFile, final String directory)
			throws SftpException, JSchException {

		boolean result = false;
		// 源文件必须存在、基础路径不为空、是文件夹路径
		File file = new File(srcFile);
		try {
			if (file.exists()) {
				if (!isDirExist(directory)) {
					mkdir(directory);
				}
				getSftpConnect().put(srcFile, new FileProgressMonitor(), ChannelSftp.OVERWRITE);
				result = true;
			}
		} finally {
			logout();
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
	public boolean download(final String downloadFile, final String saveFile)
			throws SftpException, JSchException, IOException {
		File file = new File(saveFile);
		boolean result = false;
		try {
			if (!file.exists()) {
				File parentFile = file.getParentFile();
				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				file.createNewFile();
			}
			List<String> srcList = formatPath(downloadFile);
			getSftpConnect().cd(srcList.get(0));
			getSftpConnect().get(srcList.get(1), saveFile, new FileProgressMonitor());
			result = true;
		} finally {
			logout();
		}
		return result;
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
	 * @throws JSchException
	 */
	public boolean rmFile(final String deleteFile) throws SftpException, JSchException {
		boolean result = false;
		List<String> srcList = formatPath(deleteFile);
		try {
			if (isDirExist(srcList.get(0))) {
				getSftpConnect().cd(srcList.get(0));
				getSftpConnect().rm(srcList.get(1));
				result = true;
			}
		} finally {
			logout();
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
			String fp = formatPath(directory).get(0);
			if (isDirExist(fp)) {
				if (recursion) {
					exeRmRec(fp);
					result = true;
				} else {
					getSftpConnect().rmdir(fp);
					result = true;
				}
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
	 * @throws JSchException
	 */
	@SuppressWarnings("unchecked")
	public void exeRmRec(final String directory) throws SftpException, JSchException {
		try {
			Vector<LsEntry> vector = getSftpConnect().ls(directory);
			if (vector.size() == 1) { // 文件，直接删除
				getSftpConnect().rm(directory);
			} else if (vector.size() == 2) { // 空文件夹，直接删除
				getSftpConnect().rmdir(directory);
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
				// 删除文件夹
				sftpClient.rmdir(directory);
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
	 * @throws JSchException
	 */
	@SuppressWarnings("unchecked")
	public List<String> getFileLists(String directory) throws JSchException {
		List<String> resultList = Lists.newArrayList();
		if (StringUtils.isBlank(directory)) {
			return null;
		}
		if (directory.endsWith("/")) {
			directory = directory + "/";
		}
		try {
			Vector<LsEntry> vector = getSftpConnect().ls(directory);
			String fileName;
			for (LsEntry entry : vector) {
				fileName = entry.getFilename();
				if (fileName.equals(".") || fileName.equals("..")) {
					continue;
				}
				resultList.add(fileName);
				// SftpATTRS attrs = entry.getAttrs();
				// attrs.isDir(); // 是否是文件夹
				// attrs.isFifo();
				// attrs.getSize();// 文件大小
				// attrs.getATime();// 创建时间
				// attrs.getMTime();// 最后更新时间
			}

		} catch (SftpException e) {
			e.printStackTrace();
		} finally {
			logout();
		}
		return resultList;
	}

	/**
	 * @方法描述 : 根据路径创建文件夹
	 * @param dir
	 *            路径 必须是 /xxx/xxx/xxx/ 不能就单独一个/
	 * @return 是否创建成功
	 * @throws SftpException
	 * @throws JSchException
	 * @throws Exception
	 */
	public boolean mkdir(final String dir) throws SftpException, JSchException {
		if (StringUtils.isBlank(dir)) {
			return false;
		}
		String md = dir.replaceAll("\\\\", "/");
		if (md.indexOf("/") != 0 || md.length() == 1) {
			return false;
		}
		return mkdirs(md);
	}

	/**
	 * 
	 * @方法描述 : 递归创建文件夹
	 * @param dir
	 *            路径
	 * @return 是否创建成功
	 * @throws SftpException
	 * @throws JSchException
	 */
	public boolean mkdirs(final String dir) throws SftpException, JSchException {
		String dirs = dir.substring(1, dir.length() - 1);
		String[] dirArr = dirs.split("/");
		String base = "";
		for (String d : dirArr) {
			base += "/" + d;
			if (isDirExist(base + "/")) {
				continue;
			} else {
				getSftpConnect().mkdir(base + "/");
			}
		}
		return true;
	}
}