package com.licf.bgManage.service.impl;

import com.common.base.BaseServiceImpl;
import com.licf.bgManage.entity.BgEmployee;
import com.licf.bgManage.entity.dto.BgEmployeeParam;
import com.licf.bgManage.entity.dto.BgEmployeeResult;
import com.licf.bgManage.mapper.BgEmployeeMapper;
import com.licf.bgManage.mapperstruct.BgEmployeeConverter;
import com.licf.bgManage.service.BgEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-20 20:02:46
 */
@Service
public class BgEmployeeServiceImpl extends BaseServiceImpl<BgEmployee, BgEmployeeParam, BgEmployeeResult> implements BgEmployeeService {
    @Autowired
    public void init(BgEmployeeMapper bgCustomerMapper, BgEmployeeConverter bgCustomerConverter){
        super.initMapperConverter(bgCustomerMapper, bgCustomerConverter);
    }

    @Override
    public void deleteById(int id) {
        BgEmployee bgEmployee=new BgEmployee();
        bgEmployee.setId(id);
        bgEmployee.setDeleted(1);
        mapper.updateByPrimaryKeySelective(bgEmployee);
    }
}