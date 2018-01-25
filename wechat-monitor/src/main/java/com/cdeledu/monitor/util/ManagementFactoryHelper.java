package com.cdeledu.monitor.util;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: ManagementFactory 说明
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年1月25日 下午1:51:22
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class ManagementFactoryHelper {
	/**
	 * 用于 Java 虚拟机的类加载系统的管理接口
	 */
	ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
	/**
	 * 用于 Java 虚拟机的编译系统的管理接口
	 */
	CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
	/**
	 * 用于 Java 虚拟机的垃圾回收的管理接口
	 */
	List<GarbageCollectorMXBean> gCollectorMXBean = ManagementFactory.getGarbageCollectorMXBeans();
	/**
	 * 内存管理器的管理接口
	 */
	List<MemoryManagerMXBean> memoryManagerMXBean = ManagementFactory.getMemoryManagerMXBeans();
	/**
	 * Java 虚拟机的内存系统的管理接口
	 */
	MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
	/**
	 * 用于操作系统的管理接口，Java 虚拟机在此操作系统上运行。
	 */
	OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
	/** Java 虚拟机的运行时系统的管理接口 */
	RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
	/**
	 * Java 虚拟机线程系统的管理接口
	 */
	ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
	/**
	 * Java 虚拟机各内存区信息
	 */
	List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
}
