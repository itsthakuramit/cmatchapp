package com.hcl.favourite.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select()
				.paths(postPaths()).build();
	}

	@SuppressWarnings("unchecked")
	private Predicate<String> postPaths() {
		return or(regex("/favoriteservice.*"));
	}

	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("CricApp Assignment").description("Swagger for CricApp Assignment - Favourite Service")
				.termsOfServiceUrl("http://hcl.com").contact("amit.raghav@hcl.com").license("Internal License")
				.licenseUrl("amit.raghav@hcl.com").version("1.0").build();
	}
}
