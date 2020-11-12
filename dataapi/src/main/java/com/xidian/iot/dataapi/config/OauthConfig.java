//package com.xidian.iot.dataapi.config;
//
//import com.xidian.iot.dataapi.interceptor.TokenInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.*;
//
///**
// * 拦截器配置
// * @author: Hansey
// * @date: 2020-10-20 20:27
// */
//@Configuration
//@EnableWebMvc
//public class OauthConfig extends WebMvcConfigurerAdapter implements WebMvcConfigurer {
//
//    @Bean
//    TokenInterceptor tokenInterceptor(){
//        return new TokenInterceptor();
//    }
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //注册TokenInterceptor拦截器
//        InterceptorRegistration registration = registry.addInterceptor(tokenInterceptor());
//        registration.addPathPatterns("/**");                      //所有路径都被拦截
//        registration.excludePathPatterns(                         //添加不拦截路径
//                "/oauth/getAccessToken",      //获取token接口
//                "/**/*.html",            //html静态资源
//                "/**/*.js",              //js静态资源
//                "/**/*.jpg",              //js静态资源
//                "/**/*.png",              //js静态资源
//                "/**/*.css",             //css静态资源
//                "/**/*.woff",
//                "/**/*.ttf",
//                "/**/swagger-resources/**"  //不拦截swagger
//        );
//    }
//}
