package com.csy.csy_blog.domain;

import lombok.Data;

@Data
public class ArticleLabel {
    private Long id;

    private Long articleId;

    private Long labelId;
}