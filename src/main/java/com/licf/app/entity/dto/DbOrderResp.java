package com.licf.app.entity.dto;

import lombok.Data;

/**
 * @author: licf
 * @time: 2020/10/10 15:13
 */
@Data
public class DbOrderResp {

    //渠道单号
    private String logisticID;
    //运单号集合（多个运单号）
    private String mailNo;
    //目的站部门简称
    private String arrivedOrgSimpleName;
    //最终到达外场
    private String arrivedOutFieldName;
    //提货网点编码
    private String stationNo;
    //超远派送金额
    private String muchHigherDelivery;
    //请求成功标识
    private String result;
    //结果代码
    private String resultCode;
    //错误原因
    private String reason;
    //请求唯一编号
    private String uniquerRequestNumber;
    //母单号标识
    private String parentMailNo;
}
