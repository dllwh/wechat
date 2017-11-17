package com.cdeledu.controller.system.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdeledu.controller.BaseController;
import com.cdeledu.service.monitor.MongodbService;

@Controller
@RequestMapping(value = "mongodb")
public class MongodbController extends BaseController {
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields start */
	@Autowired
	private MongodbService mongodbService;

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法描述: 获取MongoDB服务器列表
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "get-mongodb-list")
	public void getMongodbList(ModelMap modelMap) {
		mongodbService.getMongodbList();
	}

	/**
	 * @方法描述: 获取MongoDB服务器数量
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "count-mongodb")
	public Integer countMongodb(ModelMap modelMap) {
		return mongodbService.countMongodb();
	}

	/**
	 * @方法描述: 获取MongoDB服务器信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "get-mongodb")
	public void getMongodb(ModelMap modelMap, @RequestParam Long id) {
		mongodbService.getMongodb(id);
	}

	/**
	 * @方法描述: 获取MongoDB服务器概要信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "get-mongodb-overview")
	public void getMongodbOverview(ModelMap modelMap, @RequestParam Long id) {
		mongodbService.getMongodbOverview(id);
	}

	/**
	 * @方法描述: 获取MongoDB数据库列表
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "get-mongodb-listdatabases")
	public void getMongodbListDatabases(ModelMap modelMap, @RequestParam Long id) {
		mongodbService.getMongodbListDatabases(id);
	}
}
