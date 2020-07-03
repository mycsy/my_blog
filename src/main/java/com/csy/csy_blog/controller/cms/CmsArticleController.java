package com.csy.csy_blog.controller.cms;

import com.alibaba.fastjson.JSONObject;
import com.csy.csy_blog.domain.Article;
import com.csy.csy_blog.pojo.BaseQuery;
import com.csy.csy_blog.pojo.QueryResult;
import com.csy.csy_blog.pojo.Result;
import com.csy.csy_blog.service.ArticleService;
import com.csy.csy_blog.utils.ResultHelper;
import com.csy.csy_blog.vomain.ArticleVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cms/article")
public class CmsArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 分页查询
     * @param query
     * @return
     */
    @RequestMapping("/page")
    public QueryResult<Article> findPageInfo(@RequestBody BaseQuery query) {
        return articleService.findPageInfo(query);
    }

    /**
     * 创建文章
     * @param param
     * @return
     */
    @RequestMapping("/create")
    public JSONObject create(@RequestBody ArticleVo param) {
        Result result = new Result();
        result.setSuccess(true);
        try {
            articleService.create(param);
        } catch (Exception e) {
            result.setError(result, "文章创建失败!", e);
        }
        return ResultHelper.renderAsJsonWipeData(result);
    }

    /**
     * 查询文章详情
     */
    @RequestMapping("/find/detail")
    public JSONObject create(Long articleId) {
        Result result = new Result();
        result.setSuccess(true);
        try {
            ArticleVo vo = articleService.findById(articleId);
            result.addModel("data", vo);
        } catch (Exception e) {
            result.setError(result, "文章查询失败!", e);
        }
        return ResultHelper.renderAsJsonWipeData(result);
    }
}
