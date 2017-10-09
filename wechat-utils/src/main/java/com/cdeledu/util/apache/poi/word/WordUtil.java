package com.cdeledu.util.apache.poi.word;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

/**
 * @类描述: 提供读写Microsoft Word格式档案的功能
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年5月6日 上午11:11:57
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class WordUtil {

	/**
	 * @方法描述: 将word2003转换为html文件
	 * @param sourceFile
	 *            源word文件路径
	 * @param parentPath
	 *            目标文件路径
	 * @param saveFileName
	 *            目标文件名称
	 * @param charsetName
	 *            编码
	 * @return
	 * @throws Exception
	 */
	public static boolean convertHtmlByWord2003(String sourceFile, String parentPath,
			String saveFileName, String encode) throws Exception {

		if (StringUtils.isBlank(encode)) {
			encode = "UTF-8";
		}

		File imgPath = new File(parentPath);
		if (!imgPath.exists()) {// 图片目录不存在则创建
			imgPath.mkdirs();
		}

		// 创建一个文档
		HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(sourceFile));
		// 对普通文本的操作
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
				DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());

		// 对图片的操作: 图片在html文件上的相对路径
		wordToHtmlConverter.setPicturesManager(new PicturesManager() {
			public String savePicture(byte[] content, PictureType pictureType, String suggestedName,
					float widthInches, float heightInches) {
				return suggestedName;
			}
		});
		// 保存图片
		List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
		if (pics != null) {
			for (int i = 0; i < pics.size(); i++) {
				Picture pic = (Picture) pics.get(i);
				try {
					pic.writeImageContent(
							new FileOutputStream(parentPath + pic.suggestFullFileName()));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		// 解析word文档
		wordToHtmlConverter.processDocument(wordDocument);
		Document htmlDocument = wordToHtmlConverter.getDocument();

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		DOMSource domSource = new DOMSource(htmlDocument);
		StreamResult streamResult = new StreamResult(output);
		// 下面都是转换
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, encode);
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD, "html");
		serializer.transform(domSource, streamResult);
		// 调用writeFile类
		writeFile(new String(output.toByteArray()), parentPath + File.separator + saveFileName,
				encode);
		IOUtils.closeQuietly(output);
		return false;
	}

	/**
	 * @方法描述: 2007版本word转换成html
	 * @param sourceFile
	 * @param savePath
	 * @param saveFileName
	 * @param encode
	 */
	public static void convertHtmlByWord2007(String sourceFile, String parentPath,
			String saveFileName, String encode) throws Exception {
		OutputStream output = null;
		File imageFolderFile = new File(parentPath);
		if (!imageFolderFile.exists()) {// 图片目录不存在则创建
			imageFolderFile.mkdirs();
		}
		try {

			// 1) 加载word文档生成 XWPFDocument对象
			XWPFDocument wordDocument = new XWPFDocument(new FileInputStream(sourceFile));

			// 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
			XHTMLOptions options = XHTMLOptions.create();
			options.setExtractor(new FileImageExtractor(imageFolderFile));
			options.URIResolver(new FileURIResolver(imageFolderFile));
			options.setIgnoreStylesIfUnused(false);
			options.setFragment(true);

			// 3) 将 XWPFDocument转换成XHTML
			output = new FileOutputStream(parentPath + File.separator + saveFileName);
			XHTMLConverter.getInstance().convert(wordDocument, output, options);
		} finally {
			imageFolderFile.delete();
			IOUtils.closeQuietly(output);
		}

	}

	/**
	 * @方法描述: 写文件（成功返回true，失败则返回false）
	 * @param content
	 *            要写入的内容
	 * @param path
	 *            文件
	 * @param charsetName
	 *            编码
	 */
	private static boolean writeFile(String content, String filePath, String encode) {
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		try {
			File saveFile = new File(filePath);
			if (!saveFile.exists()) { // 创建文件
				saveFile.createNewFile();
			}
			fos = new FileOutputStream(saveFile);
			bw = new BufferedWriter(new OutputStreamWriter(fos, encode));
			bw.write(content);
			return true;
		} catch (Exception ioe) {
			ioe.printStackTrace();
			return false;
		} finally {
			IOUtils.closeQuietly(bw);
			IOUtils.closeQuietly(fos);
		}
	}
}
