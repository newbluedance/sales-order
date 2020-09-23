package com.common;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author lichunfeng
 **/
@ControllerAdvice
public class ExceptionHandle {

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Map errorHandler(BusinessException ex) {
        Map map = new HashMap();
        map.put("code", ex.getCode());
        map.put("message", ex.getMessage());
        return map;
    }
}
