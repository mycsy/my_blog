package com.csy.csy_blog.vomain;

import com.csy.csy_blog.domain.Article;
import com.csy.csy_blog.domain.Label;
import lombok.Data;

import java.util.List;

@Data
public class IndexVo {
    private List<ArticleVo> articleList;//文章集合
    private List<Label> labelList;//标签集合
    private List<Article> topList;//推荐文章
    private Article topArticle;//顶置文章
}
