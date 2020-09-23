package com.licf.app.service;

import com.common.base.BaseService;
import com.licf.app.entity.dto.UsrOrderParam;
import com.licf.app.entity.dto.UsrOrderResult;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
public interface UsrOrderService extends BaseService<UsrOrderParam, UsrOrderResult> {

    boolean add(UsrOrderParam param);
}