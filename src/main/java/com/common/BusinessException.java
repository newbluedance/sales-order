package com.common;

import com.common.view.Title;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lichunfeng
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -4435979898433185113L;
    @Title("code")
    private String code;
    public BusinessException(String code, String message) {
        super(message);
        this.code=code;
    }

}