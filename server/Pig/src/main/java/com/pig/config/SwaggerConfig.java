package com.pig.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * @author Blackke
 * @design swagger3配置文件，使用/swagger-ui/#访问
 *
 * 这个配置没有什么作用
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Value("${swagger.enable}")
    private Boolean enable;

    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(enable)
                .groupName("User")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pig.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(tokenScheme()))
                .securityContexts(Collections.singletonList(tokenContext()));
    }


    @SuppressWarnings("all")
    public ApiInfo apiInfo() {
        return new ApiInfo(
                "white's api",
                "redis project",
                "v1.0",
                "848675521@qq.com", //开发者团队的邮箱
                "White",
                "Apache 2.0",  //许可证
                "http://www.apache.org/licenses/LICENSE-2.0" //许可证链接
        );
    }

    private HttpAuthenticationScheme tokenScheme() {
        return HttpAuthenticationScheme.JWT_BEARER_BUILDER.name("Authorization").build();
    }

    private SecurityContext tokenContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(SecurityReference.builder()
                        .scopes(new AuthorizationScope[0])
                        .reference("Authorization")
                        .build()))
                .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                .build();
    }
}
