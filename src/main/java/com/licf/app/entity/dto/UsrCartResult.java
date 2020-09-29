package com.licf.app.entity.dto;

import com.common.base.BaseResult;
import com.licf.bgManage.entity.BgGoods;
import lombok.Data;

/**
 * 
 * @author lichunfeng
 * @date 2020-09-16 18:37:39
 */
@Data
public class UsrCartResult extends BaseResult {

    private static final long serialVersionUID = 33124488757434213L;

    /** 商品信息 */
    private Integer goodId;

    private BgGoods good;

    private int num;
}