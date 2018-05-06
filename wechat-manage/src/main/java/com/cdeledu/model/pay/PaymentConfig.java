package com.cdeledu.model.pay;

import java.util.Date;

import com.cdeledu.common.base.BaseEntity;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 支付配置
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年5月1日 下午10:38:46
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class PaymentConfig extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 支付名称 */
	private String name;
	/** 开通时间 */
	private Date openTime;
	/** 结束时间 */
	private Date endTime;
	/** 所属国 */
	private String source;
	/** 交易类型代码 */
	private String currency;
	/** 交易类型名称 */
	private String itemName;
	/** 图标 */
	private String icon;
	/** 状态 */
	private Integer ifVisible;
	/** 描述 */
	private String remark;

	
	public PaymentConfig() {
	}

	public PaymentConfig(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getIfVisible() {
		return ifVisible;
	}

	public void setIfVisible(Integer ifVisible) {
		this.ifVisible = ifVisible;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n PaymentConfig [name=" + name + ", openTime=" + openTime
				+ ", endTime=" + endTime + ", source=" + source + ", currency=" + currency
				+ ", itemName=" + itemName + ", icon=" + icon + ", ifVisible=" + ifVisible
				+ ", remark=" + remark + "]";
	}

}
