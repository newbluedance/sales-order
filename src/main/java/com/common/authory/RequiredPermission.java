package com.common.authory;

import java.lang.annotation.*;

/**
 * @description:
 * @author: licf
 * @time: 2020/10/9 17:05
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RequiredPermission {

    //角色
    String roles() default "";
    //权限
    String permits() default "";
}
