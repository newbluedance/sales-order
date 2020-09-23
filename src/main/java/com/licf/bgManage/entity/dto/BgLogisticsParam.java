package com.licf.bgManage.entity.dto;

import com.common.base.BaseParam;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class BgLogisticsParam extends BaseParam {

    private static final long serialVersionUID = 65112142178628148L;

    /** ID主键自增 */
    private Integer id;

    /** 物流名称 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "物流名称过长")
    private String logisticsName;

    /** 负责人姓名 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "负责人姓名过长")
    private String headName;

    /** 负责人电话 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "负责人电话过长")
    private String headMobile;

    /** 负责人手机号 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "负责人手机号过长")
    private String headPhone;

    /** 公司地址 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "公司地址过长")
    private String companyAddress;

    /** 注释 */
    @Length(max = 255, groups = {Update.class,Add.class}, message = "注释过长")
    private String comments;

}