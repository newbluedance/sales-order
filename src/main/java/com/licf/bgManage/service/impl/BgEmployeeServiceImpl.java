package com.licf.bgManage.service.impl;

import com.common.base.BaseServiceImpl;
import com.common.constants.SystemConstant;
import com.licf.bgManage.entity.BgEmployee;
import com.licf.bgManage.entity.dto.BgEmployeeParam;
import com.licf.bgManage.entity.dto.BgEmployeeResult;
import com.licf.bgManage.mapper.BgEmployeeMapper;
import com.licf.bgManage.service.BgEmployeeService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-20 20:02:46
 */
@Service
public class BgEmployeeServiceImpl extends BaseServiceImpl<BgEmployee, BgEmployeeParam, BgEmployeeResult> implements BgEmployeeService {

    @Resource
    BgEmployeeMapper bgEmployeeMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void clearRedisAuthenticationInfo(String account) {
        // 清除用户存储在缓存中的权限信息
        redisTemplate.delete(SystemConstant.LOGIN_USER_KEY_PREFIX.concat(account));
    }

    @Override
    public void insert(BgEmployeeParam param) {
        BgEmployee bgEmployee = converter.paramToEntity(param);
        bgEmployee.setPassword(DigestUtils.md5Hex(param.getPassword()));
        bgEmployeeMapper.insertSelectiveGeneratedKeys(bgEmployee);
    }

    @Override
    public void update(BgEmployeeParam param) {
        BgEmployee bgEmployee = converter.paramToEntity(param);
        if (StringUtils.isNotEmpty(param.getPassword())){
            bgEmployee.setPassword(DigestUtils.md5Hex(param.getPassword()));
        }
        bgEmployeeMapper.updateByPrimaryKeySelective(bgEmployee);
        // 清除用户存储在缓存中的权限信息
        clearRedisAuthenticationInfo(bgEmployee.getAccount());
    }

    @Override
    public void updateSelf(BgEmployeeParam param) {
        BgEmployee bgEmployee = new BgEmployee();
        if (StringUtils.isNotEmpty(param.getPassword())){
            bgEmployee.setPassword(DigestUtils.md5Hex(param.getPassword()));
        }
        bgEmployee.setEmployeeName(param.getEmployeeName());
        bgEmployee.setPhone(param.getPhone());
        bgEmployeeMapper.updateByPrimaryKeySelective(bgEmployee);
        // 清除用户存储在缓存中的权限信息
        clearRedisAuthenticationInfo(bgEmployee.getAccount());
    }

    @Override
    public void deleteById(int id) {
        BgEmployee bgEmployee=new BgEmployee();
        bgEmployee.setId(id);
        bgEmployee.setDeleted(1);
        mapper.updateByPrimaryKeySelective(bgEmployee);
        // 清除用户存储在缓存中的权限信息
        clearRedisAuthenticationInfo(bgEmployee.getAccount());
    }

}