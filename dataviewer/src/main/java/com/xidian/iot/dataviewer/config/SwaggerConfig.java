package com.xidian.iot.dataviewer.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2  //使swagger2生效
@Configuration   //配置注解，自动在本类上下文加载一些环境变量信息
public class SwaggerConfig extends WebMvcConfigurationSupport {

    private boolean swaggerShow = Boolean.parseBoolean(String.valueOf(true));
    private static final String INDEX_PAGE = "http://localhost:8080/dataviewer/swagger-ui.html";
    private static final String BASE_PACKAGE = "com.xidian.iot.dataviewer.controller";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("所有接口")//总分类标题
                .enable(true)//true或false决定文档是否显示
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xidian.iot.dataviewer.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("xidian.iot.dataviewer RESTful API")   //子标题
                .description("西电轻量级物联网平台接口文档")
                .contact(new Contact("xidian.iot","http://localhost:8080/dataviewer/swagger-ui.html","邮箱"))
                .version("1.1")
                .build();
    }
}