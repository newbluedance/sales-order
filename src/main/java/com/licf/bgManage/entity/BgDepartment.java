package com.licf.bgManage.entity;

import com.common.base.BaseEntity;
import java.time.LocalDateTime;
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
@Table(name = "bg_department")
public class BgDepartment extends BaseEntity {

    private static final long serialVersionUID = 64200934660702616L;

    @Title("ID")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Title("部门名称")
    @Column
    private String departmentName;

    @Title("负责人姓名")
    @Column
    private String headName;

    @Title("负责人电话")
    @Column
    private String headMobile;

    @Title("部门地址")
    @Column
    private String departmentAddress;

    @Title("所属部门")
    @Column
    private Integer parentId;

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