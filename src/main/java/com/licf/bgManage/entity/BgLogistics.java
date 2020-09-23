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
 * @date 2020-08-24 18:34:54
 */
@Data
@Table(name = "bg_logistics")
public class BgLogistics extends BaseEntity {

    private static final long serialVersionUID = 39627313268743777L;

    /** ID主键自增 */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /** 物流名称 */
    @Column
    private String logisticsName;

    /** 负责人姓名 */
    @Column
    private String headName;

    /** 负责人电话 */
    @Column
    private String headMobile;

    /** 负责人手机号 */
    @Column
    private String headPhone;

    /** 公司地址 */
    @Column
    private String companyAddress;

    /** 注释 */
    @Column
    private String comments;

    /** 删除标志 */
    @Column
    private Integer deleted;

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