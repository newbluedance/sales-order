package com.common.utils;

import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author lichunfeng
 * @date 2019-05-29
 **/
@Component
public class MessageUtils {

    private static MessageUtils messageUtils;

    @Resource
    private MessageSource messageSource;

    @PostConstruct
    public void init() {
        messageUtils = this;
        messageUtils.messageSource = this.messageSource;
    }
    public MessageUtils() {}


    /**
     * 根据code获取message
     *
     * @param code code
     * @return message
     */
    public static String getMessage(String code) {
        return getMessage(code, null);
    }

    /**
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @return
     */
    public static String getMessage(String code, Object[] args) {
        return getMessage(code, args, "");
    }

    /**
     *
     * @param code 对应messages配置的key.
     * @param args 数组参数.
     * @param defaultMessage 没有设置key的时候的默认值.
     * @return message
     */
    public static String getMessage(String code, Object[] args, String defaultMessage) {
        //这里使用比较方便的方法，不依赖request.
        Locale locale = LocaleContextHolder.getLocale();
        final String msg = "[" + code + "]" + messageUtils.messageSource.getMessage(code, args, defaultMessage, locale);
        return msg;
    }

}
