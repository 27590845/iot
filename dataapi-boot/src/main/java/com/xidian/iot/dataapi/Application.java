package com.xidian.iot.dataapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * springboot启动类
 * @author: Hansey
 * @date: 2021-01-16 14:45
 */
@SpringBootApplication
//@ComponentScan 的作用就是根据定义的扫描路径，把符合扫描规则的类装配到spring容器中
//@ComponentScan(basePackages={"com.xidian.iot.dataapi.**"})
//@EntityScan(value = "com.xidian.iot.database.entity")
@ImportResource({ "/spring/application-databiz.xml", "/spring/application-activemq-def.xml" })
public class Application implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
