package com.csy.csy_blog.service;

import com.csy.csy_blog.domain.UserInfo;

public interface UserInfoService {

    /**
     * 用户注册
     * @param userInfo
     */
    Long register(UserInfo userInfo);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    UserInfo login(String username, String password);
}
