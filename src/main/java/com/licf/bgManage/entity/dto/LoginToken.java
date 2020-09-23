package com.licf.bgManage.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户名密码登录
 * @author lichunfeng
 */
@Data
public class LoginToken implements Serializable {
    private static final long serialVersionUID = 5300661373737222616L;

    private String loginName;
    private String password;
    private String captchaCode;
    private String captcha;
}
