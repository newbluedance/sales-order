package com.licf.bgManage.entity;

import com.common.base.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import com.common.view.Title;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 
 * @author lichunfeng
 * @date 2020-09-29 18:34:34
 */
@Data
@Table(name = "bg_banner")
public class BgBanner extends BaseEntity {

    private static final long serialVersionUID = 20920387903681933L;

    @Title("ID")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Title("图片")
    @Column
    private String img;

    @Title("排序")
    @Column
    private Integer orderNum;

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