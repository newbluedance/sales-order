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
@Table(name = "pub_category")
public class PubCategory extends BaseEntity {

    private static final long serialVersionUID = 71901669601189040L;

    /** ID主键自增 */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /** 类别名称名称 */
    @Column
    private String categoryName;

    /** 父级 */
    @Column
    private Integer parentId;

    /** 排序字段 */
    @Column
    private Integer orderNum;
}