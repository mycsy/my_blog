package com.csy.csy_blog.domain;

import lombok.Data;

import java.util.Date;

@Data
public class CommentReply {
    private Long id;

    private Long articleId;

    private Long commentId;

    private Long fromId;

    private Long toId;

    private String content;

    private Date createTime;

    private String fromName;

    private String toName;
}