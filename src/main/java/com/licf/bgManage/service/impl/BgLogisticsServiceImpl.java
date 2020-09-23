package com.licf.bgManage.service.impl;

import com.common.base.BaseServiceImpl;
import com.licf.bgManage.entity.BgLogistics;
import com.licf.bgManage.entity.dto.BgLogisticsParam;
import com.licf.bgManage.entity.dto.BgLogisticsResult;
import com.licf.bgManage.mapper.BgLogisticsMapper;
import com.licf.bgManage.mapperstruct.BgLogisticsConverter;
import com.licf.bgManage.service.BgLogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-20 20:02:46
 */
@Service
public class BgLogisticsServiceImpl extends BaseServiceImpl<BgLogistics, BgLogisticsParam, BgLogisticsResult> implements BgLogisticsService {
    @Autowired
    public void init(BgLogisticsMapper bgCustomerMapper, BgLogisticsConverter bgCustomerConverter){
        super.initMapperConverter(bgCustomerMapper, bgCustomerConverter);
    }
}