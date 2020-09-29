package com.licf.app.service;

import com.common.base.BaseService;
import com.licf.app.entity.dto.*;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
public interface UsrOrderService extends BaseService<UsrOrderParam, UsrOrderResult> {

    boolean add(UsrOrderParam param);

    boolean review(UsrOrderReview param);

    boolean deliver(UsrOrderDeliver param);

    boolean writeOff(UsrOrderWriteOff param);
}