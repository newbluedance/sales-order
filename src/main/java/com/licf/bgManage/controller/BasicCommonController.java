package com.licf.bgManage.controller;

import com.common.authory.RequiredPermission;
import com.common.base.DivPageInfo;
import com.common.net.RestResponse;
import com.common.utils.RedisHelper;
import com.common.validation.group.Add;
import com.common.validation.group.Update;
import com.licf.bgManage.entity.PubModule;
import com.licf.bgManage.entity.dto.BgBannerParam;
import com.licf.bgManage.entity.dto.BgBannerResult;
import com.licf.bgManage.entity.dto.PubModuleResult;
import com.licf.bgManage.enums.PermitEnum;
import com.licf.bgManage.mapper.PubModuleMapper;
import com.licf.bgManage.mapperstruct.PubModuleConverter;
import com.licf.bgManage.service.BgBannerService;
import com.licf.common.service.BasicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lichunfeng
 * @since 2019/9/4 15:01
 */
@Slf4j
@RestController
@RequestMapping("bgManage/list")
public class BasicCommonController {

    @Resource
    private PubModuleMapper moduleMapper;

    /**
     *
     * @return
     */
    @GetMapping("/module")
    @RequiredPermission(permit = PermitEnum.ListModule)
    public RestResponse<List<PubModuleResult>> module() {
        List<PubModule> pubModules = moduleMapper.selectAll();

        List<PubModuleResult> moduleResults = pubModules.stream().map(t -> PubModuleConverter.INSTANCE.entityToResult(t)).collect(Collectors.toList());
        List<PubModuleResult> roots = moduleResults.stream().filter(t -> t.getParentId() == 0).collect(Collectors.toList());
        Map<Integer, List<PubModuleResult>> childMap = moduleResults.stream().filter(t -> t.getParentId() != 0).collect(Collectors.groupingBy(t -> t.getParentId()));
        BasicService.initChildren(roots, childMap);

        return RestResponse.success(roots);
    }

    /**
     *
     * @return
     */
    @GetMapping("/permit")
    @RequiredPermission(permit = PermitEnum.ListPermit)
    public RestResponse<List<Map>> permit() {
        PermitEnum[] values = PermitEnum.values();
        List<Map> res=new ArrayList<>();
        for (PermitEnum value : values) {
            HashMap<String, Object> map = new HashMap<>(2);
            map.put("id",value.ordinal());
            map.put("name",value.name());
        }

        return RestResponse.success(res);
    }


}
