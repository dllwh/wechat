package com.cdeledu.service.sys;

import java.util.List;

import com.cdeledu.common.base.BaseService;
import com.cdeledu.common.plugs.bootstrap.ZtreeNode;
import com.cdeledu.model.system.SysArea;

public interface SysAreaService extends BaseService<SysArea> {
	/** 属性菜单 */
	List<ZtreeNode> getSysAreaTree(int parentId);

	/** 获取子菜单 */
	List<SysArea> getArealistByParentCode(int parentId) throws Exception;

	/** 是否有叶子节点 */
	boolean hasChildren(int parentId);
}
