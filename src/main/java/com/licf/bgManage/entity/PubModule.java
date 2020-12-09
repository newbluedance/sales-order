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
 * @date 2020-11-05 17:50:46
 */
@Data
@Table(name = "pub_module")
public class PubModule extends BaseEntity {

    private static final long serialVersionUID = 87761081099032484L;

    @Title("ID")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Title("路径")
    @Column
    private String path;

    @Column
    private String href;

    @Title("名称")
    @Column
    private String name;

    @Title("图标")
    @Column
    private String icon;

    @Title("")
    @Column
    private Integer parentId;

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