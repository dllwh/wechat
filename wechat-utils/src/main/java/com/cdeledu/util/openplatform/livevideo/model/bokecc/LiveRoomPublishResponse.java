package com.cdeledu.util.openplatform.livevideo.model.bokecc;

import java.util.List;

import com.cdeledu.util.openplatform.livevideo.entity.bokecc.LiveRoomPublish;
import com.cdeledu.util.openplatform.livevideo.model.BoKeCCApiResult;

public class LiveRoomPublishResponse extends BoKeCCApiResult {
	private static final long serialVersionUID = 1L;
	private List<LiveRoomPublish> rooms;

	public List<LiveRoomPublish> getRooms() {
		return rooms;
	}

	public void setRooms(List<LiveRoomPublish> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n LiveRoomPublishResponse [rooms=" + rooms + "]";
	}
}
