package com.common.net;

import com.common.constants.CodeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lichunfeng
 */
 @Data
 @NoArgsConstructor
 @AllArgsConstructor
public class RestResponseHeader{
    /** 状态码 */
    private String code = CodeConstant.SUCCESS;

    /** 状态描述 */
    private String message;
}