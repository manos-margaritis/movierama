package com.abandonedlabs.movierama.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * The type Swagger config.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Movierama ap iv 1 docket.
	 *
	 * @return the docket
	 */
	@Bean(name = "MovieRama API")
	public Docket movieramaAPIv1() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("MovieRama API")
				.apiInfo(apiInfo())
				.select()
				.paths(regex("/api/v1.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("MovieRama API")
				.description("(Yet another) social sharing platform")
				.build();
	}
}