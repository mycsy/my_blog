package com.csy.csy_blog.domain;

import lombok.Data;

@Data
public class Label {
    private Long id;

    private String labelName;

    private String labelDescription;

    private int articleCount;//包含的文章数
}