package com.cdeledu.util.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * 
 *       <pre>
 *       CyclicBarrier 是一种多线程并发控制实用工具。
 *       CyclicBarrier 可以理解为循环栅栏。栅栏就是一种障碍物，比如，通常在私人宅邸的周围就可以围上一圈的栅栏，阻止闲杂人等入内。
 *       在这里，就是用来阻止线程继续执行，要求线程在栅栏除等待。前面的 Cyclic 意为循环，也就是说这个计数器可以反复使用，
 *       比如，假如我们将计数器设置为10，那边凑齐第一批的10个线程后，计数器接着就要归零，然后接着在凑齐下一批的10个线程。
 *       </pre>
 * 
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年7月9日 上午11:44:37
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class CyclicBarrierRunnableHelper {
	/** ----------------------------------------------------- Fields start */
	/** ----------------------------------------------------- Fields end */

	public static void main(String[] args) {
		int count = 5000;

		CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
		ExecutorService executorService = Executors.newFixedThreadPool(count);
		for (int i = 0; i < count; i++) {
			executorService.execute(new CyclicBarrierRunnableHelper().new Task(cyclicBarrier));
		}

		executorService.shutdown();
		while (!executorService.isTerminated()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public class Task implements Runnable {
		private CyclicBarrier cyclicBarrier;

		public Task(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}

		@Override
		public void run() {
			// 等待所有任务准备就绪
			try {
				cyclicBarrier.await();
			} catch (InterruptedException ie) {
				// 线程在等待时，线程被中断,大部分迫使线程等待的方法都可能会抛出这个异常
				ie.printStackTrace();
			} catch (BrokenBarrierException bbe) {
				// 抛出这个异常说明 cyclicBarrier 已经破损，可能系统无法等待所有线程到齐了
				bbe.printStackTrace();
			}
		}
	}
}
