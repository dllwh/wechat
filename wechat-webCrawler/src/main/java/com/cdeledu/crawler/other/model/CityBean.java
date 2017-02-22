package com.cdeledu.crawler.other.model;

/**
 * @类描述: 中国行政区域
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年8月18日 上午11:01:38
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href="http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/">统计用区划和城乡划分代码
 *      </a>
 */
public class CityBean {
	/** ----------------------------------------------------- Fields start */
	private int id; // 自增主键id
	private int parentId; // 父id
	private int type; // 类型 0:国家 ;1:省、直辖市;2:市;3:区;4:街道 、办事处;5:村委会、村
	private String name;// 省市区办事处
	private String code; // 编号
	private String url;// 点击访问地址

	/** ----------------------------------------------------- Fields end */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
