package com.licf.bgManage.mapperstruct;

import com.common.base.BaseConverter;
import com.licf.bgManage.entity.BgEmployee;
import com.licf.bgManage.entity.dto.BgEmployeeParam;
import com.licf.bgManage.entity.dto.BgEmployeeResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
@Mapper(componentModel = "spring")
public interface BgEmployeeConverter extends BaseConverter<BgEmployee, BgEmployeeParam, BgEmployeeResult> {
BgEmployeeConverter INSTANCE = Mappers.getMapper(BgEmployeeConverter.class);
}