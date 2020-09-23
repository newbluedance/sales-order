package com.licf.bgManage.entity;

import com.common.base.BaseEntity;

import java.math.BigDecimal;
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
@Table(name = "bg_goods")
public class BgGoods extends BaseEntity {

    private static final long serialVersionUID = 59925163806498899L;

    /** ID主键自增 */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /** 产品名称 */
    @Column
    private String goodsName;

    /** 产品编号 */
    @Column
    private String goodsNo;

    /** 类别 */
    @Column
    private String category;

    /** 规格 */
    @Column
    private String specifications;

    /** 单位 */
    @Column
    private String unit;

    /** 单价 */
    @Column
    private BigDecimal price;

    /** 库存 */
    @Column
    private BigDecimal stock;

    /** 描述 */
    @Column
    private String describ;

    /** 图片地址 */
    @Column
    private String mainImg;

    /** 用法用量 */
    @Column
    private String dosage;

    /** 主治功能 */
    @Column
    private String majorFunction;

    /** 产品成分 */
    @Column
    private String ingredients;

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