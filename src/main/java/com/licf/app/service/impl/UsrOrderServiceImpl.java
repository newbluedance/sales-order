package com.licf.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.common.base.BaseServiceImpl;
import com.common.utils.IdUtil;
import com.common.utils.LoginUtils;
import com.licf.app.entity.UsrCart;
import com.licf.app.entity.UsrOrder;
import com.licf.app.entity.dto.UsrCartParam;
import com.licf.app.entity.dto.UsrOrderGoodsShot;
import com.licf.app.entity.dto.UsrOrderParam;
import com.licf.app.entity.dto.UsrOrderResult;
import com.licf.app.mapper.UsrCartMapper;
import com.licf.bgManage.mapper.BgGoodsMapper;
import com.licf.app.mapperstruct.UsrOrderConverter;
import com.licf.app.service.UsrOrderService;
import com.licf.bgManage.entity.BgEmployee;
import com.licf.bgManage.entity.BgGoods;
import com.licf.app.mapper.UsrOrderMapper;
import com.licf.bgManage.mapperstruct.BgGoodsConverter;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author lichunfeng
 * @date 2020-08-20 20:02:46
 */
@Service
public class UsrOrderServiceImpl extends BaseServiceImpl<UsrOrder, UsrOrderParam, UsrOrderResult> implements UsrOrderService {

    @Resource
    BgGoodsMapper goodsMapper;
    @Resource
    UsrCartMapper cartMapper;

    @Override
    public boolean add(UsrOrderParam param) {
        BgEmployee loginUser = LoginUtils.getLoginUser();

        List<UsrCartParam> goods = param.getGoods();
        //购物车移除
        UsrCart cart = cartMapper.selectByPrimaryKey(loginUser.getId());
        List<UsrCartParam> cartGoods = JSON.parseArray(cart.getGoods(), UsrCartParam.class);
        cartGoods.remove(goods);
        cart.setGoods(JSON.toJSON(cartGoods).toString());
        cartMapper.updateByPrimaryKey(cart);

        //订单表新增
        UsrOrder usrOrder = UsrOrderConverter.INSTANCE.paramToEntity(param);
        //订单表商品快照设置
        List<UsrOrderGoodsShot> goodsShots = BgGoodsConverter.INSTANCE.paramToShot(goods);

        String goodIds = goods.stream().map(UsrCartParam::getGoodId).collect(Collectors.joining(","));
        List<BgGoods> bgGoods = goodsMapper.selectByIds(goodIds);
        Map<Integer, BgGoods> goodsMap = bgGoods.stream().collect(Collectors.toMap(BgGoods::getId, BgGoods -> BgGoods));
        for (UsrOrderGoodsShot goodsShot : goodsShots) {
            BgGoods bgGood = goodsMap.get(goodsShot.getGoodId());
            goodsShot.setGoodName(bgGood.getGoodsName());
            goodsShot.setGoodPrice(bgGood.getPrice());
        }

        usrOrder.setOrderNo(IdUtil.createIdByDate());
        usrOrder.setOrderGoodsShot(JSON.toJSONString(goodsShots));
        usrOrder.setSalesman(loginUser.getAccount());
        usrOrder.setCreateTime(LocalDateTime.now());
        mapper.insert(usrOrder);
        return true;
    }

    @Autowired
    public void init(UsrOrderMapper bgCustomerMapper, UsrOrderConverter bgCustomerConverter) {
        super.initMapperConverter(bgCustomerMapper, bgCustomerConverter);
    }
}