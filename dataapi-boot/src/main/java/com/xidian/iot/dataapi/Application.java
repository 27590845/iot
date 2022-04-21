package com.xidian.iot.dataapi;

import org.apache.catalina.core.ApplicationFilterConfig;
import org.apache.shiro.web.filter.mgt.SimpleNamedFilterList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationImportListener;
import org.springframework.boot.autoconfigure.AutoConfigurationImportSelector;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

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
//@ImportResource({ "/spring/application-databiz.xml", "/spring/application-activemq-def.xml", "/spring/application-shiro.xml", "/spring/application-datapush.xml"})
@ImportResource({ "/spring/application-databiz.xml", "/spring/application-activemq-def.xml"})
public class Application implements WebMvcConfigurer {
    public static void main(String[] args) throws IOException {
        List<Properties> files = new ArrayList();
//Spring加载META-INF/spring.factories相同方式
        Enumeration<URL> urls = new Application().getClass().getClassLoader().getResources("my.properties");
        while (urls.hasMoreElements()){
            URL url = urls.nextElement();
            Properties properties = new Properties();
            properties.load(url.openStream());
            files.add(properties);
        }


        SpringApplication.run(Application.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");


        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

//    @WebFilter(filterName = "shiroFilter",urlPatterns = "/*", initParams = {@WebInitParam(name = "targetFilterLifecycle", value = "true")})
//    public class ShiroFilter extends DelegatingFilterProxy { }

}
