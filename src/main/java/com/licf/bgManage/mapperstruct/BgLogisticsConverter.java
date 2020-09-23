package com.licf.bgManage.mapperstruct;

import com.common.base.BaseConverter;
import com.licf.bgManage.entity.BgLogistics;
import com.licf.bgManage.entity.dto.BgLogisticsParam;
import com.licf.bgManage.entity.dto.BgLogisticsResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
@Mapper(componentModel = "spring")
public interface BgLogisticsConverter extends BaseConverter<BgLogistics, BgLogisticsParam, BgLogisticsResult> {
BgLogisticsConverter INSTANCE = Mappers.getMapper(BgLogisticsConverter.class);
}