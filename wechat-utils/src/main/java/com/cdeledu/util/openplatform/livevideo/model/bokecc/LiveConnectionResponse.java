package com.cdeledu.util.openplatform.livevideo.model.bokecc;

import java.util.List;

import com.cdeledu.util.openplatform.livevideo.entity.bokecc.LiveConnection;
import com.cdeledu.util.openplatform.livevideo.model.BoKeCCApiResult;

public class LiveConnectionResponse extends BoKeCCApiResult {
	private static final long serialVersionUID = 1L;
	private String roomId;
	private List<LiveConnection> connections;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public List<LiveConnection> getConnections() {
		return connections;
	}

	public void setConnections(List<LiveConnection> connections) {
		this.connections = connections;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n LiveConnectionResponse [roomId=" + roomId + ", connections="
				+ connections + "]";
	}

}
