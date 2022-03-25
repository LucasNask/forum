package com.ibsoftware.forum.config.swagger;

import com.ibsoftware.forum.modelo.Usuario;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.MultipleOpenApiGroupsCondition;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springdoc.core.SwaggerUiOAuthProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ org.springdoc.core.SpringDocConfigProperties.class,
        org.springdoc.webmvc.core.MultipleOpenApiSupportConfiguration.class,
        org.springdoc.core.SpringDocConfiguration.class, org.springdoc.webmvc.core.SpringDocWebMvcConfiguration.class,
        SwaggerUiConfigParameters.class, SwaggerUiOAuthProperties.class,
        org.springdoc.core.SwaggerUiConfigProperties.class, org.springdoc.core.SwaggerUiOAuthProperties.class,
        org.springdoc.webmvc.ui.SwaggerConfig.class, MultipleOpenApiGroupsCondition.class,
        org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration.class })
public class SwaggerConfigurations {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("topicos")
                .pathsToMatch("/topicos/**","/auth/**")
                .pathsToExclude("/actuator/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("springshop-admin")
                .pathsToMatch("/actuator/**")
                //.addOpenApiMethodFilter(method -> method.isAnnotationPresent(Usuario.class))
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components().addSecuritySchemes("bearer-key",
                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))).info(new Info().title("Spring MVC REST API")
                .contact(new Contact().name("Rasika Kaluwalgoda")).version("1.0.0"));
    }

}