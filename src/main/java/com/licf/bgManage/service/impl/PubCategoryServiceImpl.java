package com.licf.bgManage.service.impl;

import com.common.base.BaseServiceImpl;
import com.licf.bgManage.entity.PubCategory;
import com.licf.bgManage.entity.dto.PubCategoryParam;
import com.licf.bgManage.entity.dto.PubCategoryResult;
import com.licf.bgManage.mapper.PubCategoryMapper;
import com.licf.bgManage.mapperstruct.PubCategoryConverter;
import com.licf.bgManage.service.PubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-20 20:02:46
 */
@Service
public class PubCategoryServiceImpl extends BaseServiceImpl<PubCategory, PubCategoryParam, PubCategoryResult> implements PubCategoryService {
    @Autowired
    public void init(PubCategoryMapper bgCustomerMapper, PubCategoryConverter bgCustomerConverter){
        super.initMapperConverter(bgCustomerMapper, bgCustomerConverter);
    }
}