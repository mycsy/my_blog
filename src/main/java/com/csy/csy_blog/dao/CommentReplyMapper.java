package com.csy.csy_blog.dao;

import com.csy.csy_blog.domain.CommentReply;

public interface CommentReplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommentReply record);

    int insertSelective(CommentReply record);

    CommentReply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommentReply record);

    int updateByPrimaryKey(CommentReply record);
}