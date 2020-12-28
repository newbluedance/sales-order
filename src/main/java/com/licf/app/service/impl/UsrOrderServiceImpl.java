package com.licf.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.common.BusinessException;
import com.common.base.BaseServiceImpl;
import com.common.constants.CodeConstant;
import com.common.utils.DopUtil;
import com.common.utils.IdUtil;
import com.common.utils.LoginUtils;
import com.common.utils.RedisHelper;
import com.deppon.dop.module.sdk.shared.domain.result.ResultDO;
import com.deppon.dop.module.sdk.shared.util.FastJsonUtil;
import com.deppon.dop.module.sdk.shared.util.HttpUtils;
import com.deppon.dop.module.sdk.shared.util.SecurityUtil;
import com.licf.app.entity.UsrCart;
import com.licf.app.entity.UsrOrder;
import com.licf.app.entity.dto.*;
import com.licf.app.mapper.UsrCartMapper;
import com.licf.app.mapperstruct.UsrOrderConverter;
import com.licf.app.service.UsrOrderService;
import com.licf.bgManage.entity.BgGoods;
import com.licf.bgManage.entity.dto.BgEmployeeResult;
import com.licf.bgManage.enums.EOrderStatus;
import com.licf.bgManage.mapperstruct.BgGoodsConverter;
import org.apache.commons.httpclient.NameValuePair;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author lichunfeng
 * @date 2020-08-20 20:02:46
 */
@Service
public class UsrOrderServiceImpl extends BaseServiceImpl<UsrOrder, UsrOrderParam, UsrOrderResult> implements UsrOrderService {

    @Resource
    UsrCartMapper cartMapper;

    @Override
    public boolean add(UsrOrderParam param) {
        BgEmployeeResult loginUser = LoginUtils.getLoginUser();

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

        BigDecimal orderAmount = BigDecimal.ZERO;
        double orderWeight = 0;
        for (UsrOrderGoodsShot goodsShot : goodsShots) {
            BgGoods bgGood = RedisHelper.getGood(goodsShot.getGoodId());
            goodsShot.setGoodName(bgGood.getGoodsName());
            goodsShot.setGoodPrice(bgGood.getPrice());
            goodsShot.setSpecifications(bgGood.getSpecifications());
            goodsShot.setUnit(bgGood.getUnit());
            goodsShot.setWeight(bgGood.getWeight());

            orderAmount = orderAmount.add(bgGood.getPrice().multiply(new BigDecimal(goodsShot.getNum())));
            orderWeight += goodsShot.getWeight() * goodsShot.getNum();
        }

        usrOrder.setDepartmentId(loginUser.getDepartmentId());
        usrOrder.setOrderAmount(orderAmount);
        usrOrder.setOrderNo(IdUtil.createIdByDate());
        usrOrder.setOrderGoodsShot(JSON.toJSONString(goodsShots));
        usrOrder.setWeight(orderWeight);
        usrOrder.setSalesman(loginUser.getId());
        usrOrder.setStatus(EOrderStatus.PENDING_LEADER_REVIEW);
        mapper.insertSelective(usrOrder);
        return true;
    }

    @Override
    public boolean review(UsrOrderReview param, EOrderStatus curStatus) {
        UsrOrder curOrder = mapper.selectByPrimaryKey(param.getId());
        if (curStatus != curOrder.getStatus()) {
            throw new BusinessException(CodeConstant.UNDIFINE, "订单已不是待审核状态!");
        }

        UsrOrder usrOrder = new UsrOrder();
        usrOrder.setId(param.getId());
        if (param.isPass()) {
            usrOrder.setStatus(curStatus.next());
        } else {
            usrOrder.setStatus(curStatus.reject());
            usrOrder.setReason(param.getReason());
        }
        mapper.updateByPrimaryKeySelective(usrOrder);
        return true;

    }

    @Override
    public boolean deliver(UsrOrderDeliver param) {
        UsrOrder curOrder = mapper.selectByPrimaryKey(param.getId());
        EOrderStatus curStatus = curOrder.getStatus();
        if (EOrderStatus.PENDING_DELIVER != curStatus) {
            throw new BusinessException(CodeConstant.UNDIFINE, "订单已不是待发货状态!");
        }

        UsrOrder usrOrder = new UsrOrder();
        usrOrder.setId(param.getId());
        if (param.isReject()) {
            usrOrder.setStatus(curStatus.reject());
            usrOrder.setReason(param.getReason());
        } else {
            usrOrder.setStatus(curStatus.next());
            usrOrder.setLogisticsId(param.getLogisticsId());
            usrOrder.setLogisticsName(RedisHelper.getLogistics(param.getLogisticsId()).getLogisticsName());
            if (param.getLogisticsNo() != null) {
                usrOrder.setLogisticsNo(param.getLogisticsNo());
            }
        }
        mapper.updateByPrimaryKeySelective(usrOrder);
        return true;
    }

