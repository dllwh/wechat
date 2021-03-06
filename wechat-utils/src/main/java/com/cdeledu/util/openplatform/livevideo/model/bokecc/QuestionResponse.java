package com.cdeledu.util.openplatform.livevideo.model.bokecc;

import java.util.List;

import com.cdeledu.util.openplatform.livevideo.model.BoKeCCApiResult;

public class QuestionResponse extends BoKeCCApiResult {
	private static final long serialVersionUID = 1L;
	/** 提问总数 */
	private Integer questionCount;
	/** 提问列表信息 */
	private List<Question> questions;

	class Question {
		/** 学员id */
		private String viewerId;
		/** 学员名称 */
		private String viewerName;
		/** 操作时间 */
		private String time;
		/** 内容 */
		private String content;
		/** 回答列表 */
		private List<Answer> answers;

		class Answer {
			/** 学员id */
			private String viewerId;
			/** 学员名称 */
			private String viewerName;
			/** 回答者角色(0:未统计，1:主讲，2:助教，3:主持人，4:学员) */
			private Integer viewerRole;
			/** 操作时间 */
			private String time;
			/** 内容 */
			private String content;
			/** 是否私密回答 */
			private Integer isPrivate;

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

			public Integer getIsPrivate() {
				return isPrivate;
			}

			public void setIsPrivate(Integer isPrivate) {
				this.isPrivate = isPrivate;
			}

			@Override
			public String toString() {
				return "Answer [viewerId=" + viewerId + ", viewerName=" + viewerName
						+ ", viewerRole=" + viewerRole + ", time=" + time + ", content=" + content
						+ ", isPrivate=" + isPrivate + "]";
			}
		}

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

		public List<Answer> getAnswers() {
			return answers;
		}

		public void setAnswers(List<Answer> answers) {
			this.answers = answers;
		}

		@Override
		public String toString() {
			return "Question [viewerId=" + viewerId + ", viewerName=" + viewerName + ", time="
					+ time + ", content=" + content + ", answers=" + answers + "]";
		}
	}

	public Integer getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(Integer questionCount) {
		this.questionCount = questionCount;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n QuestionResponse [questionCount=" + questionCount
				+ ", questions=" + questions + "]";
	}

}
