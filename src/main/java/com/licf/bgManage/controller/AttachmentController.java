//package com.licf.bgManage.controller;
//
//import com.common.BusinessException;
//import com.common.authory.RequiredPermission;
//import com.common.constants.CodeConstant;
//import com.common.net.RestResponse;
//import com.common.utils.MessageUtils;
//import com.common.validation.group.Select;
//import com.licf.bgManage.entity.Attachment;
//import com.licf.bgManage.entity.dto.AttachmentParam;
//import com.licf.bgManage.enums.PermitEnum;
//import com.licf.bgManage.service.AttachmentService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.beanutils.BeanUtils;
//import org.springframework.boot.system.ApplicationHome;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.HandlerMapping;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.io.File;
//import java.nio.file.Paths;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * @author lichunfeng
// */
//@Slf4j
//@RestController
//@RequestMapping("/business")
//public class AttachmentController {
//
//    @Resource
//    private AttachmentService attachmentService;
//    @Resource
//    private ResourceLoader resourceLoader;
//
//    /**
//     * 返回附件list
//     *
//     * @author
//     */
//    @GetMapping("/attachment")
//    @RequiredPermission(permit = PermitEnum.CategoryQuery)
//    public RestResponse<List<Attachment>> queryList(@Validated(Select.class) AttachmentParam param) {
//        List<Attachment> attachmentList = attachmentService.getAttachmentList(param.getModuleName(), param.getModuleDataCode());
//
//        return RestResponse.success(attachmentList);
//
//    }
//
//    /**
//     * 根据路径显示图片
//     *
//     * @param request
//     * @return
//     */
//    @GetMapping("/img/**")
//    @RequiredPermission(permit = PermitEnum.CategoryQuery)
//    public ResponseEntity<?> showImg(HttpServletRequest request) {
//        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
//        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
//        String filePath = new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
//
//        String bootPath = new ApplicationHome(getClass()).getSource().getParent().concat(File.separator);
//        try {
//            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
//                    .body(resourceLoader.getResource("file:" + Paths.get(bootPath, filePath)));
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    /**
//     * 文件上传
//     *
//     * @param file  上传的文件
//     * @param param 其他参数
//     */
//    @PostMapping("/attachment")
//    @RequiredPermission(permit = PermitEnum.CategoryInsert)
//    public RestResponse upload(@RequestParam MultipartFile file, @RequestParam HashMap<String, String> param) {
//        try {
//            AttachmentParam attachmentParam = new AttachmentParam();
//            BeanUtils.populate(attachmentParam, param);
//
//            return attachmentService.saveAttachment(file, attachmentParam, null);
//        } catch (BusinessException businessException) {
//            throw businessException;
//        } catch (Exception e) {
//            log.error("文件上传失败:" + e.getMessage(), e);
//            e.printStackTrace();
//            throw new BusinessException(CodeConstant.ERROR_PARAMETER,
//                    MessageUtils.getMessage(CodeConstant.ERROR_PARAMETER));
//        }
//    }
//
//    /**
//     * 删除文件
//     *
//     * @return RestResponse
//     */
//    @DeleteMapping("/attachment/{id}")
//    public RestResponse deleteFile(@PathVariable int id) {
//        attachmentService.deleteById(id);
//        return RestResponse.success();
//    }
//
//}