    @Override
    public boolean writeOff(UsrOrderWriteOff param) {
        UsrOrder curOrder = mapper.selectByPrimaryKey(param.getId());
        if (EOrderStatus.WRITE_OFF == param.getStatus()) {
            if (EOrderStatus.PENDING_WRITE_OFF != curOrder.getStatus()) {
                throw new BusinessException(CodeConstant.UNDIFINE, "订单已不是待核销状态!");
            }
        } else if (EOrderStatus.WRITE_BAD_DEBTS == param.getStatus()) {
            if (EOrderStatus.PENDING_WRITE_OFF != curOrder.getStatus()) {
                throw new BusinessException(CodeConstant.UNDIFINE, "订单已不是待核销状态!");
            }
        } else if (EOrderStatus.RETURN_GOODS == param.getStatus()) {
            if (EOrderStatus.WRITE_OFF != curOrder.getStatus() || EOrderStatus.PENDING_WRITE_OFF != curOrder.getStatus()) {
                throw new BusinessException(CodeConstant.UNDIFINE, "订单不是待核销或已核销状态,无法退货!");
            }
        } else {
            throw new BusinessException(CodeConstant.UNDIFINE, "你无法进行此操作!");
        }

        UsrOrder usrOrder = new UsrOrder();
        usrOrder.setId(param.getId());
        usrOrder.setStatus(param.getStatus());
        usrOrder.setReason(param.getReason());

        mapper.updateByPrimaryKeySelective(usrOrder);
        return true;
    }

    @Override
    public UsrOrder generateMailNo(UsrOrderDeliver param) {
        UsrOrder p = new UsrOrder();
        p.setId(param.getId());
        UsrOrderResult usrOrder = converter.entityToResult(mapper.selectOne(p));

        if (param.getLogisticsId() == null) {
            throw new BusinessException(CodeConstant.ERROR_PARAMETER, "物流类型id不能为空");
        }

        String logisticsName = RedisHelper.getLogistics(param.getLogisticsId()).getLogisticsName();

        //从物流公司获取单号及其他物流信息
        DbOrderReq dbOrderReq = new DbOrderReq();
        dbOrderReq.setLogisticID("DENG" + param.getId());
        dbOrderReq.setCompanyCode("EWBGHSWKJJT");
        dbOrderReq.setOrderType("2");
        if ("德邦快递".equals(logisticsName)) {
            dbOrderReq.setTransportType("PACKAGE");
        } else if ("德邦物流".equals(logisticsName)) {
            dbOrderReq.setTransportType("RCP");
        }

        dbOrderReq.setCustomerCode("E202006087777441");
        dbOrderReq.setGmtCommit(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss", Locale.CHINA)));
        dbOrderReq.setPayType("2");

        DbSender dbSender = new DbSender();
        dbSender.setName("光华生物");
        dbSender.setMobile("18168388455");
        dbSender.setProvince("河南省");
        dbSender.setCity("郑州市");
        dbSender.setCounty("新郑区");
        dbSender.setAddress("花园路111号");

        DbReceiver dbReceiver = new DbReceiver();
        dbReceiver.setName(usrOrder.getReceiver() == null ? usrOrder.getCustomerName() : usrOrder.getReceiver());
        dbReceiver.setMobile(usrOrder.getReceiverPhone());
        dbReceiver.setProvince(usrOrder.getProvince());
        dbReceiver.setCity(usrOrder.getCity());
        dbReceiver.setCounty(usrOrder.getCounty());
        dbReceiver.setAddress(usrOrder.getAddress());
        DbPackageInfo dbPackageInfo = new DbPackageInfo();
        dbPackageInfo.setCargoName(usrOrder.getGoods().get(0).getGoodName());

        int totalNum = 0;
        List<UsrOrderGoodsShot> goods = usrOrder.getGoods();
        for (UsrOrderGoodsShot good : goods) {
            totalNum += good.getNum();
        }
        dbPackageInfo.setTotalNumber(totalNum);
        dbPackageInfo.setTotalWeight(usrOrder.getWeight() == null ? 0 : usrOrder.getWeight());
        dbPackageInfo.setDeliveryType("1");

        DbAddService dbAddService = new DbAddService();
        dbAddService.setCodType("3");
        dbAddService.setCodValue(usrOrder.getActualAmount());

        dbOrderReq.setSender(dbSender);
        dbOrderReq.setReceiver(dbReceiver);
        dbOrderReq.setPackageInfo(dbPackageInfo);
        dbOrderReq.setAddServices(dbAddService);
        DbOrderResp dbOrderResp = DopUtil.addDbOrder(dbOrderReq);

        //
        if ("1000".equals(dbOrderResp.getResultCode())) {
            UsrOrder update = new UsrOrder();
            update.setId(param.getId());
            update.setLogisticsId(param.getLogisticsId());
            update.setLogisticsName(logisticsName);
            update.setLogisticsNo(dbOrderResp.getMailNo());
            update.setArrivedOrgSimpleName(dbOrderResp.getArrivedOrgSimpleName());
            mapper.updateByPrimaryKeySelective(update);

            return update;
        } else {
            throw new BusinessException(CodeConstant.UNDIFINE, dbOrderResp.getReason());
        }
    }






}