package com.cdeledu.util;

import org.apache.commons.lang3.Validate;
import org.springframework.context.ApplicationContext;

import com.cdeledu.common.constants.GlobalConstants;

public class ServiceHelper {
	/** ----------------------------------------------------- Fields start */
	private static ApplicationContext applicationContext = GlobalConstants.WEB_APP_CONTEXT;
	/** ----------------------------------------------------- Fields end */
	
	
	public static Object getService(String serviceName) {
		assertContextInjected();
		return applicationContext.getBean(serviceName);
	}
	
	/**
	 * @方法描述: 检查ApplicationContext不为空
	 */
	private static void assertContextInjected() {
		Validate.validState(applicationContext != null,
				"applicaitonContext属性未注入, 请在applicationContext.xml中定义ServiceHelper.");
	}
}
