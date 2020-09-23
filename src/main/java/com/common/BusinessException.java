package com.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lichunfeng
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -4435979898433185113L;
    /** code */
    private String code;
    public BusinessException(String code, String message) {
        super(message);
        this.code=code;
    }

}