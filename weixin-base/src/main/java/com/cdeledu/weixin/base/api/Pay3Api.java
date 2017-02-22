package com.cdeledu.weixin.base.api;

import java.io.File;
import java.util.Date;
import java.util.Map;

import com.cdeledu.weixin.base.exception.WeixinException;
import com.cdeledu.weixin.base.http.wechat.XmlResult;
import com.cdeledu.weixin.base.model.WeixinPayAccount;
import com.cdeledu.weixin.base.payment.mch.ApiResult;
import com.cdeledu.weixin.base.type.BillType;
import com.cdeledu.weixin.base.type.IdQuery;

/**
 * @ClassName: Pay3Api
 * @Description: (商户平台版)支付API
 * @author: 独泪了无痕
 * @date: 2015年10月10日 下午5:12:15
 * @version: V1.0
 * @see <a href="http://pay.weixin.qq.com/wiki/doc/api/index.html">商户平台API</a>
 */
public class Pay3Api {
	/** -------------------------- 属性 begin ------------------------------- */
	@SuppressWarnings("unused")
	private final WeixinPayAccount weixinAccount;

	/** -------------------------- 属性 end ------------------------------- */
	/** -------------------------- 私有方法 begin ------------------------------- */
	/** -------------------------- 私有方法 end ------------------------------- */
	/** -------------------------- 公有方法 begin ------------------------------- */
	public Pay3Api(WeixinPayAccount weixinAccount) {
		this.weixinAccount = weixinAccount;
	}

	/**
	 * 
	 * @Title：orderQuery
	 * @Description：订单查询 <p>
	 *                   当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；<br>
	 *                   调用支付接口后，返回系统错误或未知交易状态情况；<br>
	 *                   调用被扫支付API，返回USERPAYING的状态；<br>
	 *                   调用关单或撤销接口API之前，需确认支付状态；
	 * @param idQuery
	 *            商户系统内部的订单号, transaction_id、out_trade_no 二 选一,如果同时存在优先级:
	 *            transaction_id> out_trade_no
	 * @throws WeixinException
	 * @return 订单信息
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2">订单查询API</a>
	 */
	@SuppressWarnings("unused")
	private void orderQuery(IdQuery idQuery) throws WeixinException {

	}

	/**
	 * 
	 * @Title：refundApply
	 * @Description： 申请退款(请求需要双向证书)
	 *               当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家
	 *               ，微信支付将在收到退款请求并且验证成功之后 ， 按照退款规则将支付款按原路退到买家帐号上。
	 *               <p>
	 *               1.交易时间超过半年的订单无法提交退款；
	 *               2.微信支付退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号
	 *               。一笔退款失败后重新提交 ，要采用原来的退款单号。总退款金额不能超过用户实际支付金额。
	 *               </p>
	 * @param idQuery
	 *            商户系统内部的订单号, transaction_id 、 out_trade_no 二选一,如果同时存在优先级:
	 *            transaction_id> out_trade_no
	 * @param outRefundNo
	 *            商户系统内部的退款单号,商 户系统内部唯一,同一退款单号多次请求只退一笔
	 * @param totalFee
	 *            订单总金额,单位为元
	 * @param refundFee
	 *            退款总金额,单位为元,可以做部分退款
	 * @param opUserId
	 *            操作员帐号, 默认为商户号
	 * 
	 * @return 退款申请结果
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4">申请退款API</a>
	 * @throws WeixinException
	 */
	@SuppressWarnings("unused")
	private void refundApply(IdQuery idQuery, String outRefundNo,
			double totalFee, double refundFee, String opUserId,
			Map<String, String> mopara) throws WeixinException {

	}

	/**
	 * 
	 * @Title：closeOrder
	 * @Description： <ul>
	 *               <li>关闭订单</li>
	 *               <li>商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；</li>
	 *               <li>系统下单后，用户支付超时，系统退出不再受理，避免用户继续</li>
	 *               <li>请调用关单接口,如果关单失败,返回已完
	 *               成支付请按正常支付处理。如果出现银行掉单,调用关单成功后,微信后台会主动发起退款</li>
	 *               </ul>
	 * @param outTradeNo
	 *            商户系统内部的订单号
	 * @return 处理结果
	 * @throws WeixinException
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_3">关闭订单API</a>
	 */
	@SuppressWarnings("unused")
	private ApiResult closeOrder(String outTradeNo) throws WeixinException {
		return null;
	}

	/**
	 * 
	 * @Title：downloadbill
	 * @Description： <ul>
	 *               <li>下载对账单</li>
	 *               <li>1.微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账
	 *               单中,跟原支付单订单号一致,bill_type 为 REVOKED</li>
	 *               <li>2.微信在次日 9 点启动生成前一天的对账单,建议商户 9 点半后再获取</li>
	 *               <li>3.对账单中涉及金额的字段单位为“元”</li>
	 *               </ul>
	 * @param billDate
	 *            下载对账单的日期
	 * @param billType
	 *            下载对账单的类型 ALL,返回当日所有订单信息, 默认值 SUCCESS,返回当日成功支付的订单
	 *            REFUND,返回当日退款订单
	 * @return excel表格
	 * @throws WeixinException
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6">下载对账单API</a>
	 */
	@SuppressWarnings("unused")
	private File downloadbill(Date billDate, BillType billType)
			throws WeixinException {
		return null;
	}

	/**
	 * 
	 * @Title：refundQuery
	 * @Description：退款查询 <br>
	 *                   提交退款申请后，通过调用该接口查询退款状态。<br>
	 *                   退款有一定延时，用零钱支付的退款20分钟内到账，银行卡支付的退款3个工作日后重新查询退款状态
	 * @param idQuery
	 *            单号 refund_id、out_refund_no、 out_trade_no 、 transaction_id
	 *            四个参数必填一个,优先级为:
	 *            refund_id>out_refund_no>transaction_id>out_trade_no
	 * @return 退款记录
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5">退款查询API</a>
	 * @throws WeixinException
	 */
	@SuppressWarnings("unused")
	private void refundQuery(IdQuery idQuery) throws WeixinException {

	}

	/**
	 * 
	 * @Title：interfaceReport
	 * @Description：接口上报
	 * @param interfaceUrl
	 *            上报对应的接口的完整 URL, 类似: https://api.mch.weixin.q
	 *            q.com/pay/unifiedorder
	 * @param executeTime
	 *            接口耗时情况,单位为毫秒
	 * @param outTradeNo
	 *            商户系统内部的订单号,商 户可以在上报时提供相关商户订单号方便微信支付更好 的提高服务质量。
	 * @param ip
	 *            发起接口调用时的机器 IP
	 * @param time
	 *            ￼商户调用该接口时商户自己 系统的时间
	 * @param returnXml
	 *            调用接口返回的基本数据
	 * @return 处理结果
	 * @throws WeixinException
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_8">接口测试上报API</a>
	 */
	@SuppressWarnings("unused")
	private XmlResult interfaceReport(String interfaceUrl, int executeTime,
			String outTradeNo, String ip, Date time, XmlResult returnXml)
			throws WeixinException {
		return null;
	}

	/**
	 * 
	 * @Title：authCode2openId
	 * @Description：授权码查询OPENID接口
	 * @param authCode
	 *            扫码支付授权码，设备读取用户微信中的条码或者二维码信息
	 * @return：查询结果
	 * @see <a
	 *      href="https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_13&index=9">授权码查询OPENID</a>
	 * @throws WeixinException
	 */
	@SuppressWarnings("unused")
	private void authCode2openId(String authCode) throws WeixinException {

	}
	/** -------------------------- 公有方法 end ------------------------------- */
}
