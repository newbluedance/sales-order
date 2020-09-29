package com.common.tkmybatis.plugin;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * @description:
 * @author: licf
 * @time: 2020/9/18 9:17
 */
@MappedTypes({String[].class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class ToStringArrayTypeHandler extends BaseToArrayTypeHandler<String>{

    public ToStringArrayTypeHandler(Class<String> type) {
        super(type);
    }
}
