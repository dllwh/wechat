package com.cdeledu.monitor.util;

public enum MonitorType {
	
	ALL("all", 0),
	
	CPU("cpu", 1),
	SYSMEM("sys", 1),
	OS("os", 2),	//other
	USER("user", 2),	//other
	FILESYSTEM("fs", 1),
	NET("net", 1),
	DIRECTORY("directory", 1),	
	
	JVMCLASSLOADING("jvmclassloading", 1),
	JVMCOMPILATION("jvmcompilation", 1),
	JVMGARBAGECOLLECTOR("jvmgc", 1),
	JVMMEMORYMANAGER("jvmmemmgr", 2),	//other
	JVMMEMORYPOOL("jvmmempool", 1),
	JVMMEMORY("jvmmem", 1),
	JVMOPERATINGSYSTEM("jvmos", 1),
	JVMRUNTIME("jvmrt", 2),	//other
	JVMTHREAD("jvmthd", 2),	//other
	
	HTTPREQUEST("httprequest", 2),
	HTTPSESSION("httpsession", 2),
	
	SQL("sql", 2);
//	SQLDETAIL("sqldetail", 2);

	private String name;
	private int status;
	
	private MonitorType(String name, int status) {
		this.name = name;
		this.status = status;
	}

	public String getName() {
		return name;
	}
	
	public int getStatus() {
		return this.status;
	}
}
