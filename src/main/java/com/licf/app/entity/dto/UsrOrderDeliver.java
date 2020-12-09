package com.licf.app.entity.dto;

import com.common.base.BaseParam;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
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
public class UsrOrderDeliver {

    @Title("ID")
    @NotNull(message = "id不能为空!")
    private Integer id;

    @Title("拒绝")
    private boolean reject;

    private Integer logisticsId;

    private String logisticsName;

    private String logisticsNo;

    @Title("原因")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "原因过长")
    private String reason;

}