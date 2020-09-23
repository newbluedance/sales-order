package com.licf.app.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 订单商品快照dto
 * @author: licf
 * @time: 2020/9/4 11:07
 */
@Data
public class UsrOrderGoodsShot {

    private Integer goodId;

    private String image;

    private String goodName;

    private BigDecimal goodPrice;

    private Integer num;
}
