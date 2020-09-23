package com;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.IOException;

@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
@MapperScan(basePackages = "com.licf.mapper.*")
public class SalesOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesOrderApplication.class, args);
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
        String bootPath = new ApplicationHome(getClass()).getSource().getParent().concat(File.separator) + "uploadtmp";
        try {
            FileUtils.forceMkdir(new File(bootPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        factory.setLocation(bootPath);
        return factory.createMultipartConfig();
    }
}
