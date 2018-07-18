package com.cdeledu.service.cms;

import java.util.List;

import com.cdeledu.model.cms.notice.Article;

public interface NoticeService {
	List<Article> getArticleList(Article article) throws Exception;

	Integer getArticleCount(Article article);
}
