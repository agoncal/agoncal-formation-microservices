package org.bookstore.generator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Bean
    public Docket apiDocket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.bookstore.generator"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
        docket.protocols(new HashSet<>(Arrays.asList("http", "https")));
        return docket;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Number Generator API",
                "Allows you to get generated numbers for books",
                "1.0",
                "https://termsfeed.com/terms-service",
                new Contact("BookStore Inc", "http://bookstore.com", "contact@bookstore.com"),
                "MIT",
                "https://opensource.org/licenses/MIT",
                Collections.emptyList()
        );
    }
}
