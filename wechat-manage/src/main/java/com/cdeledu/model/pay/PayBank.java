package com.cdeledu.model.pay;

public class PayBank {
	private Integer id;
	/** 银行名称 */
	private String name;
	/** 是否可用 */
	private Integer ifEnabled;
	/** 是否有效 */
	private Integer ifVisible;
	/** 图标地址 */
	private String iconPath;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIfEnabled() {
		return ifEnabled;
	}

	public void setIfEnabled(Integer ifEnabled) {
		if (ifEnabled == 0 || ifEnabled == 1) {
			this.ifEnabled = ifEnabled;
		} else {
			this.ifEnabled = 1;
		}
	}

	public Integer getIfVisible() {
		return ifVisible;
	}

	public void setIfVisible(Integer ifVisible) {

		if (ifVisible == 0 || ifVisible == 1) {
			this.ifVisible = ifVisible;
		} else {
			this.ifVisible = 1;
		}
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	@Override
	public String toString() {
		return "PayBank [id=" + id + ", name=" + name + ", ifEnabled=" + ifEnabled + ", ifVisible="
				+ ifVisible + ", iconPath=" + iconPath + "]";
	}
}