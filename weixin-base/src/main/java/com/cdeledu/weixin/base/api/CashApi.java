package com.cdeledu.weixin.base.api;

import com.cdeledu.weixin.base.exception.WeixinException;
import com.cdeledu.weixin.base.model.WeixinPayAccount;
import com.cdeledu.weixin.base.payment.mch.MPPayment;
import com.cdeledu.weixin.base.payment.mch.MPPaymentRecord;
import com.cdeledu.weixin.base.payment.mch.MPPaymentResult;
import com.cdeledu.weixin.base.payment.mch.Redpacket;
import com.cdeledu.weixin.base.payment.mch.RedpacketRecord;
import com.cdeledu.weixin.base.payment.mch.RedpacketSendResult;

/**
 * @ClassName: CashApi
 * @Description: 现金API
 * @author: 独泪了无痕
 * @date: 2015年10月10日 下午4:53:37
 * @version: V1.0
 * @see <a
 *      href="http://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=13_1">现金红包</a>
 * @see <a
 *      href="http://pay.weixin.qq.com/wiki/doc/api/mch_pay.php?chapter=14_1">企业付款</a>
 */
public class CashApi {
	/** -------------------------- 属性 begin ------------------------------- */
	/** -------------------------- 属性 end ------------------------------- */
	/** -------------------------- 私有方法 begin ------------------------------- */
	/** -------------------------- 私有方法 end ------------------------------- */
	/** -------------------------- 公有方法 begin ------------------------------- */
	private final WeixinPayAccount weixinAccount;

	public CashApi(WeixinPayAccount weixinAccount) {
		this.weixinAccount = weixinAccount;
	}
	/**
	 * 
	 * @Title：sendRedpack
	 * @Description：发放红包 企业向微信用户个人发现金红包
	 * @param redpacket
	 *            红包信息
	 * @return 发放结果
	 * @throws WeixinException
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=13_5">发放红包接口说明</a>
	 */
	public RedpacketSendResult sendRedpack(Redpacket redpacket)
			throws WeixinException {
		return null;
	}

	/**
	 * 
	 * @Title：queryRedpack
	 * @Description： 查询红包记录
	 * @param outTradeNo
	 *            商户发放红包的商户订单号
	 * @return
	 * @throws WeixinException
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=13_6">查询红包接口说明</a>
	 * @return：红包记录
	 */
	public RedpacketRecord queryRedpack(String outTradeNo)
			throws WeixinException {
		return null;
	}

	/**
	 * 
	 * @Title：mchPayment
	 * @Description：企业付款 实现企业向个人付款，针对部分有开发能力的商户， 提供通过API完成企业付款的功能。
	 *                   比如目前的保险行业向客户退保、给付、理赔。
	 * @param mpPayment
	 *            付款信息
	 * @return 付款结果
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/mch_pay.php?chapter=14_1">企业付款</a>
	 */
	public MPPaymentResult mchPayment(MPPayment mpPayment) {
		return null;
	}

	/**
	 * 
	 * @Title：mchPaymentQuery
	 * @Description：企业付款查询 用于商户的企业付款操作进行结果查询，返回付款操作详细结果
	 * @param outTradeNo
	 *            商户调用企业付款API时使用的商户订单号
	 * @return 付款记录
	 * @return：MPPaymentRecord 返回类型
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/mch_pay.php?chapter=14_3">企业付款查询</a>
	 *      O
	 */
	public MPPaymentRecord mchPaymentQuery(String outTradeNo) {
		return null;
	}
	/** -------------------------- 公有方法 end ------------------------------- */
}
