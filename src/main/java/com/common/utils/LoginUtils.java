package com.common.utils;

import com.licf.bgManage.entity.BgEmployee;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author lichunfeng
 * @create 2019-09-06 10:34
 **/
public class LoginUtils {

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static BgEmployee getLoginUser() {
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            return (BgEmployee) servletRequestAttributes.getRequest().getAttribute("loginUser");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
