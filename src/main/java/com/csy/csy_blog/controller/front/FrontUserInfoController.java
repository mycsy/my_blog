package com.csy.csy_blog.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.csy.csy_blog.domain.Label;
import com.csy.csy_blog.domain.UserInfo;
import com.csy.csy_blog.pojo.Result;
import com.csy.csy_blog.service.UserInfoService;
import com.csy.csy_blog.utils.ResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/front/user")
public class FrontUserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户自注册
     */
    @RequestMapping("/register")
    public JSONObject register(UserInfo userInfo) {
        Result result = new Result();
        result.setSuccess(true);
        try {
            Long userId = userInfoService.register(userInfo);
            userInfo.setId(userId);
            result.addModel("user_info", userInfo);
        } catch (Exception e) {

        }
        return ResultHelper.renderAsJsonWipeData(result);
    }

    /**
     * 用户登录
     */
    @RequestMapping("/login")
    public JSONObject login(String username, String password) {
        Result result = new Result();
        result.setSuccess(true);
        try {
            UserInfo userInfo = userInfoService.login(username, password);
            result.addModel("user_info", userInfo);
        } catch (Exception e) {

        }
        return ResultHelper.renderAsJsonWipeData(result);
    }
}
