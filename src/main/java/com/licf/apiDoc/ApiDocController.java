package com.licf.apiDoc;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.licf.bgManage.entity.dto.BgCustomerResult;
import com.licf.bgManage.service.BgCustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author licf
 * @date 2021/1/29
 */
@Controller
@RequestMapping("/apiDoc")
public class ApiDocController {
    @Resource
    private BgCustomerService bgCustomerService;

    @GetMapping
    public ModelAndView index() {
        List<BgCustomerResult> list = bgCustomerService.list(null);
        ModelAndView mv = new ModelAndView();
        mv.addObject("list", list);
        mv.setViewName("blog.html");

        return mv;
    }

    public static void main(String[] args) {

    }


}
