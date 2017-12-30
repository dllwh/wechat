package com.cdeledu.service.sys;

import java.util.List;
import java.util.Map;

import com.cdeledu.common.base.BaseService;
import com.cdeledu.model.system.SysArea;

public interface SysAreaService extends BaseService<SysArea> {
	/** 属性菜单 */
	List<Map<String, Object>> getSysAreaTree() throws Exception;

	/** 获取子菜单 */
	List<SysArea> getArealistByParentCode(int parentId) throws Exception;

	/** 是否有叶子节点 */
	boolean hasChildren(int parentId) throws Exception;
}
