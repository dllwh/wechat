package com.cdeledu.shell;

/**
 * @类描述:
 * 
 *       <pre>
 *       执行自定义的一个命令
 *       </pre>
 * 
 *       <ul>
 *       <li>destroy()---杀掉子进程</li>
 *       <li>exitValue()---返回子进程的出口值。根据惯例，值0表示正常终止</li>
 *       <li>getErrorStream()---获取子进程的错误流</li>
 *       <li>getInputStream()---获取子进程的输入流</li>
 *       <li>getOutputStream()---获取子进程的输出流</li>
 *       <li>waitFor()---导致当前线程等待，如有必要，一直要等到由该 Process 对象表示的进程已经终止。
 *       如果已终止该子进程，此方法立即返回。如果没有终止该子进程，调用的线程将被阻塞，直到退出子进程。</li>
 *       </ul>
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年12月25日 下午7:02:23
 * @版本: V1.0
 * @since: JDK 1.7
 */
public interface ShellBuilder {
	Shell buildTextShell(String text) throws Exception;
}
