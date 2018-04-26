package com.cdeledu.util.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

/**
 * @类描述: 定义线程池，用作缓冲队列。
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月27日 下午11:32:18
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class ThreadUtilHelper {
	/** ----------------------------------------------------- Fields start */
	private static final Logger logger = Logger.getLogger(ThreadUtilHelper.class);
	/** 定义线程池 */
	private static ExecutorService executor = Executors.newCachedThreadPool();
	/** 记录线程号，方便查看日志 */
	private static int num = 0;

	/** ----------------------------------------------------- Fields end */

	/**
	 * 清空缓存,防止因缓存太小而使得process.waitFor();阻塞。
	 * 
	 * @author sunyunhui 2012-12-18
	 * @param process
	 */
	private static String reaseResourse(final Process process) throws Exception {
		final StringBuffer result = new StringBuffer();
		final InputStream is1 = process.getInputStream();
		final InputStream is2 = process.getErrorStream();

		/**
		 * 对InputStream进行处理
		 */
		new Thread() {
			public void run() {
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(is1, "UTF-8"));
					String lineB = null;

					while ((lineB = br.readLine()) != null) {
						if (lineB != null) {
							result.append(lineB);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
		/**
		 * 对ErrorStream进行处理
		 */
		new Thread() {
			public void run() {
				try {
					BufferedReader br2 = new BufferedReader(new InputStreamReader(is2, "UTF-8"));
					String lineC = null;
					while ((lineC = br2.readLine()) != null) {

						if (lineC != null) {
							// System.out.println(lineC);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
		return result.toString();
	}

	/**
	 * 得到当前任务序列号
	 * 
	 * @author sunyunhui 2012-12-18
	 * @return
	 */
	private static synchronized int getTaskId() {
		int id;
		if (num < Integer.MAX_VALUE) {
			num++;
		} else {
			num = 0;
		}
		id = num;
		return id;
	}

	/**
	 * @方法描述: 执行任务命令
	 * @param cmd
	 * @param fileName
	 * @throws Exception
	 */
	private static void execProcess(String cmd) throws IOException {
		int id = getTaskId();
		try {
			Process process = Runtime.getRuntime().exec(cmd); // 调用脚本，开始执行任务。
			reaseResourse(process); // 释放缓存
			process.waitFor(); // 任务进程等待
			process.destroy(); // 销毁任务进程
		} catch (Exception e) {
			logger.error(Thread.currentThread().getName() + "(" + id + ")" + cmd + " 当前任务出现疑问. "
					+ new Date()); // 打印日志：当前任务出现疑问
			e.printStackTrace();
		}

	}

	/**
	 * @方法描述:执行命令（简单执行）
	 * @param cmd
	 * @throws Exception
	 */
	public static void execCmd(String cmd) throws Exception {
		Runtime.getRuntime().exec(cmd);
	}

	/**
	 * @方法描述:执行命令并获取返回数据
	 * @param cmd
	 * @throws Exception
	 */
	public static String execAndGetResult(String cmd) throws Exception {
		Process process = Runtime.getRuntime().exec(cmd);
		return reaseResourse(process);
	}

	/**
	 * @方法描述: 若需要将任务加入缓冲队列中来减少服务器压力，则调用此方法
	 * @param cmd
	 */
	public static void execTaskInThreadPool(final String cmd) {
		executor.execute(new Runnable() {
			public void run() {
				try {
					execProcess(cmd);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 重启公共线程池
	 */
	public static void restart() {
		executor.shutdownNow();
		executor = Executors.newCachedThreadPool();
	}

	/**
	 * @方法:新建一个线程池
	 * @创建人:独泪了无痕
	 * @param threadSize
	 * @return
	 */
	public static ExecutorService newExecutor(int threadSize) {
		return Executors.newFixedThreadPool(threadSize);
	}

	/**
	 * @方法:获得一个新的线程池
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static ExecutorService newExecutor() {
		return Executors.newCachedThreadPool();
	}

	/**
	 * @方法:获得一个新的线程池，只有单个线程
	 * @创建人:独泪了无痕
	 * @return
	 */
	public static ExecutorService newSingleExecutor() {
		return Executors.newSingleThreadExecutor();
	}

	/**
	 * @方法: 挂起当前线程
	 * @创建人:独泪了无痕
	 * @param millis
	 *            挂起的毫秒数
	 * @return 被中断返回false，否则true
	 */
	public static boolean sleep(Long millis) {
		if (millis == null || millis <= 0) {
			return true;
		}

		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			return false;
		}
		return true;
	}
}
