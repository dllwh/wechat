package com.cdeledu.weixin.mp.datacube;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.cdeledu.weixin.base.type.UserSourceType;

/**
 * @ClassName: UserSummary
 * @Description: 数据统计:用户增减(返回参数说明)
 * @author: 独泪了无痕
 * @date: 2015年11月22日 下午1:53:41
 * @version: V1.0
 * @since: JDK 1.7
 * @see <a
 *      href="http://mp.weixin.qq.com/wiki/3/ecfed6e1a0a03b5f35e5efac98e864b7.html">用户分析数据接口</a>
 */
public class UserSummary implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 数据的日期
	 */
	@JSONField(name = "ref_date")
	private Date refDate;
	/**
	 * 用户的渠道
	 */
	@JSONField(name = "user_source")
	private int userSource;
	/**
	 * 新增的用户数量
	 */
	@JSONField(name = "new_user")
	private int newUser;
	/**
	 * 取消关注的用户数量,new_user减去cancel_user即为净增用户数量
	 */
	@JSONField(name = "cancel_user")
	private int cancelUser;
	/**
	 * 总用户量
	 */
	@JSONField(name = "cumulate_user")
	private int cumulateUser;

	public Date getRefDate() {
		return refDate;
	}

	public void setRefDate(Date refDate) {
		this.refDate = refDate;
	}

	public int getUserSource() {
		return userSource;
	}

	@JSONField(serialize = false)
	public UserSourceType getFormatUserSource() {
		if (userSource == 3) {
			return UserSourceType.QRCODE;
		} else if (userSource == 17) {
			return UserSourceType.CARDSHARE;
		} else if (userSource == 35) {
			return UserSourceType.SONUMBER;
		} else if (userSource == 39) {
			return UserSourceType.SOMPACCOUNT;
		} else if (userSource == 43) {
			return UserSourceType.ARTICLEMENU;
		} else {
			return UserSourceType.OTHER;
		}
	}

	public void setUserSource(int userSource) {
		this.userSource = userSource;
	}

	public int getNewUser() {
		return newUser;
	}

	public void setNewUser(int newUser) {
		this.newUser = newUser;
	}

	public int getCancelUser() {
		return cancelUser;
	}

	public void setCancelUser(int cancelUser) {
		this.cancelUser = cancelUser;
	}

	public int getCumulateUser() {
		return cumulateUser;
	}

	public void setCumulateUser(int cumulateUser) {
		this.cumulateUser = cumulateUser;
	}

	@Override
	public String toString() {
		return "UserSummary [refDate=" + refDate + ", userSource=" + userSource
				+ ", newUser=" + newUser + ", cancelUser=" + cancelUser
				+ ", cumulateUser=" + cumulateUser + "]";
	}
}
