package com.licf.bgManage.controller;

import com.common.authory.RequiredPermission;
import com.common.base.DivPageInfo;
import com.common.net.RestResponse;
import com.common.utils.RedisHelper;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import com.licf.bgManage.entity.dto.BgGoodsParam;
import com.licf.bgManage.entity.dto.BgGoodsResult;
import com.licf.bgManage.enums.PermitEnum;
import com.licf.bgManage.service.BgGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * @author lichunfeng
 * @since 2019/9/4 15:01
 */
@Slf4j
@RestController
@RequestMapping("bgManage/bgGoods")
public class BgGoodsController {

    @Resource
    private BgGoodsService bgGoodsService;

    /**
     * 根据条件获取分页list
     *
     * @param param    持有查询条件
     * @param pageable 持有排序字段,和分页条件
     * @return RestResponse 持有分页对象
     */
    @GetMapping
    @RequiredPermission(permit = PermitEnum.GoodsQuery)
    public RestResponse<DivPageInfo<BgGoodsResult>> page(BgGoodsParam param, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return RestResponse.success(bgGoodsService.pageList(param, pageable));
    }

    /**
     * 文件上传
     *
     * @param file  上传的文件
     * @param param 其他参数
     */
    @PostMapping("/save")
    @RequiredPermission(permit = PermitEnum.GoodsInsert)
    public RestResponse save(@RequestParam MultipartFile file, @RequestParam HashMap<String, String> param) throws InvocationTargetException, IllegalAccessException, IOException {
        BgGoodsParam bgGoodsParam = new BgGoodsParam();
        BeanUtils.populate(bgGoodsParam, param);

        bgGoodsService.save(file, bgGoodsParam);
        return RestResponse.success();

    }

    /**
     * 新增
     *
     * @param param 插入对象
     * @return RestResponse
     */
    @PostMapping
    @RequiredPermission(permit = PermitEnum.GoodsInsert)
    public RestResponse insert(@Validated(Add.class) @RequestBody BgGoodsParam param) {
        bgGoodsService.insert(param);
        return RestResponse.success();
    }

    /**
     * 更新
     *
     * @param param 更新对象
     * @return RestResponse
     */
    @PutMapping
    @RequiredPermission(permit = PermitEnum.GoodsUpdate)
    public RestResponse update(@Validated(Update.class) @RequestBody BgGoodsParam param) {
        RedisHelper.clearGood(param.getId());
        bgGoodsService.update(param);
        return RestResponse.success();
    }

    /**
     * 删除单个
     *
     * @param id id
     * @return RestResponse
     */
    @DeleteMapping("/{id}")
    @RequiredPermission(permit = PermitEnum.GoodsDelete)
    public RestResponse deleteBgGoods(@PathVariable int id) {
        RedisHelper.clearGood(id);
        bgGoodsService.deleteById(id);
        return RestResponse.success();
    }
}
