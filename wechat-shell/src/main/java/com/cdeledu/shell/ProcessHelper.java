package com.cdeledu.shell;

/**
 * @类描述:
 *       <ul>
 *       <li>ProcessBuilder,进程生成器</li>
 *       <li>ProcessBuilder类是J2SE
 *       1.5在java.lang中新添加的一个新类，此类用于创建操作系统进程，它提供一种启动和管理进程（也就是应用程序）的方法</li>
 *       <li>每个 ProcessBuilder 实例管理一个进程属性集。它的start() 方法利用这些属性创建一个新的 Process
 *       实例。start() 方法可以从同一实例重复调用，以利用相同的或相关的属性创建新的子进程</li>
 *       </ul>
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年12月25日 下午7:58:34
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface ProcessHelper {
	/**
	 * @方法描述: 销毁（杀掉）进程
	 */
	void kill();
}
