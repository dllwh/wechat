package com.cdeledu.weixin.mp.api;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.cdeledu.weixin.base.exception.WeixinException;
import com.cdeledu.weixin.base.http.wechat.JsonResult;
import com.cdeledu.weixin.mp.model.Button;

/**
 * @ClassName: MenuApi
 * @Description: 菜单相关API
 * @author: 独泪了无痕
 * @date: 2015年11月22日 下午5:27:39
 * @version: V1.0
 * @since: JDK 1.7
 * @see <a href=
 *      "http://mp.weixin.qq.com/wiki/0/c48ccd12b69ae023159b4bfaa7c39c20.html">
 *      个性化菜单接口</a>
 */
public class MenuApi extends MpApi {

	/**
	 * @Title：createMenu
	 * @Description：自定义菜单创建接口
	 * @throws WeixinException
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki/10/0234e39a2025342c17a7d23595c6b40a.html">
	 *      创建自定义菜单</a>
	 * @return：void 返回类型
	 */
	public JsonResult createMenu(List<Button> buttons) throws WeixinException {
		String menu_create_uri = getRequestUri("menu_create_uri");
		System.out.println(menu_create_uri);
		return null;
	}

	/**
	 * @方法: 查询菜单
	 * @创建人:独泪了无痕
	 * @return <a href=
	 *         "http://mp.weixin.qq.com/wiki/16/ff9b7b85220e1396ffa16794a9d95adc.html">
	 *         创建自定义菜单</a>
	 * @throws WeixinException
	 */
	public JSONObject getAllMenu() throws WeixinException {
		String menu_get_uri = getRequestUri("menu_get_uri");
		return null;
	}

	/**
	 * @方法:删除菜单
	 * @创建人:独泪了无痕
	 * @return
	 * @throws WeixinException
	 * @see <a href=
	 *      "http://mp.weixin.qq.com/wiki/16/8ed41ba931e4845844ad6d1eeb8060c8.html">
	 *      删除菜单</a>
	 */
	public JsonResult deleteMenu() throws WeixinException {
		String menu_delete_uri = getRequestUri("menu_delete_uri");
		return null;
	}

	public static void main(String[] args) {
		try {
			new MenuApi().createMenu(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
