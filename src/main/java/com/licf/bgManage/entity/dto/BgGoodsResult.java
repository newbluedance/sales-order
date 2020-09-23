package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class BgGoodsResult extends BaseResult {

    private static final long serialVersionUID = 24900813282761413L;

    /** ID主键自增 */
    private Integer id;

    /** 产品名称 */
    private String goodsName;

    /** 产品编号 */
    private String goodsNo;

    /** 类别 */
    private String category;

    /** 规格 */
    private String specifications;

    /** 单位 */
    private String unit;

    /** 单价 */
    private BigDecimal price;

    /** 库存 */
    private BigDecimal stock;

    /** 描述 */
    private String describ;

    /** 图片地址 */
    private String mainImg;

    /** 用法用量 */
    private String dosage;

    /** 主治功能 */
    private String majorFunction;

    /** 产品成分 */
    private String ingredients;

    /** 状态 */
    private String status;

    /** 注释 */
    private String comments;

    /** 删除标志 */
    private Integer deleted;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 创建人 */
    private String createdBy;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 更新人 */
    private String updatedBy;
}