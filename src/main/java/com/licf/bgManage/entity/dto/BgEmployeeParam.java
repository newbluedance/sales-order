package com.licf.bgManage.entity.dto;

import com.common.base.BaseParam;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class BgEmployeeParam extends BaseParam {

    private static final long serialVersionUID = 15591357468439455L;

    /** ID主键自增 */
    private Integer id;

    /** 姓名 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "姓名过长")
    private String employeeName;

    /** 手机号 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "手机号过长")
    private String phone;

    /** 账号 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "账号过长")
    private String account;

    /** 密码 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "密码过长")
    private String password;

    /** 所属部门id */
    private Integer departmentId;

    /** 职位 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "职位过长")
    private String position;

    /** 角色id */
    private Integer roleId;

    /** 状态 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "状态过长")
    private String status;

    /** 注释 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "注释过长")
    private String comments;


}