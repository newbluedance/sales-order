package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;
import com.common.view.Title;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class BgLogisticsResult extends BaseResult {

    private static final long serialVersionUID = 63912292218406924L;

    @Title("ID")
    private Integer id;

    @Title("物流名称")
    private String logisticsEN;

    @Title("物流名称")
    private String logisticsName;

    @Title("负责人姓名")
    private String headName;

    @Title("负责人电话")
    private String headMobile;

    @Title("负责人手机号")
    private String headPhone;

    @Title("公司地址")
    private String companyAddress;

    @Title("注释")
    private String comments;

    @Title("删除标志")
    private Integer deleted;

    @Title("创建时间")
    private LocalDateTime createTime;

    @Title("创建人")
    private String createdBy;

    @Title("更新时间")
    private LocalDateTime updateTime;

    @Title("更新人")
    private String updatedBy;
}