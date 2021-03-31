package com.xidian.iot.dataapi;

import org.apache.catalina.core.ApplicationFilterConfig;
import org.apache.shiro.web.filter.mgt.SimpleNamedFilterList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * springboot启动类
 * @author: Hansey
 * @date: 2021-01-16 14:45
 */
@EnableZuulProxy
@SpringBootApplication
//@ComponentScan 的作用就是根据定义的扫描路径，把符合扫描规则的类装配到spring容器中
//@ComponentScan(basePackages={"com.xidian.iot.dataapi.**"})
//@EntityScan(value = "com.xidian.iot.database.entity")
//@ImportResource({ "/spring/application-databiz.xml", "/spring/application-activemq-def.xml", "/spring/application-shiro.xml" })
@ImportResource({ "/spring/application-databiz.xml", "/spring/application-activemq-def.xml" })
public class Application implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        SimpleNamedFilterList simpleNamedFilterList;
        ApplicationFilterConfig applicationFilterConfig;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

//    @WebFilter(filterName = "shiroFilter",urlPatterns = "/*", initParams = {@WebInitParam(name = "targetFilterLifecycle", value = "true")})
//    public class ShiroFilter extends DelegatingFilterProxy { }

}
