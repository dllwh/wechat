package com.cdeledu.util.openplatform.alibaba.constant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.UUID;

public final class ParameterHelper {
	/** ----------------------------------------------------- Fields start */
	private final static String TIME_ZONE = "GMT";
	private final static String FORMAT_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	private final static String FORMAT_RFC2616 = "EEE, dd MMM yyyy HH:mm:ss zzz";
	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法:唯一随机数
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static String getUniqueNonce() {
		return UUID.randomUUID().toString();
	}

	/**
	 * @方法:日期格式按照ISO8601标准表示，并需要使用UTC时间。格式为：YYYY-MM-DDThh:mm:ssZ
	 * @创建人:独泪了无痕
	 * @param date
	 * @return
	 */
	public static String getISO8601Time(Date date) {
		Date nowDate = date;
		if (null == date) {
			nowDate = new Date();
		}
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_ISO8601);
		df.setTimeZone(new SimpleTimeZone(0, TIME_ZONE));

		return df.format(nowDate);
	}

	/**
	 * @方法:日期格式按照RFC2104标准表示，并需要使用UTC时间。
	 * @创建人:独泪了无痕
	 * @param date
	 * @return
	 */
	public static String getRFC2616Date(Date date) {
		Date nowDate = date;
		if (null == date) {
			nowDate = new Date();
		}
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_RFC2616, Locale.ENGLISH);
		df.setTimeZone(new SimpleTimeZone(0, TIME_ZONE));
		return df.format(nowDate);
	}
}
