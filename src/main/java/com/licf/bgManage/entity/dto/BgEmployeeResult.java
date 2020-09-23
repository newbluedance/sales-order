package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class BgEmployeeResult extends BaseResult {

    private static final long serialVersionUID = 52843144704117635L;

    /** ID主键自增 */
    private Integer id;

    /** 姓名 */
    private String employeeName;

    /** 手机号 */
    private String phone;

    /** 账号 */
    private String account;

    /** 密码 */
    private String password;

    /** 所属部门id */
    private Integer departmentId;

    /** 职位 */
    private String position;

    /** 角色id */
    private Integer roleId;

    /** 状态 */
    private String status;

    /** 注释 */
    private String comments;

   private String token;
}