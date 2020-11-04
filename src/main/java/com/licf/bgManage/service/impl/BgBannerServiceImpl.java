package com.licf.bgManage.service.impl;

import com.common.base.BaseServiceImpl;
import com.common.base.DivPageInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.licf.bgManage.entity.BgBanner;
import com.licf.bgManage.entity.dto.BgBannerParam;
import com.licf.bgManage.entity.dto.BgBannerResult;
import com.licf.bgManage.mapper.BgBannerMapper;
import com.licf.bgManage.mapperstruct.BgBannerConverter;
import com.licf.bgManage.service.BgBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * 
 * @author lichunfeng
 * @date 2020-09-29 18:34:34
 */
@Service
public class BgBannerServiceImpl extends BaseServiceImpl<BgBanner, BgBannerParam, BgBannerResult> implements BgBannerService {

    @Resource
    BgBannerMapper bgBannerMapper;

    @Override
    public DivPageInfo<BgBannerResult> pageList(BgBannerParam param, Pageable pageable) {

        PageInfo<BgBannerResult> pageInfo = PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize()).doSelectPageInfo(() -> bgBannerMapper.selectAllForId());
        DivPageInfo<BgBannerResult> page = new DivPageInfo<>();
        page.setList(pageInfo.getList());
        page.setPageNum(pageInfo.getPageNum());
        page.setPageSize(pageInfo.getPageSize());
        page.setPages(pageInfo.getPages());
        page.setSize(pageInfo.getSize());
        page.setTotal(pageInfo.getTotal());
        return page;
    }

    @Autowired
    public void init(BgBannerMapper bgBannerMapper, BgBannerConverter bgBannerConverter){
        super.initMapperConverter(bgBannerMapper, bgBannerConverter);
    }
}