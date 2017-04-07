package com.cdeledu.util.apache.poi.excel;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.Region;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cdeledu.common.mapper.ObjectUtils;

/**
 * 
 * @类名称 : ExcelExportUtil
 * @功能说明 : excel 导出工具类
 * @创建人: 独泪了无痕
 *
 */
@SuppressWarnings("deprecation")
final class ExcelExportUtil {
	/** -------------------------- 私有属性 start ------------------------------- */
	// 创建Excel文件(Workbook)
	static HSSFWorkbook hssfWorkbook;
	static XSSFWorkbook xssfWorkbook;
	// 创建工作表(Sheet)
	static HSSFSheet hSheet;
	// 创建行,从0开始
	static Row row;
	// 创建行的单元格,从0开始
	static Cell cell;
	// 字体
	static Font font;
	// 日期格式
	static DataFormat dataFormat;

	/** -------------------------- 私有属性 end ------------------------------- */

	/** -------------------------- 私有方法 start ------------------------------- */
	/** 单元格样式 */
	private static Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> cellStyle = new HashMap<String, CellStyle>();
		// 创建实例
		CellStyle style;
		// 创建字体实例
		Font font;

		/**
		 * 标题
		 */
		style = wb.createCellStyle();
		font = wb.createFont();
		font.setFontHeightInPoints((short) 18);// 字号 大小
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);// 加粗
		font.setFontName("宋体");// 字体名
		style.setAlignment(CellStyle.ALIGN_CENTER);// 设置水平居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 设置垂直居中
		style.setFont(font);// 设置字体
		cellStyle.put("title", style);

		/**
		 * 表头
		 */
		font = wb.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);// 加粗
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);// 设置水平居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 设置垂直居中
		style.setWrapText(true);// 自动换行
		style.setFont(font);
		cellStyle.put("header", style);

		/**
		 * 列
		 */
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);// 设置水平居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 设置垂直居中
		style.setWrapText(true);
		cellStyle.put("cell", style);

		return cellStyle;
	}

	/** -------------------------- 私有方法 end ------------------------------- */

	/** -------------------------- 公有方法 start ------------------------------- */
	/**
	 * 
	 * @Title: export
	 * @Description: 基于结果集的导出
	 *               <ul>
	 *               <li>基于结果集的导出</li>
	 *               <li>XSSFWorkbook used for .xslx >= 2007</li>
	 *               <li>HSSFWorkbook used for .xsl 03</li>
	 *               </ul>
	 * @param title
	 * @param cellNames
	 * @param dbList
	 * @return
	 * @throws Exception
	 */
	public static <T> byte[] export(String title, String[][] cellNames, List<T> dbList)
			throws Exception {
		if (CollectionUtils.isEmpty(dbList) || dbList.size() < 0)
			return null;
		
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook workbook = new HSSFWorkbook();
		Map<String, CellStyle> getStyle = createStyles(workbook);
		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = workbook.createSheet(title);
		// 创建字体样式
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		// 创建单元格样式
		// 创建Excel的sheet的一行
		HSSFRow row = sheet.createRow(0);
		// 设定行的高度
		row.setHeight((short) 500);
		// 创建一个Excel的单元格
		HSSFCell cell = row.createCell(0);
		// 合并单元格(startRow，endRow，startColumn，endColumn)
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, cellNames.length - 1));
		// 给合并后的单元格加上样式
		for (int j = 0; j <= cellNames.length - 1; j++) {
			HSSFCell cell_temp = row.getCell(j);
			if (cell_temp == null) {
				cell_temp = row.createCell(j);
			}
		}
		// 给Excel的单元格设置样式和赋值
		cell.setCellValue(title);
		HSSFRow rowTitle = sheet.createRow(1);
		for (int i = 0; i < cellNames.length; i++) {
			HSSFCell cellTitle = rowTitle.createCell(i);
			// 设置excel列名
			cellTitle.setCellValue(cellNames[i][1]);
		}
		int i = 0;
		for (T bd : dbList) {
			row = sheet.createRow(i + 2);
			for (int j = 0; j < cellNames.length; j++) {
				HSSFCell cellvalue = row.createCell(j);
				String value = null;
				Object object = ObjectUtils.getProperty(bd, cellNames[j][0]);
				if (null == object) {
					value = "";
				} else if (object instanceof Date) {
					Date date = (Date) object;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					value = sdf.format(date);
				} else {
					value = String.valueOf(object);
				}
				if ("str".equals(cellNames[j][2])) {
					cellvalue.setCellValue(value);
					cellvalue.setCellStyle(getStyle.get("cell"));
				} else {
					HSSFDataFormat format = workbook.createDataFormat();
					HSSFCellStyle formatStyle = workbook.createCellStyle();
					// 设置边框
					formatStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					formatStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					formatStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					formatStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					// 设置字体
					formatStyle.setFont(font);
					if ("amt".equals(cellNames[j][2])) {
						cellvalue.setCellValue(Double.parseDouble(value));
						// 设置保留2位小数--使用Excel内嵌的格式
						formatStyle.setDataFormat(format.getFormat("#,##0.00"));
					} else if ("datetime".equals(cellNames[j][2])) {
						cellvalue.setCellValue(value);
						// 设置日期格式--使用Excel内嵌的格式
						formatStyle.setDataFormat(format.getFormat("yyyy-MM-dd hh:mm:ss"));
					}
					// 设置货币格式--使用自定义的格式
					// cellStyle.setDataFormat(format.getFormat("¥#,##0"));
					// 设置百分比格式--使用自定义的格式
					// cellStyle.setDataFormat(DataFormat.getBuiltinFormat("0.00%"));
					// 设置中文大写格式--使用自定义的格式
					// cellStyle.setDataFormat(format.getFormat("[DbNum2][$-804]0"));
					cellvalue.setCellStyle(formatStyle);
				}
			}
			i++;
		}
		if (i == 0) {
			return null;
		}
		for (int k = 0; k < cellNames.length; k++) {
			// 自动设置列宽
			sheet.autoSizeColumn(k, true);
		}
		// 将生成的Excel放入IO流中
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		// 在内存中把数据写入ByteArrayOutputStream os
		workbook.write(os);
		// 在内存中,得到os的字节数组
		byte[] content = os.toByteArray();
		return content;
	}

	/** ----------------------------------------------------- 练习示例 start */
	/**
	 * @方法:导出Excel文件
	 * @创建人:独泪了无痕
	 * @param excelFilePath
	 *            文件路径
	 * @param title
	 *            标题
	 * @param subject
	 *            主题
	 * @param author
	 *            作者
	 * @param company
	 *            公司
	 * @param comments
	 *            备注
	 * @see <a href=
	 *      "https://github.com/dllwh/commonUtil/blob/master/src/main/resources/tmp/files/Execl%E7%A4%BA%E4%BE%8B1.bmp">
	 *      练习示例图</a>
	 */
	public static void export(String excelFilePath, String title, String subject, String author,
			String company, String comments) {

		hssfWorkbook = new HSSFWorkbook();
		Map<String, CellStyle> getStyle = createStyles(hssfWorkbook);
		/**
		 * 1.创建文档信息
		 */
		hssfWorkbook.createInformationProperties();
		// 摘要信息
		DocumentSummaryInformation dsi = hssfWorkbook.getDocumentSummaryInformation();
		// 类别
		dsi.setCategory("Microsoft Office Excel 97-2003 工作表 (.xls)");
		if (StringUtils.isNotEmpty(company)) {
			dsi.setCompany(company);
		}
		// 摘要信息
		SummaryInformation si = hssfWorkbook.getSummaryInformation();
		// 主题
		if (StringUtils.isNotEmpty(subject)) {
			si.setSubject(subject);
		}
		// 标题
		if (StringUtils.isNotEmpty(title)) {
			si.setTitle(title);
		}
		// 作者
		if (StringUtils.isNotEmpty(author)) {
			si.setAuthor(author);
		}
		// 备注
		if (StringUtils.isNotEmpty(comments)) {
			si.setComments(comments);
		}

		/**
		 * 2.工作表设置
		 */
		if (StringUtils.isNoneBlank(title))
			hSheet = hssfWorkbook.createSheet(title);
		else
			hSheet = hssfWorkbook.createSheet();

		// 指示是否符合页面打印选项是启用标志
		hSheet.setFitToPage(true);
		// 是否输出水平为中心的网页上
		hSheet.setHorizontallyCenter(true);
		// 自适应列宽度
		hSheet.autoSizeColumn(1, true);
		// 列宽度
		hSheet.setDefaultColumnWidth((short) 20);
		/**
		 * 3.设置打印区域
		 */
		PrintSetup printSetup = hSheet.getPrintSetup();
		// 设置一个布尔值，允许或阻止横向打印[默认状态:false]
		printSetup.setLandscape(true);
		// 缩放比例(默认状态:100)
		printSetup.setScale((short) 100);
		// 页宽
		printSetup.setFitWidth((short) 1);
		// 页高
		printSetup.setFitHeight((short) 0);
		/**
		 * 4.合并单元格的行或者列
		 */
		// 四个参数分别是：起始行，起始列，结束行，结束列
		hSheet.addMergedRegion(new Region(0, (short) (0), 1, (short) 1));
		hSheet.addMergedRegion(new Region(0, (short) (2), 1, (short) 4));

		/**
		 * 5.创建表头
		 */
		Row headerRow = hSheet.createRow(0);
		Cell headerCell;

		headerCell = headerRow.createCell(0);

		headerCell.setCellValue("罗杰夫:luojiefu189");
		headerCell.setCellStyle(getStyle.get("header"));

		headerCell = headerRow.createCell(2);
		headerCell.setCellValue("财务报表分析事务--成为老板想要的会计(第五期) \r\n ID:420554");
		headerCell.setCellStyle(getStyle.get("header"));

		Row headerRowRegion = hSheet.createRow(2);
		headerRowRegion.setHeightInPoints(15);
		Cell headerCellRegion;

		headerCellRegion = headerRowRegion.createCell(0);
		headerCellRegion.setCellValue("序号");

		headerCellRegion = headerRowRegion.createCell(1);
		headerCellRegion.setCellValue("用户名");

		headerCellRegion = headerRowRegion.createCell(2);
		headerCellRegion.setCellValue("姓名");
		headerCellRegion.setCellStyle(getStyle.get("header"));

		headerCellRegion = headerRowRegion.createCell(3);
		headerCellRegion.setCellValue("交费时间");
		headerCellRegion.setCellStyle(getStyle.get("header"));

		headerCellRegion = headerRowRegion.createCell(4);
		headerCellRegion.setCellValue("交费金额");
		headerCellRegion.setCellStyle(getStyle.get("header"));

		/**
		 * 6.数据部分
		 */
		int leng = 6;
		int num = 0;
		int sum = 0;
		for (int i = 0; i < leng; i++) {
			// 定义数据从第4行开始
			Cell rowCell;
			Row cellRow = hSheet.createRow(i + 3);
			rowCell = cellRow.createCell(0);
			rowCell.setCellValue(i + 1);
			rowCell.setCellStyle(getStyle.get("cell"));

			rowCell = cellRow.createCell(1);
			rowCell.setCellValue("test" + i);
			rowCell.setCellStyle(getStyle.get("cell"));

			rowCell = cellRow.createCell(2);
			rowCell.setCellValue("test" + i);
			rowCell.setCellStyle(getStyle.get("cell"));

			rowCell = cellRow.createCell(3);
			rowCell.setCellValue("test" + i);
			rowCell.setCellStyle(getStyle.get("cell"));

			rowCell = cellRow.createCell(4);
			int result = i * i;
			rowCell.setCellValue(result);
			rowCell.setCellStyle(getStyle.get("cell"));
			num++;
			sum += result;
		}

		/**
		 * 7.统计部分
		 */
		Row countRowRegion;
		Cell countCell;
		hSheet.addMergedRegion(new Region(leng + 3, (short) (0), leng + 3, (short) 3));
		countRowRegion = hSheet.createRow(leng + 3);
		countCell = countRowRegion.createCell(0);
		countCell.setCellValue("总计");
		countCell.setCellStyle(getStyle.get("header"));
		countCell = countRowRegion.createCell(4);
		countCell.setCellValue(sum);
		countCell.setCellStyle(getStyle.get("cell"));
		hSheet.addMergedRegion(new Region(leng + 4, (short) (0), leng + 4, (short) 3));
		countRowRegion = hSheet.createRow(leng + 4);
		countCell = countRowRegion.createCell(0);
		countCell.setCellValue("总数");
		countCell.setCellStyle(getStyle.get("header"));
		countCell = countRowRegion.createCell(4);
		countCell.setCellValue(num);
		countCell.setCellStyle(getStyle.get("cell"));
		hSheet.addMergedRegion(new Region(leng + 5, (short) (0), leng + 5, (short) 3));
		countRowRegion = hSheet.createRow(leng + 5);
		countCell = countRowRegion.createCell(0);
		countCell.setCellValue("老师获取分成金额");
		countCell.setCellStyle(getStyle.get("header"));
		countCell = countRowRegion.createCell(4);
		countCell.setCellValue(sum * 0.8);
		countCell.setCellStyle(getStyle.get("cell"));
		try {
			/**
			 * 第一种输出到路径文件
			 */
			FileOutputStream fout = new FileOutputStream(excelFilePath);
			hssfWorkbook.write(fout);
			fout.flush();
			fout.close();
			/**
			 * 第二种输出到页面,形成下载文件
			 */
			/*
			 * response.reset();
			 * response.setContentType("application/x-msdownload");
			 * response.setHeader("Content-Disposition","attachment; filename="
			 * +new String(pName.getBytes("gb2312"),"ISO-8859-1")+".xls");
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** ----------------------------------------------------- 练习示例 end */
	/** -------------------------- 公有方法 end ------------------------------- */
}
