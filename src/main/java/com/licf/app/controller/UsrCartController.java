package com.licf.app.controller;

import com.common.net.RestResponse;
import com.licf.app.entity.dto.UsrCartParam;
import com.licf.app.entity.dto.UsrCartResult;
import com.licf.app.service.UsrCartService;
import com.licf.bgManage.entity.BgGoods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: licf
 * @time: 2020/9/16 18:38
 */
@Slf4j
@RestController
@RequestMapping("usr/cart")
public class UsrCartController {

    @Resource
    private UsrCartService usrCartService;

    /**
     * 查看购物车
     * @return
     */
    @GetMapping("/query")
    public RestResponse<List<UsrCartResult>> query() {
        return RestResponse.success(usrCartService.query());
    }

    /**
     * 保存
     * @param param 更新对象 商品id 数量
     * @return RestResponse
     */
    @PostMapping("/save")
    public RestResponse save(@RequestBody UsrCartParam param) {
        usrCartService.save(param);
        return RestResponse.success();
    }

}
