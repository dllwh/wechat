package com.cdeledu.util.apache.io.file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.cdeledu.common.property.PropertyHelper;

/**
 * 
 * @ClassName: UploadUtils
 * @Description: 文件上传工具类
 * @author: 独泪了无痕
 * @date: 2015年7月25日 下午5:17:51
 * @version: V1.0
 */
public class UploadUtils {
	/**
	 * 上传临时目录
	 */
	public static final String UPLOAD_TMP_PATH = "download" + File.separator + "tmp";
	/**
	 * 图片目录
	 */
	public static final String ARICLE_PATH = "download" + File.separator + "image_url";
	/**
	 * 文件目录
	 */
	public static final String FILE_PATH = "download" + File.separator + "file_url";
	/**
	 * 视频目录
	 */
	public static final String VIDEO_PATH = "download" + File.separator + "video";

	private static Properties props = null;
	private static Map<String, String> propMap = null;

	static {
		props = PropertyHelper.getProps("/properties/config.properties");
		propMap = PropertyHelper.getPropertyMap(props);
	}

	/*-------------------------- 私有属性 begin -------------------------------*/
	/**
	 * 表单字段常量
	 */
	public static final String FORM_FIELDS = propMap.get("form");
	/**
	 * 文件域常量
	 */
	public static final String FILE_FIELDS = propMap.get("file");

	// 最大文件大小
	private static long maxSize = PropertyHelper.getLongValue(props, "uploadFileMaxSize");
	// 定义允许上传的文件扩展名
	private static Map<String, String> extMap = new HashMap<String, String>();

	public UploadUtils() {
		/** 其中images,flashs,medias,files,对应文件夹名称,对应dirName */
		/** key文件夹名称 */
		/** value该文件夹内可以上传文件的后缀名 */
		extMap.put("images", propMap.get("fileExts.images"));
		extMap.put("flashs", propMap.get("fileExts.flashs"));
		extMap.put("medias", propMap.get("fileExts.medias"));
		extMap.put("files", propMap.get("fileExts.files"));
	}

	// 文件保存目录相对路径
	private static String basePath = "upload";
	// 文件的目录名
	private static String dirName = "images";
	// 上传临时路径
	private static final String TEMP_PATH = "/temp";
	private static String tempPath = basePath + TEMP_PATH;

	// 文件保存目录路径
	private static String savePath;
	// 文件保存目录url
	private static String saveUrl;
	// 文件最终的url包括文件名
	private static String fileUrl;

	/*-------------------------- 私有属性 end   -------------------------------*/
	/*-------------------------- 私有方法 begin -------------------------------*/
	/**
	 * 
	 * @Title：validateFields
	 * @Description：上传验证,并初始化文件目录
	 * @param request
	 * @return
	 * @return：String 返回类型
	 */
	private static String validateFields(HttpServletRequest request) {
		String errorInfo = "true";

		// 获取内容类型
		String contentType = request.getContentType();
		int contentLength = request.getContentLength();
		// 文件保存目录路径
		savePath = request.getSession().getServletContext().getRealPath("/") + basePath + "/";
		// 文件保存目录URL
		saveUrl = request.getContextPath() + "/" + basePath + "/";

		File uploadDir = new File(savePath);

		if (contentType == null || !contentType.startsWith("multipart")) {
			errorInfo = "请求不包含multipart/form-data流";
		} else if (maxSize < contentLength) {
			errorInfo = "上传文件大小超出文件最大大小[" + maxSize + "]";
		} else if (!ServletFileUpload.isMultipartContent(request)) {
			errorInfo = "请选择文件";
		} else if (!uploadDir.isDirectory()) { // 检查目录(必须是文件夹不能是文件)
			errorInfo = "上传目录[" + savePath + "]不存在";
		} else if (!uploadDir.canWrite()) {
			errorInfo = "上传目录[" + savePath + "]没有写权限";
		} else if (!extMap.containsKey(dirName)) {
			errorInfo = "目录名不正确";
		} else {
			// .../basePath/dirName/
			// 创建文件夹
			savePath += dirName + "/";
			saveUrl += dirName + "/";
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			// .../basePath/dirName/yyyyMMdd/
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			savePath += ymd + "/";
			saveUrl += ymd + "/";
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}

			// 获取上传临时路径
			tempPath = request.getSession().getServletContext().getRealPath("/") + tempPath + "/";
			File file = new File(tempPath);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
		return errorInfo;
	}

