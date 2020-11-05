package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @author lichunfeng
 * @date 2020-11-05 17:50:47
 */
@Data
public class PubModuleResult extends BaseResult {

    private static final long serialVersionUID = 43776292278863063L;

    /** ID主键自增 */
    private Integer id;

    /** 路径 */
    private String path;

    /** 名称 */
    private String name;

    /** 图标 */
    private String icon;

    /**  */
    private Integer parentId;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 创建人 */
    private String createdBy;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 更新人 */
    private String updatedBy;
}