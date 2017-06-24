package com.cdeledu.monitor.bean;

import java.io.Serializable;

/**
 * @类描述: 获取指定文件目录信息
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年5月31日 下午3:14:47
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class Directory implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 目录路径 */
	private String dirPath;
	/** 创建时间 */
	private long createDate;
	/** 最后访问时间 */
	private long lastAccessTime;
	/** 大小 */
	private long size;
	/** 最后编辑时间 */
	private long lastModifiedTime;
	/** 是否是符号连接（文件快捷方式） */
	private int isSymbolicLink;
	/** 是否目录 */
	private int isDirectory;
	/** 是否是普通文件 */
	private int isRegularFile;
	/** 是否隐藏 */
	private int isHidden;
	/** 是否存在 */
	private int isExists;

	public String getDirPath() {
		return dirPath;
	}

	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public long getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(long lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public int getIsSymbolicLink() {
		return isSymbolicLink;
	}

	public void setIsSymbolicLink(int isSymbolicLink) {
		this.isSymbolicLink = isSymbolicLink;
	}

	public int getIsDirectory() {
		return isDirectory;
	}

	public void setIsDirectory(int isDirectory) {
		this.isDirectory = isDirectory;
	}

	public int getIsRegularFile() {
		return isRegularFile;
	}

	public void setIsRegularFile(int isRegularFile) {
		this.isRegularFile = isRegularFile;
	}

	public int getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(int isHidden) {
		this.isHidden = isHidden;
	}

	public int getIsExists() {
		return isExists;
	}

	public void setIsExists(int isExists) {
		this.isExists = isExists;
	}
}
