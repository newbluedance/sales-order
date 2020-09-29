package com.licf.app.entity.dto;

/**
 * @author: licf
 * @time: 2020/9/29 15:42
 */
public enum EOrderStatus {
    //待审核
    PENDING_REVIEW,
    //待发货
    PENDING_DELIVER,
    //待核销
    PENDING_WRITE_OFF,
    //已核销
    WRITE_OFF,
    //审核拒绝
    REVIEW_REJECT,
    //发货拒绝
    DELIVER_REJECT,
    //核销坏账
    WRITE_BAD_DEBTS,
    //退货完成
    RETURN_GOODS,
}
