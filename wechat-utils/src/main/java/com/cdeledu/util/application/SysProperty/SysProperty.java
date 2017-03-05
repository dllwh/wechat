package com.cdeledu.util.application.SysProperty;

public final class SysProperty {
	/**
	 * Java类信息(Java运行时环境信息)
	 */
	// Java 运行时环境规范名称
	public final static String SPECIFICATION_NAME = "java.specification.name";
	// Java 运行时环境版本
	public final static String JAVA_VERSION = "java.version";
	// Java 运行时环境规范版本
	public final static String SPECIFICATION_VERSION = "java.specification.version";
	// Java 运行时环境供应商
	public final static String VENDOR = "java.vendor";
	// Java 运行时环境规范供应商
	public final static String SPECIFICATION_VENDOR = "java.specification.vendor";
	// Java 供应商的 URL
	public final static String VENDOR_URL = "java.vendor.url";
	// Java 安装目录(当前JRE的安装目录)
	public final static String JAVA_HOME = "java.home";
	// 加载库时搜索的路径列表(当前JRE的library搜索路径)
	public final static String JAVA_LIBRARY_PATH = "java.library.path";
	// 默认的临时文件路径
	public final static String TMPDIR = "java.io.tmpdir";
	// 要使用的 JIT 编译器的名称
	public final static String COMPILER = "java.compiler";
	// 一个或多个扩展目录的路径(当前JRE的扩展目录列表)
	public final static String JAVA_EXT_DIRS = "java.ext.dirs";
	// 当前JRE的名称
	public final static String JAVA_RUNTIME_NAME = "java.runtime.name";
	// 当前JRE的版本
	public final static String JAVA_RUNTIME_VERSION = "java.runtime.version";
	// 当前JRE的endorsed目录列表
	public final static String JAVA_ENDORSED_DIRS = "java.endorsed.dirs";
	// Java 类格式版本号(当前JRE的class文件格式的版本)
	public final static String JAVA_CLASS_VERSION = "java.class.version";
	// Java 类路径
	public final static String JAVA_CLASS_PATH = "java.class.path";
	/**
	 * Java虚拟机信息
	 */
	// Java 虚拟机实现名称
	public final static String JAVA_VM_NAME = "java.vm.name";
	// Java 虚拟机规范名称
	public final static String VM_SPECIFICATION_NAME = "java.vm.specification.name";
	// Java 虚拟机实现版本
	public final static String JAVA_VM_VERSION = "java.vm.version";
	// Java 虚拟机规范版本
	public final static String VM_SPECIFICATION_VERSION = "java.vm.specification.version";
	// Java 虚拟机实现供应商
	public final static String JAVA_VM_VENDOR = "java.vm.vendor";
	// Java 虚拟机规范供应商
	public final static String VM_SPECIFICATION_VENDOR = "java.vm.specification.vendor";

	/**
	 * 操作系统
	 */
	// 操作系统的名称
	public final static String OS_NAME = "os.name";
	// 操作系统的架构
	public final static String OS_ARCH = "os.arch";
	// 操作系统的版本
	public final static String OS_VERSION = "os.version";
	// 文件编码，源程序编码
	public final static String FILE_ENCODING = "file.encoding";
	// 文件分隔符（在 UNIX 系统中是“/”）
	public final static String FILE_SEPRATOR = "file.separator";
	// 路径分隔符（在 UNIX 系统中是“:”）
	public final static String PATH_SEPRATOR = "path.separator";
	// 操作系统信息
	public final static String SUN_DESKTOP = "sun.desktop";
	// CPU信息
	public final static String SUN_CPU_ISALIST = "sun.cpu.isalist";
	// 行结束符
	public final static String LINE_SEPRATOR = "line.separator";
	/**
	 * 用户信息
	 */
	// 用户的账户名称
	public final static String USER_NAME = "user.name";
	// 用户的主目录
	public final static String USER_HOME = "user.home";
	// 用户的当前工作目录
	public final static String USER_DIR = "user.dir";
	// 当前登录用户的语言设置
	public final static String USER_LANGUAGE = "user.language";
	// 当前登录用户的国家设置
	public final static String USER_COUNTRY = "user.country";
	// 当前登录用户的区域设置
	public final static String USER_REGION = "user.region";
	// 临时目录
	public final static String JAVA_IO_TMPDIR = "java_io_tmpdir";

	/**
	 *  网络代理
	 */
	// 代理服务器地址或IP地址
	public static final String HTTP_PROXY_HOST = "http.proxyHost";
	// 代理服务器端口
	public static final String HTTP_PROXY_PORT = "http.proxyPort";
	// 代理服务器用户名称
	public static final String HTTP_PROXY_USER = "http.proxyUser";
	// 代理服务器用户密码
	public static final String HTTP_PROXY_PASSWORD = "http.proxyPassword";
}
