/**
 * 
 */
package com.cdeledu.util;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 执行定时任务
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年1月28日 下午2:23:07
 * @版本: V1.0
 * @since: JDK 1.7
*/
public class ScheduleRunnable implements Runnable {
	/** ----------------------------------------------------- Fields start */

	/** ----------------------------------------------------- Fields end */
	private Object target;
	private Method method;
	private String params;
	
	public ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
		this.target = SpringContextUtil.getBean(beanName);
		this.params = params;
		
		if(StringUtils.isNotBlank(params)){
			this.method = target.getClass().getDeclaredMethod(methodName, String.class);
		}else{
			this.method = target.getClass().getDeclaredMethod(methodName);
		}
	}
	@Override
	public void run() {
		try {
			ReflectionUtils.makeAccessible(method);
			if(StringUtils.isNotBlank(params)){
				method.invoke(target, params);
			}else{
				method.invoke(target);
			}
		}catch (Exception e) {
			throw new RuntimeException("执行定时任务失败", e);
		}
	}
	
}
