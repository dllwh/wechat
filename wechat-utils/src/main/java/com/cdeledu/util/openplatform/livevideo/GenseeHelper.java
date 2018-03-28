package com.cdeledu.util.openplatform.livevideo;

import java.util.Map;

import com.cdeledu.util.apache.collection.MapUtilHelper;
import com.cdeledu.util.openplatform.livevideo.entity.TrainingCourseware;
import com.cdeledu.util.openplatform.livevideo.entity.TrainingRoomRequest;
import com.cdeledu.util.security.Md5Helper;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 展视互动帮助类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年3月28日 下午10:02:06
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="http://www.gensee.com">网络视频直播系统-展视互动</a>
 */
public class GenseeHelper {
	private String loginName;
	private String password;
	private boolean sec;
	private String trainingUrl = "http://%s.gensee.com/integration/site/training/";

	/**
	 * 
	 * @param domain
	 *            站点访问域名
	 * @param loginName
	 *            登录名 *
	 * @param password
	 *            密码
	 * @param sec
	 *            rue:表示密码是经过加密的
	 */
	public GenseeHelper(String domain, String loginName, String password, boolean sec) {
		this.loginName = loginName;
		this.password = Md5Helper.md5(password, 32);
		this.sec = sec;
		trainingUrl = String.format(trainingUrl, domain);
	}

	/**
	 * @方法:创建实时课堂
	 * @创建人:独泪了无痕
	 * @param trainingRoom
	 * @return
	 */
	public String createTrainingRoom(TrainingRoomRequest trainingRoom) {
		Map<String, Object> paramMap = MapUtilHelper.beanToMap(trainingRoom);
		paramMap.put("loginName", loginName);
		paramMap.put("password", password);
		paramMap.put("sec", sec);
		String params = Joiner.on("&").withKeyValueSeparator("=").join(paramMap);
		return trainingUrl + "room/created?" + params;
	}

	/**
	 * @方法:根据课堂ID修改实时课堂设置
	 * @创建人:独泪了无痕
	 * @param trainingRoom
	 * @return
	 */
	public String modifyTrainingRoom(TrainingRoomRequest trainingRoom) {
		Map<String, Object> paramMap = MapUtilHelper.beanToMap(trainingRoom);
		paramMap.put("loginName", loginName);
		paramMap.put("password", password);
		paramMap.put("sec", sec);
		paramMap.put("id", trainingRoom.getRoomId());
		String params = Joiner.on("&").withKeyValueSeparator("=").join(paramMap);
		return trainingUrl + "room/modify?" + params;
	}

	/**
	 * @方法:根据课堂ID删除实时课堂
	 * @创建人:独泪了无痕
	 * @param roomId
	 *            实时课堂主题ID
	 * @return
	 */
	public String deletedTrainingRoom(String roomId) {
		Map<String, Object> paramMap = Maps.newConcurrentMap();
		paramMap.put("loginName", loginName);
		paramMap.put("password", password);
		paramMap.put("sec", sec);
		paramMap.put("roomId", roomId);
		String params = Joiner.on("&").withKeyValueSeparator("=").join(paramMap);
		return trainingUrl + "room/deleted?" + params;
	}

	/**
	 * @方法:根据课堂ID获取课堂详细信息
	 * @创建人:独泪了无痕
	 * @param roomId
	 *            实时课堂主题ID
	 * @return
	 */
	public String getTrainingRoom(String roomId) {
		Map<String, Object> paramMap = Maps.newConcurrentMap();
		paramMap.put("loginName", loginName);
		paramMap.put("password", password);
		paramMap.put("sec", sec);
		paramMap.put("roomId", roomId);
		String params = Joiner.on("&").withKeyValueSeparator("=").join(paramMap);
		return trainingUrl + "room/info?" + params;
	}

	/**
	 * @方法:获取课堂录制下的所有课件
	 * @创建人:独泪了无痕
	 * @param roomId
	 *            实时课堂主题ID
	 * @return
	 */
	public String getTrainingCourseList(String roomId) {
		Map<String, Object> paramMap = Maps.newConcurrentMap();
		paramMap.put("loginName", loginName);
		paramMap.put("password", password);
		paramMap.put("sec", sec);
		paramMap.put("roomId", roomId);
		String params = Joiner.on("&").withKeyValueSeparator("=").join(paramMap);
		return trainingUrl + "courseware/list?" + params;
	}

