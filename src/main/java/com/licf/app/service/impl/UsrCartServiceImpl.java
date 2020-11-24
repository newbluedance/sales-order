package com.licf.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.common.base.BaseServiceImpl;
import com.common.utils.LoginUtils;
import com.common.utils.RedisHelper;
import com.licf.app.entity.UsrCart;
import com.licf.app.entity.dto.UsrCartParam;
import com.licf.app.entity.dto.UsrCartResult;
import com.licf.app.mapperstruct.UsrCartConverter;
import com.licf.app.service.UsrCartService;
import com.licf.bgManage.entity.dto.BgEmployeeResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lichunfeng
 * @date 2020-09-16 18:37:39
 */
@Service
public class UsrCartServiceImpl extends BaseServiceImpl<UsrCart, UsrCartParam, UsrCartResult> implements UsrCartService {

    @Override
    public List<UsrCartResult> query() {
        BgEmployeeResult loginUser = LoginUtils.getLoginUser();
        UsrCart cart = mapper.selectByPrimaryKey(loginUser.getId());
        if (cart != null && StringUtils.isNotBlank(cart.getGoods())) {
            List<UsrCartParam> usrCartParamList = JSON.parseArray(cart.getGoods(), UsrCartParam.class);
            List<UsrCartResult> usrCartResults = UsrCartConverter.INSTANCE.paramToResult(usrCartParamList);
            for (UsrCartResult usrCartResult : usrCartResults) {
                usrCartResult.setGood(RedisHelper.getGood(usrCartResult.getGoodId()));
            }
            return usrCartResults;
        }
        return new ArrayList<>(0);
    }

    @Override
    public boolean save(UsrCartParam param) {
        BgEmployeeResult loginUser = LoginUtils.getLoginUser();
        UsrCart cart = mapper.selectByPrimaryKey(loginUser.getId());
        if (cart == null) {
            cart = new UsrCart();
            cart.setEmployeeId(loginUser.getId());
            List<UsrCartParam> goods = new ArrayList<>(1);
            goods.add(param);
            cart.setGoods(JSON.toJSON(goods).toString());
            mapper.insert(cart);
        } else {
            String goodStrs = cart.getGoods();
            List<UsrCartParam> goods = JSON.parseArray(goodStrs, UsrCartParam.class);
            boolean contain = false;
            for (UsrCartParam good : goods) {
                if (good.getGoodId().equals(param.getGoodId())) {
                    if (param.getNum() == 0) {
                        goods.remove(good);
                    } else {
                        good.setNum(param.getNum());
                    }
                    contain = true;
                    break;
                }
            }
            if (!contain) {
                goods.add(param);
            }

            cart.setGoods(JSON.toJSON(goods).toString());

            mapper.updateByPrimaryKey(cart);
        }
        return true;
    }



}