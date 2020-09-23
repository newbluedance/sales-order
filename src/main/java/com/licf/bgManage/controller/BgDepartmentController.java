package com.licf.bgManage.controller;

import com.common.base.DivPageInfo;
import com.licf.bgManage.entity.dto.BgDepartmentParam;
import com.licf.bgManage.entity.dto.BgDepartmentResult;
import com.licf.bgManage.service.BgDepartmentService;
import com.common.net.RestResponse;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
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
@RequestMapping("bgManage/bgDepartment")
public class BgDepartmentController {
    @Resource
    private BgDepartmentService bgDepartmentService;

    /**
     * 根据条件获取分页list
     * @param param 持有查询条件
     * @param pageable 持有排序字段,和分页条件
     * @return RestResponse 持有分页对象
     */
    @GetMapping
    public RestResponse<DivPageInfo<BgDepartmentResult>> page(BgDepartmentParam param, @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return RestResponse.success(bgDepartmentService.pageList(param, pageable));
    }

    /**
     * 新增
     * @param param 插入对象
     * @return RestResponse
     */
    @PostMapping
    public RestResponse insert(@Validated(Add.class) @RequestBody BgDepartmentParam param) {
        bgDepartmentService.insert(param);
        return RestResponse.success();
    }

    /**
     * 更新
     * @param param 更新对象
     * @return RestResponse
     */
    @PutMapping
    public RestResponse update(@Validated(Update.class) @RequestBody BgDepartmentParam param) {
        bgDepartmentService.update(param);
        return RestResponse.success();
    }

    /**
     * 删除单个
     * @param id id
     * @return RestResponse
     */
    @DeleteMapping("/{id}")
    public RestResponse deleteBgDepartment(@PathVariable int id) {
        bgDepartmentService.deleteById(id);
        return RestResponse.success();
    }
}
