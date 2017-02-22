package com.cdeledu.util.apache.lang;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @类描述:时间工具类
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月14日 下午11:16:10
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class TimerUtil {
	public static String getDisplayTime(Date date) {
		long millisecond = System.currentTimeMillis() - date.getTime();

		// 现在时间与参数时间之间的秒数，简称"差秒"
		long second = millisecond / 1000;

		// 5分钟内都为刚刚
		if (second < 5 * 60) {
			return "刚刚";
		}

		// 1小时内为"x分钟前"
		if (second < 1 * 3600) {
			long minute = second / 60;
			return minute + "分钟前";
		}

		// 如果"差秒"在1小时以上，3小时以内
		if (second <= 3 * 3600) {
			long hour = (second / 60) / 60;
			return hour + "小时前";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtilHelper.NORM_TIME_PATTERN_THREE);
		// 今天最开始的秒数
		long todayFirstSecond = DateUtilHelper.getSecoendOfBegin(Calendar.DATE) / 1000;

		// 如果参数秒数大于今天最开始的秒数，则是今天的时间
		long dateSecond = date.getTime() / 1000;
		if (dateSecond >= todayFirstSecond) {
			return "今天" + sdf.format(date);
		}

		// 如果参数秒数小于今天最开始的秒数，则是今天以前的时间，如昨天
		long second1 = todayFirstSecond - dateSecond;
		if (second1 <= 86400) {
			return "昨天" + sdf.format(date);
		}

		if (second1 <= 86400 * 2) {
			return "前天" + sdf.format(date);
		}

		if (second1 <= 86400 * 3) {
			long day = ((second / 60) / 60) / 24;
			return day + "天前";
		}
		long yearFirstSecond = DateUtilHelper.getSecoendOfBegin(Calendar.YEAR) / 1000;
		long second2 = yearFirstSecond - dateSecond;
		if (second2 < 0) {
			sdf.applyPattern(DateUtilHelper.NORM_TIME_PATTERN_TWO);
			return sdf.format(date);
		}

		sdf.applyPattern(DateUtilHelper.NORM_TIME_PATTERN_ONE);
		return sdf.format(date);
	}
}
