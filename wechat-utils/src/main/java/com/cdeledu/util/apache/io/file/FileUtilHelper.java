package com.cdeledu.util.apache.io.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.cdeledu.common.constant.ConstantHelper;

/**
 * 
 * @ClassName: FileUtilHelper
 * @Description: 文件操作类
 * @author: 独泪了无痕
 * @date: 2015年8月27日 下午5:00:48
 * @version: V2.0
 * @history:
 */
public class FileUtilHelper extends FileUtils {
	/** -------------------------- 属性 start ------------------------------- */
	private static final Logger logger = LoggerFactory.getLogger(FileUtilHelper.class);

	/** -------------------------- 属性 end ------------------------------- */
	/** -------------------------- 私有方法 start ------------------------------- */

	@SuppressWarnings("unused")
	private static String[] splitt(String str) {
		String strr = str.trim();
		String[] abc = strr.split("[\\p{Space}]+");
		for (int i = 0; i < abc.length; i++) {
			System.out.println(abc[i]);
		}
		return abc;
	}
	/** -------------------------- 属性 end ------------------------------- */

	/**
	 * 
	 * @方法名称: getFileSize
	 * @方法描述: 获取文件大小
	 *        <ul>
	 *        <li>如果路径为空或空，返回-1</li>
	 *        <li>如果路径存在，它是一个文件，返回文件大小，否则retur-1</li>
	 *        <ul>
	 * @param path
	 * @return 返回以字节此文件的长度。返回-1，如果文件不存在。
	 */
	public static long getFileSize(String path) {
		if (StringUtils.isBlank(path)) {
			return -1;
		}
		File file = new File(path);
		return (file.exists() && file.isFile() ? file.length() : -1);
	}

	/**
	 * 
	 * @Title：convertFileSize @Description：
	 *                        <ul>
	 *                        <li>文件大小转换成可显示的TB、GB、MB、KB、字节方法</li>
	 *                        <li>字节转换成直观带单位的值</li>
	 *                        </ul>
	 * @param fileSize
	 * @return
	 * @return：String 返回类型
	 */
	public static String convertFileSize(long fileSize) {
		String result = "";
		if (fileSize >= ONE_EB) {// EB
			result = String.format("%.2f EB", (float) fileSize / ONE_EB);
		} else if (fileSize >= ONE_PB) {// PB
			float num = (float) fileSize / ONE_PB;
			result = String.format(num > 100 ? "%.1f PB" : "%.2f PB", num);
		} else if (fileSize >= ONE_TB) { // TB 级别
			float num = (float) fileSize / ONE_TB;
			result = String.format(num > 100 ? "%.1f TB" : "%.2f TB", num);
		} else if (fileSize >= ONE_GB) { // GB 级别
			float num = (float) fileSize / ONE_GB;
			result = String.format(num > 100 ? "%.1f GB" : "%.2f GB", num);
		} else if (fileSize >= ONE_MB) { // // MB 级别
			float num = (float) fileSize / ONE_MB;
			result = String.format(num > 100 ? "%.1f MB" : "%.2f MB", num);
		} else if (fileSize >= ONE_KB) { // // KB 级别
			float num = (float) fileSize / ONE_KB;
			result = String.format(num > 100 ? "%.1f KB" : "%.2f kB", num);
		} else {
			result = String.format("%d B", fileSize);
		}

		return result;
	}

	/**
	 * 
	 * @Title：saveFile
	 * @Description：保存上传文件
	 * @param path
	 * @param file
	 * @param uuid
	 * @return
	 * @return：boolean 返回类型
	 */
	public static boolean saveFile(String path, MultipartFile file, String uuid) {
		try {
			File newFile = new File(path);
			if (!newFile.exists()) {
				newFile.mkdir();
			}
			// 上传文件名
			String fileName = file.getOriginalFilename();
			// 检查文件后缀格式
			String fileEnd = fileName.substring(fileName.lastIndexOf(".") + 1);

			// 判断文件是否为空
			if (!file.isEmpty()) {
				// 真实上传路径
				StringBuffer sbRealPath = new StringBuffer();
				sbRealPath.append(path).append(uuid).append(".").append(fileEnd);
				// 转存文件
				file.transferTo(new File(sbRealPath.toString()));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @方法:递归遍历目录以及子目录中的所有文件
	 * @创建人:独泪了无痕
	 * @param file
	 *            当前遍历文件
	 * @return
	 */
	public static List<File> loadFileName(File file) {
		List<File> fileNameList = new ArrayList<File>();
		if (file.isFile()) {
			fileNameList.add(file);
		}
		if (file.isDirectory()) {
			for (File fileName : file.listFiles()) {
				fileNameList.addAll(loadFileName(fileName));
			}
		}
		return fileNameList;
	}

	/**
	 * @方法:目录是否为空
	 * @创建人:独泪了无痕
	 * @param file
	 * @return
	 */
	public static boolean isEmpty(File file) {
		if (null == file) {
			return true;
		}
		if (file.isDirectory()) {
			String[] subFiles = file.list();
			if (ArrayUtils.isEmpty(subFiles)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @方法:获取标准的绝对路径
	 * @创建人:独泪了无痕
	 * @param file
	 *            文件
	 * @return 绝对路径
	 */
	public static String getAbsolutePath(File file) {
		if (file == null) {
			return null;
		}

		try {
			return file.getCanonicalPath();
		} catch (IOException e) {
			return file.getAbsolutePath();
		}
	}

	public static String readFile(String filePath) {
		return readFile(filePath, ConstantHelper.UTF_8.name());
	}

	/**
	 * @方法描述: 读取文件内容(i/o进行读取htm模版)
	 * @创建者: 独泪了无痕
	 * @创建时间: 2016年2月26日 下午2:07:28
	 * @param filePath
	 *            要读取文件的路径
	 * @param encoding
	 *            要读取文件的编码
	 * @param filePath
	 *            要读取文件的路径
	 * @return
	 */
	public static String readFile(String filePath, String encoding) {
		String fileContent = "";
		if (StringUtils.isBlank(encoding)) {
			return fileContent;
		}

		if (StringUtils.isBlank(encoding) || StringUtils.equalsIgnoreCase(encoding, "null")) {
			encoding = ConstantHelper.UTF_8.name();
		}

		try {
			File f = new File(filePath);
			if (f.isFile() && f.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(f), encoding);
				BufferedReader reader = new BufferedReader(read);
				String line;
				while ((line = reader.readLine()) != null) {
					// splitt(line); // system控制台
					fileContent += line + "\n";
				}
				reader.close();
				read.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileContent;
	}

	/**
	 * @方法描述: 删除文件夹以及其子文件
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年10月11日 下午2:35:00
	 * @param folder
	 * @return
	 */
	public static boolean deleteFolder(File folder) {
		return deleteFolderContents(folder) && folder.delete();
	}

	/**
	 * @方法描述: 遍历删除文件夹的文件,但保留文件夹
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年10月11日 下午2:37:24
	 * @param folder
	 * @return
	 */
	public static boolean deleteFolderContents(File folder) {
		logger.debug("Deleting content of: " + folder.getAbsolutePath());
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				if (!file.delete()) {
					return false;
				}
			} else {
				if (!deleteFolder(file)) {
					return false;
				}
			}
		}
		return true;
	}
}
