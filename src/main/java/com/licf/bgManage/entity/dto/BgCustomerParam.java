package com.licf.bgManage.entity.dto;

import com.common.base.BaseParam;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import java.time.LocalDateTime;
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

    /** ID主键自增 */
    private Integer id;

    /** 客户名称 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "客户名称过长")
    private String customerName;

    /** 手机号 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "手机号过长")
    private String phone;

    /** 地址 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "地址过长")
    private String address;

    /** 所属部门id */
    private Integer departmentId;

    /** 业务员 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "业务员过长")
    private String salesman;

    /** 注释 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "注释过长")
    private String comments;

}