package com;

import com.common.utils.RedisHelper;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.IOException;

@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
@MapperScan(basePackages = "com.licf.app.mapper.*")
public class SalesOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesOrderApplication.class, args);
        //清空redis
        RedisHelper.fluShall();
    }

    /**
     * 解决文件上传,临时文件夹被程序自动删除问题
     * <p>
     * 文件上传时自定义临时路径
     *
     * @return
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //2.该处就是指定的路径
        String bootPath = new ApplicationHome(getClass()).getSource().getParent().concat(File.separator) + "images";
        try {
            FileUtils.forceMkdir(new File(bootPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        factory.setLocation(bootPath);
        return factory.createMultipartConfig();
    }
//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(redirectConnector());
//        return tomcat;
//    }
//
//    private Connector redirectConnector() {
//        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//        connector.setScheme("http");
//        connector.setPort(28089);
//        connector.setSecure(false);
//        connector.setRedirectPort(443);
//        return connector;
//    }
}
