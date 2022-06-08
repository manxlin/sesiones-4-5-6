package com.example.sesiones456.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

//HTML:     http://localhost:8083/swagger-ui/index.html
//JSON:     http://localhost:8083/v2/api-docs

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api (){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiDetails() {
        return new ApiInfo("Spring Boot API LAPTOP",
                "LAPTOPS",
                "1.0",
                "https://www.google.com",
                new Contact("John Doe", "https://www.google.com", "johndoe@gmail.com"),
                "MIT",
                "https://www.google.com",
                Collections.emptyList());
    }

}
