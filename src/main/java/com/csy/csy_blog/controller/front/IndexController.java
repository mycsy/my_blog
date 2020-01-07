package com.csy.csy_blog.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.csy.csy_blog.domain.Article;
import com.csy.csy_blog.pojo.BaseQuery;
import com.csy.csy_blog.pojo.Result;
import com.csy.csy_blog.service.ArticleService;
import com.csy.csy_blog.utils.ResultHelper;
import com.csy.csy_blog.vomain.IndexVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/front/index")
public class IndexController {
    @Autowired
    private ArticleService articleService;

    /**
     * 获取首页内容
     * @return
     */
    @GetMapping("/get")
    public JSONObject index() {
        Result result = new Result();
        result.setSuccess(true);
        try {
            IndexVo indexVo = new IndexVo();
            //获取热门文章集合
            List<Article> articleList = articleService.findHotList(5);
            indexVo.setArticleList(articleList);
            result.addModel("data", indexVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultHelper.renderAsJsonWipeData(result);
    }
}
