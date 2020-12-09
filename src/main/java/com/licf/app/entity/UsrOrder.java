package com.licf.app.entity;

import com.common.base.BaseEntity;
import com.licf.bgManage.enums.EOrderStatus;
import lombok.Data;

import javax.persistence.Column;
import com.common.view.Title;
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

    @Title("ID")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Title("订单编号")
    @Column
    private String orderNo;

    @Title("订单商品快照")
    @Column
    private String orderGoodsShot;

    @Title("客户名称")
    @Column
    private String customerName;

    @Title("手机号")
    @Column
    private String phone;

    @Title("所属业务员")
    @Column
    private Integer salesman;

    @Title("所属部门")
    @Column
    private Integer departmentId;

    @Title("订单金额")
    @Column
    private BigDecimal orderAmount;

    @Column
    private Double weight;

    @Title("实际金额")
    @Column
    private BigDecimal actualAmount;

    @Title("支付方式")
    @Column
    private String payMode;

    @Title("收货人")
    @Column
    private String receiver;

    @Title("收货人电话")
    @Column
    private String receiverPhone;

    //收货人省份
    @Column
    private String province;
    //收货人城市
    @Column
    private String city;
    //收货人区县
    @Column
    private String county;

    @Title("收货地址")
    @Column
    private String address;

    @Title("物流编号")
    @Column
    private Integer logisticsId;

    @Title("物流名称")
    @Column
    private String logisticsName;

    @Title("物流单号")
    @Column
    private String logisticsNo;

    @Title("大头笔信息")
    @Column
    private String arrivedOrgSimpleName;

    @Title("订单状态")
    @Column
    private EOrderStatus status;

    @Title("原因")
    @Column
    private String reason;

    @Title("注释")
    @Column
    private String comments;

    @Title("删除标志")
    @Column
    private Integer deleted;

    @Title("创建时间")
    @Column
    private LocalDateTime createTime;

    @Title("更新时间")
    @Column
    private LocalDateTime updateTime;

    @Title("更新人")
    @Column
    private String updatedBy;

}