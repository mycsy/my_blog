package com.csy.csy_blog.vomain;

import com.csy.csy_blog.domain.Article;
import com.csy.csy_blog.domain.Label;
import lombok.Data;

import java.util.List;

@Data
public class IndexVo {
    List<ArticleVo> articleList;//文章集合
    List<Label> labelList;//标签集合
}
