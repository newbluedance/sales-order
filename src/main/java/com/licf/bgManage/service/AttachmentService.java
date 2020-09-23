package com.licf.bgManage.service;

import com.common.net.RestResponse;
import com.licf.bgManage.entity.Attachment;
import com.licf.bgManage.entity.dto.AttachmentParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author lichunfeng
 * @date 2019-06-21 10:23:37
 */
public interface AttachmentService {
    /**
     * 根据moduleDataId,String moduleName获取上传的文件
     *
     * @param moduleName     模块的名称
     * @param moduleDataCode 模块的id
     * @return
     */
    List<Attachment> getAttachmentList(String moduleName, String moduleDataCode);

    /**
     * 上传统一逻辑
     *
     * @param file        文件
     * @param param       参数
     * @param loginUserId loginUserId
     * @return
     * @throws Exception
     */
    RestResponse saveAttachment(MultipartFile file, AttachmentParam param, Integer loginUserId) throws Exception;

    /**
     * 删除文件
     *
     * @return
     */
    void deleteById(Integer id);

    /**
     * 删除文件
     *
     * @param param
     * @return
     */
    void deleteByModuleData(AttachmentParam param);
}