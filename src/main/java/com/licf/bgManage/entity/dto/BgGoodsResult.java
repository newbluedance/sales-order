package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.common.view.Title;
import lombok.Data;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class BgGoodsResult extends BaseResult {

    private static final long serialVersionUID = 24900813282761413L;

    @Title("ID")
    private Integer id;

    @Title("产品名称")
    private String goodsName;

    @Title("产品编号")
    private String goodsNo;

    @Title("类别")
    private String category;

    @Title("规格")
    private String specifications;

    @Title("单位")
    private String unit;

    private Double weight;

    @Title("单价")
    private BigDecimal price;

    @Title("库存")
    private BigDecimal stock;

    @Title("描述")
    private String describ;

    @Title("图片地址")
    private String mainImg;

    @Title("用法用量")
    private String dosage;

    @Title("主治功能")
    private String majorFunction;

    @Title("产品成分")
    private String ingredients;

    @Title("状态")
    private String status;

    @Title("注释")
    private String comments;

    @Title("删除标志")
    private Integer deleted;

    @Title("创建时间")
    private LocalDateTime createTime;

    @Title("创建人")
    private String createdBy;

    @Title("更新时间")
    private LocalDateTime updateTime;

    @Title("更新人")
    private String updatedBy;
}