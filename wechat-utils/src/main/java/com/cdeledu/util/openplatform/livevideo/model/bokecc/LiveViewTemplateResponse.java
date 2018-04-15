package com.cdeledu.util.openplatform.livevideo.model.bokecc;

import java.util.List;

import com.cdeledu.util.openplatform.livevideo.entity.bokecc.ViewTemplateInfo;
import com.cdeledu.util.openplatform.livevideo.model.BoKeCCApiResult;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 直播间各模板信息
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年4月13日 下午1:14:48
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class LiveViewTemplateResponse extends BoKeCCApiResult {
	private static final long serialVersionUID = 1L;
	private List<ViewTemplateInfo> templates = null;// 其他信息

	public List<ViewTemplateInfo> getTemplates() {
		return templates;
	}

	public void setTemplates(List<ViewTemplateInfo> templates) {
		this.templates = templates;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
