package com.csy.csy_blog.dao;

import com.csy.csy_blog.domain.ArticleLabel;

import java.util.List;

public interface ArticleLabelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleLabel record);

    int insertSelective(ArticleLabel record);

    ArticleLabel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleLabel record);

    int updateByPrimaryKey(ArticleLabel record);

    void insertBatch(List<ArticleLabel> list);

    void deleteByArticleId(Long articleId);
}