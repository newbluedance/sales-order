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
 * @date 2020-08-24 18:34:54
 */
@Data
@Table(name = "bg_customer")
public class BgCustomer extends BaseEntity {

    private static final long serialVersionUID = 15972091288565095L;

    @Title("ID")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Title("客户名称")
    @Column
    private String customerName;

    @Title("手机号")
    @Column
    private String phone;

    //收货人省份
    @Column
    private String province;
    //收货人城市
    @Column
    private String city;
    //收货人区县
    @Column
    private String county;

    @Title("地址")
    @Column
    private String address;

    @Title("所属部门id")
    @Column
    private Integer departmentId;

    @Title("业务员")
    @Column
    private Integer salesman;

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