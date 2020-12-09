package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;
import com.common.view.Title;
import lombok.Data;

import java.util.List;

/**
 * 
 * @author lichunfeng
 * @date 2020-11-05 17:50:47
 */
@Data
public class PubModuleResult extends BaseResult {

    private static final long serialVersionUID = 43776292278863063L;

    @Title("ID")
    private Integer id;

    @Title("路径")
    private String path;

    private String href;

    @Title("名称")
    private String name;

    @Title("图标")
    private String icon;

    @Title("")
    private Integer parentId;

    private List<PubModuleResult> children;

}