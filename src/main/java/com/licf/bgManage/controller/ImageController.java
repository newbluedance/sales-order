package com.licf.bgManage.controller;

import com.common.authory.RequiredPermission;
import com.licf.bgManage.enums.PermitEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Paths;

/**
 * @author lichunfeng
 * @since 2019/9/4 15:01
 */
@Slf4j
@RestController
public class ImageController {

    @Resource
    private ResourceLoader resourceLoader;


    /**
     * 根据路径显示图片
     *
     * @param request
     * @return
     */
    @GetMapping("/**")
    @RequiredPermission(permit = PermitEnum.GoodsQuery)
    public ResponseEntity<?> showImg(HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String filePath = new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);

        String bootPath = new ApplicationHome(getClass()).getSource().getParent().concat(File.separator);
        try {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                    .body(resourceLoader.getResource("file:" + Paths.get(bootPath, filePath)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
