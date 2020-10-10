package com.licf.app.entity.dto;

import lombok.Data;

/**
 * @author: licf
 * @time: 2020/10/10 15:13
 */
@Data
public class DbOrderReq {
    //渠道单号
    private String logisticID;
    //客户订单号/商户订单号
    private String custOrderNo;
    //运单号
    private String mailNo;
    //是否需要订阅轨迹
    private String needTraceInfo;
    //第三方接入商的公司编码
    private String companyCode;
    //下单模式
    private String orderType;
    //运输方式/产品类型
    private String transportType;
    //客户编码/月结账号
    private String customerCode;
    //发货人信息
    private DbSender sender;
    //收货人信息
    private DbReceiver receiver;
    //包裹信息
    private DbPackageInfo packageInfo;
    //订单提交时间
    private String gmtCommit;
    //支付方式
    private String payType;
    //短信通知
    private String smsNotify;
    //上门接货开始时间
    private String sendStartTime;
    //上门接货结束时间
    private String sendEndTime;
    //原运单号
    private String originalWaybillNumber;
    //备注
    private String remark;
    //是否外发
    private String isOut;
    //是否口令签收
    private String passwordSigning;
    //是否可派送
    private String isdispatched;
    //是否预售单
    private String ispresaleorder;
    //是否合伙人自提
    private String isPickupSelf;
    ;
}
