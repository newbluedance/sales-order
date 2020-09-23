package com.licf.bgManage.service.impl;

import com.common.base.BaseServiceImpl;
import com.licf.bgManage.entity.PubPayMode;
import com.licf.bgManage.entity.dto.PubPayModeParam;
import com.licf.bgManage.entity.dto.PubPayModeResult;
import com.licf.bgManage.mapper.PubPayModeMapper;
import com.licf.bgManage.mapperstruct.PubPayModeConverter;
import com.licf.bgManage.service.PubPayModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-20 20:02:46
 */
@Service
public class PubPayModeServiceImpl extends BaseServiceImpl<PubPayMode, PubPayModeParam, PubPayModeResult> implements PubPayModeService {
    @Autowired
    public void init(PubPayModeMapper bgCustomerMapper, PubPayModeConverter bgCustomerConverter){
        super.initMapperConverter(bgCustomerMapper, bgCustomerConverter);
    }
}