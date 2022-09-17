package com.test.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Michael
 * @date 2022-9-1116:38
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        ApiInfo info = new ApiInfoBuilder()
                .contact(new Contact("麦扣", "", "1493404205@qq.com"))
                .title("- 获取股票json数据 - ")
                .description("后端API文档")
                .build();
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(info)
                .select()       //对项目中的所有API接口进行选择
                .apis(RequestHandlerSelectors.basePackage("com.test.controller"))
                .build();
    }
}