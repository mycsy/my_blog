package com.csy.csy_blog.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.csy.csy_blog.domain.Label;
import com.csy.csy_blog.pojo.Result;
import com.csy.csy_blog.service.LabelService;
import com.csy.csy_blog.utils.ResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/front/label")
public class FrontLabelController {
    @Autowired
    private LabelService labelService;

    /**
     * 获取所有标签
     */
    @RequestMapping("/find/all")
    public JSONObject findAllLabel() {
        Result result = new Result();
        result.setSuccess(true);
        try {
            List<Label> allLabel = labelService.findAllLabel();
            result.addModel("data", allLabel);
        } catch (Exception e) {

        }
        return ResultHelper.renderAsJsonWipeData(result);
    }
}
