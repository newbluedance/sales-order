package com.common.tkmybatis;


import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

/**
 * @author lichunfeng
 * @since 2019/7/31 10:19
 */
@RegisterMapper
public interface DiyMapper<T> {

    /**
     * 根据Example条件批量更新实体`record`包含的不是null的属性值
     *
     * @return
     */
    @UpdateProvider(type = DiyProvider.class, method = "dynamicSQL")
    int updateBatchByPrimaryKeySelective(List<? extends T> recordList);

    /**
     * 插入 舍弃id和为空的列
     *
     * @param record
     * @return
     */
    @UpdateProvider(type = DiyProvider.class, method = "dynamicSQL")
    int insertSelectiveGeneratedKeys(T record);

}