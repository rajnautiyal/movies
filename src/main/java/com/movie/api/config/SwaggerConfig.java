package com.movie.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Component
public class SwaggerConfig {
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.addServersItem(new Server().url("http://localhost:8080"))
				.addServersItem(new Server().url("http://google.com"))
				.components(
						new Components()
						.addSecuritySchemes("basicScheme",new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"))
						)
				.info(
						new Info()
						.title("Movies API")
						.version("0.0.1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org"))
						);
	}
}
