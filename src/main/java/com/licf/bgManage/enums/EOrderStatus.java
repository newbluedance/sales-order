package com.licf.bgManage.enums;

/**
 * @author: licf
 * @time: 2020/9/29 15:42
 */
public enum EOrderStatus {

    // 待主管审核
    PENDING_LEADER_REVIEW,
    // 待仓库审核
    PENDING_STORAGE_REVIEW,
    // 待发货员审核
    PENDING_DELIVER_REVIEW,
    //待发货
    PENDING_DELIVER,
    //待核销
    PENDING_WRITE_OFF,
    //已核销
    WRITE_OFF,
    //主管审核拒绝
    LEADER_REVIEW_REJECT,
    //仓库审核拒绝
    STORAGE_REVIEW_REJECT,
    //发货员审核拒绝
    DELIVER_REVIEW_REJECT,
    //发货拒绝
    DELIVER_REJECT,
    //核销坏账
    WRITE_BAD_DEBTS,
    //退货完成
    RETURN_GOODS;

    public EOrderStatus next() {
        if (this.ordinal() < WRITE_OFF.ordinal()) {
            return EOrderStatus.values()[this.ordinal() + 1];
        } else {
            return null;
        }

    }

    public EOrderStatus reject() {
        if (this == PENDING_LEADER_REVIEW) {
            return LEADER_REVIEW_REJECT;
        } else if (this == PENDING_STORAGE_REVIEW) {
            return STORAGE_REVIEW_REJECT;
        } else if (this == PENDING_DELIVER_REVIEW) {
            return DELIVER_REVIEW_REJECT;
        } else if (this == PENDING_DELIVER) {
            return DELIVER_REJECT;
        } else if (this == PENDING_WRITE_OFF) {
            return WRITE_BAD_DEBTS;
        }
        return null;

    }

}
