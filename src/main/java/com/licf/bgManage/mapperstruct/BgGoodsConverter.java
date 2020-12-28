package com.licf.bgManage.mapperstruct;

import com.common.base.BaseConverter;
import com.licf.app.entity.dto.UsrCartParam;
import com.licf.bgManage.entity.BgGoods;
import com.licf.bgManage.entity.dto.BgGoodsParam;
import com.licf.bgManage.entity.dto.BgGoodsResult;
import com.licf.app.entity.dto.UsrOrderGoodsShot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
@Mapper(componentModel = "spring")
public interface BgGoodsConverter extends BaseConverter<BgGoods, BgGoodsParam, BgGoodsResult> {
    BgGoodsConverter INSTANCE = Mappers.getMapper(BgGoodsConverter.class);

    @Mapping(target = "weight", expression = "java(com.common.utils.RedisHelper.getGood(param.getGoodId()).getWeight())")
    UsrOrderGoodsShot paramToShot(UsrCartParam param);

    List<UsrOrderGoodsShot> paramToShot(List<UsrCartParam> params);
}