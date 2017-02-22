package com.cdeledu.util.apache.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * @类描述: 利用zxing开源工具生成二维码QRCode与解析通用类
 * @创建者: 独泪了无痕
 * @创建时间: 2016年2月26日 下午5:44:46
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class QrcodeUtilHelper {
	/**
	 * @方法描述: 生成QRCode二维码
	 * @创建者: 独泪了无痕
	 * @创建时间: 2016年2月26日 下午5:57:15
	 * @param content
	 *            需要生成的内容
	 * @param imgPath
	 *            生成后保存的地址
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 */
	@SuppressWarnings("deprecation")
	public static void encode(String content, String imgPath, int width,int height) {
		if (StringUtils.isBlank(content) || StringUtils.isBlank(imgPath)) {
			return;
		}
		BitMatrix byteMatrix;
		try {
			 //消除乱码
			content = new String(content.getBytes("utf-8"), "iso-8859-1");
			// 生成矩阵
			byteMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE, width, height);
			File file = new File(imgPath);
			// 输出图像
			MatrixToImageWriter.writeToFile(byteMatrix, "png", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @方法描述: 解析二维码
	 * @创建者: 独泪了无痕
	 * @创建时间: 2016年2月26日 下午6:03:54
	 * @param imgPath
	 *            二维码路径
	 * @return 返回解析后的内容
	 */
	public static String decode(String imgPath) {
		if (StringUtils.isBlank(imgPath)) {
			System.out.println("尚未找到想要解析的图片");
			return "";
		}
		BufferedImage image;
		Result result;
		try {
			image = ImageIO.read(new File(imgPath));
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
			//解码设置编码方式为：GBK
			hints.put(DecodeHintType.CHARACTER_SET, "GBK");
			result = new MultiFormatReader().decode(bitmap, hints);
			// 对图像进行解码  
			String resultStr = result.getText();
			return resultStr;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (NotFoundException nfe) {
			nfe.printStackTrace();
		}
		return "";
	}
}
