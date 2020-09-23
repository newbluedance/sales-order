package com.licf.bgManage.mapper;

import com.licf.bgManage.entity.Attachment;
import com.licf.bgManage.entity.dto.AttachmentParam;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @author lichunfeng
 * @date 2019-06-21 10:23:37
 */
@Mapper
public interface AttachmentMapper extends BaseMapper<Attachment> {

    /**
     * 删除文件
     *
     * @param param
     * @return
     */
    void deleteByModuleData(AttachmentParam param);

}