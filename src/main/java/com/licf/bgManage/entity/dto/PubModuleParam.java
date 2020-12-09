package com.licf.bgManage.entity.dto;

import com.common.base.BaseParam;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.common.view.Title;

/**
 * 
 * @author lichunfeng
 * @date 2020-11-05 17:50:46
 */
@Data
public class PubModuleParam extends BaseParam {

    private static final long serialVersionUID = 16633898035302018L;

    @Title("ID")
    private Integer id;

    @Title("路径")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "路径过长")
    private String path;

    private String href;

    @Title("名称")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "名称过长")
    private String name;

    @Title("图标")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "图标过长")
    private String icon;

    @Title("")
    private Integer parentId;

    @Title("创建时间")
    private LocalDateTime createTime;

    @Title("创建人")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "创建人过长")
    private String createdBy;

    @Title("更新时间")
    private LocalDateTime updateTime;

    @Title("更新人")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "更新人过长")
    private String updatedBy;
}