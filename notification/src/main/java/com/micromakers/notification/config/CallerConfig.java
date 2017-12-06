package com.micromakers.notification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource({"classpath:properties/common.properties"})
public class CallerConfig {
	
	@Bean
	public RestTemplate caller() {
		RestTemplate template = new RestTemplate();
		template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		return template;
	}
	
	@Bean
	public PropertySourcesPlaceholderConfigurer placeHolder() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
