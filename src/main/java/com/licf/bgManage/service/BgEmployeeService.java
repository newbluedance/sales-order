package com.licf.bgManage.service;

import com.common.base.BaseService;
import com.licf.bgManage.entity.BgEmployee;
import com.licf.bgManage.entity.dto.BgEmployeeParam;
import com.licf.bgManage.entity.dto.BgEmployeeResult;

/**
 * @author lichunfeng
 * @date 2020-08-24 18:34:55
 */
public interface BgEmployeeService extends BaseService<BgEmployeeParam, BgEmployeeResult> {

    /**
     * 清除用户存储在缓存中的权限信息
     * @param userName
     */
    void clearRedisAuthenticationInfo(String userName);

    void updateSelf(BgEmployeeParam param);
}