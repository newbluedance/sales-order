package com.licf.bgManage.entity;

import com.common.base.BaseEntity;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import com.common.view.Title;
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

    @Title("ID")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Title("姓名")
    @Column
    private String employeeName;

    @Title("手机号")
    @Column
    private String phone;

    @Title("账号")
    @Column
    private String account;

    @Title("密码")
    @Column
    private String password;

    @Title("所属部门id")
    @Column
    private Integer departmentId;

    @Title("职位")
    @Column
    private String position;

    @Title("角色id")
    @Column
    private Integer[] roleIds;

    @Title("状态")
    @Column
    private String status;

    @Title("注释")
    @Column
    private String comments;

    @Title("删除标志")
    @Column
    private Integer deleted;

    @Title("创建时间")
    @Column
    private LocalDateTime createTime;

    @Title("创建人")
    @Column
    private String createdBy;

    @Title("更新时间")
    @Column
    private LocalDateTime updateTime;

    @Title("更新人")
    @Column
    private String updatedBy;
}