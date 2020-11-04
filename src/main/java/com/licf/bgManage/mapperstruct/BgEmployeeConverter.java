package com.licf.bgManage.mapperstruct;

import com.common.base.BaseConverter;
import com.licf.bgManage.entity.BgEmployee;
import com.licf.bgManage.entity.dto.BgEmployeeParam;
import com.licf.bgManage.entity.dto.BgEmployeeResult;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
@Mapper(componentModel = "spring")
public interface BgEmployeeConverter extends BaseConverter<BgEmployee, BgEmployeeParam, BgEmployeeResult> {
BgEmployeeConverter INSTANCE = Mappers.getMapper(BgEmployeeConverter.class);

    /**
     * 实体类转result
     *
     * @param entity
     * @return dto
     */
    @Mapping(ignore = true,target = "departmentName")
    BgEmployeeResult entityToResult(BgEmployee entity);
}