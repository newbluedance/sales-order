package com.licf.bgManage.mapperstruct;

import com.common.base.BaseConverter;
import com.licf.bgManage.entity.BgDepartment;
import com.licf.bgManage.entity.dto.BgDepartmentParam;
import com.licf.bgManage.entity.dto.BgDepartmentResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
@Mapper(componentModel = "spring")
public interface BgDepartmentConverter extends BaseConverter<BgDepartment, BgDepartmentParam, BgDepartmentResult> {
BgDepartmentConverter INSTANCE = Mappers.getMapper(BgDepartmentConverter.class);
}