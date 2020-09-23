package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;
import lombok.Data;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
@Data
public class PubCategoryResult extends BaseResult {

    private static final long serialVersionUID = 71244753321940153L;

    /** ID主键自增 */
    private Integer id;

    /** 类别名称名称 */
    private String categoryName;

    /** 父级 */
    private Integer parentId;

    /** 排序字段 */
    private Integer orderNum;
}