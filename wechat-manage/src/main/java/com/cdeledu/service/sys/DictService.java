package com.cdeledu.service.sys;

import java.util.List;

import com.cdeledu.common.base.BaseService;
import com.cdeledu.model.system.SysDict;

public interface DictService extends BaseService<SysDict> {
	public List<SysDict> findAllList(SysDict dict) throws Exception;
	public List<String> findTypeList(SysDict dict) throws Exception;
}
