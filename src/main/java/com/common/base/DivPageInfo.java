package com.common.base;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: licf
 * @time: 2020/8/22 17:06
 */
@Data
public class DivPageInfo<T> {
    protected long total;
    protected List<T> list;
    private int pageNum;
    private int pageSize;
    private int size;
    private int pages;
}