	/**
	 * @方法:获取课堂录制下的所有课件
	 * @创建人:独泪了无痕
	 * @param coursewareId
	 *            课件ID
	 * @return
	 */
	public String getTrainingCourseInfo(String coursewareId) {
		Map<String, Object> paramMap = Maps.newConcurrentMap();
		paramMap.put("loginName", loginName);
		paramMap.put("password", password);
		paramMap.put("sec", sec);
		paramMap.put("coursewareId", coursewareId);
		String params = Joiner.on("&").withKeyValueSeparator("=").join(paramMap);
		return trainingUrl + "courseware/info?" + params;
	}

	/**
	 * @方法:根据课件ID修改课件的基本属性
	 * @创建人:独泪了无痕
	 * @param coursewareId
	 *            课件ID
	 * @return
	 */
	public String modifyTrainingCourseInfo(TrainingCourseware trainingCourseware) {
		Map<String, Object> paramMap = MapUtilHelper.beanToMap(trainingCourseware);
		paramMap.put("loginName", loginName);
		paramMap.put("password", password);
		paramMap.put("sec", sec);
		String params = Joiner.on("&").withKeyValueSeparator("=").join(paramMap);
		return trainingUrl + "courseware/modify?" + params;
	}

	/**
	 * @方法:根据课件ID删除课件
	 * @创建人:独泪了无痕
	 * @param coursewareId
	 *            课件ID
	 * @return
	 */
	public String deletedTrainingCourseInfo(String coursewareId) {
		Map<String, Object> paramMap = Maps.newConcurrentMap();
		paramMap.put("loginName", loginName);
		paramMap.put("password", password);
		paramMap.put("sec", sec);
		paramMap.put("coursewareId", coursewareId);
		String params = Joiner.on("&").withKeyValueSeparator("=").join(paramMap);
		return trainingUrl + "courseware/deleted?" + params;
	}

	/**
	 * @方法:创建老师，老师主要起到调用接口所需要的账号作用
	 * @创建人:独泪了无痕
	 * @param teacherLoginName
	 *            登录名(长度：1-40)
	 * @param teacherPassword
	 *            密码(长度：6-15)
	 * @return
	 */
	public String createTrainingTeacher(String teacherLoginName, String teacherPassword) {
		Map<String, Object> paramMap = Maps.newConcurrentMap();
		paramMap.put("loginName", loginName);
		paramMap.put("password", password);
		paramMap.put("sec", sec);
		paramMap.put("teacherLoginName", teacherLoginName);
		paramMap.put("teacherPassword", teacherPassword);
		String params = Joiner.on("&").withKeyValueSeparator("=").join(paramMap);
		return trainingUrl + "teacher/created?" + params;
	}

	/**
	 * @方法:分页获取本站点保存的录制件数据。每页50条数据
	 * @创建人:独泪了无痕
	 * @param pageNo
	 *            指定第几页，默认为1
	 * @param startTime
	 *            同步创建时间大于等于startTime的录制件,时间格式：yyyy-MM-dd HH:mm:ss或者为long型 (毫秒数)
	 * @param endTime
	 *            同步创建时间小于等于endTime的录制件,时间格式：yyyy-MM-dd HH:mm:ss或者为long型 (毫秒数)
	 * @return
	 */
	public String synTrainingRecord(int pageNo, String startTime, String endTime) {
		Map<String, Object> paramMap = Maps.newConcurrentMap();
		paramMap.put("loginName", loginName);
		paramMap.put("password", password);
		paramMap.put("sec", sec);
		paramMap.put("pageNo", pageNo);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		String params = Joiner.on("&").withKeyValueSeparator("=").join(paramMap);
		return trainingUrl + "record/syn?" + params;
	}

	/**
	 * @方法:分页获取本站点中的课件数据。每页50条数据
	 * @创建人:独泪了无痕
	 * @param pageNo
	 *            指定第几页，默认为1
	 * @param startTime
	 *            同步创建时间大于等于startTime的录制件,时间格式：yyyy-MM-dd HH:mm:ss或者为long型 (毫秒数)
	 * @param endTime
	 *            同步创建时间小于等于endTime的录制件,时间格式：yyyy-MM-dd HH:mm:ss或者为long型 (毫秒数)
	 * @return
	 */
	public String synTrainingCourseware(int pageNo, String startTime, String endTime) {
		Map<String, Object> paramMap = Maps.newConcurrentMap();
		paramMap.put("loginName", loginName);
		paramMap.put("password", password);
		paramMap.put("sec", sec);
		paramMap.put("pageNo", pageNo);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		String params = Joiner.on("&").withKeyValueSeparator("=").join(paramMap);
		return trainingUrl + "courseware/syn?" + params;
	}
}
