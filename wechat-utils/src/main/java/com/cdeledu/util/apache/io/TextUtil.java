package com.cdeledu.util.apache.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;

import com.cdeledu.common.exception.ExceptionHelper;

/**
 * @类描述: 文本操作相关的操作
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月11日 下午8:08:49
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class TextUtil {
	/*--------------------------私有属性 start -------------------------------*/
	/*--------------------------私有属性 end   -------------------------------*/
	/*--------------------------私有方法 start -------------------------------*/
	/*--------------------------私有方法 end   -------------------------------*/
	/*--------------------------公有方法 start -------------------------------*/
	public static List<T> importFileByText(String realPath) {
		List<Object> params = new ArrayList<Object>();
		InputStreamReader read = null;
		if (StringUtils.isBlank(realPath)) {
			return null;
		}

		File fileInfo = new File(realPath);
		String line;

		try {
			read = new InputStreamReader(new FileInputStream(fileInfo), "GBK");
			BufferedReader reader = new BufferedReader(read);
			while ((line = reader.readLine()) != null) {
				params.add(line);
			}
			read.close();
		} catch (Exception e) {
			ExceptionHelper.catchHttpUtilException(e, null);
		} finally {
			IOUtils.closeQuietly(read);
		}

		return null;
	}
	/**
	 * @方法:先读取原有文件内容，然后进行写入操作
	 * @创建人:独泪了无痕
	 * @param filePath
	 * @param filein
	 * @return
	 * @throws Exception
	 */
	public static boolean writeTxtFile(String filePath, String filein){
		boolean flag = false;

		String temp = "";

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		FileOutputStream fos = null;
		PrintWriter pw = null;

		try {
			// 文件路径
			File file = new File(filePath);
			// 将文件读入输入流
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file));// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);

			StringBuffer buf = new StringBuffer();

			// 保存该文件原有的内容

			while ((temp = bufferedReader.readLine()) != null) {
				buf = buf.append(temp);
				// System.getProperty("line.separator") 行与行之间的分隔符 相当于“\n”
				buf.append(System.getProperty("line.separator"));
			}

			buf.append(filein);

			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);

			pw.write(buf.toString().toCharArray());
			pw.flush();
			flag = true;

			bufferedReader.close();
			fos.close();
			pw.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(pw);
			IOUtils.closeQuietly(fos);
			IOUtils.closeQuietly(br);
			IOUtils.closeQuietly(isr);
			IOUtils.closeQuietly(fis);
		}
		return flag;
	}
	/*--------------------------公有方法 end   -------------------------------*/
}
