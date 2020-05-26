package com.csy.csy_blog.service.impl;

import com.csy.csy_blog.dao.CommentReplyMapper;
import com.csy.csy_blog.domain.CommentReply;
import com.csy.csy_blog.service.CommentReplyService;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentReplyServiceImpl implements CommentReplyService {
    @Autowired
    private CommentReplyMapper commentReplyMapper;

    @Override
    public int insert(CommentReply record) {
        return commentReplyMapper.insert(record);
    }
}
