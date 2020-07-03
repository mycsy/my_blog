package com.csy.csy_blog.service;

import com.csy.csy_blog.domain.Article;
import com.csy.csy_blog.pojo.BaseQuery;
import com.csy.csy_blog.pojo.QueryResult;
import com.csy.csy_blog.vomain.ArticleByDateVo;
import com.csy.csy_blog.vomain.ArticleVo;

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
    List<ArticleVo> findHotList(int limit);

    /**
     * 创建文章
     * @param param
     */
    void create(ArticleVo param);

    /**
     * 查询文章详情
     * @param articleId
     * @return
     */
    ArticleVo findById(Long articleId);

    /**
     * 文章阅读数增加
     * @param articleId
     */
    void addArticleReadCount(Long articleId);

    /**
     * 查询推荐文章集合
     * @return
     */
    List<Article> findTopList();

    /**
     * 查询顶置文章
     * @return
     */
    Article findTopArticle();

    /**
     * 按时间归档文章
     */
    List<ArticleByDateVo> findArticleByDateList();

    /**
     * 编辑文章
     * @param param
     */
    void edit(ArticleVo param);
}
