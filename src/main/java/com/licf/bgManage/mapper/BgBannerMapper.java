package com.licf.bgManage.mapper;

import com.common.base.BaseMapper;
import com.licf.bgManage.entity.BgBanner;
import com.licf.bgManage.entity.dto.BgBannerResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * @author lichunfeng
 * @date 2020-09-29 18:34:34
 */
@Mapper
public interface BgBannerMapper extends BaseMapper<BgBanner> {
    List<BgBannerResult> selectAllForId();
}