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
@Table(name = "bg_department")
public class BgDepartment extends BaseEntity {

    private static final long serialVersionUID = 64200934660702616L;

    /** ID主键自增 */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /** 部门名称 */
    @Column
    private String departmentName;

    /** 负责人姓名 */
    @Column
    private String headName;

    /** 负责人电话 */
    @Column
    private String headMobile;

    /** 部门地址 */
    @Column
    private String departmentAddress;

    /** 所属部门 */
    @Column
    private Integer parentId;

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