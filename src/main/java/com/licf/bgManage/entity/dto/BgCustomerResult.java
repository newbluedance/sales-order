package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class BgCustomerResult extends BaseResult {

    private static final long serialVersionUID = 28212764319486588L;

    /** ID主键自增 */
    private Integer id;

    /** 客户名称 */
    private String customerName;

    /** 手机号 */
    private String phone;

    /** 地址 */
    private String address;

    /** 所属部门id */
    private Integer departmentId;

    /** 业务员 */
    private Integer salesman;

    /** 注释 */
    private String comments;

    /** 删除标志 */
    private Integer deleted;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 创建人 */
    private String createdBy;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 更新人 */
    private String updatedBy;
}