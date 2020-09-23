package com.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

/**
 * @author lichunfeng
 */
@Configuration
@Slf4j
public class DatasourceConfig {
    /**
     * 写事务方法前缀
     */
    private static final String[] TX_WRITE_FUNC_LIST = { TxConstants.FUNC_PREFIX_SAVE, TxConstants.FUNC_PREFIX_INSERT,
            TxConstants.FUNC_PREFIX_UPDATE, TxConstants.FUNC_PREFIX_MODIFY, TxConstants.FUNC_PREFIX_REMOVE,
            TxConstants.FUNC_PREFIX_DELETE };

    /**
     * 读事务方法前缀
     */
    private static final String[] READ_FUNC_LIST = { TxConstants.FUNC_PREFIX_QUERY,TxConstants.FUNC_PREFIX_LIST, TxConstants.FUNC_PREFIX_SELECT,
            /*TxConstants.FUNC_PREFIX_GET, */TxConstants.FUNC_PREFIX_COUNT };

    /**
     * 事务拦截属性设置
     *
     * @return Properties
     */
    @Bean
    public Properties txInterceptorProperties() {
        Properties p = new Properties();
        // 默认只读（不符合开发规范的写操作一律屏蔽掉）
        p.setProperty(TxConstants.FUNC_PREFIX_ALL, TxConstants.READ_ONLY);

        // 写操作事务级别
        for (String prefix : TX_WRITE_FUNC_LIST) {
            if (StringUtils.isBlank(prefix)) {
                continue;
            }
            p.setProperty(prefix, TxConstants.LEVEL_PROPAGATION_REQUIRED);
        }

        // 读操作事务级别
        for (String prefix : READ_FUNC_LIST) {
            if (StringUtils.isBlank(prefix)) {
                continue;
            }
            p.setProperty(prefix, TxConstants.LEVEL_PROPAGATION_SUPPORTS);
        }
        return p;
    }

    /**
     * 事务切面设置
     *
     * @param txManager txManager
     * @param txInterceptorProperties txInterceptorProperties
     * @return Advisor
     */
    @Bean
    @Primary
    public Advisor jpaRepositoryAdvisor(PlatformTransactionManager txManager,
                                        @Qualifier("txInterceptorProperties") Properties txInterceptorProperties) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(TxConstants.DEFAULT_TX_AOP_EXPRESSION);
        TransactionInterceptor txInterceptor = new TransactionInterceptor(txManager, txInterceptorProperties);
        txInterceptor.afterPropertiesSet();
        log.debug("Transaction Interceptor use Properties[{}].", txInterceptorProperties);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, txInterceptor);
        advisor.setOrder(TxConstants.DEFAULT_TX_AOP_ORDER);
        return advisor;
    }
}
