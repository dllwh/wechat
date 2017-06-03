package com.cdeledu.util.gank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Gank implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	private String _id;
	/** 创建时间 */
	private Date createdAt;
	/** 对内容的描述 */
	private String desc;
	/** 发布时间 */
	private Date publishedAt;
	/** 来源 */
	private String source;
	/** 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all */
	private String type;
	/** 地址 */
	private String url;
	/** 是否使用 */
	private boolean used;
	/** 提交者的网络 ID */
	private String who;

	private List<String> images;

	private boolean isHeader;
	private boolean hasLoadImage = false;

	public boolean isMeizi() {
		return type.equals("福利");
	}

	@Override
	public Gank clone() {
		Gank gank = null;
		try {
			gank = (Gank) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return gank;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public boolean isHeader() {
		return isHeader;
	}

	public void setHeader(boolean isHeader) {
		this.isHeader = isHeader;
	}

	public boolean isHasLoadImage() {
		return hasLoadImage;
	}

	public void setHasLoadImage(boolean hasLoadImage) {
		this.hasLoadImage = hasLoadImage;
	}

	@Override
	public String toString() {
		super.toString();
		return "Gank [_id=" + _id + ", createdAt=" + createdAt + ", desc=" + desc + ", publishedAt="
				+ publishedAt + ", source=" + source + ", type=" + type + ", url=" + url + ", used="
				+ used + ", who=" + who + ", images=" + images + ", isHeader=" + isHeader
				+ ", hasLoadImage=" + hasLoadImage + "]";
	}
}
