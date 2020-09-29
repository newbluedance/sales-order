package com.licf.app.controller;

import com.common.base.DivPageInfo;
import com.common.net.RestResponse;
import com.common.utils.LoginUtils;
import com.common.validation.group.Add;
import com.licf.app.entity.dto.UsrOrderDeliver;
import com.licf.app.entity.dto.UsrOrderParam;
import com.licf.app.entity.dto.UsrOrderResult;
import com.licf.app.entity.dto.UsrOrderReview;
import com.licf.app.service.UsrOrderService;
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
@RequestMapping("usr/order")
public class UsrOrderController {

    @Resource
    private UsrOrderService UsrOrderService;

    /**
     * 查看自己的订单
     * @param param    持有查询条件
     * @param pageable 持有排序字段,和分页条件
     * @return RestResponse 持有分页对象
     */
    @GetMapping("/querySelf")
    public RestResponse<DivPageInfo<UsrOrderResult>> querySelf(UsrOrderParam param, @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        param.setSalesman(LoginUtils.getLoginUser().getAccount());
        return RestResponse.success(UsrOrderService.pageList(param, pageable));
    }

    /**
     * 根据条件获取分页list
     * @param param    持有查询条件
     * @param pageable 持有排序字段,和分页条件
     * @return RestResponse 持有分页对象
     */
    @GetMapping
    public RestResponse<DivPageInfo<UsrOrderResult>> page(UsrOrderParam param, @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return RestResponse.success(UsrOrderService.pageList(param, pageable));
    }

    /**
     * 下订单
     * @param param 插入对象
     * @return RestResponse
     */
    @PostMapping("/add")
    public RestResponse add(@Validated(Add.class) @RequestBody UsrOrderParam param) {
        UsrOrderService.add(param);
        return RestResponse.success();
    }

    /**
     * 审核
     * @param param 更新对象
     * @return RestResponse
     */
    @PostMapping
    public RestResponse review(@Validated @RequestBody UsrOrderReview param) {
        UsrOrderService.review(param);
        return RestResponse.success();
    }

    /**
     * 发货
     * @param param 更新对象
     * @return RestResponse
     */
    @PostMapping
    public RestResponse deliver(@Validated @RequestBody UsrOrderDeliver param) {
        UsrOrderService.deliver(param);
        return RestResponse.success();
    }
}
