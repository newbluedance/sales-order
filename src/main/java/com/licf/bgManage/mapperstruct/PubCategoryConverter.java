package com.licf.bgManage.mapperstruct;

import com.common.base.BaseConverter;
import com.licf.bgManage.entity.PubCategory;
import com.licf.bgManage.entity.dto.PubCategoryParam;
import com.licf.bgManage.entity.dto.PubCategoryResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
@Mapper(componentModel = "spring")
public interface PubCategoryConverter extends BaseConverter<PubCategory, PubCategoryParam, PubCategoryResult> {
PubCategoryConverter INSTANCE = Mappers.getMapper(PubCategoryConverter.class);
}