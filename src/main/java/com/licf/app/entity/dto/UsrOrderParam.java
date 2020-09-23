package com.licf.app.entity.dto;

import com.common.base.BaseParam;
import com.common.validation.group.Add;
import com.common.validation.group.Update;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.licf.app.entity.dto.UsrCartParam;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class UsrOrderParam extends BaseParam {

    private static final long serialVersionUID = 65050981938727138L;

    /** ID主键自增 */
    private Integer id;

    /** 订单编号 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "订单编号过长")
    private String orderNo;

    private List<UsrCartParam> goods;

    /** 所属业务员 */
    private String salesman;

    /** 客户名称 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "客户名称过长")
    private String customerName;

    /** 手机号 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "手机号过长")
    private String phone;

    /** 所属部门 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "所属部门过长")
    private String departmentId;

    /** 实际金额 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "实际金额过长")
    private BigDecimal actualAmount;

    /** 支付方式 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "支付方式过长")
    private String payMode;

    /** 收货人 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "收货人过长")
    private String receiver;

    /** 收货地址 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "收货地址过长")
    private String address;

    /** 物流编号 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "物流编号过长")
    private String logisticsId;

    /** 物流名称 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "物流名称过长")
    private String logisticsName;

    /** 物流单号 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "物流单号过长")
    private String logisticsNo;

    /** 订单状态 0 待审核,1 待发货,2 待核销,3 退货 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "订单状态过长")
    private String status;

    /** 原因 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "原因过长")
    private String reason;

    /** 注释 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "注释过长")
    private String comments;


}