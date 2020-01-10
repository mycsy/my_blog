package com.csy.csy_blog.service;

import com.csy.csy_blog.domain.Article;
import com.csy.csy_blog.domain.Label;
import com.csy.csy_blog.vomain.ArticleVo;

import java.util.List;

public interface LabelService {

    /**
     * 为文章填充标签
     * @param articleList
     * @return
     */
    List<ArticleVo> fillAutoLabelForArticle(List<Article> articleList);

    /**
     * 查询所有标签
     */
    List<Label> findAllLabel();
}
