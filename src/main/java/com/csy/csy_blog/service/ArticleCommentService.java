package com.csy.csy_blog.service;

import com.csy.csy_blog.domain.ArticleComment;
import com.csy.csy_blog.vomain.CommentVo;

import java.util.List;

public interface ArticleCommentService {

    int insert(ArticleComment record);

    /**
     * 查询文章评论集合
     * @param articleId
     * @return
     */
    List<CommentVo> findCommentListByArticleId(Long articleId);
}
