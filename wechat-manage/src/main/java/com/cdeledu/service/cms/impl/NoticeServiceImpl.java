package com.cdeledu.service.cms.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.model.cms.notice.Article;
import com.cdeledu.service.cms.NoticeService;

@Service
@SuppressWarnings("unchecked")
public class NoticeServiceImpl implements NoticeService {
	/** ----------------------------------------------------- Fields start */
	@Resource
	private BaseDaoSupport<?> baseDao;
	private static final String PREFIX = "com.cdeledu.dao.impl.cms.ArticleDaoImpl.";

	/** ----------------------------------------------------- Fields end */
	@Override
	public List<Article> getArticleList(Article article) throws Exception {
		return (List<Article>) baseDao.findListForJdbcParam(PREFIX + "getArticleList", article);
	}

	@Override
	public Integer getArticleCount(Article article) {
		return null;
	}
}