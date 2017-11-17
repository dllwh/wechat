package com.cdeledu.controller.system.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdeledu.controller.BaseController;
import com.cdeledu.service.monitor.RedisService;

@Controller
@RequestMapping(value = "redis")
public class RedisController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	@Autowired
	private RedisService redisService;

	/** ----------------------------------------------------- Fields end */

	/**
	 * @方法描述: 获取redis服务器列表
	 * @return
	 */
	@RequestMapping(value = "/get-redis-list")
	public void getRedisList() {
		redisService.getRedisList();
	}

	/**
	 * @方法描述: 获取Redis信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "get-redis")
	public void getRedis(ModelMap modelMap, @RequestParam Long id) {
		redisService.getRedis(id);
	}

	/**
	 * @方法描述: 获取Redis详情
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "get-redis-detail")
	public void getRedisDetail(ModelMap modelMap, @RequestParam Long id) {
		redisService.getRedisDetail(id);
	}

	/**
	 * @方法描述: 获取redis服务器数量
	 * @return
	 */
	@RequestMapping(value = "/count-redis")
	public Integer countRedis() {
		return redisService.countRedis();
	}

	/**
	 * @方法描述: 获取Redis获取redis实时监控数据
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "get-redis-monitoring")
	public void getRedisMonitoring(ModelMap modelMap) {
		redisService.getRedisMonitoring();
	}

	/**
	 * @方法描述: 获取Redis获取redis历史图表数据
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "get-redis-status-history-chart")
	public void getRedisStatusHistoryChart(ModelMap modelMap, @RequestParam Long id,
			@RequestParam String timeRange) {
		redisService.getRedisStatusHistoryChart(id, timeRange);
	}

	/**
	 * @方法描述: 查询redis数据
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "query-redis-keys")
	public Object queryRedisKeys(ModelMap modelMap) {
		return null;
	}
	/** ----------------------------------------------- [私有方法] */
	/** ----------------------------------------------- [私有方法] */

	/** ----------------------------------------------- [测试方法] */
	/** ----------------------------------------------- [测试方法] */
}
