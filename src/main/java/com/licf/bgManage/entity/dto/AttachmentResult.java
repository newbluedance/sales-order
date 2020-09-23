package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;
import lombok.Data;

/**
 * @author lichunfeng
 * @date 2019-06-21 10:51:42
 */
@Data
public class AttachmentResult extends BaseResult {

    private static final long serialVersionUID = 84176589575271294L;

    /** ID主键自增 */
    private Integer id;

    /** 对应数据所在模块 */
    private String moduleName;

    /** 对应数据编号(有一个或多个其他关联id组成) */
    private String moduleDataCode;

    /** 对应数据的附件类型 */
    private String moduleFileType;

    /** 文件名称 */
    private String fileName;

    /** 大小 */
    private Long fileSize;

    /** 文件排序 */
    private Integer fileOrder;

    /** 文件完整路径 */
    private String filePath;

    /** 备注 */
    private String remark;

}