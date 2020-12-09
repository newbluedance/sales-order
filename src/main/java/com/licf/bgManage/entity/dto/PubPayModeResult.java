package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;
import com.common.view.Title;
import lombok.Data;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
@Data
public class PubPayModeResult extends BaseResult {

    private static final long serialVersionUID = 36786509525550433L;

    @Title("ID")
    private Integer id;

    @Title("角色名称")
    private String roleName;

    @Title("排序字段")
    private Integer orderNum;
}