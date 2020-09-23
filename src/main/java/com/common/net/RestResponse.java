package com.common.net;

import com.common.constants.CodeConstant;
import com.common.utils.MessageUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lichunfeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4118386780685379201L;


    /**
     * 状态码
     */
    private String code = CodeConstant.SUCCESS;

    /**
     * 状态描述
     */
    private String message;

    /**
     * 响应消息体(泛型)
     */
    private T data = null;


    /**
     * 构造方法
     *
     * @param data 响应消息体(泛型)
     */
    public RestResponse(T data) {
        super();
        this.data = data;
    }

    /**
     * 构造方法
     *
     * @param code    状态码
     * @param message 状态描述
     */
    public RestResponse(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    /**
     * 静态构造方法，处理成功
     */
    public static <T> RestResponse<T> success() {
        return new RestResponse<T>(CodeConstant.SUCCESS, MessageUtils.getMessage(CodeConstant.SUCCESS));

    }

    /**
     * 静态构造方法，处理成功
     *
     * @param data 响应消息体(泛型)
     */
    public static <T> RestResponse<T> success(T data) {
        return new RestResponse<T>(CodeConstant.SUCCESS, MessageUtils.getMessage(CodeConstant.SUCCESS), data);
    }
}

