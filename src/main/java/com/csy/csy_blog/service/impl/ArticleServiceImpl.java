package com.csy.csy_blog.service.impl;

import com.csy.csy_blog.dao.ArticleLabelMapper;
import com.csy.csy_blog.dao.ArticleMapper;
import com.csy.csy_blog.dao.LabelMapper;
import com.csy.csy_blog.domain.Article;
import com.csy.csy_blog.domain.ArticleLabel;
import com.csy.csy_blog.pojo.BaseQuery;
import com.csy.csy_blog.pojo.QueryResult;
import com.csy.csy_blog.service.ArticleService;
import com.csy.csy_blog.service.LabelService;
import com.csy.csy_blog.utils.SnowflakeIdWorker;
import com.csy.csy_blog.vomain.ArticleVo;
import com.csy.csy_blog.vomain.LabelVo;
import org.hibernate.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private LabelService labelService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private ArticleLabelMapper articleLabelMapper;

    @Override
    public QueryResult<Article> findPageInfo(BaseQuery query) {
        QueryResult<Article> result = new QueryResult<Article>();
        result.setQuery(query);
        Map<String, Object> params = result.getQuery().build();
        int amount = articleMapper.findPageCount(params);
        result.setTotal(amount);
        if (amount == 0) {
            return result;
        }
        List<Article> list = articleMapper.findPageInfo(params);
        if (!CollectionUtils.isEmpty(list)) {
            result.setRows(list);
        }
        return result;
    }

    /**
     * 查询热门文章
     * @param limit
     * @return
     */
    @Override
    public List<ArticleVo> findHotList(int limit) {
        List<Article> list = articleMapper.findHotArticleList(limit);
        //填充标签
        List<ArticleVo> voList = labelService.fillAutoLabelForArticle(list);
        return voList;
    }

    /**
     * 创建文章
     * @param param
     */
    @Override
    public void create(ArticleVo param) {
        Article article = new Article();
        Date now = new Date();
        Long articleId = snowflakeIdWorker.nextId();
        BeanUtils.copyProperties(param, article);
        article.setId(articleId);
        article.setArticleDate(now);
        article.setArticleCommentCount(0);
        article.setArticleLikeCount(0);
        article.setArticleViews(0);
        //文章标签关联表
        List<ArticleLabel> als = new ArrayList<>();
        for (LabelVo vo : param.getLabelList()) {
            ArticleLabel al = new ArticleLabel();
            al.setId(snowflakeIdWorker.nextId());
            al.setArticleId(articleId);
            al.setLabelId(vo.getId());
            als.add(al);
        }
        //插入
        articleMapper.insert(article);
        articleLabelMapper.insertBatch(als);

    }
}
