package com.common.utils;

import com.common.constants.SystemConstant;
import com.licf.bgManage.entity.BgGoods;
import com.licf.bgManage.mapper.BgGoodsMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: licf
 * @time: 2020/9/29 15:10
 */
@Component
public class RedisHelper {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private BgGoodsMapper goodsMapper;

    public BgGoods getGood(Integer id) {
        BgGoods goods = (BgGoods) redisTemplate.opsForValue().get(SystemConstant.GOOD_KEY_PREFIX.concat(String.valueOf(id)));
        if (goods == null) {
            goods = goodsMapper.selectByPrimaryKey(id);
            redisTemplate.opsForValue().set(SystemConstant.GOOD_KEY_PREFIX.concat(String.valueOf(id)), goods);
        }
        return goods;
    }
}
