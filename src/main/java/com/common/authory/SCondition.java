package com.common.authory;

import com.licf.bgManage.enums.PermitEnum;

import java.lang.annotation.*;

/**
 * @description:
 * @author: licf
 * @time: 2020/10/9 17:05
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SCondition {

    //条件
    String value() default "";

}
