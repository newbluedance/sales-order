package com.common.net;

import com.common.constants.CodeConstant;
import com.common.view.Title;
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
    @Title("状态码")
    private String code = CodeConstant.SUCCESS;

    @Title("状态描述")
    private String message;
}