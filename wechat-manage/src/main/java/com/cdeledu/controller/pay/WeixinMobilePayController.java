package com.cdeledu.controller.pay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdeledu.common.constants.FilterHelper;
import com.cdeledu.controller.BaseController;
import com.cdeledu.service.pay.WeixinPayService;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 微信H5支付
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月16日 下午3:15:42
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping(value = "weixinMobile")
public class WeixinMobilePayController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	@Autowired
	private WeixinPayService weixinPayService;

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [公共方法] */
	/**
	 * @方法描述 : H5支付(需要公众号内支付)
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "pay", method = RequestMethod.POST)
	public String pay(ModelMap map) {
		logger.info("H5支付(需要公众号内支付)");
		weixinPayService.weixinPayMobile();
		return "redirect:";
	}

	/**
	 * @方法描述 : 公众号H5支付主页
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "payPage", method = RequestMethod.GET)
	public String pay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "weixin/payPage";
	}

	/**
	 * @方法描述 : 纯H5支付(不建议在APP端使用)
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "h5pay", method = RequestMethod.POST)
	public String h5pay(ModelMap map) {
		logger.info("纯H5支付(不建议在APP端使用)");
		String mweb_url = weixinPayService.weixinPayH5();
		// mweb_url为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付,mweb_url的有效期为5分钟。
		if (StringUtils.isNotBlank(mweb_url)) {
			return "redirect:" + mweb_url;
		} else {
			return "redirect:" + FilterHelper.PAY_ERROR_WEIXIN_H5;// 自定义错误页面
		}
	}

	/**
	 * @方法描述 : 小程序支付(需要HTTPS)
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "smallRoutine", method = RequestMethod.POST)
	public String smallRoutine(ModelMap map) {
		logger.info("小程序支付(需要HTTPS)、不需要支付目录和授权域名");
		String url = weixinPayService.weixinPayMobile();
		return "redirect:" + url;
	}

	/**
	 * @方法描述 : 手机支付完成回调
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "WXPayBack", method = RequestMethod.POST)
	public void WXPayBack(HttpServletRequest request, HttpServletResponse response) {

	}
	/** ----------------------------------------------- [公共方法] */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
