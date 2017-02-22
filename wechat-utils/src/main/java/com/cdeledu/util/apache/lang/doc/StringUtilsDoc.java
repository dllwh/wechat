package com.cdeledu.util.apache.lang.doc;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Locale;

/**
 * @类描述: StringUtils 操作方法 说明文档
 * @创建者: 独泪了无痕
 * @创建日期: 2016年1月21日 下午9:58:11
 * @版本: V1.0
 * @since: JDK 1.7
 * @see <a href=
 *      "http://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/">
 *      org.apache.commons.lang3</a>
 */
interface StringUtilsDoc {
	/**
	 * @方法:判断是否Null 或者 ""
	 */
	public boolean isEmpty(final CharSequence cs);

	/**
	 * @方法:判断是否Null 或者 ""
	 */
	public boolean isNotEmpty(final CharSequence cs);

	/**
	 * @方法:检查是否有一个或多个字符串为""或null
	 */
	public boolean isAnyEmpty(final CharSequence... css);

	/**
	 * @方法:检查一个字符串是否为空白字符串、""或null
	 */
	public boolean isNoneEmpty(final CharSequence... css);

	/**
	 * @方法:判断是否null 或者 "" 去空格
	 */
	public boolean isBlank(final CharSequence cs);

	/**
	 * @方法:判断是否null 或者 "" 去空格
	 */
	public boolean isNotBlank(final CharSequence cs);

	/**
	 * @方法:检查是否有一个或多个字符串为空白字符串、""或null
	 */
	public boolean isAnyBlank(final CharSequence... css);

	/**
	 * @方法:去掉字符串两端的控制符(control characters, char <= 32) ，
	 *                         如果输入空白字符串、""，则返回""；如果为null，则返回null 。
	 */
	public boolean isNoneBlank(final CharSequence... css);

	/**
	 * @方法:去掉字符串两端的控制符(control characters, char <= 32)
	 *                         ，如果输入空白字符串、""或null，则返回null 。
	 */
	public String trim(final String str);

	/**
	 * @方法:去掉字符串两端的控制符(control characters, char <= 32) ，如果输入空白字符串、""或null，则返回""
	 */
	public String trimToNull(final String str);

	/**
	 * @方法:去掉字符串两端的空白符(whitespace)，如果输入null则返回null，将NULL 和 "" 转换为""
	 */
	public String trimToEmpty(final String str);

	/**
	 * @方法:可能是对特殊空格符号去除？？
	 */
	public String strip(final String str);

	/**
	 * @方法:同上，将""和null转换为Null
	 */
	public String stripToNull(String str);

	/**
	 * @方法:去空格.去字符 如果第二个参数为null去空格(否则去掉字符串2边一样的字符，到不一样为止)
	 */
	public String strip(String str, final String stripChars);

	/**
	 * @方法:如果第二个参数为null只去前面空格(否则去掉字符串前面一样的字符，到不一样为止)
	 */
	public String stripStart(final String str, final String stripChars);

	/**
	 * @方法:如果第二个参数为null只去后面空格，(否则去掉字符串后面一样的字符，到不一样为止)
	 */
	public String stripEnd(final String str, final String stripChars);

	/**
	 * @方法:对数组没个字符串进行去空格
	 */
	public String[] stripAll(final String... strs);

	/**
	 * @方法:如果第二个参数为null.对数组每个字符串进行去空格。(否则去掉数组每个元素开始和结尾一样的字符)
	 */
	public String[] stripAll(final String[] strs, final String stripChars);

	/**
	 * @方法:去掉参数中的一些特殊符号 如： 'à' 会被替换为 'a'
	 */
	public String stripAccents(final String input);

	/**
	 * @方法:判断2个字符串是否相等相等,Null也相等
	 */
	public boolean equals(final CharSequence cs1, final CharSequence cs2);

	/**
	 * @方法:不区分大小写比较
	 */
	public boolean equalsIgnoreCase(final CharSequence str1, final CharSequence str2);

	/**
	 * @方法:普通查找字符，如果一参数为null或者""返回-1
	 */
	public int indexOf(final CharSequence seq, final int searchChar);

