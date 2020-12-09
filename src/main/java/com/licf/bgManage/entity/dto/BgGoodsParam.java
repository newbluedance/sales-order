package com.licf.bgManage.entity.dto;

import com.common.authory.SCondition;
import com.common.base.BaseParam;
import com.common.validation.group.Add;
import com.common.validation.group.Update;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.common.view.Title;

/**
 *
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class BgGoodsParam extends BaseParam {

    private static final long serialVersionUID = 80129776752690415L;

    @Title("ID")
    private Integer id;

    @Title("产品名称")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "产品名称过长")
    @SCondition("like")
    private String goodsName;

    @Title("产品编号")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "产品编号过长")
    private String goodsNo;

    @Title("类别")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "类别过长")
    private String category;

    @Title("规格")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "规格过长")
    private String specifications;

    @Title("单位")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "单位过长")
    private String unit;

    private Double weight;

    @Title("单价")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "单价过长")
    private BigDecimal price;

    @Title("库存")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "库存过长")
    private BigDecimal stock;

    @Title("描述")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "描述过长")
    private String describ;

    @Title("图片地址")
    private String mainImg;

    @Title("用法用量")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "用法用量过长")
    private String dosage;

    @Title("主治功能")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "主治功能过长")
    private String majorFunction;

    @Title("产品成分")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "产品成分过长")
    private String ingredients;

    @Title("状态")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "状态过长")
    private String status;

    @Title("注释")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "注释过长")
    private String comments;

    private Integer deleted;


}