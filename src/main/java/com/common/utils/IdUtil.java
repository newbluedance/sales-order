package com.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: licf
 * @time: 2020/9/23 19:32
 */
public class IdUtil {
    /**
     * 主要功能:生成流水号 yyyyMMddHHmmssSSS + 3位随机数
     * 注意事项:无
     *
     * @return 流水号
     */
    public static String createIdByDate() {
        // 精确到毫秒
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String suffix = fmt.format(new Date());
        suffix = suffix + Math.round((Math.random() * 10000));
        return suffix;
    }

}
