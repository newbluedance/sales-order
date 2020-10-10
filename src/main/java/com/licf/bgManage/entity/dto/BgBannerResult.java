package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @author lichunfeng
 * @date 2020-09-29 18:34:34
 */
@Data
public class BgBannerResult extends BaseResult {

    private static final long serialVersionUID = 45780439568207778L;

    /** ID主键自增 */
    private Integer id;

    /** 图片 */
    private String img;

    /** 排序 */
    private Integer orderNum;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 创建人 */
    private String createdBy;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 更新人 */
    private String updatedBy;
}