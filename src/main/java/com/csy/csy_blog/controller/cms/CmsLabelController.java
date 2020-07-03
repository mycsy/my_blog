package com.csy.csy_blog.controller.cms;

import com.alibaba.fastjson.JSONObject;
import com.csy.csy_blog.domain.Article;
import com.csy.csy_blog.domain.Label;
import com.csy.csy_blog.pojo.BaseQuery;
import com.csy.csy_blog.pojo.QueryResult;
import com.csy.csy_blog.pojo.Result;
import com.csy.csy_blog.service.LabelService;
import com.csy.csy_blog.utils.ResultHelper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cms/label")
public class CmsLabelController {
    @Autowired
    private LabelService labelService;

    /**
     * 分页查询标签
     * @param query
     * @return
     */
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
            result.setError(result, "标签创建失败!", e);
        }
        return ResultHelper.renderAsJsonWipeData(result);
    }

    @RequestMapping("/find/all")
    public JSONObject findAll() {
        Result result = new Result();
        result.setSuccess(true);
        try {
            List<Label> labelList = labelService.findAllLabel();
            result.addModel("data", labelList);
        } catch (Exception e) {
            result.setError(result, "标签查询失败!", e);
        }
        return ResultHelper.renderAsJsonWipeData(result);
    }}
