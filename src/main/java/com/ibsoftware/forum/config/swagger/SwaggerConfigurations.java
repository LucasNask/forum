package com.ibsoftware.forum.config.swagger;

import com.ibsoftware.forum.modelo.Usuario;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.nio.file.Paths;

@Configuration
public class SwaggerConfigurations {

    @Bean
    public GroupedOpenApi api() {
        return new GroupedOpenApi().builder()
                .select()
                .group("br.com.alura.forum")
                .pathsToMatch("/**")
                .build()
                .ignoredParameterTypes(Usuario.class)
                .globalOperationParameters(
                        Arrays.asList(
                                new ParameterBuilder()
                                        .name("Authorization")
                                        .description("Header para Token JWT")
                                        .modelRef(new ModelRef("string"))
                                        .parameterType("header")
                                        .required(false)
                                        .build()));
    }

}