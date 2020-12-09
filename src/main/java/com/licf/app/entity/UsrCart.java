package com.licf.app.entity;

import com.common.base.BaseEntity;
import javax.persistence.Column;
import com.common.view.Title;
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

    @Title("员工id")
    @Id
    private Integer employeeId;

    @Title("商品信息")
    @Column
    private String goods;
}