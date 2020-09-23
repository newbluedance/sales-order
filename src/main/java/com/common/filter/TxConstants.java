package com.common.filter;

/**
 * @author lichunfeng
 * @since 2019/6/25 14:45
 */
class TxConstants {
    /**
     * 默认事务切面表达式（默认为com.hdtd下biz包拦截所有方法）
     */
    static final String DEFAULT_TX_AOP_EXPRESSION = "(execution(* com.hdtd..biz..*.*(..)) && "
            + "!@target(org.springframework.transaction.annotation.Transactional) && "
            + "!@annotation(org.springframework.transaction.annotation.Transactional))";
    /**
     * 默认的事务AOP顺序
     */
    static final int DEFAULT_TX_AOP_ORDER = 2147483647;

    /**
     * 只读
     */
    static final String READ_ONLY = "readOnly";
    /**
     * 读写事务级别
     */
    static final String LEVEL_PROPAGATION_REQUIRED = "PROPAGATION_REQUIRED,-Exception";
    /**
     * 只读事务级别
     */
    static final String LEVEL_PROPAGATION_SUPPORTS = "PROPAGATION_SUPPORTS,readOnly,-Exception";

    /**
     * 全局事务方法前缀：*
     */
    static final String FUNC_PREFIX_ALL = "*";
    /**
     * 写事务方法前缀：存储
     */
    static final String FUNC_PREFIX_SAVE = "save*";
    /**
     * 写事务方法前缀：存储
     */
    static final String FUNC_PREFIX_INSERT = "insert*";
    /**
     * 写事务方法前缀：修改
     */
    static final String FUNC_PREFIX_UPDATE = "update*";
    /**
     * 写事务方法前缀：修改
     */
    static final String FUNC_PREFIX_MODIFY = "modify*";
    /**
     * 写事务方法前缀：删除
     */
    static final String FUNC_PREFIX_REMOVE = "remove*";
    /**
     * 写事务方法前缀：删除
     */
    static final String FUNC_PREFIX_DELETE = "delete*";

    /**
     * 读事务方法前缀：装载
     */
    static final String FUNC_PREFIX_QUERY = "query*";

    /**
     * 读事务方法前缀：装载
     */
    static final String FUNC_PREFIX_LIST = "list*";
    /**
     * 读事务方法前缀：查询
     */
    static final String FUNC_PREFIX_SELECT = "select*";
    /**
     * 读事务方法前缀：获取
     */
    static final String FUNC_PREFIX_GET = "get*";
    /**
     * 读事务方法前缀：计数
     */
    static final String FUNC_PREFIX_COUNT = "count*";

}
