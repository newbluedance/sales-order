package com.licf.app.entity.dto;

import com.common.view.Title;
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

    private Double weight;

    @Title("规格")
    private String specifications;

    @Title("单位")
    private String unit;

    public Double getWeight() {
        if (weight == null && com.common.utils.RedisHelper.getGood(getGoodId()) != null) {
            return com.common.utils.RedisHelper.getGood(getGoodId()).getWeight();
        }
        return weight;
    }


}
