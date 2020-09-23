package com.licf.bgManage.service.impl;

import com.common.base.BaseServiceImpl;
import com.licf.bgManage.entity.BgCustomer;
import com.licf.bgManage.entity.dto.BgCustomerParam;
import com.licf.bgManage.entity.dto.BgCustomerResult;
import com.licf.bgManage.mapper.BgCustomerMapper;
import com.licf.bgManage.mapperstruct.BgCustomerConverter;
import com.licf.bgManage.service.BgCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-20 20:02:46
 */
@Service
public class BgCustomerServiceImpl extends BaseServiceImpl<BgCustomer, BgCustomerParam, BgCustomerResult> implements BgCustomerService {

    @Autowired
    public void init(BgCustomerMapper bgCustomerMapper,BgCustomerConverter bgCustomerConverter){
        super.initMapperConverter(bgCustomerMapper, bgCustomerConverter);
    }
}