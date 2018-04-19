package com.cdeledu.util.openplatform.livevideo.model.bokecc;

import java.util.List;

import com.cdeledu.util.openplatform.livevideo.entity.bokecc.LiveRoomEntity;
import com.cdeledu.util.openplatform.livevideo.model.BoKeCCApiResult;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 创建直播间列表
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年4月15日 下午2:48:42
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class LiveRoomListResponse extends BoKeCCApiResult {
	private static final long serialVersionUID = 1L;
	/** 页码 */
	private Integer pageIndex;
	/** 直播间总数 */
	private Integer count;
	/** 直播间列表信息 */
	private List<LiveRoomEntity> rooms;

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

	public List<LiveRoomEntity> getRooms() {
		return rooms;
	}

	public void setRooms(List<LiveRoomEntity> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n LiveRoomListResponse [pageIndex=" + pageIndex + ", count="
				+ count + ", rooms=" + rooms + "]";
	}

}
