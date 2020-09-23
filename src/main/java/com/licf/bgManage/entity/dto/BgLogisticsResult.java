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
public class BgLogisticsResult extends BaseResult {

    private static final long serialVersionUID = 63912292218406924L;

    /** ID主键自增 */
    private Integer id;

    /** 物流名称 */
    private String logisticsName;

    /** 负责人姓名 */
    private String headName;

    /** 负责人电话 */
    private String headMobile;

    /** 负责人手机号 */
    private String headPhone;

    /** 公司地址 */
    private String companyAddress;

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