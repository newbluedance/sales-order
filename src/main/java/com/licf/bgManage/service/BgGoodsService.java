package com.licf.bgManage.service;

import com.common.base.BaseService;
import com.licf.bgManage.entity.dto.BgGoodsParam;
import com.licf.bgManage.entity.dto.BgGoodsResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
public interface BgGoodsService extends BaseService<BgGoodsParam, BgGoodsResult> {

    void save(MultipartFile file, BgGoodsParam bgGoodsParam) throws IOException;
}