package com.licf.app.entity.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class UsrOrderWriteOff {

    /** ID主键自增 */
    @NotNull(message = "id不能为空!")
    private Integer id;

    /** 状态 */
    private EOrderStatus status;

    /** 原因 */
    @Length(max = 255, message = "原因过长")
    private String reason;

}