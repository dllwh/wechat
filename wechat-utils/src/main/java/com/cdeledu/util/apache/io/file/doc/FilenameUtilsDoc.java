package com.cdeledu.util.apache.io.file.doc;

import java.util.Collection;

import org.apache.commons.io.IOCase;

/**
 * 
 * @ClassName: FilenameUtilsDoc
 * @Description: <ul>
 *               <li>FilenameUtils方法说明文档</li>
 *               <li>参数说明</li>
 *               <li>basePath : 文件路径</li>
 *               <li>fileName : 文件名称</li>
 *               <li>fullFileName : 文件路径 +File.separator+文件名称</li>
 *               </ul>
 * @author: 独泪了无痕
 * @date: 2015年8月29日 下午2:25:13
 * @version: V1.0
 * @history:
 */
interface FilenameUtilsDoc {

	/** 合并目录和文件名为文件全路径: */
	public String concat(String basePath, String fileName);

	/** 判断目录下是否包含指定文件或目录: */
	public boolean directoryContains(String basePath, String fullFileName);

	/** 判断文件路径是否相同，非格式化: */
	public boolean equals(String fullFileName, String linuxFileName);

	/** 判断文件路径是否相同，格式化并大小写不敏感: */
	public boolean equals(String fullFileName, String linuxFileName,
			boolean normalized, IOCase caseSensitivity);

	/** 判断文件路径是否相同，格式化并大小写敏感: */
	public boolean equalsNormalized(String fullFileName, String linuxFileName);

	public boolean equalsNormalizedOnSystem(String fullFileName,
			String linuxFileName);

	/** 判断文件路径是否相同，不格式化，大小写敏感根据系统规则：windows：敏感；linux：不敏感: */
	public boolean equalsOnSystem(String fullFileName, String linuxFileName);

	/** 文件路径去除目录和后缀后的文件名 */
	public String getBaseName(String fullFileName);

	/** 获取文件的后缀: */
	public String getExtension(String fullFileName);

	/** 获取文件的目录: */
	public String getFullPath(String fullFileName);

	/** 获取文件的目录不包含结束符 */
	public String getFullPathNoEndSeparator(String fullFileName);

	/** 获取文件名称，包含后缀 */
	public String getName(String fullFileName);

	/** 去除前缀的路径 */
	public String getPath(String fullFileName);

	/** 去除前缀并结尾去除分隔符: */
	public String getPathNoEndSeparator(String fullFileName);

	/** 获取前缀 */
	public String getPrefix(String fullFileName);

	/** 获取前缀长度 */
	public int getPrefixLength(String fullFileName);

	/** 获取最后一个.的位置 */
	public int indexOfExtension(String fullFileName);

	/** 获取最后一个/的位置 */
	public int indexOfLastSeparator(String fullFileName);

	/** 判断文件扩展名是否包含在指定集合中 */
	public boolean isExtension(String fullFileName,
			Collection<String> extensions);

	/** 判断文件扩展名是否等于指定扩展名 */
	public boolean isExtension(String fullFileName, String extension);

	/** 判断文件扩展名是否包含在指定字符串数组中 */
	public boolean isExtension(String filename, String[] extensions);

	/** 获取当前系统格式化路径 */
	public String normalize(String fullFileName);

	/** 获取linux系统格式化路径 */
	public String normalize(String fullFileName, boolean unixSeparator);

	/** 获取当前系统无结尾分隔符的路径 */
	public String normalizeNoEndSeparator(String basePath);

	/** 获取linux系统有无结尾分隔符的路径: */
	public String normalizeNoEndSeparator(String basePath, boolean unixSeparator);

	/** 移除文件的扩展名: */
	public String removeExtension(String fullFileName);

	/** 转换分隔符为当前系统分隔符: */
	public String separatorsToSystem(String fullFileName);

	/** 转换分隔符为linux系统分隔符: */
	public String separatorsToUnix(String fullFileName);

	/** 转换分隔符为windows系统分隔符: */
	public String separatorsToWindows(String fullFileName);

	/** 判断文件扩展名是否和指定规则匹配，大小写敏感 */
	public boolean wildcardMatch(String fileName, String wildcardMatcher);

	/** 判断文件扩展名是否和指定规则匹配，大小写不敏感 */
	public boolean wildcardMatch(String fileName, String wildcardMatcher,
			IOCase caseSensitivity);

	/** 判断文件扩展名是否和指定规则匹配，根据系统判断敏感型：windows:不敏感；linux：敏感 */
	public boolean wildcardMatchOnSystem(String fileName, String wildcardMatcher);
}
