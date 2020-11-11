package com.common.utils;

import com.common.constants.SystemConstant;
import com.licf.bgManage.entity.*;
import com.licf.bgManage.mapper.*;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: licf
 * @time: 2020/9/29 15:10
 */
public class RedisHelper {

    private static RedisTemplate<String, Object> redisTemplate = (RedisTemplate)SpringContextUtil.getBean("redisTemplate");

    private static BgGoodsMapper goodsMapper = SpringContextUtil.getBean(BgGoodsMapper.class);

    private static BgDepartmentMapper deptMapper = SpringContextUtil.getBean(BgDepartmentMapper.class);

    private static BgBannerMapper bgBannerMapper = SpringContextUtil.getBean(BgBannerMapper.class);

    private static PubRoleMapper roleMapper = SpringContextUtil.getBean(PubRoleMapper.class);

    private static PubModuleMapper moduleMapper = SpringContextUtil.getBean(PubModuleMapper.class);

    public static BgGoods getGood(Integer id) {
        BgGoods goods = (BgGoods) redisTemplate.opsForValue().get(SystemConstant.GOOD_KEY_PREFIX.concat(String.valueOf(id)));
        if (goods == null) {
            goods = goodsMapper.selectByPrimaryKey(id);
            redisTemplate.opsForValue().set(SystemConstant.GOOD_KEY_PREFIX.concat(String.valueOf(id)), goods);
            //redis失效时间24小时
            redisTemplate.expire(SystemConstant.GOOD_KEY_PREFIX.concat(String.valueOf(id)), 3600000L*24,
                    TimeUnit.MILLISECONDS);
        }
        return goods;
    }

    public static BgDepartment getDept(Integer id) {
        BgDepartment dept = (BgDepartment) redisTemplate.opsForValue().get(SystemConstant.DEPT_KEY_PREFIX.concat(String.valueOf(id)));
        if (dept == null) {
            dept = deptMapper.selectByPrimaryKey(id);
            redisTemplate.opsForValue().set(SystemConstant.DEPT_KEY_PREFIX.concat(String.valueOf(id)), dept);
            //redis失效时间24小时
            redisTemplate.expire(SystemConstant.DEPT_KEY_PREFIX.concat(String.valueOf(id)), 3600000L*24,
                    TimeUnit.MILLISECONDS);
        }
        return dept;
    }

    public static BgBanner getBanner(Integer id) {
        BgBanner banner = (BgBanner) redisTemplate.opsForValue().get(SystemConstant.BANNER_KEY_PREFIX.concat(String.valueOf(id)));
        if (banner == null) {
            banner = bgBannerMapper.selectByPrimaryKey(id);
            redisTemplate.opsForValue().set(SystemConstant.BANNER_KEY_PREFIX.concat(String.valueOf(id)), banner);
            //redis失效时间24小时
            redisTemplate.expire(SystemConstant.BANNER_KEY_PREFIX.concat(String.valueOf(id)), 3600000L*24,
                    TimeUnit.MILLISECONDS);
        }
        return banner;
    }

    public static PubRole getRole(Integer id) {
        PubRole pubRole = (PubRole) redisTemplate.opsForValue().get(SystemConstant.ROLE_KEY_PREFIX.concat(String.valueOf(id)));
        if (pubRole == null) {
            pubRole = roleMapper.selectByPrimaryKey(id);
            redisTemplate.opsForValue().set(SystemConstant.ROLE_KEY_PREFIX.concat(String.valueOf(id)), pubRole);
            //redis失效时间24小时
            redisTemplate.expire(SystemConstant.ROLE_KEY_PREFIX.concat(String.valueOf(id)), 3600000L*24,
                    TimeUnit.MILLISECONDS);
        }
        return pubRole;
    }
    public static PubModule getModule(Integer id) {
        PubModule pubModule = (PubModule) redisTemplate.opsForValue().get(SystemConstant.MODULE_KEY_PREFIX.concat(String.valueOf(id)));
        if (pubModule == null) {
            pubModule = moduleMapper.selectByPrimaryKey(id);
            redisTemplate.opsForValue().set(SystemConstant.MODULE_KEY_PREFIX.concat(String.valueOf(id)), pubModule);
            //redis失效时间24小时
            redisTemplate.expire(SystemConstant.MODULE_KEY_PREFIX.concat(String.valueOf(id)), 3600000L*24,
                    TimeUnit.MILLISECONDS);
        }
        return pubModule;
    }

    public static void clearGood(Integer id) {
        redisTemplate.delete(SystemConstant.GOOD_KEY_PREFIX.concat(String.valueOf(id)));
    }

    public static void clearDept(Integer id) {
        redisTemplate.delete(SystemConstant.DEPT_KEY_PREFIX.concat(String.valueOf(id)));
    }
    public static void clearBanner(Integer id) {
        redisTemplate.delete(SystemConstant.BANNER_KEY_PREFIX.concat(String.valueOf(id)));
    }

    /**
     * 清空redis
     */
    public static void fluShall(){
        Set<String> keys = redisTemplate.keys(SystemConstant.ROLE_KEY_PREFIX+"*");
        keys.addAll(redisTemplate.keys(SystemConstant.MODULE_KEY_PREFIX+"*"));
        keys.addAll(redisTemplate.keys(SystemConstant.MODULE_KEY_PREFIX+"**"));
        redisTemplate.delete(keys);
    }


}
