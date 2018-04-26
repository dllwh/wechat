package com.cdeledu.util.openplatform.livevideo.model.bokecc;

import java.util.List;

import com.cdeledu.util.openplatform.livevideo.model.BoKeCCApiResult;

public class LiveConnectionResponse extends BoKeCCApiResult {
	private static final long serialVersionUID = 1L;
	/** 直播间id */
	private String roomId;
	/** 连接统计信息 */
	private List<LiveConnection> connections;

	class LiveConnection {
		/** 统计时间点 */
		private String time;
		/** 连接总数 */
		private Integer count;
		/** 回放连接总数 */
		private Integer replayCount;

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		public Integer getReplayCount() {
			return replayCount;
		}

		public void setReplayCount(Integer replayCount) {
			this.replayCount = replayCount;
		}

		@Override
		public String toString() {
			return "LiveConnection [time=" + time + ", count=" + count + ", replayCount="
					+ replayCount + "]";
		}
	}

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
