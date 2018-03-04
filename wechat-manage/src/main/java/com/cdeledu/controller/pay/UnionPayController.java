package com.cdeledu.controller.pay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdeledu.common.constants.PayConstant.PayWay;
import com.cdeledu.controller.BaseController;
import com.cdeledu.service.pay.UnionPayService;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 银联支付
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年12月16日 下午3:11:59
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping(value = "unionpay")
public class UnionPayController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UnionPayService unionPayService;

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [公共方法] */
	/** ----------------------------------------------- [公共方法] */
	/**
	 * @方法描述 : 支付主页
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "unionpay/index";
	}

	/**
	 * @方法描述 : 电脑支付
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "pcPay", method = RequestMethod.POST)
	public String pcPay(ModelMap map) {
		logger.info("电脑支付");
		PayWay.PC.getCode();
		unionPayService.unionPay();
		return "unionpay/pay";
	}

	/**
	 * @方法描述 : 手机H5支付
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "mobilePay", method = RequestMethod.POST)
	public String mobilePay(ModelMap map) {
		logger.info("手机H5支付");
		PayWay.MOBILE.getCode();
		unionPayService.unionPay();
		return "unionpay/pay";
	}

	/**
	 * @方法描述 : 二维码支付
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "qcPay", method = RequestMethod.POST)
	public String qcPay(ModelMap map) {
		logger.info("二维码支付");
		unionPayService.unionPay();
		return "unionpay/";
	}

	/**
	 * @方法描述 : 支付宝支付回调(二维码、H5、网站)
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void unionNotify(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("银联接收后台通知开始");
	}
	/** ----------------------------------------------- [公共方法] */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
