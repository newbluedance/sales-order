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
public class BgDepartmentResult extends BaseResult {

    private static final long serialVersionUID = 89489284523670774L;

    /** ID主键自增 */
    private Integer id;

    /** 部门名称 */
    private String departmentName;

    /** 负责人姓名 */
    private String headName;

    /** 负责人电话 */
    private String headMobile;

    /** 部门地址 */
    private String departmentAddress;

    /** 所属部门 */
    private Integer parentId;

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