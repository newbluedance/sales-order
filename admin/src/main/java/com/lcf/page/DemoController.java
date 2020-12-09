package com.licf.page;

import com.common.view.Title;
import com.licf.bgManage.entity.dto.BgEmployeeResult;
import lombok.Data;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.util.MetaObjectUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
public class DemoController {

    @GetMapping("page/thymeleaf")
    public String thymeleaf(Model model) {
        User user = new User();
        user.setUsername("jack");
        user.setPassword("112233");
        user.setHobbies(Arrays.asList(new String[]{"singing", "dancing", "football"}));
        Map<String, String> maps = new HashMap<>();
        maps.put("1", "o");
        maps.put("2", "g");
        maps.put("3", "a");
        maps.put("4", "j");
        user.setSecrets(maps);
        model.addAttribute("user", user);
        return "thymeleaf";
    }

    @GetMapping("page/layui")
    public String layui(Model model) {

        return "layui";
    }

    @GetMapping("page/base/{module}")
    public String base(Model model, @PathVariable String module) throws Exception {
        ArrayList<FV> list = new ArrayList<>();

        model.addAttribute("url", "/bgManage/" + module);
        model.addAttribute("list", list);
        MetaObject metaObject = MetaObjectUtil.forObject(new BgEmployeeResult());
        String[] properties = metaObject.getGetterNames();
        for (String property : properties) {
            Title title = null; //在获取注解
            try {
                title = BgEmployeeResult.class.getDeclaredField(property).getAnnotation(Title.class);
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

        return "base";
    }
    @GetMapping("page/layuimini")
    public String index(Model model) throws Exception {

        return "layuimini";
    }

    @Data
    class FV {
        String field;
        String title;
        Integer width = 160;
    }
}
