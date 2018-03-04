package com.cdeledu.util.apache.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdeledu.common.model.ImageModel;

/**
 * 
 * @ClassName: ImageUtilHelper
 * @Description: 图片处理工具类
 *               <ul>
 *               <li>缩放图像、切割图像、图像类型转换、彩色转黑白、文字水印、图片水印</li>
 *               <li>http://blog.csdn.net/zhangzhikaixinya/article/details/
 *               8459400</li>
 *               <li>图片验证码</li>
 *               <li>http://blog.csdn.net/ruixue0117/article/details/22829557
 *               </li>
 *               <li>http://blog.csdn.net/dy511/article/details/6957400</li>
 *               <li>http://www.iteye.com/topic/573456</li>
 *               <li></li>
 *               </ul>
 * @author: 独泪了无痕
 * @date: 2015年7月17日 下午12:31:46
 * @version: V1.0
 */
public class ImageUtilHelper {
	/** -------------------------- 私有属性 begin ------------------------------- */
	private static Logger logger = LoggerFactory.getLogger(ImageUtilHelper.class);

	/** -------------------------- 私有属性 end ------------------------------- */
	/** -------------------------- 私有方法 begin ------------------------------- */

	/** -------------------------- 私有方法 end ------------------------------- */

	/** -------------------------- 公有方法 begin ------------------------------- */
	/** 判断文件是否是图片 */
	public static boolean isImage(File file) {
		boolean flag = false;
		ImageInputStream is = null;
		Iterator<ImageReader> iter = null;
		try {
			is = ImageIO.createImageInputStream(file);
			iter = ImageIO.getImageReaders(is);
			if (!iter.hasNext()) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
		} finally {
			IOUtils.closeQuietly(is);
		}
		return flag;
	}

	/** 判断文件是否是图片 */
	public static boolean isImage(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			return false;
		}
		try {
			BufferedImage bi = ImageIO.read(file);
			if (bi == null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @方法描述: 获取图片信息
	 *        <ul>
	 *        <li>getHeight: 返回 BufferedImage 的高度</li>
	 *        <li>getWidth: 返回 BufferedImage 的宽度</li>
	 *        <li>getType:返回图像类型</li>
	 *        <li></li>
	 *        <li></li>
	 *        </ul>
	 * @param path
	 * @return
	 */
	public static ImageModel getImageByImageReader(String path) {
		ImageModel model = null;
		BufferedImage sourceImg = null;
		model = new ImageModel();
		URL url = null;
		File picture = new File(path);
		if (path.startsWith("http")) {
			try {
				url = new URL(path);
				URLConnection uc = url.openConnection();
				sourceImg = ImageIO.read(uc.getInputStream());
			} catch (IOException ioExp) {
				logger.error("ImageUtils.getImageByImageReader", ioExp);
			}
			String file = url.getFile();
			model.setName(file.replace("/", ""));
			if (file.lastIndexOf(".") >= 0) {
				sourceImg.getType();
				model.setExt(file.substring(file.lastIndexOf(".") + 1));
			}
			model.setWidth(sourceImg.getWidth());
			model.setHeight(sourceImg.getHeight());
		} else {
			try {
				sourceImg = ImageIO.read(new FileInputStream(picture));
			} catch (IOException ioExp) {
				logger.error("使用[BufferedImage]获取图片出现异常:", ioExp);
			}

			model.setName(picture.getName());
			if (path.lastIndexOf(".") >= 0) {
				model.setExt(path.substring(path.lastIndexOf(".") + 1));
			}
			model.setSize((picture.length() / 1024.0));
			model.setWidth(sourceImg.getWidth());
			model.setHeight(sourceImg.getHeight());
		}
		return model;
	}

	public static void main(String[] args) {
		String path = "http://img05.tooopen.com/images/20140604/sy_62331342149.jpg";
		System.out.println(getImageByImageReader(path));
	}

	/** -------------------------- 公有方法 end ------------------------------- */
}
