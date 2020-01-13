package com.csy.csy_blog.controller.cms;

import com.alibaba.fastjson.JSONObject;
import com.csy.csy_blog.domain.Article;
import com.csy.csy_blog.domain.Label;
import com.csy.csy_blog.pojo.BaseQuery;
import com.csy.csy_blog.pojo.QueryResult;
import com.csy.csy_blog.pojo.Result;
import com.csy.csy_blog.service.LabelService;
import com.csy.csy_blog.utils.ResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @RequestMapping("/page")
    public QueryResult<Label> findPageInfo(@RequestBody BaseQuery query) {
        return labelService.findPageInfo(query);
    }

    /**
     * 创建标签
     * @param label
     * @return
     */
    @RequestMapping("/create")
    public JSONObject create(@RequestBody Label label) {
        Result result = new Result();
        result.setSuccess(true);
        try {
            labelService.create(label);
        } catch (Exception e) {

        }
        return ResultHelper.renderAsJsonWipeData(result);
    }
}
