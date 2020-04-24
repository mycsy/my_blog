package com.csy.csy_blog.vomain;

import lombok.Data;

@Data
public class ArticleByDateVo {
    private String yearMonth;//文章时间 年-月
    private int articleCount;//文章数量
}
