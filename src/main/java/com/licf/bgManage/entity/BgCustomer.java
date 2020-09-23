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
@Table(name = "bg_customer")
public class BgCustomer extends BaseEntity {

    private static final long serialVersionUID = 15972091288565095L;

    /** ID主键自增 */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /** 客户名称 */
    @Column
    private String customerName;

    /** 手机号 */
    @Column
    private String phone;

    /** 地址 */
    @Column
    private String address;

    /** 所属部门id */
    @Column
    private Integer departmentId;

    /** 业务员 */
    @Column
    private String salesman;

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