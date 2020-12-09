package com.licf.bgManage.entity.dto;

import com.common.base.BaseParam;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.common.view.Title;

/**
 * 
 * @author lichunfeng
 * @date 2020-09-29 18:34:34
 */
@Data
public class BgBannerParam extends BaseParam {

    private static final long serialVersionUID = 77023638625421596L;

    @Title("ID")
    private Integer id;

    @Title("图片")
    private String img;

    @Title("排序")
    private Integer orderNum;

    @Title("创建时间")
    private LocalDateTime createTime;

    @Title("创建人")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "创建人过长")
    private String createdBy;

    @Title("更新时间")
    private LocalDateTime updateTime;

    @Title("更新人")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "更新人过长")
    private String updatedBy;
}