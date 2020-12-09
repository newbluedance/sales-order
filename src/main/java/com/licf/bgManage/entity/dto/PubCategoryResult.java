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
public class PubCategoryResult extends BaseResult {

    private static final long serialVersionUID = 71244753321940153L;

    @Title("ID")
    private Integer id;

    @Title("类别名称名称")
    private String categoryName;

    @Title("父级")
    private Integer parentId;

    @Title("排序字段")
    private Integer orderNum;
}