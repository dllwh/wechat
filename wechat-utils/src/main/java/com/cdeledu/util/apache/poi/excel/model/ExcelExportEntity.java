package com.cdeledu.util.apache.poi.excel.model;

/**
 * 
 * @类名称 : ExcelExportEntity
 * @功能说明 : excel 导出工具类,对cell类型做映射
 * @创建人: 独泪了无痕
 *
 */
public class ExcelExportEntity {
	private Integer width;
	private Integer height;
	/** 对应exportName */
	private String name;
	/** 排列顺序 */
	private int orderNum;
	/** 是否支持换行 */
	private boolean isWrap;
	/** 是否需要合并 */
	private boolean needMerge;
	/** 数据库格式 */
	private String databaseFormat;
	/** 导出日期格式 */
	private String exportFormat;
	/** cell 函数 */
	private String cellFormula;

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public boolean isWrap() {
		return isWrap;
	}

	public void setWrap(boolean isWrap) {
		this.isWrap = isWrap;
	}

	public boolean isNeedMerge() {
		return needMerge;
	}

	public void setNeedMerge(boolean needMerge) {
		this.needMerge = needMerge;
	}

	public String getDatabaseFormat() {
		return databaseFormat;
	}

	public void setDatabaseFormat(String databaseFormat) {
		this.databaseFormat = databaseFormat;
	}

	public String getExportFormat() {
		return exportFormat;
	}

	public void setExportFormat(String exportFormat) {
		this.exportFormat = exportFormat;
	}

	public String getCellFormula() {
		return cellFormula;
	}

	public void setCellFormula(String cellFormula) {
		this.cellFormula = cellFormula;
	}
}
