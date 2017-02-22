package com.cdeledu.weixin.mp.api;

import java.util.List;

import com.cdeledu.weixin.base.exception.WeixinException;
import com.cdeledu.weixin.base.http.wechat.JsonResult;
import com.cdeledu.weixin.mp.model.Group;

/**
 * @ClassName: GroupApi
 * @Description: 分组相关API
 * @author: 独泪了无痕
 * @date: 2015年11月22日 下午2:44:23
 * @version: V1.0
 * @since: JDK 1.7
 * @see <a
 *      href="http://mp.weixin.qq.com/wiki/5/0d8acdd6d4433c877fbea938a2f133cd.html">分组接口</a>
 */
public class GroupApi extends MpApi {
	/**
	 * @Title: createGroup
	 * @Description: 创建分组
	 * @author: 独泪了无痕
	 * @param name
	 *            组名称
	 * @throws WeixinException
	 * @return group对象
	 * @see <a
	 *      href="http://mp.weixin.qq.com/wiki/5/0d8acdd6d4433c877fbea938a2f133cd.html#.E5.88.9B.E5.BB.BA.E5.88.86.E7.BB.84">创建分组</a>
	 */
	public Group createGroup(String name) throws WeixinException {
		String group_create_uri = getRequestUri("group_create_uri");
		return null;
	}

	/**
	 * @Title: getGroups
	 * @Description: 查询所有分组
	 * @author: 独泪了无痕
	 * @throws WeixinException
	 * @see <a
	 *      href="http://mp.weixin.qq.com/wiki/5/0d8acdd6d4433c877fbea938a2f133cd.html#.E6.9F.A5.E8.AF.A2.E6.89.80.E6.9C.89.E5.88.86.E7.BB.84">查询所有分组</a>
	 */
	public List<Group> getGroups() throws WeixinException {
		String group_get_uri = getRequestUri("group_get_uri");
		return null;
	}

	/**
	 * @Title: getGroupByOpenId
	 * @Description: 查询用户所在分组
	 * @author: 独泪了无痕
	 * @param openid
	 * @return
	 * @throws WeixinException
	 * @see <a href =
	 *      "http://mp.weixin.qq.com/wiki/5/0d8acdd6d4433c877fbea938a2f133cd.html#.E6.9F.A5.E8.AF.A2.E7.94.A8.E6.88.B7.E6.89.80.E5.9C.A8.E5.88.86.E7.BB.84">查询用户所在分组</a>
	 */
	public int getGroupByOpenId(String openid) throws WeixinException {
		String group_getid_uri = getRequestUri("group_getid_uri");
		return 0;
	}

	/**
	 * 
	 * @Title: modifyGroup
	 * @Description: 修改分组名
	 * @author: 独泪了无痕
	 * @param groupId
	 *            组ID
	 * @param name
	 *            组名称
	 * @return
	 * @throws WeixinException
	 * @see <a
	 *      href="http://mp.weixin.qq.com/wiki/5/0d8acdd6d4433c877fbea938a2f133cd.html#.E4.BF.AE.E6.94.B9.E5.88.86.E7.BB.84.E5.90.8D">修改分组名</a>
	 */
	public JsonResult modifyGroup(int groupId, String name)
			throws WeixinException {
		String group_modify_uri = getRequestUri("group_modify_uri");
		return null;
	}

	/**
	 * @Title: moveGroup
	 * @Description: 移动用户到分组
	 * @author: 独泪了无痕
	 * @param groupId
	 *            组ID
	 * @param openId
	 *            用户对应的ID
	 * @return
	 * @see <a
	 *      href="http://mp.weixin.qq.com/wiki/5/0d8acdd6d4433c877fbea938a2f133cd.html#.E7.A7.BB.E5.8A.A8.E7.94.A8.E6.88.B7.E5.88.86.E7.BB.84">移动用户分组</a>
	 */
	public JsonResult moveGroup(int groupId, String openId)
			throws WeixinException {
		String group_move_uri = getRequestUri("group_move_uri");
		return null;
	}

	/**
	 * @Title: moveGroup
	 * @Description: 批量移动用户分组
	 * @author: 独泪了无痕
	 * @param groupId
	 *            组ID
	 * @param openIds
	 *            用户ID列表(不能超过50个)
	 * @return
	 * @throws WeixinException
	 * @see <a
	 *      href="http://mp.weixin.qq.com/wiki/5/0d8acdd6d4433c877fbea938a2f133cd.html#.E6.89.B9.E9.87.8F.E7.A7.BB.E5.8A.A8.E7.94.A8.E6.88.B7.E5.88.86.E7.BB.84">批量移动用户分组</a>
	 */
	public JsonResult moveGroup(int groupId, String... openIds)
			throws WeixinException {
		String group_batchmove_uri = getRequestUri("group_batchmove_uri");
		return null;
	}

	/**
	 * @Title: deleteGroup
	 * @Description: 删除用户分组,所有该分组内的用户自动进入默认分组.
	 * @author: 独泪了无痕
	 * @param groupId
	 *            组ID
	 * @return
	 * @throws WeixinException
	 * @see <a href =
	 *      "http://mp.weixin.qq.com/wiki/5/0d8acdd6d4433c877fbea938a2f133cd.html#.E5.88.A0.E9.99.A4.E5.88.86.E7.BB.84">删除分组</a>
	 */
	public JsonResult deleteGroup(int groupId) throws WeixinException {
		String group_delete_uri = getRequestUri("group_delete_uri");
		return null;
	}
}
