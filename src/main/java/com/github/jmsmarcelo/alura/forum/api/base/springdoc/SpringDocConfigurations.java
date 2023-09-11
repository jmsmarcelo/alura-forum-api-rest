package com.github.jmsmarcelo.alura.forum.api.base.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfigurations {
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().components(new Components().addSecuritySchemes(
				"bearer-key", new SecurityScheme().type(SecurityScheme.Type.HTTP)
				.scheme("bearer").bearerFormat("JWT"))).info(new Info().title("Forum Alura API")
						.description("API REST da aplicação Forum Alura")
						.contact(new Contact().name("Jose Marcelo").url("https://github.com/jmsmarcelo")));
	}
}
