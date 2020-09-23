package com.licf.bgManage.entity;

import com.common.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
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

    /** ID主键自增 */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /** 对应数据所在模块 */
    @Column
    private String moduleName;

    /** 对应数据编号(有一个或多个其他关联id组成) */
    @Column
    private String moduleDataCode;

    /** 对应数据的附件类型 */
    @Column
    private String moduleFileType;

    /** 文件名称 */
    @Column
    private String fileName;

    /** 大小 */
    @Column
    private Long fileSize;

    /** 文件排序 */
    @Column
    private Integer fileOrder;

    /** 文件完整路径 */
    @Column
    private String filePath;

    /** 创建人 */
    @Column
    private Integer createUserId;

    /** 创建时间 */
    @Column
    private LocalDateTime createTime;

    /** 删除标记 */
    @Column
    private String deleted;

    /** 备注 */
    @Column
    private String remark;

}