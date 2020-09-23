package com.licf.bgManage.entity;

import com.common.base.BaseEntity;
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
@Table(name = "pub_pay_mode")
public class PubPayMode extends BaseEntity {

    private static final long serialVersionUID = 41847211777284426L;

    /** ID主键自增 */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /** 角色名称 */
    @Column
    private String roleName;

    /** 排序字段 */
    @Column
    private Integer orderNum;
}