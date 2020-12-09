package com.licf.bgManage.controller;

import com.common.authory.RequiredPermission;
import com.common.net.RestResponse;
import com.common.utils.LoginUtils;
import com.licf.bgManage.entity.PubModule;
import com.licf.bgManage.entity.dto.BgEmployeeResult;
import com.licf.bgManage.entity.dto.PubModuleResult;
import com.licf.bgManage.enums.PermitEnum;
import com.licf.bgManage.mapper.PubModuleMapper;
import com.licf.bgManage.mapperstruct.PubModuleConverter;
import com.licf.common.service.BasicService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @return
     */
    @GetMapping("/permit")
    @RequiredPermission(permit = PermitEnum.ListPermit)
    public RestResponse<List<Map>> permit() {
        PermitEnum[] values = PermitEnum.values();
        List<Map> res = new ArrayList<>();
        for (PermitEnum value : values) {
            HashMap<String, Object> map = new HashMap<>(2);
            map.put("id", value.ordinal());
            map.put("name", value.name());
        }

        return RestResponse.success(res);
    }

    @GetMapping("/menu")
    @RequiredPermission(permit = PermitEnum.ListModule)
    public Map<String, Object> menu() throws Exception {
        HashMap<String, Object> m = new HashMap<>();
        Menu menu1 = new Menu();
        menu1.title = "首页";
        menu1.href = "/main.html";
        m.put("homeInfo", menu1);
        Menu menu12 = new Menu();
        menu12.title = "LAYUI MINI";
        menu12.icon = "/images/logo.png";
        m.put("logoInfo", menu12);

        BgEmployeeResult loginUser = LoginUtils.getLoginUser();
        List<PubModuleResult> modules = loginUser.getModules();

        ArrayList<Object> menuInfo = new ArrayList<>();
        for (PubModuleResult module : modules) {
            Menu menu = new Menu();
            menu.title = module.getName();
            menu.href = "/view/baseList/" + module.getHref();
            menu.image = "fa fa-file-text";
            menuInfo.add(menu);
        }
        m.put("menuInfo", menuInfo);

        return m;
    }

    @Data
    class Menu {
        String title;
        String href;
        String image;
        String icon;
        String target = "_self";
    }


}
