package com.licf.app.entity.dto;

import com.common.base.BaseParam;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class UsrOrderReview extends BaseParam {

    /** ID主键自增 */
    @NotNull(message = "id不能为空!")
    private Integer id;

    /** 审核结果 */
    private boolean pass;

    /** 原因 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "原因过长")
    private String reason;

}