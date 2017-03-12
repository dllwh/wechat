package com.cdeledu.controller.system.dict;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdeledu.controller.BaseController;

/**
 * @类描述: 数据字典控制类:数据操作层(对数据的增、删、改)
 * @创建者: 皇族灬战狼
 * @创建时间: 2016年3月12日 上午10:36:33
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping(value = "DictOperate")
@Transactional(readOnly = false)
public class DictOperateController extends BaseController {
	private static final long serialVersionUID = 1L;

}
