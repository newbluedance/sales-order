package com.licf.bgManage.controller;

import com.common.authory.RequiredPermission;
import com.common.base.DivPageInfo;
import com.common.net.RestResponse;
import com.common.utils.LoginUtils;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import com.licf.bgManage.entity.dto.BgCustomerParam;
import com.licf.bgManage.entity.dto.BgCustomerResult;
import com.licf.bgManage.entity.dto.BgEmployeeResult;
import com.licf.bgManage.enums.PermitEnum;
import com.licf.bgManage.service.BgCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
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
@RequestMapping("bgManage/bgCustomer")
public class BgCustomerController {

    @Resource
    private BgCustomerService bgCustomerService;

    /**
     * 根据条件获取分页list
     * demo {{host28089}}/bgManage/bgCustomer?size=1&page=0&sort=id,asc
     *
     * @param param    持有查询条件
     * @param pageable 持有排序字段,和分页条件
     * @return RestResponse 持有分页对象
     */
    @GetMapping
    @RequiredPermission(permit = PermitEnum.CustomerQuery)
    public RestResponse<DivPageInfo<BgCustomerResult>> page(BgCustomerParam param, @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        BgEmployeeResult loginUser = LoginUtils.getLoginUser();
        if (ArrayUtils.contains(loginUser.getRoleIds(), 1)) {
        } else if (ArrayUtils.contains(loginUser.getRoleIds(), 3)) {
            // 如果是主管查看当前部门的
            param.setDepartmentId(loginUser.getDepartmentId());
        } else {
            // 如果是业务员 查看自己的
            param.setSalesman(loginUser.getId());
        }

        return RestResponse.success(bgCustomerService.pageList(param, pageable));
    }

    /**
     * 新增
     *
     * @param param 插入对象
     * @return RestResponse
     */
    @PostMapping
    @RequiredPermission(permit = PermitEnum.CustomerInsert)
    public RestResponse insert(@Validated(Add.class) @RequestBody BgCustomerParam param) {
        if (param.getSalesman() == null) {
            param.setSalesman(LoginUtils.getLoginUser().getId());
        }
        bgCustomerService.insert(param);
        return RestResponse.success();
    }

    /**
     * 更新
     *
     * @param param 更新对象
     * @return RestResponse
     */
    @PutMapping
    @RequiredPermission(permit = PermitEnum.CustomerUpdate)
    public RestResponse update(@Validated(Update.class) @RequestBody BgCustomerParam param) {
        bgCustomerService.update(param);
        return RestResponse.success();
    }

    /**
     * 删除单个
     *
     * @param id id
     * @return RestResponse
     */
    @DeleteMapping("/{id}")
    @RequiredPermission(permit = PermitEnum.CustomerDelete)
    public RestResponse deleteBgCustomer(@PathVariable int id) {
        bgCustomerService.deleteById(id);
        return RestResponse.success();
    }
}
