package com.cdeledu.util.network.ftp;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPHelper {
	/** ----------------------------------------------------- Fields start */
	private FTPClient ftp = null;
	private String ftpIP = "";
	private int ftpPort = 0;
	private String ftpUserName = "";
	private String ftpPassword = "";

	/** ----------------------------------------------------- Fields end */
	public FTPHelper() {
		ftp = new FTPClient();
	}

	public boolean login(boolean FileTypeIsBinary) {
		boolean isSuccess = true;
		try {
			String password = StringUtils.isBlank(ftpPassword) ? System.getProperty("user.name")
					+ "@" + InetAddress.getLocalHost().getHostName() : ftpPassword;

			ftp.connect(ftpIP, ftpPort);
			ftp.login(ftpUserName, password);
			if (FileTypeIsBinary) {
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				ftp.setBufferSize(1024);
			}

			int reply = ftp.getReplyCode();
			System.out.println("ftp reply code :" + reply);
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				System.out.println("FTP server refused connection.");
				isSuccess = false;
			}
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return isSuccess;
	}

	public boolean logout() {
		boolean isSuccess = true;
		try {
			ftp.logout();
		} catch (IOException e) {
			isSuccess = false;
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException e) {
					isSuccess = false;
					e.printStackTrace();
				}
			}
		}
		return isSuccess;
	}
}
