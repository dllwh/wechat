package com.cdeledu.common.model;

import java.io.Serializable;

/**
 * @类描述: 上传下载模型类
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年9月23日 下午4:04:10
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class UploadFile implements Serializable{
	private static final long serialVersionUID = 1L;
	private boolean view = false;// 是否是预览
	private boolean rename = true;// 是否重命名
	public boolean isView() {
		return view;
	}
	public void setView(boolean view) {
		this.view = view;
	}
	public boolean isRename() {
		return rename;
	}
	public void setRename(boolean rename) {
		this.rename = rename;
	}
}
