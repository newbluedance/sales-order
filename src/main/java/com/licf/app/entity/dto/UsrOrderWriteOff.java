package com.licf.app.entity.dto;

import com.licf.bgManage.enums.EOrderStatus;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.common.view.Title;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class UsrOrderWriteOff {

    @Title("ID")
    @NotNull(message = "id不能为空!")
    private Integer id;

    @Title("状态")
    private EOrderStatus status;

    @Title("原因")
    @Length(max = 255, message = "原因过长")
    private String reason;

}