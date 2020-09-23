package com.licf.bgManage.service.impl;

import com.common.base.BaseServiceImpl;
import com.licf.bgManage.entity.BgDepartment;
import com.licf.bgManage.entity.dto.BgDepartmentParam;
import com.licf.bgManage.entity.dto.BgDepartmentResult;
import com.licf.bgManage.mapper.BgDepartmentMapper;
import com.licf.bgManage.mapperstruct.BgDepartmentConverter;
import com.licf.bgManage.service.BgDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-20 20:02:46
 */
@Service
public class BgDepartmentServiceImpl extends BaseServiceImpl<BgDepartment, BgDepartmentParam, BgDepartmentResult> implements BgDepartmentService {
    @Autowired
    public void init(BgDepartmentMapper bgCustomerMapper, BgDepartmentConverter bgCustomerConverter){
        super.initMapperConverter(bgCustomerMapper, bgCustomerConverter);
    }
}