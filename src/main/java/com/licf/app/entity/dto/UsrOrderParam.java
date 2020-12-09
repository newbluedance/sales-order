package com.licf.app.entity.dto;

import com.common.authory.SCondition;
import com.common.base.BaseParam;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import com.licf.bgManage.enums.EOrderStatus;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.common.view.Title;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class UsrOrderParam extends BaseParam {

    private static final long serialVersionUID = 65050981938727138L;

    @Title("ID")
    private Integer id;

    @Title("订单编号")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "订单编号过长")
    @SCondition("like")
    private String orderNo;

    private List<UsrCartParam> goods;

    @Title("所属业务员")
    private Integer salesman;

    @Title("客户名称")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "客户名称过长")
    @SCondition("like")
    private String customerName;

    @Title("手机号")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "手机号过长")
    @SCondition("like")
    private String phone;

    @Title("所属部门")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "所属部门过长")
    private Integer departmentId;

    @Title("实际金额")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "实际金额过长")
    private BigDecimal actualAmount;

    private Double weight;

    @Title("支付方式")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "支付方式过长")
    private String payMode;

    @Title("收货人")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "收货人过长")
    private String receiver;

    @Title("收货人电话")
    private String receiverPhone;

    //收货人省份
    private String province;
    //收货人城市
    private String city;
    //收货人区县
    private String county;

    @Title("收货地址")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "收货地址过长")
    private String address;

    @Title("物流编号")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "物流编号过长")
    private Integer logisticsId;

    @Title("物流名称")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "物流名称过长")
    private String logisticsName;

    @Title("物流单号")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "物流单号过长")
    private String logisticsNo;

    @Title("订单状态 0 待审核,1 待发货,2 待核销,3 退货")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "订单状态过长")
    private EOrderStatus status;

    @Title("原因")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "原因过长")
    private String reason;

    @Title("注释")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "注释过长")
    private String comments;

    @SCondition("createTime>")
    private LocalDateTime startTime;
    @SCondition("createTime<")
    private LocalDateTime endTime;

    private Integer deleted;

}