	/**
	 * 
	 * @Title：initFields
	 * @Description：处理上传内容
	 * @param request
	 * @return：void 返回类型
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, Object> initFields(HttpServletRequest request) {

		// 存储表单字段和非表单字段
		Map<String, Object> resultMap = new HashMap<String, Object>();

		// 第一步：判断request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 第二步：解析request
		if (isMultipart) {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// 阀值,超过这个值才会写到临时目录,否则在内存中
			factory.setSizeThreshold(1024 * 1024 * 10);
			factory.setRepository(new File(tempPath));

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			upload.setHeaderEncoding("UTF-8");

			// 最大上传限制
			upload.setSizeMax(maxSize);

			/* FileItem */
			List<FileItem> items = null;
			// Parse the request
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}

			// 第3步：处理uploaded items
			if (items != null && items.size() > 0) {
				Iterator<FileItem> iter = items.iterator();
				// 文件域对象
				List<FileItem> list = new ArrayList<FileItem>();
				// 表单字段
				Map<String, String> fields = new HashMap<String, String>();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					// 处理所有表单元素和文件域表单元素
					if (item.isFormField()) { // 表单元素
						String name = item.getFieldName();
						String value = item.getString();
						fields.put(name, value);
					} else { // 文件域表单元素
						list.add(item);
					}
				}
				resultMap.put(FORM_FIELDS, fields);
				resultMap.put(FILE_FIELDS, list);
			}
		}
		return resultMap;
	}

	/**
	 * 
	 * @Title：saveFile
	 * @Description：保存文件
	 * @param item
	 * @return：void 返回类型
	 */
	private static String saveFile(FileItem item) {
		String error = "true";
		String fileName = item.getName();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

		if (item.getSize() > maxSize) { // 检查文件大小
			error = "上传文件大小超过限制";
		} else if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {// 检查扩展名
			error = "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。";
		} else {
			String newFileName;
			if ("".equals(fileName.trim())) {
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "."
						+ fileExt;
			} else {
				newFileName = fileName + "." + fileExt;
			}
			// .../basePath/dirName/yyyyMMdd/yyyyMMddHHmmss_xxx.xxx
			fileUrl = saveUrl + newFileName;
			try {
				File uploadedFile = new File(savePath, newFileName);

				item.write(uploadedFile);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return error;
	}

	/*-------------------------- 私有方法 end   -------------------------------*/
	/*-------------------------- 公有方法 begin -------------------------------*/
	/*-------------------------- 公有方法 end   -------------------------------*/

	/**
	 * 
	 * @Title：uploadFile
	 * @Description：文件上传
	 * @param request
	 * @return resultInfo
	 *         <ul>
	 *         <li>返回结果</li>
	 *         <li>info[0] 验证文件域返回错误信息</li>
	 *         <li>info[1] 上传文件错误信息</li>
	 *         <li>info[2] savePath:文件保存目录路径</li>
	 *         <li>info[3] saveUrl:文件保存目录url</li>
	 *         <li>info[4] fileUrl:文件最终的url包括文件名</li>
	 *         </ul>
	 * 		@return：String[] 返回类型
	 */
	@SuppressWarnings("unchecked")
	public static String[] uploadFile(HttpServletRequest request) {
		String[] resultInfo = new String[5];
		/**
		 * 验证
		 */
		resultInfo[0] = validateFields(request);

		/**
		 * 初始化表单元素
		 */
		Map<String, Object> fieldsMap = new HashMap<String, Object>();
		if ("true".equals(resultInfo[0])) {
			fieldsMap = initFields(request);
		}

		/**
		 * 上传
		 */
		List<FileItem> fiList = (List<FileItem>) fieldsMap.get(UploadUtils.FILE_FIELDS);
		if (CollectionUtils.isNotEmpty(fiList)) {
			for (FileItem item : fiList) {
				resultInfo[1] = saveFile(item);
			}
			resultInfo[2] = savePath;
			resultInfo[3] = saveUrl;
			resultInfo[4] = fileUrl;
		}

		return resultInfo;
	}
}
