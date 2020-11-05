package com.common.filter;

import com.common.net.LoginInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lichunfeng
 */
@Configuration
public class WebMvcConfiguration {

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public WebMvcConfigurer webConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // 可添加多个
                registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").excludePathPatterns("/system/login","/system/logout")
                        .excludePathPatterns("/");
            }

        };
    }

    @Bean
    public Converter<String, LocalDateTime> LocalDateTimeConvert() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                source=StringUtils.trim(source);
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                try {
                    return LocalDateTime.parse(source,format);
                } catch (Exception e1) {
                    format =DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    try {
                        return LocalDateTime.parse(source,format);
                    } catch (Exception e2) {
                        format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        try {
                            LocalDate parse = LocalDate.parse(source, format);
                            return LocalDateTime.of(parse, LocalTime.MIN);
                        } catch (Exception e3) {
                            throw new RuntimeException(e3);
                        }
                    }
                }
            }
        };
    }

    /**
     * cors过滤器,设置允许跨域
     * @return cors过滤器
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
