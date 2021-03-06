package com.licf.bgManage.controller;

import com.common.authory.RequiredPermission;
import com.common.base.DivPageInfo;
import com.common.net.RestResponse;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import com.licf.bgManage.entity.dto.PubCategoryParam;
import com.licf.bgManage.entity.dto.PubCategoryResult;
import com.licf.bgManage.enums.PermitEnum;
import com.licf.bgManage.service.PubCategoryService;
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
@RequestMapping("bgManage/pubCategory")
public class PubCategoryController {
    @Resource
    private PubCategoryService pubCategoryService;

    /**
     * 根据条件获取分页list
     *
     * @param param    持有查询条件
     * @param pageable 持有排序字段,和分页条件
     * @return RestResponse 持有分页对象
     */
    @GetMapping
    @RequiredPermission(permit = PermitEnum.CategoryQuery)
    public RestResponse<DivPageInfo<PubCategoryResult>> page(PubCategoryParam param, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return RestResponse.success(pubCategoryService.pageList(param, pageable));
    }

    /**
     * 新增
     *
     * @param param 插入对象
     * @return RestResponse
     */
    @PostMapping
    @RequiredPermission(permit = PermitEnum.CategoryInsert)
    public RestResponse insert(@Validated(Add.class) @RequestBody PubCategoryParam param) {
        pubCategoryService.insert(param);
        return RestResponse.success();
    }

    /**
     * 更新
     *
     * @param param 更新对象
     * @return RestResponse
     */
    @PutMapping
    @RequiredPermission(permit = PermitEnum.CategoryUpdate)
    public RestResponse update(@Validated(Update.class) @RequestBody PubCategoryParam param) {
        pubCategoryService.update(param);
        return RestResponse.success();
    }

    /**
     * 删除单个
     *
     * @param id id
     * @return RestResponse
     */
    @DeleteMapping("/{id}")
    @RequiredPermission(permit = PermitEnum.CategoryDelete)
    public RestResponse deletePubCategory(@PathVariable int id) {
        pubCategoryService.deleteById(id);
        return RestResponse.success();
    }
}
