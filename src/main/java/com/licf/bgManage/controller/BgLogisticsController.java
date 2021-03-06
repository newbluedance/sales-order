package com.licf.bgManage.controller;

import com.common.authory.RequiredPermission;
import com.common.base.DivPageInfo;
import com.common.net.RestResponse;
import com.common.utils.RedisHelper;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import com.licf.bgManage.entity.dto.BgLogisticsParam;
import com.licf.bgManage.entity.dto.BgLogisticsResult;
import com.licf.bgManage.enums.PermitEnum;
import com.licf.bgManage.service.BgLogisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lichunfeng
 * @since 2019/9/4 15:01
 */
@Slf4j
@RestController
@RequestMapping("bgManage/bgLogistics")
public class BgLogisticsController {
    @Resource
    private BgLogisticsService bgLogisticsService;

    /**
     * 根据条件获取分页list
     *
     * @param param    持有查询条件
     * @param pageable 持有排序字段,和分页条件
     * @return RestResponse 持有分页对象
     */
    @GetMapping
    @RequiredPermission(permit = PermitEnum.LogisticsQuery)
    public RestResponse<DivPageInfo<BgLogisticsResult>> page(BgLogisticsParam param, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return RestResponse.success(bgLogisticsService.pageList(param, pageable));
    }

    /**
     * 新增
     *
     * @param param 插入对象
     * @return RestResponse
     */
    @PostMapping
    @RequiredPermission(permit = PermitEnum.LogisticsInsert)
    public RestResponse insert(@Validated(Add.class) @RequestBody BgLogisticsParam param) {
        bgLogisticsService.insert(param);
        return RestResponse.success();
    }

    /**
     * 更新
     *
     * @param param 更新对象
     * @return RestResponse
     */
    @PutMapping
    @RequiredPermission(permit = PermitEnum.LogisticsUpdate)
    public RestResponse update(@Validated(Update.class) @RequestBody BgLogisticsParam param) {
        bgLogisticsService.update(param);
        RedisHelper.clearLogistics(param.getId());
        return RestResponse.success();
    }

    /**
     * 删除单个
     *
     * @param id id
     * @return RestResponse
     */
    @DeleteMapping("/{id}")
    @RequiredPermission(permit = PermitEnum.LogisticsDelete)
    public RestResponse deleteBgLogistics(@PathVariable int id) {
        bgLogisticsService.deleteById(id);
        RedisHelper.clearLogistics(id);
        return RestResponse.success();
    }
}
