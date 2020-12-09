package com.licf.bgManage.entity;

import com.common.base.BaseEntity;

import java.math.BigDecimal;
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
@Table(name = "bg_goods")
public class BgGoods extends BaseEntity {

    private static final long serialVersionUID = 59925163806498899L;

    @Title("ID")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Title("产品名称")
    @Column
    private String goodsName;

    @Title("产品编号")
    @Column
    private String goodsNo;

    @Title("类别")
    @Column
    private String category;

    @Title("规格")
    @Column
    private String specifications;

    @Title("单位")
    @Column
    private String unit;

    @Column
    private Double weight;

    @Title("单价")
    @Column
    private BigDecimal price;

    @Title("库存")
    @Column
    private BigDecimal stock;

    @Title("描述")
    @Column
    private String describ;

    @Title("图片地址")
    @Column
    private String mainImg;

    @Title("用法用量")
    @Column
    private String dosage;

    @Title("主治功能")
    @Column
    private String majorFunction;

    @Title("产品成分")
    @Column
    private String ingredients;

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