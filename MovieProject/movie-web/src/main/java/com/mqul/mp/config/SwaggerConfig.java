package com.mqul.mp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(
                new ApiInfo("pMovie API Documentation", "Simple movieDB API - created as a side project.",
                        "1.0", null,
                        new Contact("Mohamed Qulateen", "https://github.com/mqulateen", null),
                        "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList())
        );
    }
}