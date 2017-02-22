package com.cdeledu.weixin.base.type;

/**
 * @ClassName: EventType
 * @Description: 事件类型
 * @author: 独泪了无痕
 * @date: 2015年10月17日 下午2:15:47
 * @version: V1.0
 * @history:
 */
public enum EventType {
	/**
	 * 事件类型:关注(订阅)事件
	 * 
	 */
	subscribe,
	/**
	 * 事件类型:取消关注(订阅)事件
	 * 
	 */
	unsubscribe,
	/**
	 * 上报地理位置事件
	 * 
	 * @see LocationEventMessage
	 */
	location,
	/**
	 * 菜单点击关键字事件
	 * 
	 * @see MenuEventMessage
	 */
	view,
	/**
	 * 事件类型:自定义菜单点击链接事件
	 * 
	 * @see MenuEventMessage
	 */
	click,
	/**
	 * 菜单扫描事件
	 * 
	 * @see MenuScanEventMessage
	 */
	scancode_push,
	/**
	 * 菜单扫描并调出等待界面事件
	 * 
	 * @see MenuScanEventMessage
	 */
	scancode_waitmsg,
	/**
	 * 菜单弹出拍照事件
	 * 
	 * @see MenuPhotoEventMessage
	 */
	pic_sysphoto,
	/**
	 * 菜单弹出发图事件
	 * 
	 * @see MenuPhotoEventMessage
	 */
	pic_photo_or_album,
	/**
	 * 菜单弹出发图事件
	 * 
	 * @see MenuPhotoEventMessage
	 */
	pic_weixin,
	/**
	 * 菜单发送地理位置事件
	 * 
	 * @see MenuLocationEventMessage
	 */
	location_select,

	// ------------------------------公众平台特有------------------------------

	/**
	 * 二维码扫描事件
	 * 
	 * @see ScanEventMessage
	 */
	scan,
	/**
	 * 群发消息事件
	 * 
	 * @see MassEventMessage
	 */
	masssendjobfinish,
	/**
	 * 模板消息事件
	 * 
	 * @see TemplatesendjobfinishMessage
	 */
	templatesendjobfinish,
	/**
	 * 客服接入会话事件
	 * 
	 * @see KfCreateEventMessage
	 */
	kf_create_session,
	/**
	 * 客服关闭会话事件
	 * 
	 * @see KfCloseEventMessage
	 */
	kf_close_session,
	/**
	 * 客服转接会话事件
	 * 
	 * @see KfSwitchEventMessage
	 */
	kf_switch_session,
	/**
	 * 资质认证成功事件
	 */
	qualification_verify_success,
	/**
	 * 资质认证失败事件
	 */
	qualification_verify_fail,
	/**
	 * 名称认证成功事件
	 */
	naming_verify_success,
	/**
	 * 名称认证失败事件
	 */
	naming_verify_fail,
	/**
	 * 年审通知事件
	 */
	annual_renew,
	/**
	 * 认证过期失效通知
	 */
	verify_expired,
	// ------------------------------企业号特有------------------------------
	/**
	 * 异步任务完成事件
	 * 
	 * @see BatchjobresultMessage
	 */
	batch_job_result,
	/**
	 * 进入企业号应用事件
	 * 
	 * @see EnterAgentEventMessage
	 */
	enter_agent;
}
