package com.cdeledu.util.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * @描述: 使用java快速生成word文档
 * @author: 独泪了无痕
 * @date: 2015年12月15日 下午5:20:23
 * @version: V1.0
 * @since: JDK 1.7
 * @see <a href="http://blog.csdn.net/shehun1/article/details/16946065">
 *      使用java快速生成word文档</a>
 */
@SuppressWarnings("deprecation")
public class DocUtil {
	/** -------------------------- 属性 begin ------------------------------- */
	// freemark模板配置
	private Configuration configure = null;

	/**
	 * 初始化FreeMarker配置
	 */
	public DocUtil() {
		configure = new Configuration();
		configure.setDefaultEncoding("utf-8");
	}

	/** -------------------------- 属性 end ------------------------------- */
	/** -------------------------- 私有方法 begin ------------------------------- */
	/** -------------------------- 私有方法 end ------------------------------- */

	/** -------------------------- 公有方法 begin ------------------------------- */
	/**
	 * @Title: createDoc
	 * @Description: 根据Doc模板生成word文件
	 * @author: 独泪了无痕
	 * @param dataMap
	 *            需要填入模板的数据
	 * @param templatePath
	 *            需要填入模板文件的位置
	 * @param templateName
	 *            需要填入模板文件的名称
	 * @param saveFilePathAndName
	 *            保存生成文件路径
	 */
	public void createDoc(Map<String, Object> dataMap, String templatePath, String templateName,
			String saveFilePathAndName) {
		// 创建一个模版对象
		Template temp = null;
		Writer out = null;

		try {
			// 设置FreeMarker的模版文件夹位置(加载模板文件)
			configure.setClassForTemplateLoading(this.getClass(), templatePath);
			// 设置对象包装器
			configure.setObjectWrapper(new DefaultObjectWrapper());
			// 设置异常处理类
			configure.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
			// 定义Template对象,注意模板类型名字与downloadType要一致
			temp = configure.getTemplate(templateName);

			// 输出文档
			File outFile = new File(saveFilePathAndName);
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));

			// 输出数据(填充数据)到模板中,生成文件
			temp.process(dataMap, out);
			outFile.delete();
		} catch (Exception ie) {
			ie.printStackTrace();
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	public static void main(String[] args) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("createUnit", "湖南衡利丰陶瓷有限公司");
		new DocUtil().createDoc(dataMap, "/tmp/microsoft/", "wordTemplate.xml",
				"C:\\Users\\dell\\Desktop\\湖南衡利丰陶瓷有限公司生产安全事故报告表.doc");
	}
	/** -------------------------- 公有方法 end ------------------------------- */
}
