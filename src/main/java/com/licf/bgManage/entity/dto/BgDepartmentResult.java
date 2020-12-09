package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;
import java.time.LocalDateTime;

import com.common.view.Title;
import lombok.Data;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class BgDepartmentResult extends BaseResult {

    private static final long serialVersionUID = 89489284523670774L;

    @Title("ID")
    private Integer id;

    @Title("部门名称")
    private String departmentName;

    @Title("负责人姓名")
    private String headName;

    @Title("负责人电话")
    private String headMobile;

    @Title("部门地址")
    private String departmentAddress;

    @Title("所属部门")
    private Integer parentId;

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