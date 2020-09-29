package com.common.tkmybatis.plugin;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description:
 * @author: licf
 * @time: 2020/9/16 11:33
 */
public abstract class BaseToArrayTypeHandler<T> extends BaseTypeHandler<T[]> {

    private static final String splitCharset = ",";

    private final Class<T> type;

    public BaseToArrayTypeHandler(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;

    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object[] objects, JdbcType jdbcType) throws SQLException {
        String str = arrayToString(objects);
        ps.setString(i, str);
    }

    @Override
    public T[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        return split(str);
    }

    @Override
    public T[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        return split(str);
    }

    @Override
    public T[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        return split(str);
    }

    /**
     * 数组转String
     * 注：使用提前设定好的分隔符分割数组的每一项
     */
    private static String arrayToString(Object[] array) {
        StringBuilder res = new StringBuilder();
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                res.append(array[i]);
                if (i != array.length - 1)
                    res.append(splitCharset);
            }
        }
        return res.length() > 0 ? res.toString() : null;
    }

    T[] split(String str) {
        String[] split = StringUtils.split(str, splitCharset);
        if (split == null) {
            return null;
        }
        if (type.equals(String[].class)) {
            return (T[]) StringUtils.split(str, splitCharset);
        }
        if (type.equals(Integer[].class)) {

            Integer[] rs = new Integer[split.length];
            for (int i = 0; i < split.length; i++) {
                rs[i] = Integer.parseInt(split[i]);
            }
            return (T[]) rs;
        }
        return null;
    }

}
