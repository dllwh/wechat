package com.cdeledu.weixin.base.type;

/**
 * @ClassName: UserSourceType
 * @Description: 用户渠道来源
 * @author: 独泪了无痕
 * @date: 2015年11月22日 下午2:32:39
 * @version: V1.0
 * @since: JDK 1.7
 */
public enum UserSourceType {

	OTHER("其它（包括带参数二维码）"), 
	QRCODE("扫二维码"),
	CARDSHARE("名片分享"),
	SONUMBER("搜号码（即微信添加朋友页的搜索）"),
	SOMPACCOUNT("查询微信公众帐号"),
	ARTICLEMENU("图文页右上角菜单");
	
	private String desc;

	UserSourceType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
