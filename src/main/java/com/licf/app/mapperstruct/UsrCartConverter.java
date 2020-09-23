package com.licf.app.mapperstruct;

import com.common.base.BaseConverter;
import com.licf.app.entity.UsrCart;
import com.licf.app.entity.dto.UsrCartParam;
import com.licf.app.entity.dto.UsrCartResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 
 * @author lichunfeng
 * @date 2020-09-16 18:37:39
 */
@Mapper(componentModel = "spring")
public interface UsrCartConverter extends BaseConverter<UsrCart, UsrCartParam, UsrCartResult> {
UsrCartConverter INSTANCE = Mappers.getMapper(UsrCartConverter.class);
}