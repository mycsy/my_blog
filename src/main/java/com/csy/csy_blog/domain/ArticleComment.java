package com.csy.csy_blog.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleComment {
    private Long id;

    private Long articleId;

    private Long userId;

    private String comment;

    private Date createTime;

    private String nickName;
}