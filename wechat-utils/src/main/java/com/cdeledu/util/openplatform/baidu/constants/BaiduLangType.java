package com.cdeledu.util.openplatform.baidu.constants;

/**
 * @ClassName: BaiduLangType
 * @Description: 语言列表 <br>
 *               源语言语种不确定时可设置为 auto,目标语言语种不可设置为 auto
 * @author: 独泪了无痕
 * @date: 2015年12月13日 下午3:35:37
 * @version: V1.0
 * @since: JDK 1.7
 */
public enum BaiduLangType {
	// 自动检测
	AUTO("auto"),
	// 中文
	ZH("zh"),
	// 英语
	EN("en"),
	// 粤语
	YUE("yue"),
	// 文言文
	WYW("wyw"),
	// 日语
	jp("jp"),
	// 韩语
	kor("kor"),
	// 法语
	fra("fra"),
	// 西班牙语
	spa("spa"),
	// 泰语
	th("th"),
	// 阿拉伯语
	ara("ara"),
	// 俄语
	ru("ru"),
	// 葡萄牙语
	pt("pt"),
	// 德语
	de("de"),
	// 意大利语
	it("it"),
	// 希腊语
	el("el"),
	// 荷兰语
	nl("nl"),
	// 波兰语
	pl("pl"),
	// 保加利亚语
	bul("bul"),
	// 爱沙尼亚语
	est("est"),
	// 丹麦语
	dan("dan"),
	// 芬兰语
	fin("fin"),
	// 捷克语
	cs("cs"),
	// 罗马尼亚语
	rom("rom"),
	// 斯洛文尼亚语
	slo("slo"),
	// 瑞典语
	swe("swe"),
	// 匈牙利语
	hu("hu"),
	// 繁体中文
	cht("cht");
	private String desc;

	BaiduLangType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

}
