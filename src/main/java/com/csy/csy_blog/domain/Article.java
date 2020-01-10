package com.csy.csy_blog.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Article {
    private Long id;

    private String articleTitle;

    private String articleIntroduction;

    private Date articleDate;

    private Integer articleViews;

    private Integer articleLikeCount;

    private Integer articleCommentCount;

    private String articleImageUrl;

    private String articleContent;
}