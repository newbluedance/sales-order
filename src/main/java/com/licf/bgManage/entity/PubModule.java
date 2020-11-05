package com.licf.bgManage.entity;

import com.common.base.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 
 * @author lichunfeng
 * @date 2020-11-05 17:50:46
 */
@Data
@Table(name = "pub_module")
public class PubModule extends BaseEntity {

    private static final long serialVersionUID = 87761081099032484L;

    /** ID主键自增 */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /** 路径 */
    @Column
    private String path;

    /** 名称 */
    @Column
    private String name;

    /** 图标 */
    @Column
    private String icon;

    /**  */
    @Column
    private Integer parentId;

    /** 创建时间 */
    @Column
    private LocalDateTime createTime;

    /** 创建人 */
    @Column
    private String createdBy;

    /** 更新时间 */
    @Column
    private LocalDateTime updateTime;

    /** 更新人 */
    @Column
    private String updatedBy;
}