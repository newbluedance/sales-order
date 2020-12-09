package com.licf.page;

import com.common.view.Title;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.util.MetaObjectUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("view")
public class PageController {

    @GetMapping("baseList/{module}")
    public String base(Model model, @PathVariable String module) throws Exception {
        ArrayList<FV> list = new ArrayList<>();

        model.addAttribute("module",  module);
        model.addAttribute("url",  "/bgManage/"+module);
        model.addAttribute("list", list);

        String className = "com.licf.bgManage.entity.dto."+StringUtils.capitalize(module).concat("Result");
        Class<?> aClass = Class.forName(className);

        List<String> properties = Arrays.stream(aClass.getDeclaredFields()).map(Field::getName).collect(Collectors.toList());
        for (String property : properties) {
            Title title = null; //在获取注解
            try {
                title = aClass.getDeclaredField(property).getAnnotation(Title.class);
            } catch (Exception e) {
                continue;
            }
            if (title == null) {
                continue;
            }
            FV fv = new FV();
            fv.field = property;
            fv.title = title.value();
            list.add(fv);
        }

        return "list";
    }

    @GetMapping("baseAdd/{module}")
    public String baseAdd(Model model, @PathVariable String module) throws Exception {
        ArrayList<FV> list = new ArrayList<>();

        model.addAttribute("url",  "/bgManage/"+module);
        model.addAttribute("list", list);

        String className = "com.licf.bgManage.entity.dto."+StringUtils.capitalize(module).concat("Param");
        Class<?> aClass = Class.forName(className);

        List<String> properties = Arrays.stream(aClass.getDeclaredFields()).map(Field::getName).collect(Collectors.toList());
        for (String property : properties) {
            Title title = null; //在获取注解
            try {
                title = aClass.getDeclaredField(property).getAnnotation(Title.class);
            } catch (Exception e) {
                continue;
            }
            if (title == null) {
                continue;
            }
            FV fv = new FV();
            fv.field = property;
            fv.title = title.value();
            list.add(fv);
        }

        return "add";
    }
    @GetMapping("baseDetail/{module}/{id}")
    public String baseDetail(Model model, @PathVariable String module,@PathVariable String id) throws Exception {
        ArrayList<FV> list = new ArrayList<>();

        model.addAttribute("url",  "/bgManage/"+module);
        model.addAttribute("list", list);

        String className = "com.licf.bgManage.entity.dto."+StringUtils.capitalize(module).concat("Param");
        Class<?> aClass = Class.forName(className);

        List<String> properties = Arrays.stream(aClass.getDeclaredFields()).map(Field::getName).collect(Collectors.toList());
        for (String property : properties) {
            Title title = null; //在获取注解
            try {
                title = aClass.getDeclaredField(property).getAnnotation(Title.class);
            } catch (Exception e) {
                continue;
            }
            if (title == null) {
                continue;
            }
            FV fv = new FV();
            fv.field = property;
            fv.title = title.value();
            list.add(fv);
        }

        return "add";
    }

    @Data
    class FV {
        String title;
        String field;
        String value;
        Integer width = 120;
    }
}
