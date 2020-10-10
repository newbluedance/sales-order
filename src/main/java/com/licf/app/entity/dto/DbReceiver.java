package com.licf.app.entity.dto;

import lombok.Data;

/**
 * @author: licf
 * @time: 2020/10/10 15:20
 */
@Data
public class DbReceiver {
    //到达部门编码
    private String toNetworkNo;
    //收货人名称
    private String name;
    //收货人电话
    private String phone;
    //收货人手机
    private String mobile;
    //收货人省份
    private String province;
    //收货人城市
    private String city;
    //收货人区县
    private String county;
    //收货人镇街道
    private String town;
    //收货人详细地址
    private String address;
    //收货人公司
    private String companyName;
}
