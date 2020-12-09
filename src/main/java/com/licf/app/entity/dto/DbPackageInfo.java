package com.licf.app.entity.dto;

import lombok.Data;

/**
 * @author: licf
 * @time: 2020/10/10 15:17
 */
@Data
public class DbPackageInfo {
    //货物名称
    private String cargoName;
    //总件数（包裹数）
    private Integer totalNumber;
    //总重量
    private double totalWeight;
    //总体积
    private double totalVolume;
    //包装
    private String packageService;
    //送货方式
    private String deliveryType;
}
