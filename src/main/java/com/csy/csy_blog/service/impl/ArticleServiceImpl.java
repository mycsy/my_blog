package com.csy.csy_blog.service.impl;

import com.csy.csy_blog.dao.ArticleMapper;
import com.csy.csy_blog.domain.Article;
import com.csy.csy_blog.pojo.BaseQuery;
import com.csy.csy_blog.pojo.QueryResult;
import com.csy.csy_blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public QueryResult<Article> findPageInfo(BaseQuery query) {
        QueryResult<Article> result = new QueryResult<Article>();
        result.setQuery(query);
        Map<String, Object> params = result.getQuery().build();
        int amount = articleMapper.findPageCount(params);
        result.setTotalRecord(amount);
        if (amount == 0) {
            return result;
        }
        List<Article> list = articleMapper.findPageInfo(params);
        if (!CollectionUtils.isEmpty(list)) {
            result.setResultList(list);
        }
        return result;
    }

    /**
     * 查询热门文章
     * @param limit
     * @return
     */
    @Override
    public List<Article> findHotList(int limit) {
        List<Article> list = articleMapper.findHotArticleList(limit);
        return list;
    }
}
