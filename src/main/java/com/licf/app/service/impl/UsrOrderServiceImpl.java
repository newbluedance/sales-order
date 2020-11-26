package com.licf.app.service.impl;

import ch.qos.logback.core.db.dialect.DBUtil;
import com.alibaba.fastjson.JSON;
import com.common.BusinessException;
import com.common.base.BaseServiceImpl;
import com.common.base.DivPageInfo;
import com.common.constants.CodeConstant;
import com.common.utils.IdUtil;
import com.common.utils.LoginUtils;
import com.common.utils.RedisHelper;
import com.deppon.dop.module.sdk.shared.domain.result.ResultDO;
import com.deppon.dop.module.sdk.shared.util.FastJsonUtil;
import com.deppon.dop.module.sdk.shared.util.HttpUtils;
import com.deppon.dop.module.sdk.shared.util.SecurityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.licf.app.entity.UsrCart;
import com.licf.app.entity.UsrOrder;
import com.licf.app.entity.dto.*;
import com.licf.app.mapper.UsrCartMapper;
import com.licf.app.mapper.UsrOrderMapper;
import com.licf.app.mapperstruct.UsrOrderConverter;
import com.licf.app.service.UsrOrderService;
import com.licf.bgManage.entity.BgEmployee;
import com.licf.bgManage.entity.BgGoods;
import com.licf.bgManage.entity.dto.BgEmployeeResult;
import com.licf.bgManage.enums.EOrderStatus;
import com.licf.bgManage.mapperstruct.BgGoodsConverter;
import org.apache.commons.httpclient.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
        for (UsrOrderGoodsShot goodsShot : goodsShots) {
            BgGoods bgGood = RedisHelper.getGood(goodsShot.getGoodId());
            goodsShot.setGoodName(bgGood.getGoodsName());
            goodsShot.setGoodPrice(bgGood.getPrice());
            goodsShot.setSpecifications(bgGood.getSpecifications());
            goodsShot.setUnit(bgGood.getUnit());

            orderAmount = orderAmount.add(bgGood.getPrice().multiply(new BigDecimal(goodsShot.getNum())));
        }

        usrOrder.setDepartmentId(loginUser.getDepartmentId());
        usrOrder.setOrderAmount(orderAmount);
        usrOrder.setOrderNo(IdUtil.createIdByDate());
        usrOrder.setOrderGoodsShot(JSON.toJSONString(goodsShots));
        usrOrder.setSalesman(loginUser.getId());
        usrOrder.setCreateTime(LocalDateTime.now());
        usrOrder.setStatus(EOrderStatus.PENDING_LEADER_REVIEW);
        mapper.insert(usrOrder);
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
            usrOrder.setLogisticsName(param.getLogisticsName());
            if (param.getLogisticsNo() != null) {
                usrOrder.setLogisticsNo(param.getLogisticsNo());
            } else {
                //从物流公司获取单号及其他物流信息
                DbOrderReq dbOrderReq = new DbOrderReq();
                dbOrderReq.setLogisticID("");
                dbOrderReq.setCompanyCode("");
                dbOrderReq.setOrderType("");
                dbOrderReq.setTransportType("");
                dbOrderReq.setCustomerCode("");
                dbOrderReq.setGmtCommit("");
                dbOrderReq.setPayType("");
                DbSender dbSender = new DbSender();
                dbSender.setName("光华生物");
                dbSender.setMobile("");
                dbSender.setProvince("");
                dbSender.setCity("");
                dbSender.setCounty("");
                dbSender.setAddress("");

                DbReceiver dbReceiver = new DbReceiver();
                dbReceiver.setName(curOrder.getReceiver());
                dbReceiver.setMobile(curOrder.getPhone());
                dbReceiver.setProvince("");
                dbReceiver.setCity("");
                dbReceiver.setCounty("");
                dbReceiver.setAddress(curOrder.getAddress());
                DbPackageInfo dbPackageInfo = new DbPackageInfo();
                dbPackageInfo.setCargoName("");
                dbPackageInfo.setTotalNumber("");
                dbPackageInfo.setTotalWeight("");
                dbPackageInfo.setDeliveryType("");


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



   /* @Override
    public DivPageInfo<UsrOrderResult> pageList(UsrOrderParam param, Pageable pageable) {
        UsrOrder entity = converter.paramToEntity(param);
        Example example = new Example(entity.getClass());
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(param);
        if (param.getStartTime() != null) {
            criteria.andGreaterThanOrEqualTo("createTime", param.getStartTime());
        }
        if (param.getEndTime() != null) {
            criteria.andLessThanOrEqualTo("createTime", param.getEndTime());
        }

        example.setOrderByClause(formatSortString(pageable));

        PageInfo<UsrOrder> pageInfo = PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize()).doSelectPageInfo(() -> mapper.selectByExample(example));
        return pageConverter(pageInfo);
    }*/

    public static void main(String[] args) {
        //从物流公司获取单号及其他物流信息
        DbOrderReq dbOrderReq = new DbOrderReq();
        dbOrderReq.setLogisticID("");
        dbOrderReq.setCompanyCode("");
        dbOrderReq.setOrderType("");
        dbOrderReq.setTransportType("");
        dbOrderReq.setCustomerCode("");
        dbOrderReq.setGmtCommit("");
        dbOrderReq.setPayType("");
        DbSender dbSender = new DbSender();
        dbSender.setName("光华生物");
        dbSender.setMobile("18168388455");
        dbSender.setProvince("河南省");
        dbSender.setCity("郑州市");
        dbSender.setCounty("新郑区");
        dbSender.setAddress("花园路111号");

        DbReceiver dbReceiver = new DbReceiver();
        dbReceiver.setName("李春风");
        dbReceiver.setMobile("13401908428");
        dbReceiver.setProvince("江苏省");
        dbReceiver.setCity("南京市");
        dbReceiver.setCounty("雨花台区");
        dbReceiver.setAddress("雨花台软件大道108号");
        DbPackageInfo dbPackageInfo = new DbPackageInfo();
        dbPackageInfo.setCargoName("");
        dbPackageInfo.setTotalNumber("");
        dbPackageInfo.setTotalWeight("");
        dbPackageInfo.setDeliveryType("");
        DbOrderResp dbOrderResp = addDbOrder(dbOrderReq);
        System.out.println(dbOrderReq);

    }


    public static DbOrderResp addDbOrder(DbOrderReq common) {
        //订单内容 json字符串，SDK提供FastJsonUtil转Json
        String params = FastJsonUtil.toJSONString(common);
        //请求url
        String url = "this is request address";
        //companyCode与appkey为双方约定
        String companyCode = "this is companyCode";
        String appkey = "this is appkey";
        //时间戳 SDK提供SecurityUtil获取时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());
        //摘要 SDK提供SecurityUtil生成摘要
        String digest = SecurityUtil.getCommonDigest(params + appkey + timestamp, null);
        //post请求参数
        NameValuePair[] data = new NameValuePair[4];
        data[0] = new NameValuePair("companyCode", companyCode);
        data[1] = new NameValuePair("digest", digest);
        data[2] = new NameValuePair("timestamp", timestamp);
        data[3] = new NameValuePair("params", params);
        ResultDO<String> response = null;
        //返回结果
        response = HttpUtils.sendRequest(url, data, "UTF-8", 5000);
        System.out.print(response);
        return JSON.parseObject(response.getModel(), DbOrderResp.class);
    }
}