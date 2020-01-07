package com.csy.csy_blog.vomain;

import com.csy.csy_blog.domain.Article;
import lombok.Data;

import java.util.List;

@Data
public class IndexVo {
    List<Article> articleList;//文章集合
}
