package com.licf.app.entity.dto;

import lombok.Data;

/**
 * @author: licf
 * @time: 2020/10/10 15:22
 */
@Data
public class DbSender {
    //发货人公司
    private String companyName;
    //发货部门编码
    private String businessNetworkNo;
    //发货人名称
    private String name;
    //发货人手机
    private String mobile;
    //发货人电话
    private String phone;
    //发货人省份
    private String province;
    //发货人城市
    private String city;
    //发货人区县
    private String county;
    //发货人乡镇街道
    private String town;
    //发货人详细地址
    private String address;
}
