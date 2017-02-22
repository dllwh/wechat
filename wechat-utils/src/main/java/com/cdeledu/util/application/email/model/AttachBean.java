package com.cdeledu.util.application.email.model;

import java.io.File;
import java.io.Serializable;

/**
 * 
 * @ClassName: AttachBean
 * @Description: 附件类，只有文件，即附件才文件名
 * @author: 独泪了无痕
 * @date: 2015年7月17日 上午9:49:20
 * @version: V1.0
 */
public class AttachBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cid;
	private File file;
	/** 发送邮件的附件的位置 */
	private String[] filepath;
	/** 发送邮件的附件显示的名称 */
	private String fileName;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String[] getFilepath() {
		return filepath;
	}

	public void setFilepath(String[] filepath) {
		this.filepath = filepath;
	}
}
