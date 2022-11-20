package com.im2.brickback.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


// https://swagger.io/docs/specification/about/
// http://localhost:8080/swagger-ui/index.html

//        Docket: Swagger 설정의 핵심이 되는 Bean
//        useDefaultResponseMessages : Swagger 에서 제공해주는 기본 응답 코드 (200, 401, 403, 404)
//        false : 기본 응답 코드 노출하지 않음
//        apis : api 스펙이 작성되어 있는 패키지(Controller)를 지정
//        paths : apis 에 있는 API 중 특정 path 를 선택
//        apiInfo : Swagger UI로 노출할 정보

@Configuration
@EnableWebMvc //@EnableSwagger2 : swagger 2.0 버전
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30) // new Docket(DocumentationType.SWAGGER_2) //2.0
                .useDefaultResponseMessages(false) // Swagger 에서 제공해주는 기본 응답 코드 (200, 401, 403, 404) 등의 노출 여부
                .apiInfo(apiInfo()) // Swagger UI 로 노출할 정보
                .select()
                .apis(RequestHandlerSelectors.any())
                //.apis(RequestHandlerSelectors.basePackage("com.im2.brickback.SampleController")) // api 스펙이 작성되어 있는 패키지 (controller)
                .paths(PathSelectors.any()) // apis 에 위치하는 API 중 특정 path 를 선택
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot Practice Rest API Documentation")
                .description("springboot rest api practice.")
                .version("0.1")
                .build();
    }

}