package com.cdeledu.util.apache.poi.ppt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

/**
 * @类描述: 提供读写Microsoft PowerPoint格式档案的功能
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年5月6日 上午11:10:13
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class PptUtil {

	/**
	 * @方法描述: PPT转图片(处理PPT文件以 .pptx 后缀结尾的文档)
	 * @param sourceFile
	 * @param path
	 *            存放路径
	 * @param picName
	 *            图片前缀名称
	 * @param picType
	 *            转成图片的类型
	 */
	public static boolean toImage2007(String sourceFile, String savePath, String picName,
			String picType) throws Exception {
		boolean convertReturnResult = false;
		File file = new File(sourceFile);
		if (!file.exists() || !file.canExecute() || !file.isFile()) {
			return convertReturnResult;
		}

		File saveFile = new File(savePath);
		if (!saveFile.exists()) {
			saveFile.mkdirs();
		}

		if (StringUtils.isBlank(picType)) {
			picType = "jpg";
		}
		int _index = picType.lastIndexOf(".");
		if (_index >= 0) {
			picType = picType.substring(_index + 1);
		}

		FileInputStream input = null;
		FileOutputStream out = null;
		XMLSlideShow xmlSlideShow = null;

		try {
			input = new FileInputStream(sourceFile);
		} catch (Exception e) {
			e.printStackTrace();
			return convertReturnResult;
		}

		try {
			xmlSlideShow = new XMLSlideShow(input);
		} catch (Exception e) {
			e.printStackTrace();
			return convertReturnResult;
		}
		IOUtils.closeQuietly(input);

		// 获取PPT 文件中所有的PPT页面，并转换成播放片
		XSLFSlide[] xslfSlides = xmlSlideShow.getSlides();
		// 获取大小
		Dimension pageSize = xmlSlideShow.getPageSize();

		for (int i = 0; i < xslfSlides.length; i++) {
			// 设置字体为宋体，解决中文乱码问题
			setFont(xslfSlides[i]);

			// 根据幻灯片大小生成图片
			BufferedImage img = new BufferedImage(pageSize.width, pageSize.height,
					BufferedImage.TYPE_INT_RGB);

			Graphics2D graphics = getGrapics(pageSize, img);
			// 最核心的代码
			xslfSlides[i].draw(graphics);
			// 图片将要存放的路径
			String filename = savePath + "/" + picName + (i + 1) + "." + picType;
			// 这里设置图片的存放路径和图片的格式(jpeg,png,bmp等等),注意生成文件路径

			try {
				out = new FileOutputStream(filename);
			} catch (Exception e) {
				e.printStackTrace();
				return convertReturnResult;
			}

			try {
				// 写入到图片中去
				ImageIO.write(img, picType, out);
			} catch (Exception e) {
				e.printStackTrace();
				return convertReturnResult;
			}
			IOUtils.closeQuietly(out);
		}
		return true;
	}

	/**
	 * @方法描述: PPT转图片(处理PPT文件以 .ppt 后缀结尾的文档)
	 * @param sourceFile
	 * @param path
	 *            存放路径
	 * @param picName
	 *            图片前缀名称
	 * @param picType
	 *            转成图片的类型
	 * @return 转换成功，返回true；转换失败，返回false
	 */
	public static boolean toImage2003(String sourceFile, String savePath, String picName,
			String picType) {
		boolean convertReturnResult = false;
		File file = new File(sourceFile);
		if (!file.exists() || !file.canExecute() || !file.isFile()) {
			return convertReturnResult;
		}

		File saveFile = new File(savePath);
		if (!saveFile.exists()) {
			saveFile.mkdirs();
		}
		
		if (StringUtils.isBlank(picType)) {
			picType = "jpg";
		}
		int _index = picType.lastIndexOf(".");
		if (_index >= 0) {
			picType = picType.substring(_index + 1);
		}
		
		
		FileInputStream input = null;
		FileOutputStream output = null;
		SlideShow slideShow;
		try {
			input = new FileInputStream(sourceFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return convertReturnResult;
		}

		try {
			slideShow = new SlideShow(input);
		} catch (IOException e) {
			e.printStackTrace();
			return convertReturnResult;
		}

		IOUtils.closeQuietly(input);

		Slide[] slide = slideShow.getSlides();
		Dimension pageSize = slideShow.getPageSize();

		for (int i = 0; i < slide.length; i++) {
			setFont(slide[i]);

			// 根据幻灯片大小生成图片
			BufferedImage img = new BufferedImage(pageSize.width, pageSize.height,
					BufferedImage.TYPE_INT_RGB);

			Graphics2D graphics = getGrapics(pageSize, img);
			slide[i].draw(graphics);
			// 图片的保存位置
			try {
				output = new FileOutputStream(savePath + "/" + (i + 1) + "." + picType);
			} catch (Exception e) {
				e.printStackTrace();
				return convertReturnResult;
			}
			// 这里设置图片的存放路径和图片的格式(jpeg,png,bmp等等),注意生成文件路径
			try {
				ImageIO.write(img, picType, output);
			} catch (IOException e) {
				e.printStackTrace();
				return convertReturnResult;
			}
			IOUtils.closeQuietly(output);
		}

		IOUtils.closeQuietly(input);
		return true;
	}

	/**
	 * @方法描述: 设置PPTX字体
	 * @param slide
	 */
	private static void setFont(XSLFSlide slide) {
		for (XSLFShape shape : slide.getShapes()) {
			if (shape instanceof XSLFTextShape) {
				XSLFTextShape txtshape = (XSLFTextShape) shape;
				for (XSLFTextParagraph paragraph : txtshape.getTextParagraphs()) {
					List<XSLFTextRun> truns = paragraph.getTextRuns();
					for (XSLFTextRun textRun : truns) {
						System.out.println("原有的字体名字: " + textRun.getFontFamily());
						textRun.setFontFamily("宋体");
					}
				}
			}
		}
	}

	/**
	 * @方法描述: // 重新设置 字体索引 和 字体名称 是为了防止生成的图片乱码问题
	 * @param slide
	 */
	private static void setFont(Slide slide) {
		TextRun[] truns = slide.getTextRuns();
		for (int k = 0; k < truns.length; k++) {
			RichTextRun[] rtruns = truns[k].getRichTextRuns();

			for (RichTextRun textRun : rtruns) {
				System.out.println("原有的字体名字: " + textRun.getFontName());
				textRun.setFontName("宋体");
			}
		}
	}

	/**
	 * @方法描述: 创建bufferedImage对象，图像的尺寸为原来PPT的每页的尺寸
	 * @param pageSize
	 * @param img
	 * @return
	 */
	private static Graphics2D getGrapics(Dimension pageSize, BufferedImage img) {
		Graphics2D graphics = img.createGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		graphics.setPaint(Color.white);
		graphics.fill(new Rectangle2D.Float(0, 0, pageSize.width, pageSize.height));
		return graphics;
	}

	public static void main(String[] args) throws Exception {
		String sourceFile = "C://Users/dell/Desktop/UML-迭代子模式(黄亚丽).pptx";
		String path = "C://Users/dell/Desktop/ppt";
		String picName = "ppt 转图片";
		String picType = "jpg";
		toImage2007(sourceFile, path, picName, picType);
	}
}
