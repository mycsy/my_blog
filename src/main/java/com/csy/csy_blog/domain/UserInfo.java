package com.csy.csy_blog.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private Long id;

    private String userName;

    private String passward;

    private String nickName;

    private Date createTime;
}