package com.cdeledu.core.security;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: token的验证器
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年1月6日 下午4:46:04
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface TokenValidator {
	boolean validate(String token);
}
