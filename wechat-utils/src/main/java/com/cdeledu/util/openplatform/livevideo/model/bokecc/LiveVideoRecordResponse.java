package com.cdeledu.util.openplatform.livevideo.model.bokecc;

import com.cdeledu.util.openplatform.livevideo.entity.bokecc.LiveVideoInfo;
import com.cdeledu.util.openplatform.livevideo.model.BoKeCCApiResult;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 回放信息
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年4月13日 下午2:38:31
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class LiveVideoRecordResponse extends BoKeCCApiResult {
	private static final long serialVersionUID = 1L;
	/** 回放总数 */
	private Integer count;
	/** 页码 */
	private Integer pageIndex;
	/** 回放列表信息 */
	private LiveVideoInfo record;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public LiveVideoInfo getRecord() {
		return record;
	}

	public void setRecords(LiveVideoInfo record) {
		this.record = record;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n LiveVideoRecordResponse [count=" + count + ", pageIndex="
				+ pageIndex + ", records=" + record + "]";
	}

}
