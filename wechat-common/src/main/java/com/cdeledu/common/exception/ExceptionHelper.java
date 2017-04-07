package com.cdeledu.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.SocketTimeoutException;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;

/**
 * @类描述: 异常信息参数配置加载类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年1月20日 下午4:52:42
 * @版本: V2.0
 * @since: JDK 1.7
 */
public class ExceptionHelper extends ExceptionUtils {
	/*-------------------------- 私有属性 start -------------------------------*/
	// private static Logger log =
	// LoggerFactory.getLogger(ExceptionHelper.class);
	private static String MARKED_WORDS_ONE = "调用%s.%s()方法出现异常,原因是:%s";
	private static String MARKED_WORDS_TWO = "调用%s方法出现异常,原因是:%s";
	// private static Map<String, Object> parameterCacheDto = null;
	// private static ExceptionVO exceptionVO = null;

	// static {
	// try {
	// if (log.isDebugEnabled()) {
	// log.debug("解析XML异常参数配置文件...");
	// }
	// parameterCacheDto = new HashMap<String, Object>();
	// SAXReader reader = new SAXReader();
	// InputStream is =
	// ExceptionHelper.class.getResourceAsStream("exceptionInfo.xml");
	// Document document = reader.read(is);
	// Element elRoot = document.getRootElement();
	// Iterator<?> elIt = elRoot.elementIterator();
	// String id = "";
	// while (elIt.hasNext()) {
	// exceptionVO = new ExceptionVO();
	// Element el = (Element) elIt.next();
	// id = el.attribute("id").getText();
	// exceptionVO.setId(el.attribute("id").getText());
	// exceptionVO.setInfo(el.attribute("info").getText());
	// exceptionVO.setSuggest(el.attribute("suggest").getText());
	// parameterCacheDto.put(id, exceptionVO);
	// }
	// } catch (Exception exp) {
	// throw new RuntimeExceptionHelper("解析XML异常参数配置文件出错.", exp);
	// }
	// }

	/**
	 * 
	 * @Title: getExceptionHint
	 * @Description: 异常提示语
	 * @param className
	 *            出现异常所在类
	 * @param methodName
	 *            出现异常所在方法
	 * @param tips
	 *            出现异常的提示
	 */
	public static void getExceptionHint(String className, String methodName, String tips) {
		throw new IllegalArgumentException(
				String.format(MARKED_WORDS_ONE, className, methodName, tips));
	}

	/**
	 * 
	 * @Title: getExceptionHint
	 * @Description: 异常提示语
	 * @param className
	 *            出现异常所在类
	 * @param tips
	 *            出现异常的提示
	 */
	public static void getExceptionHint(String className, String tips) {
		throw new IllegalArgumentException(String.format(MARKED_WORDS_TWO, className, tips));
	}

	/**
	 * @方法描述: 抓取异常
	 *        <ul>
	 *        <li>instanceof 是一个简单的二元操作符,是用来判断一个对象是否是一个类的实例,其操作类似于>=、==</li>
	 *        </ul>
	 * @创建者: 独泪了无痕
	 * @修改时间: 2016年2月25日 上午10:41:02
	 * @param e
	 * @param url
	 */
	public static void catchHttpUtilException(Exception e, String url) {

		if (e instanceof NullPointerException) {
			throw new IllegalArgumentException("请求通信[" + url + "]时空指针异常,堆栈轨迹如下", e);
		}

		/**
		 * 解析异常 <br/>
		 * 该异常是否是 ParseException 的实例
		 */
		if (e instanceof ParseException) {
			throw new RuntimeException("请求通信[" + url + "]时解析异常,堆栈轨迹如下", e);
		}
		/** 读取超时 */
		if (e instanceof SocketTimeoutException) {
			throw new RuntimeException("请求通信[" + url + "]时读取超时,堆栈轨迹如下", e);
		}
		/** 连接超时 */
		if (e instanceof ConnectTimeoutException) {
			throw new RuntimeException("请求通信[" + url + "]时连接超时,堆栈轨迹如下", e);
		}
		/** 网络异常 */
		if (e instanceof IOException) {
			// 该异常通常是网络原因引起的,如HTTP服务器未启动等
			throw new RuntimeException("请求通信[" + url + "]时网络异常,堆栈轨迹如下", e);
		}

		/** 网络协议 */
		if (e instanceof ClientProtocolException) {
			throw new RuntimeException("请求通信[" + url + "]时网络协议异常,堆栈轨迹如下", e);
		}
		if (e instanceof Exception) {
			throw new RuntimeException("请求通信[" + url + "]时偶遇异常,堆栈轨迹如下", e);
		}
	}

	/**
	 * @方法:返回错误信息字符串
	 * @创建人:独泪了无痕
	 * @param exception
	 * @return
	 */
	public static String getExceptionMessage(Exception exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		return sw.toString();
	}

	/**
	 * @方法描述:将CheckedException转换为UncheckedException
	 */
	public static RuntimeException unchecked(Exception e) {
		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		} else {
			return new RuntimeException(e);
		}
	}

	/**
	 * @方法描述: 获取异常配置参数
	 * @param errID
	 * @return
	 */
	/**
	 * public ExceptionVO getExceptionInfo(String errID) { ExceptionVO vo =
	 * (ExceptionVO) parameterCacheDto.get(errID); return vo; }
	 */
}
