package com.licf.app.entity.dto;

import com.common.base.BaseResult;
import com.common.utils.RedisHelper;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class UsrOrderResult extends BaseResult {

    private static final long serialVersionUID = 84260164317125111L;

    /** ID主键自增 */
    private Integer id;

    /** 订单编号 */
    private String orderNo;

    private List<UsrOrderGoodsShot> goods;

    /** 客户名称 */
    private String customerName;

    /** 手机号 */
    private String phone;

    /** 所属业务员 */
    private String salesman;

    /** 所属部门 */
    private Integer departmentId;

    /** 订单金额 */
    private BigDecimal orderAmount;

    /** 实际金额 */
    private BigDecimal actualAmount;

    /** 支付方式 */
    private String payMode;

    /** 收货人 */
    private String receiver;

    /** 收货地址 */
    private String address;

    /** 物流编号 */
    private String logisticsId;

    /** 物流名称 */
    private String logisticsName;

    /** 物流单号 */
    private String logisticsNo;

    /** 订单状态 */
    private EOrderStatus status;

    /** 原因 */
    private String reason;

    /** 注释 */
    private String comments;

    /** 删除标志 */
    private Integer deleted;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 更新人 */
    private String updatedBy;

    private String departmentName;

}