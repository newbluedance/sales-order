package com.licf.app.service;

import com.common.base.BaseService;
import com.common.net.RestResponse;
import com.licf.app.entity.dto.UsrCartParam;
import com.licf.app.entity.dto.UsrCartResult;

import java.util.List;

/**
 * 
 * @author lichunfeng
 * @date 2020-09-16 18:37:39
 */
public interface UsrCartService extends BaseService<UsrCartParam, UsrCartResult> {

    List<UsrCartParam> query();
    boolean save(UsrCartParam param);

}