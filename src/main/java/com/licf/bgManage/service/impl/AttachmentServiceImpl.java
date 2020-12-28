//package com.licf.bgManage.service.impl;
//
//import ch.qos.logback.core.util.FileUtil;
//import com.common.constants.CodeConstant;
//import com.common.constants.YesNoConstant;
//import com.common.net.RestResponse;
//import com.common.validation.group.Select;
//import com.licf.bgManage.entity.Attachment;
//import com.licf.bgManage.entity.dto.AttachmentParam;
//import com.licf.bgManage.mapper.AttachmentMapper;
//import com.licf.bgManage.service.AttachmentService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.time.DateFormatUtils;
//import org.springframework.boot.system.ApplicationHome;
//import org.springframework.stereotype.Service;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.annotation.Resource;
//import java.io.File;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
///**
// * @author lichunfeng
// * @date 2019-06-21 10:23:37
// */
//@Service
//@Slf4j
//public class AttachmentServiceImpl implements AttachmentService {
//
//    @Resource
//    private AttachmentMapper attachmentMapper;
//
//    @Override
//    public List<Attachment> getAttachmentList(String moduleName, String moduleDataCode) {
//        Attachment query = new Attachment();
//        query.setModuleDataCode(moduleDataCode);
//        query.setModuleName(moduleName);
//        query.setDeleted(YesNoConstant.NO);
//        return attachmentMapper.select(query);
//    }
//
//    @Override
//    public RestResponse saveAttachment(MultipartFile file, AttachmentParam param, Integer loginUserId) throws Exception {
//        //删除已有
//        deleteByModuleData(param);
//
//        String bootPath = new ApplicationHome(getClass()).getSource().getParent().concat(File.separator);
//        String subPath = param.getModuleName().concat(File.separator).concat(DateFormatUtils.format(new Date(), "yyyy-MM-dd")).concat(File.separator);
//        String originalFilename = file.getOriginalFilename();
//        String extension = file.getContentType();
//        String targetFileName = UUID.randomUUID().toString();
//        File targetFile = new File(bootPath.concat(subPath).concat(targetFileName));
//        FileUtil.createMissingParentDirectories(targetFile);
//        //把文件保存到硬盘
//        FileCopyUtils.copy(file.getBytes(), targetFile);
//        log.info("文件已保存到:" + targetFile);
//
//        Attachment attachment = new Attachment();
//        // 使用BeanUtils.populate 拷贝相应的属性
//        attachment.setModuleName(param.getModuleName());
//        attachment.setModuleDataCode(param.getModuleDataCode());
//
//        // 设置相应的属性值
//        attachment.setModuleFileType(extension);
//        attachment.setFileName(originalFilename);
//        attachment.setFileSize((long) file.getBytes().length);
//        attachment.setFilePath(subPath.concat(targetFileName));
//        attachment.setCreateTime(LocalDateTime.now());
//        attachment.setCreateUserId(loginUserId);
//        attachmentMapper.insertSelective(attachment);
//
//        return new RestResponse<>(CodeConstant.SUCCESS, null, attachment);
//    }
//
//    @Override
//    public void deleteById(Integer id) {
//        Attachment cri = new Attachment();
//        cri.setId(id);
//        cri.setDeleted(YesNoConstant.YES);
//        attachmentMapper.updateByPrimaryKeySelective(cri);
//    }
//
//    @Override
//    public void deleteByModuleData(@Validated(Select.class) AttachmentParam param) {
//        attachmentMapper.deleteByModuleData(param);
//    }
//}