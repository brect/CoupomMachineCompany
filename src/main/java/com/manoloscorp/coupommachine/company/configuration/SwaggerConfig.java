package com.manoloscorp.coupommachine.company.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static com.manoloscorp.coupommachine.company.shared.ConfigConstants.BASE_PACKAGE;
import static com.manoloscorp.coupommachine.company.shared.ConfigConstants.DEFAULT_INCLUDE_PATTERN;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
            .paths(regex(DEFAULT_INCLUDE_PATTERN))
            .build()
            .forCodeGeneration(true)
            .apiInfo(metaData());
  }

  private ApiInfo metaData() {
    return new ApiInfo(
            "Coupom Machine Company API",
            "Description of API.",
            "API 1.0.0",
            "Terms of service",
            new Contact("Manolos Corp", "www.manoloscorp.com", "contact@manoloscorp.com"),
            "License of API",
            "API license URL",
            Collections.emptyList());
  }

}
