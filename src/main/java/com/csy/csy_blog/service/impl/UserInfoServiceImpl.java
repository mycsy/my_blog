package com.csy.csy_blog.service.impl;

import com.csy.csy_blog.dao.UserInfoMapper;
import com.csy.csy_blog.domain.UserInfo;
import com.csy.csy_blog.service.UserInfoService;
import com.csy.csy_blog.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public Long register(UserInfo userInfo) {
        Long userId = snowflakeIdWorker.nextId();
        userInfo.setId(userId);
        userInfoMapper.insert(userInfo);
        return userId;
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public UserInfo login(String username, String password) {
        return userInfoMapper.login(username, password);
    }
}
