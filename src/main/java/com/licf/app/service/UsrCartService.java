package com.licf.app.service;

import com.common.base.BaseService;
import com.common.net.RestResponse;
import com.licf.app.entity.dto.UsrCartParam;
import com.licf.app.entity.dto.UsrCartResult;
import com.licf.bgManage.entity.BgGoods;
import com.licf.bgManage.entity.dto.BgGoodsResult;

import java.util.List;

/**
 * 
 * @author lichunfeng
 * @date 2020-09-16 18:37:39
 */
public interface UsrCartService extends BaseService<UsrCartParam, UsrCartResult> {

    List<UsrCartResult> query();
    boolean save(UsrCartParam param);

}