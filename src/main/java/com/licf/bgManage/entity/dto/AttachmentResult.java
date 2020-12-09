package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;
import com.common.view.Title;
import lombok.Data;

/**
 * @author lichunfeng
 * @date 2019-06-21 10:51:42
 */
@Data
public class AttachmentResult extends BaseResult {

    private static final long serialVersionUID = 84176589575271294L;

    @Title("ID")
    private Integer id;

    @Title("对应数据所在模块")
    private String moduleName;

    @Title("对应数据编号(有一个或多个其他关联id组成)")
    private String moduleDataCode;

    @Title("对应数据的附件类型")
    private String moduleFileType;

    @Title("文件名称")
    private String fileName;

    @Title("大小")
    private Long fileSize;

    @Title("文件排序")
    private Integer fileOrder;

    @Title("文件完整路径")
    private String filePath;

    @Title("备注")
    private String remark;

}