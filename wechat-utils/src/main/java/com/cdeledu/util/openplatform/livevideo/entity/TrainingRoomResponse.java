package com.cdeledu.util.openplatform.livevideo.entity;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 教育培训响应实体类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月28日 下午9:16:27
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class TrainingRoomResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 实时课堂ID */
	private String id;
	/** 课堂编号 */
	private String number;
	/** 助教口令 */
	private String assistantToken;
	/** Web端学员口令 */
	private String studentToken;
	/** 老师口令 */
	private String teacherToken;
	/** 学生客户端口令 */
	private String studentClientToken;
	/** 预期开始时间 */
	private long startDate;
	/** 是否允许web端学生加入 */
	private boolean webJoin;
	/** 是否允许客户端学生加入 */
	private boolean clientJoin;
	/** 失效日期 */
	private long invalidDate;
	/** 老师和助教加入URL */
	private String teacherJoinUrl;
	/** 学员加入URL */
	private String studentJoinUrl;
	/** 0:大讲堂，1：小班课 */
	private Integer scene;
	/** 返回结果代码 */
	private String code;
	/** 结果说明 */
	private String message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAssistantToken() {
		return assistantToken;
	}

	public void setAssistantToken(String assistantToken) {
		this.assistantToken = assistantToken;
	}

	public String getStudentToken() {
		return studentToken;
	}

	public void setStudentToken(String studentToken) {
		this.studentToken = studentToken;
	}

	public String getTeacherToken() {
		return teacherToken;
	}

	public void setTeacherToken(String teacherToken) {
		this.teacherToken = teacherToken;
	}

	public String getStudentClientToken() {
		return studentClientToken;
	}

	public void setStudentClientToken(String studentClientToken) {
		this.studentClientToken = studentClientToken;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public boolean isWebJoin() {
		return webJoin;
	}

	public void setWebJoin(boolean webJoin) {
		this.webJoin = webJoin;
	}

	public boolean isClientJoin() {
		return clientJoin;
	}

	public void setClientJoin(boolean clientJoin) {
		this.clientJoin = clientJoin;
	}

	public long getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(long invalidDate) {
		this.invalidDate = invalidDate;
	}

	public String getTeacherJoinUrl() {
		return teacherJoinUrl;
	}

	public void setTeacherJoinUrl(String teacherJoinUrl) {
		this.teacherJoinUrl = teacherJoinUrl;
	}

	public String getStudentJoinUrl() {
		return studentJoinUrl;
	}

	public void setStudentJoinUrl(String studentJoinUrl) {
		this.studentJoinUrl = studentJoinUrl;
	}

	public Integer getScene() {
		return scene;
	}

	public void setScene(Integer scene) {
		this.scene = scene;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
