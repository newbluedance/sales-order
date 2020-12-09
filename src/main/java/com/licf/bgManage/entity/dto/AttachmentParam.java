package com.licf.bgManage.entity.dto;

import com.common.base.BaseParam;
import com.common.validation.group.Add;
import com.common.validation.group.Select;
import com.common.validation.group.Update;
import com.common.view.Title;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lichunfeng
 * @date 2019-06-21 10:51:42
 */
@Data
public class AttachmentParam extends BaseParam {

    private static final long serialVersionUID = 50326363026845437L;

    @Title("ID")
    private Integer id;

    @Title("对应数据所在模块")
    @NotNull(groups = {Select.class, Add.class, Update.class}, message = "moduleName")
    private String moduleName;

    @Title("对应数据编号(有一个或多个其他关联id组成)")
    @NotNull(groups = {Select.class, Add.class, Update.class}, message = "moduleDataCode")
    private String moduleDataCode;

    @Title("备注")
    private String remark;

}