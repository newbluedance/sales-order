package com.licf.app.mapperstruct;

import com.common.base.BaseConverter;
import com.licf.app.entity.UsrOrder;
import com.licf.app.entity.dto.UsrOrderParam;
import com.licf.app.entity.dto.UsrOrderResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
@Mapper(componentModel = "spring")
public interface UsrOrderConverter extends BaseConverter<UsrOrder, UsrOrderParam, UsrOrderResult> {
    UsrOrderConverter INSTANCE = Mappers.getMapper(UsrOrderConverter.class);

    @Mapping(target = "goods", expression = "java(com.alibaba.fastjson.JSON.parseArray(entity.getOrderGoodsShot(), com.licf.app.entity.dto.UsrCartParam.class))")
    UsrOrderResult entityToResult(UsrOrder entity);

    List<UsrOrderResult> entityToResult(List<UsrOrder> entity);
}