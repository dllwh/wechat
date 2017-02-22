package com.cdeledu.weixin.mp.api;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.cdeledu.util.security.WeiXinDigestUtilHelper;
import com.cdeledu.weixin.base.exception.WeixinException;
import com.cdeledu.weixin.base.payment.mch.ApiResult;
import com.cdeledu.weixin.mp.model.KfAccount;

/**
 * @ClassName: CustomApi
 * @Description: 多客服API
 * @author: 独泪了无痕
 * @date: 2015年11月22日 下午1:36:45
 * @version: V1.0
 * @since: JDK 1.7
 * @see <a href="http://dkf.qq.com">多客服说明</a>
 * @see <a href=
 *      "http://mp.weixin.qq.com/wiki/19/7c129ec71ddfa60923ea9334557e8b23.html">
 *      多客服功能</a>
 */
public class CustomServiceApi extends MpApi {
	/**
	 * @方法描述: 获取公众号中所设置的客服基本信息，包括客服工号、客服昵称、客服登录账号
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月5日 下午4:19:32
	 * @return 多客服信息列表
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki/11/c88c270ae8935291626538f9c64bd123.html#.E8.8E.B7.E5.8F.96.E6.89.80.E6.9C.89.E5.AE.A2.E6.9C.8D.E8.B4.A6.E5.8F.B7">
	 *      获取客服基本信息</a>
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044813&token=&lang=zh_CN">
	 *      获取客服基本信息</a>
	 * @throws WeixinException
	 */
	public List<KfAccount> listKfAccount() throws WeixinException {
		String kf_list_uri = getRequestUri("kf_list_uri");
		return null;
	}

	/**
	 * @方法描述: 新增客服账号
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年8月5日 下午4:26:31
	 * @param id
	 *            完整客服账号，格式为：账号前缀@公众号微信号，账号前缀最多10个字符，必须是英文或者数字字符。如果没有公众号微信号，
	 *            请前往微信公众平台设置。
	 * @param name
	 *            客服昵称，最长6个汉字或12个英文字符
	 * @param pwd
	 *            客服账号登录密码
	 * @return
	 * @throws WeixinException
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki/11/c88c270ae8935291626538f9c64bd123.html#.E6.B7.BB.E5.8A.A0.E5.AE.A2.E6.9C.8D.E5.B8.90.E5.8F.B7">
	 *      新增客服账号</a>
	 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044813&token=&lang=zh_CN">
	 *      新增客服账号</a>
	 */
	public ApiResult createKfAccount(String id, String name, String pwd) throws WeixinException {
		JSONObject obj = new JSONObject();
		obj.put("kf_account", id);
		obj.put("nickname", name);
		obj.put("password", WeiXinDigestUtilHelper.MD5(pwd));
		String kf_create_uri = getRequestUri("kf_create_uri");
		return null;
	}
}
