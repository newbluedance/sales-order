package com.licf.bgManage.mapperstruct;

import com.common.base.BaseConverter;
import com.licf.bgManage.entity.BgBanner;
import com.licf.bgManage.entity.dto.BgBannerParam;
import com.licf.bgManage.entity.dto.BgBannerResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 
 * @author lichunfeng
 * @date 2020-09-29 18:34:34
 */
@Mapper(componentModel = "spring")
public interface BgBannerConverter extends BaseConverter<BgBanner, BgBannerParam, BgBannerResult> {
BgBannerConverter INSTANCE = Mappers.getMapper(BgBannerConverter.class);
}