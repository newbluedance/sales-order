package com.licf.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.common.BusinessException;
import com.common.base.BaseServiceImpl;
import com.common.constants.CodeConstant;
import com.common.utils.IdUtil;
import com.common.utils.LoginUtils;
import com.common.utils.RedisHelper;
import com.licf.app.entity.UsrCart;
import com.licf.app.entity.UsrOrder;
import com.licf.app.entity.dto.*;
import com.licf.app.mapper.UsrCartMapper;
import com.licf.app.mapper.UsrOrderMapper;
import com.licf.app.mapperstruct.UsrOrderConverter;
import com.licf.app.service.UsrOrderService;
import com.licf.bgManage.entity.BgEmployee;
import com.licf.bgManage.entity.BgGoods;
import com.licf.bgManage.mapperstruct.BgGoodsConverter;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lichunfeng
 * @date 2020-08-20 20:02:46
 */
@Service
public class UsrOrderServiceImpl extends BaseServiceImpl<UsrOrder, UsrOrderParam, UsrOrderResult> implements UsrOrderService {

    @Resource
    UsrCartMapper cartMapper;

    @Resource
    RedisHelper redisHelper;

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

        for (UsrOrderGoodsShot goodsShot : goodsShots) {
            BgGoods bgGood = redisHelper.getGood(goodsShot.getGoodId());
            goodsShot.setGoodName(bgGood.getGoodsName());
            goodsShot.setGoodPrice(bgGood.getPrice());
        }

        usrOrder.setOrderNo(IdUtil.createIdByDate());
        usrOrder.setOrderGoodsShot(JSON.toJSONString(goodsShots));
        usrOrder.setSalesman(loginUser.getAccount());
        usrOrder.setCreateTime(LocalDateTime.now());
        usrOrder.setStatus(EOrderStatus.PENDING_REVIEW);
        mapper.insert(usrOrder);
        return true;
    }

    @Override
    public boolean review(UsrOrderReview param) {
        BgEmployee loginUser = LoginUtils.getLoginUser();
        Integer[] roleIds = loginUser.getRoleIds();
        boolean isReviewer = ArrayUtils.contains(roleIds, 4);
        if (!isReviewer) {
            throw new BusinessException(CodeConstant.UNDIFINE, "你不是审核人员!");
        }

        UsrOrder usrOrder = new UsrOrder();
        usrOrder.setId(param.getId());
        if (param.isPass()) {
            usrOrder.setStatus(EOrderStatus.PENDING_DELIVER);
        } else {
            usrOrder.setStatus(EOrderStatus.REVIEW_REJECT);
            usrOrder.setReason(param.getReason());
        }
        mapper.updateByPrimaryKeySelective(usrOrder);
        return true;
    }

    @Override
    public boolean deliver(UsrOrderDeliver param) {
        BgEmployee loginUser = LoginUtils.getLoginUser();
        Integer[] roleIds = loginUser.getRoleIds();
        boolean isReviewer = ArrayUtils.contains(roleIds, 5);
        if (!isReviewer) {
            throw new BusinessException(CodeConstant.UNDIFINE, "你不是发货人员!");
        }

        UsrOrder usrOrder = new UsrOrder();
        usrOrder.setId(param.getId());
        if (param.isReject()) {
            usrOrder.setStatus(EOrderStatus.DELIVER_REJECT);
            usrOrder.setReason(param.getReason());
        } else {
            usrOrder.setStatus(EOrderStatus.PENDING_WRITE_OFF);
            usrOrder.setLogisticsName(param.getLogisticsName());
            usrOrder.setLogisticsNo(param.getLogisticsNo());
        }
        mapper.updateByPrimaryKeySelective(usrOrder);
        return true;
    }

    @Autowired
    public void init(UsrOrderMapper usrOrderMapper, UsrOrderConverter usrOrderConverter) {
        super.initMapperConverter(usrOrderMapper, usrOrderConverter);
    }
}