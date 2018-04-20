package com.cdeledu.util.openplatform.baidu.developer.entity;

import java.io.Serializable;

/**
 * @ClassName: MusicUrl
 * @Description: 百度音乐链接
 * @author: 独泪了无痕
 * @date: 2015年11月13日 下午5:57:27
 * @version: V1.0
 * @since: JDK 1.7
 */
public class MusicUrl implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 普通品质的音乐链接 */
	private String url;
	/** 高品质音乐的链接 */
	private String durl;

	public MusicUrl() {
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDurl() {
		return durl;
	}

	public void setDurl(String durl) {
		this.durl = durl;
	}
}
