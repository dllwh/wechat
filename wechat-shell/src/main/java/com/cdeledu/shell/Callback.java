package com.cdeledu.shell;

/**
 * @类描述: 输出返回值
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年12月26日 上午8:00:54
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface Callback {
	void accept(String line, ProcessHelper helper);
}
