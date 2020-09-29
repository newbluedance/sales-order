package com.common.base;

import java.util.List;

/**
 * 示例DTO转换接口
 *
 * @author lichunfeng
 */
public interface BaseConverter<E extends BaseEntity, P extends BaseParam, R extends BaseResult> {


    /**
     * 实体类转result
     *
     * @param entity
     * @return dto
     */
    R entityToResult(E entity);

    /**
     * 实体类转result
     *
     * @param entity
     * @return dto
     */
    List<R> entityToResult(List<E> entity);

    /**
     * param转成实体类
     *
     * @param param
     * @return entity
     */
    E paramToEntity(P param);

    /**
     * param转成实体类
     *
     * @param param
     * @return entity
     */
    List<E> paramToEntity(List<P> param);

    /**
     * result转成实体类
     *
     * @param std
     * @return
     */
    E resultToEntity(R std);

    /**
     * result转成实体类
     *
     * @param std
     * @return
     */
    List<E> resultToEntity(List<R> std);

    /**
     * result 转 param
     *
     * @param std
     * @return
     */
    P resultToParam(R std);

    /**
     * result 转 param
     *
     * @param std
     * @return
     */
    List<P> resultToParam(List<R> std);

    /**
     * result 转 param
     *
     * @param std
     * @return
     */
    R paramToResult(P std);

    /**
     * result 转 param
     *
     * @param std
     * @return
     */
    List<R> paramToResult(List<P> std);


}
