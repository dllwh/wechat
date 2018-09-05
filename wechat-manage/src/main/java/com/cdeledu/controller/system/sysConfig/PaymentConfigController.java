package com.cdeledu.controller.system.sysConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.base.ResponseBean;
import com.cdeledu.common.base.LayuiResponse;
import com.cdeledu.common.constants.SystemConstant.SysOpType;
import com.cdeledu.controller.BaseController;
import com.cdeledu.core.annotation.SystemLog;
import com.cdeledu.model.pay.PayBank;
import com.cdeledu.service.pay.PaymentService;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 支付配置管理
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年5月1日 下午5:59:51
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("system/payment/config")
public class PaymentConfigController extends BaseController {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PaymentService paymentService;

	@RequestMapping("")
	public ModelAndView index() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/payment/config");
		try {
			mv.addObject("paymentConfigList", paymentService.getPaymentConfigList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * @方法:支付详细信息
	 * @创建人:独泪了无痕
	 * @return
	 */
	@RequestMapping(value = "details")
	public ModelAndView details(Integer ownerId) {
		ModelAndView mv = this.getModelAndView();
		try {
			mv.addObject("paymentConfig", paymentService.getPaymentConfigInfo(ownerId));
			mv.addObject("paymentBank", paymentService.getPaymentConfigBank(ownerId));
			mv.addObject("notExistPayBank", paymentService.getNotExistPaymentBank(ownerId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("system/payment/details");
		return mv;
	}

	/**
	 * @方法:获取银行数据
	 * @创建人:独泪了无痕
	 * @return
	 */
	@RequestMapping(value = "bankInit")
	public ModelAndView bankInit(Integer ownerId) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/payment/bankInit");
		return mv;
	}

	/**
	 * @方法:银行分类
	 * @创建人:独泪了无痕
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "bankInit", params = "getList")
	public LayuiResponse getList(Integer ownerId) {
		LayuiResponse layuiResponse = new LayuiResponse();
		try {
			layuiResponse.setData(paymentService.getPaymentBank());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return layuiResponse;
	}

	/**
	 * @方法:更新银行信息
	 * @创建人:独泪了无痕
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateBank")
	public ResponseBean updateBank(PayBank payBank) {
		ResponseBean responseBean = new ResponseBean();
		try {
			paymentService.updateBank(payBank);
			responseBean.setSuccess(true);
		} catch (Exception e) {
			responseBean.setSuccess(false);
		}
		return responseBean;
	}

	/**
	 * @方法:银行分类
	 * @创建人:独泪了无痕
	 * @return
	 */
	@RequestMapping(value = "saveBank", params = "createBank")
	public ModelAndView createBank(Integer ownerId) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/payment/createBank");
		return mv;
	}

	/**
	 * @方法:新增银行分类
	 * @创建人:独泪了无痕
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveBank")
	@SystemLog(tableName = "sys_payment_bank", opType = SysOpType.INSERT, desc = "新增加银行")
	public ResponseBean saveBank(PayBank payBank) {
		ResponseBean responseBean = new ResponseBean();
		try {
			paymentService.insertBank(payBank);
			responseBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setMsg(e.getMessage());
			responseBean.setSuccess(false);
		}
		return responseBean;
	}

	/**
	 * @方法:删除银行，实际是更新
	 * @创建人:独泪了无痕
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delBank")
	@SystemLog(tableName = "sys_payment_bank,sys_payment_config,sys_payment_config_bank", opType = SysOpType.DEL, desc = "删除银行")
	public ResponseBean delBank(PayBank payBank) {
		ResponseBean responseBean = new ResponseBean();
		try {
			payBank.setIfEnabled(0);
			paymentService.updateBank(payBank);
			responseBean.setSuccess(true);
		} catch (Exception e) {
			responseBean.setSuccess(false);
		}
		return responseBean;
	}
}
