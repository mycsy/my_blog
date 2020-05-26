package com.csy.csy_blog.dao;

import com.csy.csy_blog.domain.ArticleComment;
import com.csy.csy_blog.vomain.CommentVo;

import java.util.List;

public interface ArticleCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleComment record);

    int insertSelective(ArticleComment record);

    ArticleComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleComment record);

    int updateByPrimaryKey(ArticleComment record);

    List<CommentVo> findCommentListByArticleId(Long ArticleId);
}