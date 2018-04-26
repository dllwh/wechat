package com.cdeledu.util.openplatform.livevideo.model.bokecc;

import java.util.List;

import com.cdeledu.util.openplatform.livevideo.model.BoKeCCApiResult;

public class LiveChatMsgResponse extends BoKeCCApiResult {
	private static final long serialVersionUID = 1L;
	/** 聊天总数 */
	private Integer count;
	/** 聊天列表信息 */
	private List<ChatMsg> chatMsgs;

	class ChatMsg {
		/** 观众id */
		private String viewerId;
		/** 观众名称 */
		private String viewerName;
		/** 观众角色(0:未统计，1:主讲，2:助教，3:主持人，4:学员) */
		private Integer viewerRole;
		/** 聊天时间 */
		private String time;
		/** 聊天内容 */
		private String content;

		public String getViewerId() {
			return viewerId;
		}

		public void setViewerId(String viewerId) {
			this.viewerId = viewerId;
		}

		public String getViewerName() {
			return viewerName;
		}

		public void setViewerName(String viewerName) {
			this.viewerName = viewerName;
		}

		public Integer getViewerRole() {
			return viewerRole;
		}

		public void setViewerRole(Integer viewerRole) {
			this.viewerRole = viewerRole;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		@Override
		public String toString() {
			return "ChatMsg [viewerId=" + viewerId + ", viewerName=" + viewerName + ", viewerRole="
					+ viewerRole + ", time=" + time + ", content=" + content + "]";
		}

	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<ChatMsg> getChatMsgs() {
		return chatMsgs;
	}

	public void setChatMsgs(List<ChatMsg> chatMsgs) {
		this.chatMsgs = chatMsgs;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n LiveChatMsg [count=" + count + ", chatMsgs=" + chatMsgs
				+ "]";
	}

}
