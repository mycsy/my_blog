package com.csy.csy_blog.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.csy.csy_blog.domain.ArticleComment;
import com.csy.csy_blog.pojo.Result;
import com.csy.csy_blog.service.ArticleCommentService;
import com.csy.csy_blog.utils.ResultHelper;
import com.csy.csy_blog.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/front/comment")
public class FrontArticleCommentController {
    @Autowired
    private ArticleCommentService articleCommentService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @RequestMapping("/create")
    public JSONObject create(ArticleComment articleComment) {
        Result result = new Result();
        result.setSuccess(true);
        try {
            articleComment.setId(snowflakeIdWorker.nextId());
            articleComment.setCreateTime(new Date());
            //添加评论
            articleCommentService.insert(articleComment);
            result.addModel("data", articleComment);
        } catch (Exception e) {

        }
        return ResultHelper.renderAsJsonWipeData(result);
    }
}
