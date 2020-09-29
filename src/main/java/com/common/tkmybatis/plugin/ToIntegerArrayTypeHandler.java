package com.common.tkmybatis.plugin;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * @description:
 * @author: licf
 * @time: 2020/9/18 9:17
 */
@MappedTypes({Integer[].class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class ToIntegerArrayTypeHandler extends BaseToArrayTypeHandler<Integer> {
    public ToIntegerArrayTypeHandler(Class<Integer> type) {
        super(type);
    }
}
