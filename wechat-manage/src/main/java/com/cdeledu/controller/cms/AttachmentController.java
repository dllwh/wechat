package com.cdeledu.controller.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.base.LayuiResponse;
import com.cdeledu.controller.BaseController;
import com.cdeledu.model.cms.resources.Attachment;
import com.cdeledu.service.cms.AttachmentService;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 附件管理
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年6月17日 下午6:23:07
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("attachment")
public class AttachmentController extends BaseController {
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields start */
	@Autowired
	private AttachmentService attachmentService;

	/** ----------------------------------------------------- Fields end */
	/**
	 * @方法描述: 文件管理
	 * @return
	 */
	@RequestMapping("index")
	public String index(ModelMap modelMap) {
		return "cms/attachment";
	}

	/**
	 * @方法描述: 文件管理
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "index", params = "getList")
	public LayuiResponse getList(ModelMap modelMap, Attachment attachment) {
		LayuiResponse response = new LayuiResponse();
		try {
			response.setCount(attachmentService.getAttachmentCount(attachment));
			response.setData(attachmentService.getAttachmentList(attachment));
		} catch (Exception e) {
			e.printStackTrace();
			response.setMsg(e.getMessage());
			response.setCount(0);
		}
		return response;
	}
}
