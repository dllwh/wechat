package com.cdeledu.model.cms.picture;

import java.util.Date;
import java.util.List;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年6月3日 下午10:12:10
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class GankResponse {
	private Boolean error;
	private List<Gank> results;

	public boolean ifSuccess() {
		if (error == Boolean.FALSE) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	class Gank {
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

		@Override
		public String toString() {
			return "Gank [createdAt=" + createdAt + ", desc=" + desc + ", publishedAt="
					+ publishedAt + ", source=" + source + ", type=" + type + ", url=" + url
					+ ", used=" + used + "]";
		}
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public List<Gank> getResults() {
		return results;
	}

	public void setResults(List<Gank> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "Gank [error=" + error + ", results=" + results + "]";
	}

}
