package com.cdeledu.util.apache.io.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @ClassName: DownFileHelper
 * @Description: 文件下载工具类
 * @author: 独泪了无痕
 * @date: 2015-8-19 下午11:30:21
 * @version: V2.0
 */
class DownFileHelper {

	/**
	 * 
	 * @Title：isLinux
	 * @Description：判断是否是Linux系统
	 * @return
	 * @return：boolean 返回类型
	 */
	private static boolean isLinux() {
		String osType = System.getProperties().getProperty("os.name")
				.toLowerCase();
		if (osType.startsWith("windows")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * @Title：downloadLocal
	 * @Description： 下载本地文件
	 * @param request
	 * @param response
	 * @param fileUrl
	 *            是指欲下载的文件的路径。
	 * @return：void 返回类型
	 */
	public void downloadLocal(HttpServletRequest request,
			HttpServletResponse response, String filePath) {
		InputStream is = null;
		String basePath = "";// 文件保存目录相对路径(默认是windows之下的upload)
		String fileUrl = "";// 文件的存放路径

		/**
		 * 1.创建要下载的文件的对象(参数为要下载的文件在服务器上的路径)
		 */
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				response.sendError(404, "File not found!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		/**
		 * 2.取得文件名(fileName)、文件后缀(ext)
		 * <p>
		 * 设置要显示在保存窗口的文件名，如果文件名中有中文的话，则要设置字符集，否则会出现乱码。另外，要写上文件后缀名
		 */
		String fileName = file.getName();
		fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();

		try {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		/**
		 * 3.设置输出的格式
		 */
		// 1.清空response
		response.reset();
		// 2.设置response的Header
		// 使用setHeader()方法弹出"是否要保存"的对话框,打引号的部分都是固定的值,不要改变
		String disposition = "attachment; filename=\"" + fileName + "\"";
		response.setHeader("Content-Disposition", disposition);
		if ("Post".equalsIgnoreCase(request.getMethod())) {
			response.setHeader("content_Length", String.valueOf(file.length()));
		}

		/**
		 * 4.循环取出流中的数据.设置缓冲区为1024个字节，即1KB
		 */
		byte[] b = new byte[1024];

		try {
			if (isLinux()) {
				basePath = "";// 文件保存目录相对路径(linux之下的某个文件件的名称)
			}
			String fileTitle = filePath.substring(filePath.indexOf("\\") + 1);
			fileUrl = basePath + "/" + fileTitle;
			/**
			 * 5.读到流中
			 */
			int len = 0;
			is = new FileInputStream(fileUrl);
			/**
			 * 读取数据。返回值为读入缓冲区的字节总数,如果到达文件末尾，则返回-1
			 */
			while ((len = is.read(b)) > 0) {
				// 将指定 byte数组中从下标 0 开始的 len个字节写入此文件输出流,(即读了多少就写入多少)
				response.getOutputStream().write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				// 用户在下载窗口点击取消，跳到此处
			}
		}
	}

	/**
	 * 
	 * @Title：download
	 * @Description：文件下载
	 * @param path
	 * @param response
	 * @return
	 * @return：HttpServletResponse 返回类型
	 */
	public static HttpServletResponse download(String path,
			HttpServletResponse response) {
		try {
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 取得文件的后缀名。 String ext =
			filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			// byte[] buffer = new byte[1024];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return response;
	}
}
