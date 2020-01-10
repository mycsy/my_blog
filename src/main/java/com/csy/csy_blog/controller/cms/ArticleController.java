package com.csy.csy_blog.controller.cms;

import com.csy.csy_blog.domain.Article;
import com.csy.csy_blog.pojo.BaseQuery;
import com.csy.csy_blog.pojo.QueryResult;
import com.csy.csy_blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cms/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/page")
    public QueryResult<Article> findPageInfo(@RequestBody BaseQuery query) {
        return articleService.findPageInfo(query);
    }
}
