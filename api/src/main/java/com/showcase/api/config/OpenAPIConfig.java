package com.showcase.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
							  .title("TODO API")
							  .version("1.0")
							  .description("TODO API Showcase")
							  .contact(new Contact()
											   .name("John Doe")
											   .email("johndoe@email.com")
							  )
				);
	}
}
