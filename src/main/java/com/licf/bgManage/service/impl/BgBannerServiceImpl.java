package com.licf.bgManage.service.impl;

import com.common.base.BaseServiceImpl;
import com.licf.bgManage.entity.BgBanner;
import com.licf.bgManage.entity.dto.BgBannerParam;
import com.licf.bgManage.entity.dto.BgBannerResult;
import com.licf.bgManage.mapper.BgBannerMapper;
import com.licf.bgManage.mapperstruct.BgBannerConverter;
import com.licf.bgManage.service.BgBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author lichunfeng
 * @date 2020-09-29 18:34:34
 */
@Service
public class BgBannerServiceImpl extends BaseServiceImpl<BgBanner, BgBannerParam, BgBannerResult> implements BgBannerService {
    @Autowired
    public void init(BgBannerMapper bgBannerMapper, BgBannerConverter bgBannerConverter){
        super.initMapperConverter(bgBannerMapper, bgBannerConverter);
    }
}