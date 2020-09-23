package com.licf.bgManage.service.impl;

import com.common.base.BaseServiceImpl;
import com.licf.bgManage.entity.BgGoods;
import com.licf.bgManage.entity.dto.BgGoodsParam;
import com.licf.bgManage.entity.dto.BgGoodsResult;
import com.licf.bgManage.mapper.BgGoodsMapper;
import com.licf.bgManage.mapperstruct.BgGoodsConverter;
import com.licf.bgManage.service.BgGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-20 20:02:46
 */
@Service
public class BgGoodsServiceImpl extends BaseServiceImpl<BgGoods, BgGoodsParam, BgGoodsResult> implements BgGoodsService {
    @Autowired
    public void init(BgGoodsMapper bgCustomerMapper, BgGoodsConverter bgCustomerConverter){
        super.initMapperConverter(bgCustomerMapper, bgCustomerConverter);
    }
}