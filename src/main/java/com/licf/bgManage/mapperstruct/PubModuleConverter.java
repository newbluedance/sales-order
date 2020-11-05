package com.licf.bgManage.mapperstruct;

import com.common.base.BaseConverter;
import com.licf.bgManage.entity.PubModule;
import com.licf.bgManage.entity.dto.PubModuleParam;
import com.licf.bgManage.entity.dto.PubModuleResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 
 * @author lichunfeng
 * @date 2020-11-05 17:50:49
 */
@Mapper(componentModel = "spring")
public interface PubModuleConverter extends BaseConverter<PubModule, PubModuleParam, PubModuleResult> {
PubModuleConverter INSTANCE = Mappers.getMapper(PubModuleConverter.class);
}