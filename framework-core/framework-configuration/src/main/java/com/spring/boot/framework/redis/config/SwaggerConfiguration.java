package com.spring.boot.framework.redis.config;

import com.spring.cloud.framework.constant.CommonConstant;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingying on 2018/8/23.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        List<Parameter> operationParameter = new ArrayList<>();
        ParameterBuilder builder = new ParameterBuilder();
        builder.name("Authorization")
                .defaultValue("Bearer 请求中获取heard中token参数|获取cookie中的x-access-token值")
                .description("Bearer 令牌值")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .build();
        operationParameter.add(builder.build());
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(operationParameter);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Swagger-API")
                .description("https://github.com/itwmang/exercise-parent/wiki")
                .termsOfServiceUrl("https://github.com/itwmang/exercise-parent")
                .contact(new Contact("wmang", "https://github.com/itwmang/exercise-parent", "itfast@yeah.net"))
                .version(CommonConstant.SWAGGER_API_VERSION)
                .build();
    }


}
