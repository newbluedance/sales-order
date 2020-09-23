package com.licf.bgManage.mapperstruct;

import com.common.base.BaseConverter;
import com.licf.bgManage.entity.PubRole;
import com.licf.bgManage.entity.dto.PubRoleParam;
import com.licf.bgManage.entity.dto.PubRoleResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
@Mapper(componentModel = "spring")
public interface PubRoleConverter extends BaseConverter<PubRole, PubRoleParam, PubRoleResult> {
PubRoleConverter INSTANCE = Mappers.getMapper(PubRoleConverter.class);
}