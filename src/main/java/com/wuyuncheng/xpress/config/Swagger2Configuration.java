package com.wuyuncheng.xpress.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 // 启用 Swagger2
public class Swagger2Configuration {

    @Bean
    public Docket createRestAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wuyuncheng.xpress"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("XBLOG RESTful API 文档")
                .description("Github: https://github.com/wuyc/xpress")
//                .termsOfServiceUrl("https://github.com/wuyc/xblog/blob/master/LICENSE")
                .contact(new Contact("Wu YunCheng", "https://github.com/wuyc", "vincentgo8848+xpress@gmail.com"))
                .version("1.0")
                .build();
    }

}
