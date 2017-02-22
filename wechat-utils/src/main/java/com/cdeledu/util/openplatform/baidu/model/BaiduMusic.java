package com.cdeledu.util.openplatform.baidu.model;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: BaiduMusic
 * @Description: 百度音乐API结果返回实体类
 * @author: 独泪了无痕
 * @date: 2015年11月13日 上午11:19:51
 * @version: V1.0
 * @since: JDK 1.7
 */
public class BaiduMusic implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 搜索到的音乐数 */
	private int count;
	/** 音乐文件url */
	private List<MusicUrl> getMusicUrl;

	public BaiduMusic() {
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<MusicUrl> getGetMusicUrl() {
		return getMusicUrl;
	}

	public void setGetMusicUrl(List<MusicUrl> getMusicUrl) {
		this.getMusicUrl = getMusicUrl;
	}
}
