package com.licf.bgManage.entity.dto;

import com.common.authory.SCondition;
import com.common.base.BaseParam;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import com.common.view.Title;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class BgCustomerParam extends BaseParam {

    private static final long serialVersionUID = 72831442948578440L;

    @Title("ID")
    private Integer id;

    @Title("客户名称")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "客户名称过长")
    @SCondition("like")
    private String customerName;

    @Title("手机号")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "手机号过长")
    @SCondition("like")
    private String phone;

    //收货人省份
    private String province;
    //收货人城市
    private String city;
    //收货人区县
    private String county;

    @Title("地址")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "地址过长")
    private String address;

    @Title("所属部门id")
    private Integer departmentId;

    @Title("业务员")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "业务员过长")
    private Integer salesman;

    @Title("注释")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "注释过长")
    private String comments;

    private Integer deleted;

}