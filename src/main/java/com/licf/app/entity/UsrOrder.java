package com.licf.app.entity;

import com.common.base.BaseEntity;
import com.licf.bgManage.enums.EOrderStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author lichunfeng
 * @date 2020-09-23 19:10:17
 */
@Data
@Table(name = "usr_order")
public class UsrOrder extends BaseEntity {

    private static final long serialVersionUID = 65964423391715014L;

    /** ID主键自增 */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /** 订单编号 */
    @Column
    private String orderNo;

    /** 订单商品快照 */
    @Column
    private String orderGoodsShot;

    /** 客户名称 */
    @Column
    private String customerName;

    /** 手机号 */
    @Column
    private String phone;

    /** 所属业务员 */
    @Column
    private Integer salesman;

    /** 所属部门 */
    @Column
    private Integer departmentId;

    /** 订单金额 */
    @Column
    private BigDecimal orderAmount;

    /** 实际金额 */
    @Column
    private BigDecimal actualAmount;

    /** 支付方式 */
    @Column
    private String payMode;

    /** 收货人 */
    @Column
    private String receiver;

    /** 收货地址 */
    @Column
    private String address;

    /** 物流编号 */
    @Column
    private String logisticsId;

    /** 物流名称 */
    @Column
    private String logisticsName;

    /** 物流单号 */
    @Column
    private String logisticsNo;

    /** 订单状态 */
    @Column
    private EOrderStatus status;

    /** 原因 */
    @Column
    private String reason;

    /** 注释 */
    @Column
    private String comments;

    /** 删除标志 */
    @Column
    private Integer deleted;

    /** 创建时间 */
    @Column
    private LocalDateTime createTime;

    /** 更新时间 */
    @Column
    private LocalDateTime updateTime;

    /** 更新人 */
    @Column
    private String updatedBy;

}