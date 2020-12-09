package com.licf.bgManage.entity;

import com.common.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import com.common.view.Title;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 
 * @author lichunfeng
 * @date 2019-06-21 10:51:42
 */
@Data
@Table(name = "attachment")
public class Attachment extends BaseEntity {

    private static final long serialVersionUID = 83572613280497547L;

    @Title("ID")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Title("对应数据所在模块")
    @Column
    private String moduleName;

    @Title("对应数据编号(有一个或多个其他关联id组成)")
    @Column
    private String moduleDataCode;

    @Title("对应数据的附件类型")
    @Column
    private String moduleFileType;

    @Title("文件名称")
    @Column
    private String fileName;

    @Title("大小")
    @Column
    private Long fileSize;

    @Title("文件排序")
    @Column
    private Integer fileOrder;

    @Title("文件完整路径")
    @Column
    private String filePath;

    @Title("创建人")
    @Column
    private Integer createUserId;

    @Title("创建时间")
    @Column
    private LocalDateTime createTime;

    @Title("删除标记")
    @Column
    private String deleted;

    @Title("备注")
    @Column
    private String remark;

}