package com.csy.csy_blog.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.csy.csy_blog.domain.CommentReply;
import com.csy.csy_blog.pojo.Result;
import com.csy.csy_blog.service.CommentReplyService;
import com.csy.csy_blog.utils.ResultHelper;
import com.csy.csy_blog.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/front/reply")
public class FrontCommentReplyController {
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private CommentReplyService commentReplyService;

    @RequestMapping("/create")
    public JSONObject create(CommentReply commentReply) {
        Result result = new Result();
        result.setSuccess(true);
        try {
            commentReply.setId(snowflakeIdWorker.nextId());
            commentReply.setCreateTime(new Date());
            //添加回复
            commentReplyService.insert(commentReply);
            result.addModel("data", commentReply);
        } catch (Exception e) {

        }
        return ResultHelper.renderAsJsonWipeData(result);
    }
}
