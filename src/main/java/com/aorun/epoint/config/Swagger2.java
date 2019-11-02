package com.aorun.epoint.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author zh
 * @ClassName cn.saytime.Swgger2
 * @Description
 * @date 2017-07-10 22:12:31
 */
@Configuration
public class Swagger2 {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.aorun.epoint.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("积分服务api文档")
				.description("restful风格API")
				.termsOfServiceUrl("")
				.version("1.0")
				.build();
	}

	//访问链接：
	//http://localhost:8087/epoint_service/swagger-ui.html#/

}