package com.cdeledu.weixin.mp.api;

import java.util.ArrayList;
import java.util.List;

import com.cdeledu.weixin.base.exception.WeixinException;
import com.cdeledu.weixin.base.http.wechat.JsonResult;
import com.cdeledu.weixin.base.type.LangType;
import com.cdeledu.weixin.mp.model.Following;
import com.cdeledu.weixin.mp.model.User;

/**
 * @ClassName: UserApi
 * @Description: 用户管理API
 * @author: 独泪了无痕
 * @date: 2015年10月10日 下午4:04:04
 * @version: V1.0
 * @history:
 */
public class UserApi extends MpApi {
	/**
	 * @Title：getUser @Description： 获取用户信息
	 * @param openId
	 *            用户对应的ID
	 * @return 用户对象
	 * @throws WeixinException
	 */
	public User getUser(String openId) throws WeixinException {
		return getUser(openId, LangType.zh_CN);
	}

	/**
	 * @Title：getUser @Description：
	 *                <ul>
	 *                <li>获取用户信息</li>
	 *                <li>在关注者与公众号产生消息交互后,公众号可获得关注者的OpenID</li>
	 *                <li>（加密后的微信号,每个用户对每个公众号的OpenID是唯一的,对于不同公众号, 同一用户的openid不同)
	 *                </li>
	 *                <li>公众号可通过本接口来根据OpenID获取用户基本信息,包括昵称、头像、性别、所在城市、语言和关注时间
	 *                </li>
	 *                <li></li>
	 *                <li>http请求方式: GET</li>
	 *                <li>每日调用上限500000次</li>
	 *                </ul>
	 * @param openId
	 *            用户对应的ID
	 * @param lang
	 *            国家地区语言版本
	 * @return 用户对象
	 * @throws WeixinException
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki/14/bb5031008f1494a59c6f71fa0f319c66.html">
	 *      获取用户信息</a>
	 */
	
	public User getUser(String openId, LangType lang) throws WeixinException {
		String api_user_info_uri = getRequestUri("api_user_info_uri");
		return null;
	}

	/**
	 * @Title：getUsers
	 * @Description：批量获取用户信息
	 * @param openIds
	 *            用户对应的ID
	 * @return 用户对象
	 * @throws WeixinException
	 */
	public List<User> getUsers(String... openIds) throws WeixinException {
		return getUsers(LangType.zh_CN, openIds);
	}

	/**
	 * @Title：getUsers
	 * @描述:
	 * 		<ol>
	 *      <li>批量获取用户基本信息。最多支持一次拉取100条</li>
	 *      <li>http请求方式: POST</li>
	 *      </ol>
	 * @param lang
	 *            国家地区语言版本
	 * @param openIds
	 *            用户对应的ID
	 * @return 用户对象
	 * @throws WeixinException
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki/14/bb5031008f1494a59c6f71fa0f319c66.html">
	 *      获取用户信息</a>
	 */
	public List<User> getUsers(LangType lang, String... openIds) throws WeixinException {
		String api_users_info_uri = getRequestUri("api_users_info_uri");
		return null;
	}

	/**
	 * @Title：getFollowing
	 * @描述:
	 * 		<ul>
	 *      <li>通过本接口来获取帐号的关注者列表</li>
	 *      <li>一次拉取调用最多拉取10000个关注者的OpenID,可以通过多次拉取的方式来满足需求</li>
	 *      <li>http请求方式: GET(请使用https协议)</li>
	 *      </ul>
	 * @param nextOpenId
	 *            下一次拉取数据的openid
	 * @return：关注信息
	 * @throws WeixinException
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki/3/17e6919a39c1c53555185907acf70093.html">
	 *      获取关注者列表</a>
	 */
	public Following getFollowing(String nextOpenId) throws WeixinException {
		String following_uri = getRequestUri("following_uri");

		return null;
	}

	/**
	 * @Title：getAllFollowing @Description：
	 *                        <ul>
	 *                        <li>获取用户全部的关注者列表</li>
	 *                        <li>当公众号关注者数量超过10000时,可通过填写next_openid的值,
	 *                        从而多次拉取列表的方式来满足需求,
	 *                        将上一次调用得到的返回中的next_openid值,作为下一次调用中的next_openid值
	 *                        </li>
	 *                        </ul>
	 * @return 用户对象集合
	 * @throws WeixinException
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki/3/17e6919a39c1c53555185907acf70093.html">
	 *      获取关注者列表</a>
	 */
	public List<User> getAllFollowing() throws WeixinException {
		List<User> userList = new ArrayList<User>();
		String nextOpenId = "";
		Following following = null;
		for (;;) {
			following = getFollowing(nextOpenId);
			if (following.getCount() == 0) {
				break;
			}
			userList.addAll(following.getUserList());
			nextOpenId = following.getNextOpenId();
		}
		return userList;
	}

	/**
	 * @Title：remarkUserName
	 * @描述:
	 * 		<ul>
	 *      <li>通过该接口对指定用户设置备注名,该接口暂时开放给微信认证的服务号</li>
	 *      <li>http请求方式: POST(请使用https协议)</li>
	 *      <li>POST数据格式：JSON</li>
	 *      </ul>
	 * @param openId
	 *            用户ID
	 * @param remark
	 *            备注名
	 * @throws WeixinException
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki/10/bf8f4e3074e1cf91eb6518b6d08d223e.html">
	 *      设置用户备注名</a>
	 */
	public JsonResult remarkUserName(String openId, String remark) throws WeixinException {
		String updateremark_uri = getRequestUri("updateremark_uri");
		return null;
	}

	/** -------------------------- 公有方法 end ------------------------------- */
	public static void main(String[] args) {
		try {
			new UserApi().getUser("1");
		} catch (WeixinException e) {
			e.printStackTrace();
		}
	}

}
