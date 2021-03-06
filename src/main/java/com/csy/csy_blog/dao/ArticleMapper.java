package com.csy.csy_blog.dao;

import com.csy.csy_blog.domain.Article;
import com.csy.csy_blog.vomain.ArticleByDateVo;
import com.csy.csy_blog.vomain.ArticleVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    int findPageCount(Map<String, Object> params);

    List<Article> findPageInfo(Map<String, Object> params);

    List<Article> findHotArticleList(int limit);

    void addArticleReadCount(Long articleId);

    List<Article> findTopList();

    Article findTopArticle();

    List<ArticleByDateVo> findArticleByDateList();

    int editArticle(ArticleVo vo);
}