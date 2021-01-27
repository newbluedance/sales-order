package com.licf.bgManage.enums;

import com.common.view.Title;

/**
 * @author: licf
 * @time: 2020/9/29 15:42
 */
public enum EOrderStatus {

    @Title("主管意见")
    PENDING_LEADER_REVIEW,
    @Title("后勤意见")
    PENDING_STORAGE_REVIEW,
    @Title("发货员意见")
    PENDING_DELIVER_REVIEW,
    @Title("待发货")
    PENDING_DELIVER,
    @Title("待核销")
    PENDING_WRITE_OFF,
    @Title("已核销")
    WRITE_OFF,
    @Title("主管审核拒绝")
    LEADER_REVIEW_REJECT,
    @Title("后勤审核拒绝")
    STORAGE_REVIEW_REJECT,
    @Title("发货员审核拒绝")
    DELIVER_REVIEW_REJECT,
    @Title("发货拒绝")
    DELIVER_REJECT,
    @Title("核销坏账")
    WRITE_BAD_DEBTS,
    @Title("退货完成")
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
