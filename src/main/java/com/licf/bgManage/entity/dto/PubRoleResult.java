package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;
import com.licf.bgManage.enums.PermitEnum;
import lombok.Data;

/**
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
@Data
public class PubRoleResult extends BaseResult {

    private static final long serialVersionUID = 58412646753199080L;

    /**
     * ID主键自增
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    private PermitEnum[] permits;

    private Integer[] moduleIds;

    /**
     * 排序字段
     */
    private Integer orderNum;


 }