package com.licf.bgManage.mapperstruct;

import com.common.base.BaseConverter;
import com.licf.bgManage.entity.BgCustomer;
import com.licf.bgManage.entity.dto.BgCustomerParam;
import com.licf.bgManage.entity.dto.BgCustomerResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
@Mapper(componentModel = "spring")
public interface BgCustomerConverter extends BaseConverter<BgCustomer, BgCustomerParam, BgCustomerResult> {
BgCustomerConverter INSTANCE = Mappers.getMapper(BgCustomerConverter.class);
}