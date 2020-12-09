package com.licf.app.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: licf
 * @time: 2020/10/10 15:22
 */
@Data
public class DbAddService {
    //保价金额
    private BigDecimal insuranceValue;
    //代收货款类型
    private String codType;
    //代收货款客户账号
    private String reciveLoanAccount;
    //代收货款客户开户名
    private String accountName;
    //代收货款金额
    private BigDecimal codValue;
    //签收回单
    private String backSignBill;

}
