package com.cdeledu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdeledu.controller.BaseController;
import com.cdeledu.model.api.token.TokenEntity;
import com.cdeledu.service.api.token.TokenService;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 鉴权与授权
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年1月28日 下午4:56:19
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Controller
@RequestMapping("/api/token")
public class TokenController extends BaseController {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;
	@Autowired
	private TokenService tokenService;

	/** ----------------------------------------------------- Fields end */
	/**
	 * 
	 * @方法:获取token
	 * @创建人:独泪了无痕
	 * @param userId
	 * @return
	 */
	public TokenEntity getToken(long userId) {
		return tokenService.createToken(userId);
	}

}
