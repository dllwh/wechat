package com.cdeledu.service.sys;

import java.util.List;

import com.cdeledu.common.base.BaseService;
import com.cdeledu.model.system.Dict;

public interface DictService extends BaseService<Dict> {
	public List<Dict> findAllList(Dict dict) throws Exception;
	public List<String> findTypeList(Dict dict) throws Exception;
}
