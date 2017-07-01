package com.cdeledu.monitor.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @类描述: 获取内存管理器信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年7月1日 下午5:37:20
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class JVMMemoryManager implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 返回此内存管理器管理的内存池名称 */
	private String[] memoryPoolNames;
	/** 返回表示此内存管理器的名称 */
	private String name;

	public String[] getMemoryPoolNames() {
		return memoryPoolNames;
	}

	public void setMemoryPoolNames(String[] memoryPoolNames) {
		this.memoryPoolNames = memoryPoolNames;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "JVMMemoryManager [memoryPoolNames=" + Arrays.toString(memoryPoolNames) + ", name="
				+ name + "]";
	}

}
