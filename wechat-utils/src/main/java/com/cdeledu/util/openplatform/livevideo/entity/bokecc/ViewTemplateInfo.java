package com.cdeledu.util.openplatform.livevideo.entity.bokecc;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: CC视频 直播间模板信息
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年3月30日 下午2:40:29
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="http://doc.bokecc.com/live/dev/liveapi/#toc_22">直播间模板信息</a>
 */
public class ViewTemplateInfo implements Serializable {	
	private static final long serialVersionUID = 1L;
	/** 模板类型 */
	private String type;
	/** 模板名称 */
	private String name;
	/** 模板描述 */
	private String desc;
	/** 模板图标路径 */
	private String iconPath;
	/** 是否开启pdf视图，0：不开启；1：开启 */
	private String pdfView;
	/** 是否开启聊天视图，0：不开启；1：开启 */
	private String chatView;
	/** 是否开启问答视图，0：不开启；1：开启 */
	private String qaView;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getPdfView() {
		return pdfView;
	}

	public void setPdfView(String pdfView) {
		this.pdfView = pdfView;
	}

	public String getChatView() {
		return chatView;
	}

	public void setChatView(String chatView) {
		this.chatView = chatView;
	}

	public String getQaView() {
		return qaView;
	}

	public void setQaView(String qaView) {
		this.qaView = qaView;
	}

	@Override
	public String toString() {
		return "BokeCcViewTemplates [type=" + type + ", name=" + name + ", desc=" + desc
				+ ", iconPath=" + iconPath + ", pdfView=" + pdfView + ", chatView=" + chatView
				+ ", qaView=" + qaView + "]";
	}
}
