package com.csy.csy_blog.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.csy.csy_blog.aspect.ArticleReadCount;
import com.csy.csy_blog.pojo.Result;
import com.csy.csy_blog.service.ArticleService;
import com.csy.csy_blog.utils.ResultHelper;
import com.csy.csy_blog.vomain.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;

@RestController
@RequestMapping("/front/article")
public class FrontArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 查询文章全文
     * @param article_id
     * @return
     */
    @ArticleReadCount
    @RequestMapping("/find/detail")
    public JSONObject findDetail(Long article_id) {
        Result result = new Result();
        result.setSuccess(true);
        try {
            ArticleVo articleVo = articleService.findById(article_id);
            result.addModel("data", articleVo);
        } catch (Exception e) {

        }
        return ResultHelper.renderAsJsonWipeData(result);
    }
}
