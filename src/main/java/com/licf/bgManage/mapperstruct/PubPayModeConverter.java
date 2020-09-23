package com.licf.bgManage.mapperstruct;

import com.common.base.BaseConverter;
import com.licf.bgManage.entity.PubPayMode;
import com.licf.bgManage.entity.dto.PubPayModeParam;
import com.licf.bgManage.entity.dto.PubPayModeResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
@Mapper(componentModel = "spring")
public interface PubPayModeConverter extends BaseConverter<PubPayMode, PubPayModeParam, PubPayModeResult> {
PubPayModeConverter INSTANCE = Mappers.getMapper(PubPayModeConverter.class);
}