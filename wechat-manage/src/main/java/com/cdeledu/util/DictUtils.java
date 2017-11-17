package com.cdeledu.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cdeledu.common.mapper.JsonMapper;
import com.cdeledu.core.listener.DictListener;
import com.cdeledu.model.system.SysDict;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @类描述: 字典工具类
 * @创建者: 皇族灬战狼
 * @创建时间: 2017年2月24日 下午12:15:38
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class DictUtils {
	/** ----------------------------------------------------- Fields start */

	public static final String CACHE_DICT_MAP = "dictMap";

	/** ----------------------------------------------------- Fields end */

	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/**
	 * @方法描述: 获取字典值
	 * @param label
	 * @param type
	 * @param defaultLabel
	 * @return
	 */
	public static String getDictValue(String code, String defaultValue) {
		if (StringUtils.isNotBlank(code)) {
			for (SysDict dict : DictListener.dictsList) {
				if (code.equals(dict.getItemCode())) {
					return dict.getItemName();
				}
			}
		}
		return defaultValue;
	}

	/**
	 * @方法描述: 获取字典对象列表
	 * @param type
	 * @return
	 */
	public static List<SysDict> getDictList(String type) {
		Map<String, List<SysDict>> dictMap = null;
		if (dictMap == null) {
			dictMap = Maps.newHashMap();
		}
		List<SysDict> dictList = dictMap.get(type);
		if (dictList == null) {
			dictList = Lists.newArrayList();

		}
		return dictList;
	}

	/**
	 * @方法描述: 返回字典列表（JSON）
	 * @param type
	 * @return
	 */
	public static String getDictListJson(String type) {
		return JsonMapper.toJsonString(getDictList(type));
	}
}
