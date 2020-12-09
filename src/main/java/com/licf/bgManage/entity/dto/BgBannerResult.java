package com.licf.bgManage.entity.dto;

import com.common.base.BaseResult;
import java.time.LocalDateTime;

import com.common.utils.RedisHelper;
import com.common.view.Title;
import com.licf.bgManage.entity.BgBanner;
import com.licf.bgManage.entity.BgDepartment;
import lombok.Data;

/**
 * 
 * @author lichunfeng
 * @date 2020-09-29 18:34:34
 */
@Data
public class BgBannerResult extends BaseResult {

    private static final long serialVersionUID = 45780439568207778L;

    @Title("ID")
    private Integer id;

    @Title("图片")
    private String img;

    @Title("排序")
    private Integer orderNum;

    @Title("创建时间")
    private LocalDateTime createTime;

    @Title("创建人")
    private String createdBy;

    @Title("更新时间")
    private LocalDateTime updateTime;

    @Title("更新人")
    private String updatedBy;

    public String getImg() {
        BgBanner banner = RedisHelper.getBanner(id);
        if (banner != null)
            return banner.getImg();
        return null;
    }
}