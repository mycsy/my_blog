package com.csy.csy_blog.service;

import com.csy.csy_blog.domain.Article;
import com.csy.csy_blog.pojo.BaseQuery;
import com.csy.csy_blog.pojo.QueryResult;

import java.util.List;

public interface ArticleService{

    /**
     * 分页查询文章
     * @return
     */
    QueryResult<Article> findPageInfo(BaseQuery query);

    /**
     * 查询热门文章
     * @param limit
     * @return
     */
    List<Article> findHotList(int limit);
}
