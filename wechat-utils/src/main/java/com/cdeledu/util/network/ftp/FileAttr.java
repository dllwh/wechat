package com.cdeledu.util.network.ftp;

import java.io.Serializable;
import java.util.Date;

/**
 * @类描述: TP文件属性
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年5月30日 下午9:21:16
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class FileAttr implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	private String fileName;
	private Date ModifyTime;
	private Long size;

	/** ----------------------------------------------------- Fields end */
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getModifyTime() {
		return ModifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		ModifyTime = modifyTime;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

}
