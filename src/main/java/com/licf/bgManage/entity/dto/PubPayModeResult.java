package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;
import lombok.Data;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
@Data
public class PubPayModeResult extends BaseResult {

    private static final long serialVersionUID = 36786509525550433L;

    /** ID主键自增 */
    private Integer id;

    /** 角色名称 */
    private String roleName;

    /** 排序字段 */
    private Integer orderNum;
}