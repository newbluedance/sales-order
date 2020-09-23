package com.licf.bgManage.service.impl;

import com.common.base.BaseServiceImpl;
import com.licf.bgManage.entity.PubRole;
import com.licf.bgManage.entity.dto.PubRoleParam;
import com.licf.bgManage.entity.dto.PubRoleResult;
import com.licf.bgManage.mapper.PubRoleMapper;
import com.licf.bgManage.mapperstruct.PubRoleConverter;
import com.licf.bgManage.service.PubRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-20 20:02:46
 */
@Service
public class PubRoleServiceImpl extends BaseServiceImpl<PubRole, PubRoleParam, PubRoleResult> implements PubRoleService {
    @Autowired
    public void init(PubRoleMapper bgCustomerMapper, PubRoleConverter bgCustomerConverter){
        super.initMapperConverter(bgCustomerMapper, bgCustomerConverter);
    }
}