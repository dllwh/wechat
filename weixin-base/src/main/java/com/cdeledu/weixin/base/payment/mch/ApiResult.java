package com.cdeledu.weixin.base.payment.mch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.alibaba.fastjson.annotation.JSONField;
import com.cdeledu.weixin.base.http.wechat.XmlResult;

/**
 * @ClassName: ApiResult
 * @Description: 调用V3.x接口返回的公用字段
 * @author: 独泪了无痕
 * @date: 2015-10-13 上午12:01:45
 * @version: V1.0
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ApiResult extends XmlResult {
	private static final long serialVersionUID = 1L;
	/**
	 * 微信分配的公众账号 ID商户号 非空
	 */
	@XmlElement(name = "appid")
	@JSONField(name = "appid")
	private String appId;
	/**
	 * 微信支付分配的商户号 非空
	 */
	@XmlElement(name = "mch_id")
	@JSONField(name = "mch_id")
	private String mchId;
	/**
	 * 代理模式下分配的商户号 可能为空
	 */
	@XmlElement(name = "sub_mch_id")
	@JSONField(name = "sub_mch_id")
	private String subMchId;
	/**
	 * 随机字符串 非空
	 */
	@XmlElement(name = "nonce_str")
	@JSONField(name = "nonce_str")
	private String nonceStr;
	/**
	 * 签名 <font color="red">调用者无需关心</font>
	 */
	private String sign;
	/**
	 * 微信支付分配的终端设备号 可能为空
	 */
	@XmlElement(name = "device_info")
	@JSONField(name = "device_info")
	private String deviceInfo;
	/**
	 * 是否需要继续调用接口 Y- 需要,N-不需要
	 */
	private String recall;

	protected ApiResult() {

	}

	public ApiResult(String returnCode, String returnMsg) {
		super(returnCode, returnMsg);
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getSubMchId() {
		return subMchId;
	}

	public void setSubMchId(String subMchId) {
		this.subMchId = subMchId;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getRecall() {
		return recall;
	}

	public void setRecall(String recall) {
		this.recall = recall;
	}

	@JSONField(serialize = false)
	public boolean getFormatRecall() {
		return recall != null && recall.equalsIgnoreCase("y");
	}

	@Override
	public String toString() {
		return "appId=" + appId + ", mchId=" + mchId + ", subMchId=" + subMchId
				+ ", nonceStr=" + nonceStr + ", sign=" + sign + ", deviceInfo="
				+ deviceInfo + ", recall=" + getFormatRecall() + ", "
				+ super.toString();
	}
}
