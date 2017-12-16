package com.cdeledu.controller.pay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdeledu.controller.BaseController;
import com.cdeledu.service.pay.WeixinPayService;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 微信支付
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月16日 下午3:15:42
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping(value = "weixin")
public class WeixinPayController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	@Autowired
	private WeixinPayService weixinPayService;

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [公共方法] */
	/**
	 * @方法描述 : 支付主页
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "weixinpay/index";
	}

	/**
	 * @方法描述 : 二维码支付
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "qcPay", method = RequestMethod.POST)
	public String qcPay(ModelMap map) {
		logger.info("二维码支付");
		weixinPayService.weixinPay();
		return "weixinpay/qcpay";
	}

	/**
	 * @方法描述 : 支付后台回调
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "pay", method = RequestMethod.POST)
	public void weixin_notify(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

	}
	/** ----------------------------------------------- [公共方法] */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
