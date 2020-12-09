package com.licf.app.service;

import com.common.base.BaseService;
import com.licf.app.entity.UsrOrder;
import com.licf.app.entity.dto.*;
import com.licf.bgManage.enums.EOrderStatus;

/**
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
public interface UsrOrderService extends BaseService<UsrOrderParam, UsrOrderResult> {

    boolean add(UsrOrderParam param);

    boolean review(UsrOrderReview param, EOrderStatus curStatus);

    UsrOrder generateMailNo(UsrOrderDeliver param);

    boolean deliver(UsrOrderDeliver param);

    boolean writeOff(UsrOrderWriteOff param);
}