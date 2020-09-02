package com.xidian.iot.datacenter;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

/**
 * @author mrl
 * @Title: Application
 * @Package com.xidian.iot.datacenter
 * @Description: app initer for datacenter
 * @date 2020/8/319:01 下午
 */
public class Application extends ResourceConfig {

    public Application() {
        // register application resources
        packages("com.xidian.iot.datacenter.resource");
        // register filters
        register(RequestContextFilter.class);
        register(JacksonJsonProvider.class);
        //注册文件上传模块
//        register(MultiPartFeature.class);
//        register(AuthorizationFilter.class);
//        register(VisitorAuthorizationFilter.class);
//        register(CorsFilter.class);
    }
}
