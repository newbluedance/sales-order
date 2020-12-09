package com.licf.bgManage.entity;

import com.common.base.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import com.common.view.Title;
import com.common.view.Title;
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

    @Title("ID")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Title("物流名称")
    @Column(name = "logistics_en")
    private String logisticsEN;

    @Title("物流名称")
    @Column
    private String logisticsName;

    @Title("负责人姓名")
    @Column
    private String headName;

    @Title("负责人电话")
    @Column
    private String headMobile;

    @Title("负责人手机号")
    @Column
    private String headPhone;

    @Title("公司地址")
    @Column
    private String companyAddress;

    @Title("注释")
    @Column
    private String comments;

    @Title("删除标志")
    @Column
    private Integer deleted;

    @Title("创建时间")
    @Column
    private LocalDateTime createTime;

    @Title("创建人")
    @Column
    private String createdBy;

    @Title("更新时间")
    @Column
    private LocalDateTime updateTime;

    @Title("更新人")
    @Column
    private String updatedBy;
}