package com.licf.app.controller;

import com.common.authory.RequiredPermission;
import com.common.base.DivPageInfo;
import com.common.net.RestResponse;
import com.common.utils.LoginUtils;
import com.common.validation.group.Add;
import com.licf.app.entity.dto.*;
import com.licf.app.service.UsrOrderService;
import com.licf.bgManage.entity.dto.BgEmployeeResult;
import com.licf.bgManage.enums.EOrderStatus;
import com.licf.bgManage.enums.PermitEnum;
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
@RequestMapping("usr/order")
public class UsrOrderController {

    @Resource
    private UsrOrderService UsrOrderService;

    /**
     * 查看自己的订单
     *
     * @param param    持有查询条件
     * @param pageable 持有排序字段,和分页条件
     * @return RestResponse 持有分页对象
     */
    @GetMapping("/querySelf")
    @RequiredPermission(permit = PermitEnum.OrderQuerySelf)
    public RestResponse<DivPageInfo<UsrOrderResult>> querySelf(UsrOrderParam param, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        param.setSalesman(LoginUtils.getLoginUser().getId());
        return RestResponse.success(UsrOrderService.pageList(param, pageable));
    }

    /**
     * 根据条件获取分页list
     *
     * @param param    持有查询条件
     * @param pageable 持有排序字段,和分页条件
     * @return RestResponse 持有分页对象
     */
    @GetMapping
    @RequiredPermission(permit = PermitEnum.OrderQuery)
    public RestResponse<DivPageInfo<UsrOrderResult>> page(UsrOrderParam param, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        BgEmployeeResult loginUser = LoginUtils.getLoginUser();
        if (ArrayUtils.contains(loginUser.getRoleIds(), 1)) {
        } else if (ArrayUtils.contains(loginUser.getRoleIds(), 3)) {
            // 如果是主管查看当前部门的
            param.setDepartmentId(loginUser.getDepartmentId());
        } else {
            // 如果是业务员 查看自己的
            param.setSalesman(loginUser.getId());
        }
        //select * from usr_order where JSON_CONTAINS(json_extract(order_goods_shot,'$[*].goodName') ,'"清温解毒散"','$')
        return RestResponse.success(UsrOrderService.pageList(param, pageable));
    }

    /**
     * 下订单
     *
     * @param param 插入对象
     * @return RestResponse
     */
    @PostMapping
    @RequiredPermission(permit = PermitEnum.OrderAdd)
    public RestResponse add(@Validated(Add.class) @RequestBody UsrOrderParam param) {
        UsrOrderService.add(param);
        return RestResponse.success();
    }

    /**
     * 主管审核
     *
     * @param param 更新对象
     * @return RestResponse
     */
    @PostMapping("/leaderReview")
    @RequiredPermission(permit = PermitEnum.leaderReview)
    public RestResponse leaderReview(@Validated @RequestBody UsrOrderReview param) throws NoSuchFieldException {
        UsrOrderService.review(param, EOrderStatus.PENDING_LEADER_REVIEW);
        return RestResponse.success();
    }

    /**
     * 后勤 审核
     *
     * @param param 更新对象
     * @return RestResponse
     */
    @PostMapping("/storageReview")
    @RequiredPermission(permit = PermitEnum.storageReview)
    public RestResponse storageReview(@Validated @RequestBody UsrOrderReview param) throws NoSuchFieldException {
        UsrOrderService.review(param, EOrderStatus.PENDING_STORAGE_REVIEW);
        return RestResponse.success();
    }

    /**
     * 发货员 审核
     *
     * @param param 更新对象
     * @return RestResponse
     */
    @PostMapping("/deliverReview")
    @RequiredPermission(permit = PermitEnum.deliverReview)
    public RestResponse deliverReview(@Validated @RequestBody UsrOrderReview param) throws NoSuchFieldException {
        UsrOrderService.review(param, EOrderStatus.PENDING_DELIVER_REVIEW);
        return RestResponse.success();
    }

    /**
     * 生成单号
     *
     * @param param 订单号
     * @return RestResponse
     */
    @PostMapping("/generateMailNo")
    @RequiredPermission(permit = PermitEnum.OrderDeliver)
    RestResponse generateMailNo(@Validated @RequestBody UsrOrderDeliver param) {
        return RestResponse.success(UsrOrderService.generateMailNo(param));
    }

    /**
     * 发货
     *
     * @param param 更新对象
     * @return RestResponse
     */
    @PostMapping("/deliver")
    @RequiredPermission(permit = PermitEnum.OrderDeliver)
    public RestResponse deliver(@Validated @RequestBody UsrOrderDeliver param) throws NoSuchFieldException {
        UsrOrderService.deliver(param);
        return RestResponse.success();
    }

    /**
     * 核销
     *
     * @param param 更新对象
     * @return RestResponse
     */
    @PostMapping("/writeOff")
    @RequiredPermission(permit = PermitEnum.OrderWriteOff)
    public RestResponse writeOff(@Validated @RequestBody UsrOrderWriteOff param) {
        UsrOrderService.writeOff(param);
        return RestResponse.success();
    }
}
