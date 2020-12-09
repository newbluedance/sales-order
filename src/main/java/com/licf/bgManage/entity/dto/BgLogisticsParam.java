package com.licf.bgManage.entity.dto;

import com.common.authory.SCondition;
import com.common.base.BaseParam;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.common.view.Title;

/**
 * 
 * @author lichunfeng
 * @date 2020-08-24 18:34:54
 */
@Data
public class BgLogisticsParam extends BaseParam {

    private static final long serialVersionUID = 65112142178628148L;

    @Title("ID")
    private Integer id;

    @Title("物流名称")
    private String logisticsEN;

    @Title("物流名称")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "物流名称过长")
    @SCondition("like")
    private String logisticsName;

    @Title("负责人姓名")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "负责人姓名过长")
    private String headName;

    @Title("负责人电话")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "负责人电话过长")
    private String headMobile;

    @Title("负责人手机号")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "负责人手机号过长")
    private String headPhone;

    @Title("公司地址")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "公司地址过长")
    private String companyAddress;

    @Title("注释")
    @Length(max = 255, groups = {Update.class,Add.class}, message = "注释过长")
    private String comments;

    private Integer deleted;

}