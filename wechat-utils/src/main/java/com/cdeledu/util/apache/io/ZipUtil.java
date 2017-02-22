package com.cdeledu.util.apache.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import com.cdeledu.common.constant.ConstantHelper;
import com.cdeledu.common.exception.ExceptionHelper;
import com.cdeledu.util.apache.io.file.FileUtilHelper;
import com.cdeledu.util.apache.lang.StringUtilHelper;
import com.cdeledu.util.application.log.Log;
import com.cdeledu.util.application.log.level.StaticLog;

/**
 * 
 * @ClassName: ZipUtil
 * @Description: 压缩/解压缩zip包处理类
 * @author: 独泪了无痕
 * @date: 2015年8月28日 上午7:59:32
 * @version: V1.0
 * @history:
 */
public class ZipUtil {
	/*--------------------------私有属性 start -------------------------------*/
	private final static Log log = StaticLog.get();
	private static final int BUFFEREDSIZE = 8192;

	/*--------------------------私有属性 end   -------------------------------*/
	/*--------------------------私有方法 start -------------------------------*/

	/**
	 * 
	 * @Title: getEntryName
	 * @Description: 获得zip entry 字符串
	 * @param base
	 * @param file
	 * @return
	 */
	private static String getEntryName(String base, File file) {
		File baseFile = new File(base);
		String filename = file.getPath();
		if (baseFile.getParentFile().getParentFile() == null)
			return filename.substring(baseFile.getParent().length());
		return filename.substring(baseFile.getParent().length() + 1);
	}

	/*--------------------------私有方法 end   -------------------------------*/
	/*--------------------------公有方法 start -------------------------------*/

	/**
	 * 
	 * @Title：unZipFiles
	 * @Description：解压文件到指定目录
	 * @param zipPath
	 *            源路径:要解压 zip文件路径
	 * @param targetPath
	 *            解压缩到的位置，如果为null或空字符串则默认解压缩到跟zip包同目录跟zip包同名的文件夹下
	 * @throws Exception
	 * @return：void 返回类型
	 */
	public static void unZipFiles(String zipPath, String targetPath)
			throws Exception {
		if (StringUtilHelper.isBlank(zipPath)) {
			ExceptionHelper.getExceptionHint("ZipUtil", "unZipFiles",
					"目标文件的路径不能为空!");
		}

		if (StringUtilHelper.isBlank(targetPath)) {
			targetPath = zipPath.substring(0, zipPath.lastIndexOf("."));
		}
		unZipFiles(new File(zipPath), targetPath);
	}

	/**
	 * 
	 * @Title: unZipFiles
	 * @Description: 解压文件到指定目录
	 * @param zipFile
	 * @param targetPath
	 *            解压缩到的位置，如果为null或空字符串则默认解压缩到跟zip包同目录跟zip包同名的文件夹下
	 * @throws Exception
	 */
	public static void unZipFiles(File zipFile, String targetPath) {
		OutputStream os = null;
		InputStream is = null;
		ZipFile zip = null;
		if (StringUtilHelper.isBlank(targetPath)) {
			String zipPath = zipFile.getAbsolutePath();
			targetPath = zipPath.substring(0, zipPath.lastIndexOf("."));
		}
		File pathFile = new File(targetPath);

		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		try {
			zip = new ZipFile(zipFile);

			for (Enumeration<?> entries = zip.getEntries(); entries
					.hasMoreElements();) {
				ZipEntry zipEntry = (ZipEntry) entries.nextElement();
				String zipEntryName = zipEntry.getName();
				InputStream in = zip.getInputStream(zipEntry);
				String outPath = FilenameUtils.concat(targetPath, zipEntryName)
						.replaceAll("\\*", "/");
				outPath = new String(outPath.getBytes("utf-8"), "ISO8859-1");

				// 判断路径是否存在,不存在则创建文件路径
				File file = null;
				if (outPath.indexOf("/") > 0) {
					file = new File(outPath.substring(0,
							outPath.lastIndexOf('/')));
				} else {
					file = new File(outPath.substring(0,
							outPath.lastIndexOf('\\')));
				}
				if (!file.exists()) {
					file.mkdirs();
				}
				// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
				if (new File(outPath).isDirectory()) {
					continue;
				}
				// 输出文件路径信息
				log.info(outPath);

				OutputStream out = new FileOutputStream(outPath);
				byte[] buf1 = new byte[BUFFEREDSIZE];
				int len;
				while ((len = in.read(buf1)) > 0) {
					out.write(buf1, 0, len);
				}
				in.close();
				out.close();

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (zip != null) {
					zip.close();
				}
				if (os != null) {
					os.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 
	 * @Title: zip
	 * @Description: 压缩文件或者文件目录到指定的zip或者rar包
	 * @param zipFileName
	 *            压缩产生的zip文件名--带路径,如果为null或空则默认按文件名生产压缩文件名
	 * @param source
	 *            源路径,可以是文件,也可以目录
	 * @param directory
	 *            目标路径,压缩文件名
	 */
	public static void zip(String zipFileName, String source, String directory) {
		ZipOutputStream zos = null;
		BufferedInputStream bis = null;
		String fileName = StringUtils.isBlank(zipFileName) ? FilenameUtils
				.getName(source) : zipFileName;
		byte[] buffere = new byte[BUFFEREDSIZE];
		File temp = new File(source);

		try {
			if (StringUtils.isBlank(source)) {
				return;
			} else {
				if (StringUtils.isBlank(directory)) {
					// 检查对象是否是文件夹(即目录)
					if (temp.isDirectory()) {
						directory = source + ".zip";
					} else {
						if (source.indexOf(".") > 0)
							directory = source.substring(0,
									source.lastIndexOf("."))
									+ ".zip";
						else
							directory = source + ".zip";
					}
				} else {
					// 判断目标文件是否存在,若不存在则创建
					File dirFile = new File(directory);
					if (!dirFile.exists()) {
						FileUtils.forceMkdir(dirFile);
					}
					if (fileName.indexOf(".") > 0) {
						fileName = fileName.substring(0, fileName.indexOf("."));
					}
					directory += File.separator + fileName + ".zip";
				}
			}
			// 递归获得该文件下所有文件名(不包括目录名)
			List<File> fileList = FileUtilHelper.loadFileName(new File(source));

			zos = new ZipOutputStream(new FileOutputStream(directory),
					ConstantHelper.GBK);

			for (int i = 0; i < fileList.size(); i++) {
				File file = (File) fileList.get(i);
				zos.putNextEntry(new ZipEntry(getEntryName(source, file)));

				bis = new BufferedInputStream(new FileInputStream(file));

				int length = 0;
				while ((length = bis.read(buffere)) != -1) {
					zos.write(buffere, 0, length);
				}
				bis.close();
				zos.closeEntry();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (zos != null) {
					zos.close();
				}
			} catch (Exception e) {
			}
		}
	}

	/*--------------------------公有方法 start -------------------------------*/

	public static void main(String[] args) throws Exception {
		unZipFiles("D:\\test\\aa.zip", null);
	}
}
