package com.csy.csy_blog.service.impl;

import com.csy.csy_blog.dao.ArticleCommentMapper;
import com.csy.csy_blog.domain.ArticleComment;
import com.csy.csy_blog.service.ArticleCommentService;
import com.csy.csy_blog.vomain.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {
    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Override
    public int insert(ArticleComment record) {
        return articleCommentMapper.insert(record);
    }

    @Override
    public List<CommentVo> findCommentListByArticleId(Long articleId) {
        return articleCommentMapper.findCommentListByArticleId(articleId);
    }
}
