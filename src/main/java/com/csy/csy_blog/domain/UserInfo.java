package com.csy.csy_blog.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private Long id;//主键

    private String userName;//用户名

    private String password;//密码

    private String nickName;//用户昵称

    private Date createTime;//创建时间
}