	/**
	 * @方法:从指定位置(三参数)开始查找，本例从第2个字符开始查找k字符
	 */
	public int indexOf(final CharSequence seq, final int searchChar, final int startPos);

	/**
	 * @方法:
	 */
	public int indexOf(final CharSequence seq, final CharSequence searchSeq);

	/**
	 * @方法:
	 */
	public int indexOf(final CharSequence seq, final CharSequence searchSeq, final int startPos);

	/**
	 * @方法:查找,不区分大小写
	 */
	public int ordinalIndexOf(final CharSequence str, final CharSequence searchStr, final int ordinal);

	/**
	 * @方法:
	 */
	public int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr);

	/**
	 * @方法:从指定位置(三参数)开始查找,不区分大小写
	 */
	public int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr, int startPos);

	/**
	 * @方法:从后往前查找
	 */
	public int lastIndexOf(final CharSequence seq, final int searchChar);

	/**
	 * @方法:从后往前查找
	 */
	public int lastIndexOf(final CharSequence seq, final int searchChar, final int startPos);

	/**
	 * @方法:从后往前查找
	 * 
	 */
	public int lastIndexOf(final CharSequence seq, final CharSequence searchSeq);

	/**
	 * @方法:从后往前查，不区分大小写
	 */
	public int lastOrdinalIndexOf(final CharSequence str, final CharSequence searchStr, final int ordinal);

	/**
	 * @方法:
	 */
	public int lastIndexOf(final CharSequence seq, final CharSequence searchSeq, final int startPos);

	/**
	 * @方法:返回字符串searchStr在字符串str中最后一次出现的位置，忽略大小写
	 */
	public int lastIndexOfIgnoreCase(final CharSequence str, final CharSequence searchStr);

	/**
	 * @方法:返回字符串searchStr在字符串str中最后一次出现的位置，忽略大小写
	 */
	public int lastIndexOfIgnoreCase(final CharSequence str, final CharSequence searchStr, int startPos);

	/**
	 * @方法:将数组转换为字符串形式
	 */
	public boolean contains(final CharSequence seq, final int searchChar);

	/**
	 * @方法:检查是否查到，返回boolean,null返回false
	 */
	public boolean contains(final CharSequence seq, final CharSequence searchSeq);

	/**
	 * @方法:检查是否查到，返回boolean,null返回假,不区分大小写
	 */
	public boolean containsIgnoreCase(final CharSequence str, final CharSequence searchStr);

	/**
	 * @方法:检查是否有含有空格,返回boolean
	 */
	public boolean containsWhitespace(final CharSequence seq);

	/**
	 * @方法:查询字符串跟数组任一元素相同的第一次相同的位置
	 */
	public int indexOfAny(final CharSequence cs, final char... searchChars);

	/**
	 * @方法:查询字符串中指定字符串(参数二)出现的次数
	 */
	public int indexOfAny(final CharSequence cs, final String searchChars);

	/**
	 * @方法:查找字符串中是否有字符数组中相同的字符，返回boolean
	 */
	public boolean containsAny(final CharSequence cs, final char... searchChars);

	/**
	 * @方法:未理解与lastIndexOf不同之处。是否查到，返回boolean
	 */
	public boolean containsAny(final CharSequence cs, final CharSequence searchChars);

	/**
	 * @方法:
	 */
	public boolean containsAny(CharSequence cs, CharSequence... searchCharSequences);

	public int indexOfAnyBut(final CharSequence cs, final char... searchChars);

	public int indexOfAnyBut(final CharSequence seq, final CharSequence searchChars);

	/**
	 * @方法:判断字符串中所有字符，都是出自参数二中
	 */
	boolean containsOnly(final CharSequence cs, final char... valid);

	/**
	 * @方法:判断字符串中所有字符，都是出自参数二的数组中
	 */
	public boolean containsOnly(final CharSequence cs, final String validChars);

	/**
	 * @方法:判断字符串中所有字符，都不在参数二的数组中
	 */
	public boolean containsNone(final CharSequence cs, final char... searchChars);

	/**
	 * @方法:判断字符串中所有字符，都不在参数二中
	 */
	public boolean containsNone(final CharSequence cs, final String invalidChars);

	public int indexOfAny(final CharSequence str, final CharSequence... searchStrs);

	/**
	 * @方法:从后往前查找字符串中与字符数组中相同的元素第一次出现的位置
	 */
	public int lastIndexOfAny(final CharSequence str, final CharSequence... searchStrs);

	/**
	 * @方法:截取指定位置的字符，null返回null.""返回""
	 */
	public String substring(final String str, int start);

	/**
	 * @方法:截取指定区间的字符
	 */
	public String substring(final String str, int start, int end);

	/**
	 * @方法:从左截取指定长度的字符串
	 */
	public String left(final String str, final int len);

	/**
	 * @方法:从右截取指定长度的字符串
	 */
	public String right(final String str, final int len);

	/**
	 * @方法:从第几个开始截取，三参数表示截取的长度
	 */
	public String mid(final String str, int pos, final int len);

	/**
	 * @方法:截取到等于第二个参数的字符串为止
	 */
	public String substringBefore(final String str, final String separator);

	/**
	 * @方法:从左往右查到相等的字符开始，保留后边的，不包含等于的字符
	 */
	public String substringAfter(final String str, final String separator);

	/**
	 * @方法:这个也是截取到相等的字符，但是是从右往左
	 */
	public String substringBeforeLast(final String str, final String separator);

	/**
	 * @方法:这个截取同上是从右往左。但是保留右边的字符
	 */
	public String substringAfterLast(final String str, final String separator);

	/**
	 * @方法:截取查找到第一次的位置，和第二次的位置中间的字符。如果没找到第二个返回null
	 */
	public String substringBetween(final String str, final String tag);

	/**
	 * @方法:
	 */
	public String substringBetween(final String str, final String open, final String close);

	/**
	 * @方法:返回参数二和参数三中间的字符串，返回数组形式
	 */
	public String[] substringsBetween(final String str, final String open, final String close);

	/**
	 * @方法:用空格分割成数组，null为null
	 */
	public String[] split(final String str);

	/**
	 * @方法:以指定字符分割成数组
	 */
	public String[] split(final String str, final char separatorChar);

	/**
	 * @方法:
	 */
	public String[] split(final String str, final String separatorChars);

	/**
	 * @方法:以指定字符分割成数组，第三个参数表示分隔成数组的长度，如果为0全体分割
	 */
	public String[] split(final String str, final String separatorChars, final int max);

	/**
	 * @方法:指定字符分割成数组
	 */
	public String[] splitByWholeSeparator(final String str, final String separator);

	/**
	 * @方法:以指定字符分割成数组，第三个参数表示分隔成数组的长度
	 */
	public String[] splitByWholeSeparator(final String str, final String separator, final int max);

	/**
	 * @方法:分割，但" "不会被忽略算一个元素,二参数为null默认为空格分隔
	 */
	public String[] splitByWholeSeparatorPreserveAllTokens(final String str, final String separator);

	/**
	 * @方法:同上，分割," "不会被忽略算一个元素。第三个参数代表分割的数组长度
	 */
	public String[] splitByWholeSeparatorPreserveAllTokens(final String str, final String separator, final int max);

	/**
	 * @方法:分割
	 */
	public String[] splitPreserveAllTokens(final String str);

	/**
	 * @方法:指定字符分割成数组
	 */
	public String[] splitPreserveAllTokens(final String str, final char separatorChar);

	/**
	 * @方法:指定字符分割成数组
	 */
	public String[] splitPreserveAllTokens(final String str, final String separatorChars);

	/**
	 * @方法:以指定字符分割成数组，第三个参数表示分隔成数组的长度
	 */
	public String[] splitPreserveAllTokens(final String str, final String separatorChars, final int max);

	/**
	 * @方法:以不同类型进行分隔
	 */
	public String[] splitByCharacterType(final String str);

	/**
	 * @方法:
	 */
	public String[] splitByCharacterTypeCamelCase(final String str);

	/**
	 * @方法:拼接
	 */
	@SuppressWarnings("unchecked")
	public <T> String join(final T... elements);

	/**
	 * @方法:用连接符拼接
	 */
	public String join(final Object[] array, final char separator);


	/**
	 * @方法:拼接指定数组下标的开始(三参数)和结束(四参数,不包含)的中间这些元素，用连接符连接
	 */
	public String join(final Object[] array, final char separator, final int startIndex, final int endIndex);

	public String join(final Object[] array, String separator, final int startIndex, final int endIndex);

	public String join(final Iterator<?> iterator, final char separator);

	public String join(final Iterator<?> iterator, final String separator);

	public String join(final Iterable<?> iterable, final char separator);

	public String join(final Iterable<?> iterable, final String separator);

	/**
	 * @方法:删除所有空格符
	 */
	public String deleteWhitespace(final String str);

	/**
	 * @方法:移除开始部分的相同的字符
	 */
	public String removeStart(final String str, final String remove);

	/**
	 * @方法:移除开始部分的相同的字符,不区分大小写
	 */
	public String removeStartIgnoreCase(final String str, final String remove);

	/**
	 * @方法:移除后面相同的部分
	 */
	public String removeEnd(final String str, final String remove);

	/**
	 * @方法:移除后面相同的部分，不区分大小写
	 */
	public String removeEndIgnoreCase(final String str, final String remove);

	/**
	 * @方法:移除所有相同的部分
	 */
	public String remove(final String str, final String remove);

	/**
	 * @方法:
	 */
	public String remove(final String str, final char remove);

	/**
	 * @方法:替换指定的字符，只替换第一次出现的
	 */
	public String replaceOnce(final String text, final String searchString, final String replacement);

	/**
	 * @方法:
	 */
	public String replacePattern(final String source, final String regex, final String replacement);

	/**
	 * @方法:
	 */
	public String removePattern(final String source, final String regex);

	/**
	 * @方法:替换所有出现过的字符
	 */
	public String replace(final String text, final String searchString, final String replacement);

	/**
	 * @方法:也是替换，最后一个参数表示替换几个
	 */
	public String replace(final String text, final String searchString, final String replacement, int max);

	/**
	 * @方法:二三参数对应的数组，查找二参数数组一样的值，替换三参数对应数组的值
	 */
	public String replaceEach(final String text, final String[] searchList, final String[] replacementList);

	/**
	 * @方法:二三参数对应的数组，查找二参数数组一样的值，替换三参数对应数组的值
	 */
	public String replaceEachRepeatedly(final String text, final String[] searchList, final String[] replacementList);

	/**
	 * @方法:
	 */
	public String replaceChars(final String str, final char searchChar, final char replaceChar);

	/**
	 * @方法:
	 */
	public String replaceChars(final String str, final String searchChars, String replaceChars);

	/**
	 * @方法:替换指定开始(参数三)和结束(参数四)中间的所有字符
	 */
	public String overlay(final String str, String overlay, int start, int end);

	/**
	 * @方法:移除结尾字符为"\n", "\r", 或者 "\r\n".
	 */
	public String chomp(final String str);

	/**
	 * @方法:去结尾相同字符
	 */
	public String chomp(final String str, final String separator);

	/**
	 * @方法:去掉末尾最后一个字符.如果是"\n", "\r", 或者 "\r\n"也去除
	 */
	public String chop(final String str);

	/**
	 * @方法:复制参数一的字符串，参数二为复制的次数
	 */
	public String repeat(final String str, final int repeat);

	/**
	 * @方法:复制参数一的字符串，参数三为复制的次数。参数二为复制字符串中间的连接字符串
	 */
	public String repeat(final String str, final String separator, final int repeat);

	/**
	 * @方法:如何字符串长度小于参数二的值，末尾加空格补全。(小于字符串长度不处理返回)
	 */
	public String repeat(final char ch, final int repeat);

	/**
	 * @方法:字符串长度小于二参数，末尾用参数三补上，多于的截取(截取补上的字符串)
	 * 
	 */
	public String rightPad(final String str, final int size);

	/**
	 * @方法:
	 */
	public String rightPad(final String str, final int size, final char padChar);

	/**
	 * @方法:
	 */
	public String rightPad(final String str, final int size, String padStr);

	/**
	 * @方法:同上在前面补全空格
	 */
	public String leftPad(final String str, final int size);

	/**
	 * @方法:字符串长度小于二参数，前面用参数三补上，多于的截取(截取补上的字符串)
	 */
	public String leftPad(final String str, final int size, final char padChar);

	/**
	 * @方法:字符串长度小于二参数，前面用参数三补上，多于的截取(截取补上的字符串)
	 */
	public String leftPad(final String str, final int size, String padStr);

	/**
	 * @方法:
	 */
	public int length(final CharSequence cs);

	/**
	 * @方法:字符串长度小于二参数。在两侧用空格平均补全（测试后面补空格优先）
	 */
	public String center(final String str, final int size);

	/**
	 * @方法:字符串长度小于二参数。在两侧用三参数的字符串平均补全（测试后面补空格优先）
	 */
	public String center(String str, final int size, final char padChar);

	/**
	 * @方法:
	 */
	public String center(String str, final int size, String padStr);

	/**
	 * @方法:
	 */
	public String upperCase(final String str);

	/**
	 * @方法:
	 */
	public String upperCase(final String str, final Locale locale);

	/**
	 * @方法:
	 */
	public String lowerCase(final String str);

	/**
	 * @方法:
	 */
	public String lowerCase(final String str, final Locale locale);

	/**
	 * @方法:转换第一个字符为大写.如何第一个字符是大写原始返回
	 */
	public String capitalize(final String str);

	/**
	 * @方法:/转换第一个字符为大写.如何第一个字符是大写原始返回
	 */
	public String uncapitalize(final String str);

	/**
	 * @方法:反向转换，大写变小写，小写变大写
	 */
	public String swapCase(final String str);

	/**
	 * @方法:未发现与indexOfAny不同之处 查询字符串中指定字符串(参数二)出现的次数
	 */
	public int countMatches(final CharSequence str, final CharSequence sub);

	/**
	 * @方法:
	 */
	public int countMatches(final CharSequence str, final char ch);

	/**
	 * @方法:检查是否CharSequence的只包含Unicode的字母 空将返回false 一个空的CharSequence（长（） = 0）
	 *                                    将返回true
	 */
	public boolean isAlpha(final CharSequence cs);

	/**
	 * @方法:检查是否只包含Unicode的CharSequence的字母和空格（''） 空将返回一个空的CharSequence假（长（） = 0）
	 *                                           将返回true
	 */
	public boolean isAlphaSpace(final CharSequence cs);

	/**
	 * @方法:检查是否只包含Unicode的CharSequence的字母或数字。空将返回false 一个空的CharSequence（长（） = 0）
	 *                                                 将返回true
	 */
	public boolean isAlphanumeric(final CharSequence cs);

	/**
	 * @方法:如果检查的Unicode CharSequence的只包含字母，数字或空格（''）空将返回false 一个空的CharSequence（
	 *                  长（）= 0）将返回true
	 */
	public boolean isAlphanumericSpace(final CharSequence cs);

	/**
	 * @方法:检查是否只包含ASCII可CharSequence的字符。空将返回false。一个空的CharSequence（长（）= 0）
	 *                                                                  将返回true
	 */
	public boolean isAsciiPrintable(final CharSequence cs);

	/**
	 * @方法:检查是否只包含数值
	 */
	public boolean isNumeric(final CharSequence cs);

	/**
	 * @方法:检查是否只包含数值或者空格
	 */
	public boolean isNumericSpace(final CharSequence cs);

	/**
	 * @方法:检查是否只是空格或""
	 */
	public boolean isWhitespace(final CharSequence cs);

	/**
	 * @方法:检查是否全是英文小写
	 */
	public boolean isAllLowerCase(final CharSequence cs);

	/**
	 * @方法:检查是否全是英文大写
	 */
	public boolean isAllUpperCase(final CharSequence cs);

	/**
	 * @方法:将""或者Null 转换为 ""
	 */
	public String defaultString(final String str);

	/**
	 * @方法:仅当字符串为Null时 转换为指定的字符串(二参数)
	 */
	public String defaultString(final String str, final String defaultStr);

	/**
	 * @方法:
	 */
	public <T extends CharSequence> T defaultIfBlank(final T str, final T defaultStr);

	/**
	 * @方法:
	 */
	public <T extends CharSequence> T defaultIfEmpty(final T str, final T defaultStr);

	/**
	 * @方法:将字符串倒序排列
	 */
	public String reverse(final String str);

	/**
	 * @方法:根据特定字符(二参数)分隔进行反转
	 */
	public String reverseDelimited(final String str, final char separatorChar);

	/**
	 * @方法:只显示指定数量(二参数)的字符,后面以三个点补充(参数一截取+三个点=二参数)
	 */
	public String abbreviate(final String str, final int maxWidth);

	/**
	 * @方法:
	 */
	public String abbreviate(final String str, int offset, final int maxWidth);

	/**
	 * @方法:保留指定长度，最后一个字符前加点.
	 */
	public String abbreviateMiddle(final String str, final String middle, final int length);

	/**
	 * @方法:去掉参数2字符串中在参数一中开头部分共有的部分
	 */
	public String difference(final String str1, final String str2);

	/**
	 * @方法:统计2个字符串开始部分共有的字符个数
	 */
	public int indexOfDifference(final CharSequence cs1, final CharSequence cs2);

	/**
	 * @方法:统计数组中各个元素的字符串开始都一样的字符个数
	 */
	public int indexOfDifference(final CharSequence... css);

	/**
	 * @方法:取数组每个元素共同的部分字符串
	 */
	public String getCommonPrefix(final String... strs);

	/**
	 * @方法:统计参数一中每个字符与参数二中每个字符不同部分的字符个数
	 */
	public int getLevenshteinDistance(CharSequence s, CharSequence t);

	/**
	 * @方法:
	 */
	public int getLevenshteinDistance(CharSequence s, CharSequence t, final int threshold);

	/**
	 * @方法:
	 */
	public double getJaroWinklerDistance(final CharSequence first, final CharSequence second);

	/**
	 * @方法:
	 */
	public int getFuzzyDistance(final CharSequence term, final CharSequence query, final Locale locale);

	/**
	 * @方法:判断开始部分是否与二参数相同
	 */
	public boolean startsWith(final CharSequence str, final CharSequence prefix);

	/**
	 * @方法:判断开始部分是否与二参数相同。不区分大小写
	 */
	public boolean startsWithIgnoreCase(final CharSequence str, final CharSequence prefix);

	/**
	 * @方法:判断字符串开始部分是否与数组中的某一元素相同
	 */
	public boolean startsWithAny(final CharSequence string, final CharSequence... searchStrings);

	/**
	 * @方法:判断结尾是否相同
	 */
	public boolean endsWith(final CharSequence str, final CharSequence suffix);

	/**
	 * @方法:判断结尾是否相同，不区分大小写
	 */
	public boolean endsWithIgnoreCase(final CharSequence str, final CharSequence suffix);

	/**
	 * @方法:
	 */
	public String normalizeSpace(final String str);

	/**
	 * @方法:
	 */
	public boolean endsWithAny(final CharSequence string, final CharSequence... searchStrings);

	/**
	 * @方法:
	 */
	public String appendIfMissing(final String str, final CharSequence suffix, final CharSequence... suffixes);

	/**
	 * @方法:
	 */
	public String appendIfMissingIgnoreCase(final String str, final CharSequence suffix,
			final CharSequence... suffixes);

	/**
	 * @方法:
	 */
	public String prependIfMissing(final String str, final CharSequence prefix, final CharSequence... prefixes);

	/**
	 * @方法:
	 */
	public String prependIfMissingIgnoreCase(final String str, final CharSequence prefix,
			final CharSequence... prefixes);

	/**
	 * @方法:
	 */
	public String toString(final byte[] bytes, final String charsetName) throws UnsupportedEncodingException;

	/**
	 * @方法:
	 */
	public String toEncodedString(final byte[] bytes, final Charset charset);

	/**
	 * @方法:
	 */
	public String wrap(final String str, final char wrapWith);

	/**
	 * @方法:
	 */
	public String wrap(final String str, final String wrapWith);
}
