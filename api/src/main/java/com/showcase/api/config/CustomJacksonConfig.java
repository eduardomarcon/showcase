package com.showcase.api.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class CustomJacksonConfig {

	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		return mapper;
	}

}
