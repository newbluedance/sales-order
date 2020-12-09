package com.licf.bgManage.entity.dto;

import com.common.authory.SCondition;
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
 * @date 2020-08-24 18:34:54
 */
@Data
public class BgDepartmentParam extends BaseParam {

    private static final long serialVersionUID = 73516899560028739L;

    @Title("ID")
    private Integer id;

    @Title("部门名称")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "部门名称过长")
    @SCondition("like")
    private String departmentName;

    @Title("负责人姓名")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "负责人姓名过长")
    private String headName;

    @Title("负责人电话")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "负责人电话过长")
    private String headMobile;

    @Title("部门地址")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "部门地址过长")
    private String departmentAddress;

    @Title("所属部门")
    private Integer parentId;

    @Title("注释")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "注释过长")
    private String comments;

    private Integer deleted;

}