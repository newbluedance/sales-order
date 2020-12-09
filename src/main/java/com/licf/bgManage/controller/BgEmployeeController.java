package com.licf.bgManage.controller;

import com.common.BusinessException;
import com.common.authory.RequiredPermission;
import com.common.base.DivPageInfo;
import com.common.constants.CodeConstant;
import com.common.net.RestResponse;
import com.common.utils.LoginUtils;
import com.common.utils.RedisHelper;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import com.licf.bgManage.entity.dto.BgEmployeeParam;
import com.licf.bgManage.entity.dto.BgEmployeeResult;
import com.licf.bgManage.enums.PermitEnum;
import com.licf.bgManage.service.BgEmployeeService;
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
@RequestMapping("bgManage/bgEmployee")
public class BgEmployeeController {
    @Resource
    private BgEmployeeService bgEmployeeService;

    /**
     * 根据条件获取分页list
     *
     * @param param    持有查询条件
     * @param pageable 持有排序字段,和分页条件
     * @return RestResponse 持有分页对象
     */
    @GetMapping
    @RequiredPermission(permit = PermitEnum.EmployeeQuery)
    public RestResponse<DivPageInfo<BgEmployeeResult>> page(BgEmployeeParam param, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return RestResponse.success(bgEmployeeService.pageList(param, pageable));
    }

    /**
     * 新增
     *
     * @param param 插入对象
     * @return RestResponse
     */
    @PostMapping
    @RequiredPermission(permit = PermitEnum.EmployeeInsert)
    public RestResponse insert(@Validated(Add.class) @RequestBody BgEmployeeParam param) {
        bgEmployeeService.insert(param);
        return RestResponse.success();
    }

    /**
     * 更新
     *
     * @param param 更新对象
     * @return RestResponse
     */
    @PutMapping
    @RequiredPermission(permit = PermitEnum.EmployeeUpdate)
    public RestResponse update(@Validated(Update.class) @RequestBody BgEmployeeParam param) {
        RedisHelper.clearEmployee(param.getId());
        bgEmployeeService.update(param);
        return RestResponse.success();
    }

    /**
     * 更新
     *
     * @param param 更新对象
     * @return RestResponse
     */
    @PutMapping("/updateSelf")
    @RequiredPermission(permit = PermitEnum.UpdateSelf)
    public RestResponse updateSelf(@Validated(Update.class) @RequestBody BgEmployeeParam param) {
        if (!param.getId().equals(LoginUtils.getLoginUser().getId())) {
            throw new BusinessException(CodeConstant.NO_PERMIT, "你没有权限修改别人的密码!");
        }
        RedisHelper.clearEmployee(param.getId());
        bgEmployeeService.updateSelf(param);
        return RestResponse.success();
    }

    /**
     * 删除单个
     *
     * @param id id
     * @return RestResponse
     */
    @DeleteMapping("/{id}")
    @RequiredPermission(permit = PermitEnum.EmployeeDelete)
    public RestResponse deleteBgEmployee(@PathVariable int id) {
        RedisHelper.clearEmployee(id);
        bgEmployeeService.deleteById(id);
        return RestResponse.success();
    }

}
