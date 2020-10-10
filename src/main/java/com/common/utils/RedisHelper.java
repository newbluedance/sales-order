package com.common.utils;

import com.common.constants.SystemConstant;
import com.licf.bgManage.entity.BgDepartment;
import com.licf.bgManage.entity.BgGoods;
import com.licf.bgManage.mapper.BgDepartmentMapper;
import com.licf.bgManage.mapper.BgGoodsMapper;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: licf
 * @time: 2020/9/29 15:10
 */
public class RedisHelper {

    private static RedisTemplate<String, Object> redisTemplate = (RedisTemplate)SpringContextUtil.getBean("redisTemplate");

    private static BgGoodsMapper goodsMapper = SpringContextUtil.getBean(BgGoodsMapper.class);

    private static BgDepartmentMapper deptMapper = SpringContextUtil.getBean(BgDepartmentMapper.class);

    public static BgGoods getGood(Integer id) {
        BgGoods goods = (BgGoods) redisTemplate.opsForValue().get(SystemConstant.GOOD_KEY_PREFIX.concat(String.valueOf(id)));
        if (goods == null) {
            goods = goodsMapper.selectByPrimaryKey(id);
            redisTemplate.opsForValue().set(SystemConstant.GOOD_KEY_PREFIX.concat(String.valueOf(id)), goods);
        }
        return goods;
    }

    public static BgDepartment getDept(Integer id) {
        BgDepartment dept = (BgDepartment) redisTemplate.opsForValue().get(SystemConstant.DEPT_KEY_PREFIX.concat(String.valueOf(id)));
        if (dept == null) {
            dept = deptMapper.selectByPrimaryKey(id);
            redisTemplate.opsForValue().set(SystemConstant.DEPT_KEY_PREFIX.concat(String.valueOf(id)), dept);
        }
        return dept;
    }

    public static void clearGood(Integer id) {
        redisTemplate.delete(SystemConstant.GOOD_KEY_PREFIX.concat(String.valueOf(id)));
    }

    public static void clearDept(Integer id) {
        redisTemplate.delete(SystemConstant.DEPT_KEY_PREFIX.concat(String.valueOf(id)));
    }
}
