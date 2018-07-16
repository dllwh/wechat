package com.cdeledu.controller.cms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdeledu.controller.BaseController;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 文章控制类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年7月16日 下午9:03:24
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("notice/articleController")
public class ArticleController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	/** ----------------------------------------------------- Fields end */
	
	/**
	 * @方法描述: 文章初始化页面
	 * @return
	 */
	@RequestMapping("index")
	public String index(ModelMap modelMap) {
		return "cms/article/articleInit";
	}
	
	/**
	 * @方法描述: 文章
	 * @return
	 */
	@RequestMapping("articleAdd")
	public String articleAdd(ModelMap modelMap) {
		return "cms/article/articleAdd";
	}
}
