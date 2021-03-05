package com.licf.apiDoc;

import com.jfinal.template.ext.spring.JFinalViewResolver;
import com.jfinal.template.source.ClassPathSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;

/**
 * @author licf
 * @date 2021/1/29
 */
@Configuration
public class JFinalEnjoyConfig {

    @Bean
    public ViewResolver viewResolver() {
        JFinalViewResolver resolver = new JFinalViewResolver();
        resolver.setBaseTemplatePath(null);
        resolver.setSourceFactory(new ClassPathSourceFactory());
        // or
        // resolver.getEngine().setToClassPathSourceFactory();
        resolver.setDevMode(true);
        return resolver;
    }
}