package com.cdeledu.core.interceptors;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

public class DateConvertEditor extends PropertyEditorSupport {
	/** ----------------------------------------------------- Fields start */
	private SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
	/** ----------------------------------------------------- Fields end */
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			try {
				if (text.indexOf(":") == -1 && text.length() == 10) {
					setValue(this.dateFormat.parse(text));
				} else if (text.indexOf(":") > 0 && text.length() == 19) {
					setValue(this.datetimeFormat.parse(text));
				//update-begin----author:zhangdaihao----------------------date:20130312 ------- for:时间格式化-------
				} else if (text.indexOf(":") > 0 && text.length() == 21) {
					text = text.replace(".0", "");
					setValue(this.datetimeFormat.parse(text));
				//update-end----author:zhangdaihao----------------------date:20130312 ------- for:时间格式化-------
				} else {
					throw new IllegalArgumentException(
							"Could not parse date, date format is error ");
				}
			} catch (ParseException ex) {
				IllegalArgumentException iae = new IllegalArgumentException(
						"Could not parse date: " + ex.getMessage());
				iae.initCause(ex);
				throw iae;
			}
		} else {
			setValue(null);
		}
	}
}
