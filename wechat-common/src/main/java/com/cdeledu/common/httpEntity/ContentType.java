package com.cdeledu.common.httpEntity;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Locale;

import com.cdeledu.common.constant.ConstantHelper;

/**
 * @描述: 消息体类型(内容类型) <br>
 *      从客户端到服务器的请求 <br>
 *      从服务器到客户端的响应
 *      <p>
 *      type的形式<br>
 *      Text:用于标准化地表示的文本信息，文本消息可以是多种字符集和或者多种格式的<br>
 *      Multipart:用于连接消息体的多个部分构成一个消息,这些部分可以是不同类型的数据<br>
 *      Application:用于传输应用程序数据或者二进制数据<br>
 *      Message:用于包装一个E-mail消息<br>
 *      Image:用于传输静态图片数据<br>
 *      Audio：用于传输音频或者音声数据<br>
 *      Video:用于传输动态影像数据,可以是与音频编辑在一起的视频数据格式<br>
 *      <p>
 * @author: 独泪了无痕
 * @date: 2015年11月1日 下午2:56:56
 * @version: V1.0
 * @since: JDK 1.7
 * @see <a href=
 *      "http://baike.baidu.com/link?url=8rCgwVXmh1lHVXJk0_ga-IsM-Zyw-7dagJ1fakR4dQmQV5ucg-wKamv_AQWaPzKQuH6Cg0N7nx5J9AZUmX6ui_#1">
 *      百度百科--ContentType</a>
 */
public final class ContentType implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final Charset UTF_8 = ConstantHelper.UTF_8;
	// /////////////////////
	// 常见的媒体格式类型
	// /////////////////////

	// HTML格式
	public static final ContentType TEXT_HTML = create("text/html", UTF_8);
	// 纯文本格式
	public static final ContentType TEXT_PLAIN = create("text/plain", UTF_8);
	// XML格式
	public static final ContentType TEXT_XML = create("text/xml", UTF_8);
	// gif图片格式
	public static final ContentType IMAGE_GIF = create("image/gif", UTF_8);
	// jpg图片格式
	public static final ContentType IMAGE_JPG = create("image/jpg", UTF_8);

	// /////////////////////
	// 以application开头的媒体格式类型
	// /////////////////////
	// Atom XML聚合格式
	public static final ContentType APPLICATION_ATOM_XML = create("application/atom+xml", UTF_8);
	// 表单默认的提交数据的格式
	public static final ContentType APPLICATION_FORM_URLENCODED = create("application/x-www-form-urlencoded", UTF_8);
	// JSON数据格式
	public static final ContentType APPLICATION_JSON = create("application/json", UTF_8);
	// pdf格式
	public static final ContentType APPLICATION_PDF = create("application/pdf", UTF_8);
	// 二进制流数据（如常见的文件下载）
	public static final ContentType APPLICATION_OCTET_STREAM = create("application/octet-stream");

	public static final ContentType APPLICATION_SVG_XML = create("application/svg+xml", UTF_8);
	// XHTML格式
	public static final ContentType APPLICATION_XHTML_XML = create("application/xhtml+xml", UTF_8);
	// XML数据格式
	public static final ContentType APPLICATION_XML = create("application/xml", UTF_8);
	// 需要在表单中进行文件上传时，就需要使用该格式
	public static final ContentType MULTIPART_FORM_DATA = create("multipart/form-data", UTF_8);
	public static final ContentType AUDIO_MP3 = create("audio/mp3", UTF_8);
	public static final ContentType VIDEO_MPEG4 = create("video/mpeg4", UTF_8);

	// 即为类型(默认的ContentType为 text/html 也就是网页格式.)
	private final String mimeType;
	// 决定网页的编码，一般为gb2312、UTF-8等
	private final Charset charset;

	public ContentType(String mimeType, Charset charset) {
		this.mimeType = mimeType;
		this.charset = charset;
	}

	public String getMimeType() {
		return mimeType;
	}

	public Charset getCharset() {
		return charset;
	}

	/** 验证：过滤 */
	private static boolean valid(String content) {
		for (int index = 0; index < content.length(); index++) {
			char ch = content.charAt(index);
			if (ch == '"' || ch == ',' || ch == ';')
				return false;
		}
		return true;
	}

	// 创建内容类型
	public static ContentType create(final String mimeType, final Charset charset) {
		if (mimeType == null) {
			throw new IllegalArgumentException("MIME类型 may not be null");
		}
		String type = mimeType.trim().toLowerCase(Locale.US);
		if (type.length() == 0) {
			throw new IllegalArgumentException("MIME类型 may not be empty");
		}
		if (!valid(type)) {
			throw new IllegalArgumentException("MIME类型  may not contain reserved characters");
		}
		return new ContentType(mimeType, charset);
	}

	public static ContentType create(final String mimeType) {
		return new ContentType(mimeType, (Charset) null);
	}

	public static ContentType create(final String mimeType, final String charset) throws UnsupportedCharsetException {
		return create(mimeType, (charset != null && charset.length() > 0) ? Charset.forName(charset) : null);
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append(this.mimeType);
		if (this.charset != null) {
			buf.append("; charset=");
			buf.append(this.charset.name());
		}
		return buf.toString();
	}

}
