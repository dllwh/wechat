package com.cdeledu.util.message;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/**
 * @类描述:
 *       <ul>
 *       <li>pinyin4j是一个支持将中文转换到拼音的Java开源类库</li>
 *       <li>1.支持简体中文和繁体中文字符</li>
 *       <li>2.支持转换到汉语拼音</li>
 *       <li>3.支持多音字，即可以获取一个中文字符的多种发音</li>
 *       <li>4.支持多种字符串输出格式</li>
 *       </ul>
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年11月25日 下午1:52:00
 * @版本: V2.0
 * @since: JDK 1.7
 */
public class PinyinUtil {
	/** -------------------------- 私有属性 start ------------------------------- */
	/** 创建汉语拼音处理类 */
	private static final HanyuPinyinOutputFormat DEFAULTFORMAT = new HanyuPinyinOutputFormat();
	static {
		// 输出设置，大小写，音标方式
		DEFAULTFORMAT.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		DEFAULTFORMAT.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	}

	/** -------------------------- 私有属性 end ------------------------------- */
	/** -------------------------- 私有方法 start ------------------------------- */
	/**
	 * @方法描述: 去除多音字重复数据
	 * @param chineseStr
	 * @return
	 */
	private static List<Map<String, Integer>> discountTheChinese(String chineseStr) {
		// 去除重复拼音后的拼音列表
		List<Map<String, Integer>> resultMapList = new ArrayList<Map<String, Integer>>();
		// 用于处理每个字的多音字，去掉重复
		Map<String, Integer> onlyOne = null;
		String[] firsts = chineseStr.split("--");
		// 读出每个汉字的拼音
		for (String str : firsts) {
			onlyOne = new Hashtable<String, Integer>();
			String[] china = str.split(",");
			// 多音字处理
			for (String s : china) {
				Integer count = onlyOne.get(s);
				if (count == null) {
					onlyOne.put(s, new Integer(1));
				} else {
					onlyOne.remove(s);
					count++;
					onlyOne.put(s, count);
				}
			}
			resultMapList.add(onlyOne);
		}
		return resultMapList;
	}

	/**
	 * @方法描述: 解析并组合拼音，对象合并方案(推荐使用)
	 * @param list
	 * @return
	 */
	private static List<String> parseTheChineseByObject(List<Map<String, Integer>> list) {
		Map<String, Integer> firstMap = null;
		for (int i = 0; i < list.size(); i++) {
			Map<String, Integer> tempMap = new Hashtable<String, Integer>();
			// 第一次循环,firstMap为空
			if (null != firstMap) {
				// 取出上次组合与此次集合的字符，并保存
				for (String s : firstMap.keySet()) {
					for (String s1 : list.get(i).keySet()) {
						tempMap.put(s.trim() + " " + s1, 1);
					}
				}
				// 为避免出现重复数据,清理上一次Map
				if (MapUtils.isNotEmpty(tempMap)) {
					firstMap.clear();
				}
			} else {
				for (String fs : list.get(i).keySet()) {
					tempMap.put(fs, 1);
				}
			}
			if (MapUtils.isNotEmpty(tempMap)) {
				firstMap = tempMap;
			}
		}
		List<String> resultList = Lists.newArrayList();
		for (String key : firstMap.keySet()) {
			resultList.add(key.trim());
		}
		return resultList;
	}

	/** -------------------------- 私有方法 end ------------------------------- */
	/** -------------------------- 公有方法 start ------------------------------- */

	/**
	 * @方法描述: 将某个字符串的首字母 大写
	 * @param str
	 * @return
	 */
	public static String convertInitialToUpperCase(String str) {
		if (str == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		char[] arr = str.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			char ch = arr[i];
			if (i == 0) {
				sb.append(String.valueOf(ch).trim().toUpperCase());
			} else {
				sb.append(ch);
			}
		}

		return sb.toString();
	}

	/**
	 * @方法描述:
	 *        <ul>
	 *        <li>汉字转换位汉语全拼</li>
	 *        <li>英文字符不变,特殊字符丢失,支持多音字</li>
	 *        </ul>
	 * 
	 *        <pre>
	 *        	举例说明：重当参:zhongdangcen,zhongdangcan,chongdangcen,chongdangshen,zhongdangshen,chongdangcan
	 *        </pre>
	 * 
	 * @param chinese
	 *            要转换的字符串
	 * @param isUpperCase
	 *            是否首字母大写
	 * @return
	 * @throws Exception
	 */
	public static List<String> converterToSpell(String chinese, boolean isUpperCase)
			throws Exception {
		if (StringUtils.isBlank(chinese)) {
			return null;
		}

		StringBuffer pinyinName = new StringBuffer();
		char[] nameChar = chinese.toCharArray();
		String[] resultArr = null;
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) { // 非ASCII码
				// 取得当前汉字的所有全拼
				resultArr = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], DEFAULTFORMAT);
				if (null != resultArr) {
					for (int j = 0; j < resultArr.length; j++) {
						if (isUpperCase) {
							pinyinName.append(convertInitialToUpperCase(resultArr[j]));
						} else {
							pinyinName.append(resultArr[j]);
						}

						if (j != resultArr.length - 1) {
							pinyinName.append(",");
						}
					}
				}
			} else {
				pinyinName.append(nameChar[i]);
			}
			pinyinName.append("--");
		}
		return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
	}

	/*-------------------------- 公有方法 end   -------------------------------*/
	public static void main(String[] args) throws Exception {
		String chinese = "重当参";
		System.out.println(converterToSpell(chinese, true));
	}
}
