package com.cdeledu.util.openplatform.livevideo.model.bokecc;

import java.util.List;

import com.cdeledu.util.openplatform.livevideo.entity.bokecc.LiveRoomEntity;
import com.cdeledu.util.openplatform.livevideo.model.BoKeCCApiResult;
/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 创建直播间
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年4月15日 下午2:48:42
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class LiveRoomResponse extends BoKeCCApiResult {
	private static final long serialVersionUID = 1L;
	/** 直播间信息*/
	private List<LiveRoomEntity> room;

	public List<LiveRoomEntity> getRoom() {
		return room;
	}

	public void setRoom(List<LiveRoomEntity> room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n LiveRoomResponse [room=" + room + "]";
	}

}
