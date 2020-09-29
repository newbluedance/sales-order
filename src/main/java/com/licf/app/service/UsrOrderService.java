package com.licf.app.service;

import com.common.base.BaseService;
import com.licf.app.entity.dto.UsrOrderDeliver;
import com.licf.app.entity.dto.UsrOrderParam;
import com.licf.app.entity.dto.UsrOrderResult;
import com.licf.app.entity.dto.UsrOrderReview;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
public interface UsrOrderService extends BaseService<UsrOrderParam, UsrOrderResult> {

    boolean add(UsrOrderParam param);

    boolean review(UsrOrderReview param);

    boolean deliver(UsrOrderDeliver param);
}