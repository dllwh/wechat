package com.cdeledu.weixin.mp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.cdeledu.weixin.base.type.ButtonType;

/**
 * @类名称: Button
 * @类描述: 菜单按钮
 *       <ul>
 *       <li>
 *       目前自定义菜单最多包括3个一级菜单,每个一级菜单最多包含5个二级菜单<br>
 *       一级菜单最多4个汉字,二级菜单最多7个汉字,多出来的部分将会以"..." 代替</li>
 *       <li>
 *       请注意,创建自定义菜单后,由于微信客户端缓存,需要24小时微信客户端才会展现出来<br>
 *       建议测试时可以尝试取消关注公众账号后再次关注, 则可以看到创建后的效果</li>
 *       </ul>
 * @创建者: 独泪了无痕
 * @日期: 2015-12-20 下午2:59:27
 * @版本: V1.0
 */
public class Button implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 菜单标题，不超过16个字节，子菜单不超过40个字节
	 */
	private String name;
	/**
	 * 菜单的响应动作类型(菜单类型)<br>
	 * 公众平台官网上能够设置的菜单类型有view、text、img、photo、video、voice
	 * 使用API设置的自定义菜单:click、scancode_push
	 * 、scancode_waitmsg、pic_sysphoto、pic_photo_or_album
	 */
	private ButtonType type;
	/**
	 * <p>
	 * 菜单KEY值,根据type的类型而定,用于消息接口推送,不超过128字节.<br>
	 * click等点击类型必须
	 * <p>
	 * 网页链接，用户点击菜单可打开链接，不超过256字节.<br>
	 * view类型必须
	 * <p>
	 * 调用新增永久素材接口返回的合法media_id.<br>
	 * media_id类型和view_limited类型必须
	 * <p>
	 * 官网上设置的自定义菜单：<br>
	 * <ul>
	 * <li>Text:保存文字到value</li>
	 * <li>Img、voice：保存mediaID到value</li>
	 * <li>Video：保存视频下载链接到value</li>
	 * <li>News：保存图文消息到news_info</li>
	 * <li>View：保存链接到url</li>
	 * </ul>
	 * 使用API设置的自定义菜单：</br>
	 * <ul>
	 * <li>pic_weixin、location_select：保存为key</li>
	 * <li>view：保存为url</li>
	 * <li>media_id、view_limited:保存为media_id</li>
	 * </ul>
	 */
	private Serializable content;
	/**
	 * 二级菜单数组，个数应为1~5个
	 */
	@JSONField(name = "sub_button")
	private List<Button> subs;

	public Button() {
		this.subs = new ArrayList<Button>();
	}

	/**
	 * 创建一个菜单
	 * 
	 * @param name
	 *            菜单显示的名称
	 * @param content
	 *            当buttonType为view时content设置为url,否则为key.
	 * @param type
	 *            按钮类型
	 */
	public Button(String name, String content, ButtonType type) {
		this.name = name;
		this.type = type;
		this.content = content;
		this.subs = new ArrayList<Button>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ButtonType getType() {
		return type;
	}

	public void setType(ButtonType type) {
		this.type = type;
	}

	public Serializable getContent() {
		return content;
	}

	public void setContent(Serializable content) {
		this.content = content;
	}

	public List<Button> getSubs() {
		return subs;
	}

	public void setSubs(List<Button> subs) {
		this.subs = subs;
	}

	@Override
	public String toString() {
		return "Button [name=" + name + ", type=" + type + ", content="
				+ content + ", subs=" + subs + "]";
	}
}
