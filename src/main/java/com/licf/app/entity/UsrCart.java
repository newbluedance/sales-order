package com.licf.app.entity;

import com.common.base.BaseEntity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 
 * @author lichunfeng
 * @date 2020-09-16 18:37:39
 */
@Data
@Table(name = "usr_cart")
public class UsrCart extends BaseEntity {

    private static final long serialVersionUID = 66910163744106222L;

    /** 员工id */
    @Id
    private Integer employeeId;

    /** 商品信息 */
    @Column
    private String goods;
}