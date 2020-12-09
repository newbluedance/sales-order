package com.licf.bgManage.entity.dto;

import com.common.base.BaseParam;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.common.view.Title;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class PubCategoryParam extends BaseParam {

    private static final long serialVersionUID = 79060070104423738L;

    @Title("ID")
    private Integer id;

    @Title("类别名称名称")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "类别名称名称过长")
    private String categoryName;

    @Title("父级")
    private Integer parentId;

    @Title("排序字段")
    private Integer orderNum;
}