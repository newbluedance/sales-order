package com.common.base;

import com.google.common.base.CaseFormat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author lichunfeng
 * @since 2019/7/24 18:19
 */
public interface BaseService<P extends BaseParam, R extends BaseResult> {

    /**
     * 查询
     *
     * @param param param
     * @return list对象
     */
    List<R> list(P param);

    /**
     * 分页查询
     *
     * @param param    param
     * @param pageable pageable
     * @return 分页对象
     */
    DivPageInfo<R> pageList(P param, Pageable pageable);

    /**
     * 新增
     *
     * @param param param
     */
    void insert(P param);

    /**
     * 修改
     *
     * @param param param
     */
    void update(P param);

    /**
     * 删除
     *
     * @param id id
     */
    void deleteById(int id);

    /**
     * 返回格式化的sort String
     *
     * @param pageable
     * @return
     */
    default String formatSortString(Pageable pageable) {
        if (pageable == null || pageable.getSort() == null) {
            return null;
        }
        Sort sort = pageable.getSort();
        if (sort.isSorted()) {
            StringBuilder orderClause = new StringBuilder();
            int count = 0;
            for (Sort.Order order : sort) {
                String property = order.getProperty();
                if (!property.contains("_")) {
                    property = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, property);
                }
                orderClause.append(count++ > 0 ? ", " : "");
                orderClause.append(String.format("%1$s %2$s", property, order.getDirection()));
            }
            return orderClause.toString();
        }
        return null;
    }
}
