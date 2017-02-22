package com.cdeledu.util.apache.poi.excel.model;

/**
 * 
 * @类名称 : TemplateExportParams
 * @功能说明 : 模板导出参数设置
 * @创建人: 独泪了无痕
 *
 */
public class TemplateExportParams {
	/** 模板的路径 */
	private String templateUrl;
	/** 需要导出的第几个 sheetNum,默认是第0个 */
	private int sheetNum = 0;
	/** 这只sheetName 不填就使用原来的 */
	private String sheetName;

	public String getTemplateUrl() {
		return templateUrl;
	}

	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}

	public int getSheetNum() {
		return sheetNum;
	}

	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public TemplateExportParams() {
		super();
	}

	public TemplateExportParams(String templateUrl, int sheetNum,
			String sheetName) {
		super();
		this.templateUrl = templateUrl;
		this.sheetNum = sheetNum;
		this.sheetName = sheetName;
	}

	public TemplateExportParams(String templateUrl, int sheetNum) {
		super();
		this.templateUrl = templateUrl;
		this.sheetNum = sheetNum;
	}

	public TemplateExportParams(String templateUrl, String sheetName) {
		super();
		this.templateUrl = templateUrl;
		this.sheetName = sheetName;
	}
}
