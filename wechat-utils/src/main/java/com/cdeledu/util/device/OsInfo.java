package com.cdeledu.util.device;

/**
 * @类描述: 表当前OS的信息
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月24日 下午12:05:39
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class OsInfo {

	/**
	 * @方法:取得当前OS的名称
	 *               <p>
	 *               例如：<code>"Windows XP"</code>
	 *               </p>
	 * @创建人:独泪了无痕
	 * @return 属性值，如果不能取得（因为Java安全限制）或值不存在，则返回<code>null</code>
	 */
	public static final String getName() {
		return SystemUtil.get(SystemUtil.OS_NAME, false);
	}

	/**
	 * @方法:取得当前OS的版本
	 *               <p>
	 *               例如：<code>"5.1"</code>
	 *               </p>
	 * @创建人:独泪了无痕
	 * @return 属性值，如果不能取得（因为Java安全限制）或值不存在，则返回<code>null</code>
	 */
	public static final String getVersion() {
		return SystemUtil.get(SystemUtil.OS_VERSION, false);
	}

	/**
	 * 匹配OS名称。
	 * 
	 * @param osNamePrefix
	 *            OS名称前缀
	 * @return 如果匹配，则返回<code>true</code>
	 */
	private final static boolean getOSMatches(String osNamePrefix) {
		return getName().startsWith(osNamePrefix);
	}

	/**
	 * 匹配OS名称。
	 * 
	 * @param osNamePrefix
	 *            OS名称前缀
	 * @param osVersionPrefix
	 *            OS版本前缀
	 * @return 如果匹配，则返回<code>true</code>
	 */
	private final static boolean getOSMatches(String osNamePrefix, String osVersionPrefix) {
		if ((SystemUtil.OS_NAME == null) || (SystemUtil.OS_VERSION == null)) {
			return false;
		}
		return SystemUtil.OS_NAME.startsWith(osNamePrefix)
				&& SystemUtil.OS_NAME.startsWith(osVersionPrefix);
	}

	/**
	 * 取得OS的文件路径的分隔符（取自系统属性：<code>file.separator</code>）。
	 * <p>
	 * 例如：Unix为<code>"/"</code>，Windows为<code>"\\"</code>。
	 * </p>
	 * 
	 * @return 属性值，如果不能取得（因为Java安全限制）或值不存在，则返回<code>null</code>。
	 */
	public final String getFileSeparator() {
		return SystemUtil.FILE_SEPRATOR;
	}

	/**
	 * 取得OS的文本文件换行符（取自系统属性：<code>line.separator</code>）。
	 * <p>
	 * 例如：Unix为<code>"\n"</code>，Windows为<code>"\r\n"</code>。
	 * </p>
	 * 
	 * @return 属性值，如果不能取得（因为Java安全限制）或值不存在，则返回<code>null</code>。
	 */
	public final String getLineSeparator() {
		return SystemUtil.LINE_SEPRATOR;
	}

	/**
	 * 取得OS的搜索路径分隔符（取自系统属性：<code>path.separator</code>）。
	 * <p>
	 * 例如：Unix为<code>":"</code>，Windows为<code>";"</code>。
	 * </p>
	 * 
	 * @return 属性值，如果不能取得（因为Java安全限制）或值不存在，则返回<code>null</code>。
	 */
	public final String getPathSeparator() {
		return SystemUtil.PATH_SEPRATOR;
	}

	/**
	 * 将OS的信息转换成字符串。
	 * 
	 * @return OS的字符串表示
	 */
	public final String toString() {
		StringBuilder builder = new StringBuilder();

		SystemUtil.append(builder, "OS Name:        ", getName());
		SystemUtil.append(builder, "OS Version:     ", getVersion());
		SystemUtil.append(builder, "File Separator: ", getFileSeparator());
		SystemUtil.append(builder, "Line Separator: ", getLineSeparator());
		SystemUtil.append(builder, "Path Separator: ", getPathSeparator());

		return builder.toString();
	}

	/**
	 * 判断当前OS的类型
	 */
	public static final boolean isLinux() {
		return getOSMatches("Linux") || getOSMatches("LINUX");
	}

	public final static boolean isMac() {
		return getOSMatches("Mac") || getOSMatches("os");
	}

	public final static boolean isWindows() {
		return getOSMatches("Windows") || getOSMatches("windows");
	}

	public final static boolean isWindowsME() {
		return getOSMatches("Windows", "4.9");
	}

	public final static boolean isWindowsNT() {
		return getOSMatches("Windows NT");
	}

	public final static boolean isWindowsXP() {
		return getOSMatches("Windows", "5.1");
	}
}
