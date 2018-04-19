package com.cdeledu.util.openplatform.livevideo.model.bokecc;

import java.util.List;

import com.cdeledu.util.openplatform.livevideo.entity.bokecc.LiveHistory;
import com.cdeledu.util.openplatform.livevideo.model.BoKeCCApiResult;

public class LiveHistoryResponse extends BoKeCCApiResult {
	private static final long serialVersionUID = 1L;
	/** 页码 */
	private Integer pageIndex;
	/** 直播间总数 */
	private Integer count;
	/** 直播间列表信息 */
	private List<LiveHistory> lives;

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<LiveHistory> getLives() {
		return lives;
	}

	public void setLives(List<LiveHistory> lives) {
		this.lives = lives;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n LiveHistoryResponse [pageIndex=" + pageIndex + ", count="
				+ count + ", lives=" + lives + "]";
	}

}
