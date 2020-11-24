package com.common.base;

import com.common.authory.SCondition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.data.domain.Pageable;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.MetaObjectUtil;

import java.util.List;

/**
 * @author lichunfeng
 * @since 2019/7/31 10:19
 */
public abstract class BaseServiceImpl<E extends BaseEntity, P extends BaseParam, R extends BaseResult> implements BaseService<P, R> {

    //    @Autowired
    protected BaseMapper<E> mapper;

    //    @Autowired
    protected BaseConverter<E, P, R> converter;

    public void initMapperConverter(BaseMapper<E> mapper, BaseConverter<E, P, R> converter) {
        this.mapper = mapper;
        this.converter = converter;
    }

    @Override
    public List<R> list(P param) {
        return converter.entityToResult(mapper.select(converter.paramToEntity(param)));
    }

    @Override
    public DivPageInfo<R> pageList(P param, Pageable pageable) {
        E e = converter.paramToEntity(param);
        Example example = new Example(e.getClass());

        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            MetaObject metaObject = MetaObjectUtil.forObject(param);
            String[] properties = metaObject.getGetterNames();
            for (String property : properties) {
                Object value = metaObject.getValue(property);
                //属性值不为空
                if (value != null && StringUtils.isNotBlank(value.toString())) {
                    SCondition sCondition = null;
                    try {
                        sCondition = param.getClass().getDeclaredField(property).getAnnotation(SCondition.class); //在获取注解
                    } catch (NoSuchFieldException noSuchFieldException) {
                        noSuchFieldException.printStackTrace();
                    }

                    if (sCondition != null && "like".equals(sCondition.value())) {
                        criteria.andLike(property, "%" + value + "%");
                    } else if(sCondition != null && sCondition.value().endsWith("<")){
                        criteria.andLessThanOrEqualTo(sCondition.value().replace("<",""), value);
                    }else if(sCondition != null && sCondition.value().endsWith(">")){
                        criteria.andGreaterThanOrEqualTo(sCondition.value().replace(">",""),value);
                    }else {
                        criteria.andEqualTo(property, value);
                    }

                }
            }
        }


        example.setOrderByClause(formatSortString(pageable));
        /// ...doSelectPageInfo(() -> converter.entityToResult(mapper.selectByExample(example)));
        //上面这种写法不能真正转换类型,
        //PageHelper.startPage(); .. list = converter.entityToResult(mapper.selectByExample(example); PageInfo<> page = new PageInfo<>(list);
        //上面的写法 中途转换类型,会导致count等信息失去, 故这里采用以下写法
        PageInfo<E> pageInfo = PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize()).doSelectPageInfo(() -> mapper.selectByExample(example));
        return pageConverter(pageInfo);
    }

    @Override
    public void insert(P param) {
        //这里用带Selective的方法，null的属性不会保存，会使用数据库默认值
        mapper.insertSelectiveGeneratedKeys(converter.paramToEntity(param));
    }

    @Override
    public void update(P param) {
        //这里用带Selective的方法,根据主键更新属性不为null的值
        mapper.updateByPrimaryKeySelective(converter.paramToEntity(param));
    }

    @Override
    public void deleteById(int id) {
        mapper.deleteByPrimaryKey(id);
    }

    protected DivPageInfo<R> pageConverter(PageInfo<E> pageInfo) {
        DivPageInfo<R> page = new DivPageInfo<>();
        page.setList(converter.entityToResult(pageInfo.getList()));
        page.setPageNum(pageInfo.getPageNum());
        page.setPageSize(pageInfo.getPageSize());
        page.setPages(pageInfo.getPages());
        page.setSize(pageInfo.getSize());
        page.setTotal(pageInfo.getTotal());
        return page;

    }

}
