package com.licf.bgManage.entity;

import com.common.base.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
@Table(name = "bg_employee")
public class BgEmployee extends BaseEntity {

    private static final long serialVersionUID = 16974874087544429L;

    /** ID主键自增 */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /** 姓名 */
    @Column
    private String employeeName;

    /** 手机号 */
    @Column
    private String phone;

    /** 账号 */
    @Column
    private String account;

    /** 密码 */
    @Column
    private String password;

    /** 所属部门id */
    @Column
    private Integer departmentId;

    /** 职位 */
    @Column
    private String position;

    /** 角色id */
    @Column
    private Integer roleId;

    /** 状态 */
    @Column
    private String status;

    /** 注释 */
    @Column
    private String comments;

    /** 删除标志 */
    @Column
    private Integer deleted;

    /** 创建时间 */
    @Column
    private LocalDateTime createTime;

    /** 创建人 */
    @Column
    private String createdBy;

    /** 更新时间 */
    @Column
    private LocalDateTime updateTime;

    /** 更新人 */
    @Column
    private String updatedBy;
}