package com.licf.bgManage.entity.dto;

import com.common.base.BaseParam;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author lichunfeng
 * @date 2020-09-29 18:34:34
 */
@Data
public class BgBannerParam extends BaseParam {

    private static final long serialVersionUID = 77023638625421596L;

    /** ID主键自增 */
    private Integer id;

    /** 图片 */
    private String img;

    /** 排序 */
    private Integer orderNum;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 创建人 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "创建人过长")
    private String createdBy;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 更新人 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "更新人过长")
    private String updatedBy;
}