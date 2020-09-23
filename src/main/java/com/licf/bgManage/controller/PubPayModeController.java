package com.licf.bgManage.controller;

import com.common.base.DivPageInfo;
import com.common.net.RestResponse;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import com.licf.bgManage.entity.dto.PubPayModeParam;
import com.licf.bgManage.entity.dto.PubPayModeResult;
import com.licf.bgManage.service.PubPayModeService;
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
@RequestMapping("bgManage/pubPayMode")
public class PubPayModeController {
    @Resource
    private PubPayModeService pubPayModeService;

    /**
     * 根据条件获取分页list
     * @param param 持有查询条件
     * @param pageable 持有排序字段,和分页条件
     * @return RestResponse 持有分页对象
     */
    @GetMapping
    public RestResponse<DivPageInfo<PubPayModeResult>> page(PubPayModeParam param, @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return RestResponse.success(pubPayModeService.pageList(param, pageable));
    }

    /**
     * 新增
     * @param param 插入对象
     * @return RestResponse
     */
    @PostMapping
    public RestResponse insert(@Validated(Add.class) @RequestBody PubPayModeParam param) {
        pubPayModeService.insert(param);
        return RestResponse.success();
    }

    /**
     * 更新
     * @param param 更新对象
     * @return RestResponse
     */
    @PutMapping
    public RestResponse update(@Validated(Update.class) @RequestBody PubPayModeParam param) {
        pubPayModeService.update(param);
        return RestResponse.success();
    }

    /**
     * 删除单个
     * @param id id
     * @return RestResponse
     */
    @DeleteMapping("/{id}")
    public RestResponse deletePubPayMode(@PathVariable int id) {
        pubPayModeService.deleteById(id);
        return RestResponse.success();
    }
}
