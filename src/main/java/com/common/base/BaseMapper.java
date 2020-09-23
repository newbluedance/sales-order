package com.common.base;

import com.common.tkmybatis.DiyMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.RowBoundsMapper;

/**
 * @author lichunfeng
 */
public interface BaseMapper<T> extends tk.mybatis.mapper.common.BaseMapper<T>, MySqlMapper<T>, IdsMapper<T>,
        ExampleMapper<T>, RowBoundsMapper<T>, DiyMapper<T> {

}

