package com.licf.bgManage.entity.dto;

import com.common.authory.SCondition;
import com.common.base.BaseParam;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.common.view.Title;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class BgEmployeeParam extends BaseParam {

    private static final long serialVersionUID = 15591357468439455L;

    @Title("ID")
    private Integer id;

    @Title("姓名")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "姓名过长")
    @SCondition("like")
    private String employeeName;

    @Title("手机号")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "手机号过长")
    @SCondition("like")
    private String phone;

    @Title("账号")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "账号过长")
    private String account;

    @Title("密码")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "密码过长")
    private String password;

    @Title("所属部门id")
    private Integer departmentId;

    @Title("职位")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "职位过长")
    private String position;

    @Title("角色id")
    private Integer[] roleIds;

    @Title("状态")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "状态过长")
    private String status;

    @Title("注释")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "注释过长")
    private String comments;

    private Integer deleted;

}