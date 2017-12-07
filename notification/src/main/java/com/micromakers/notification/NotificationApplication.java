package com.micromakers.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.micromakers.notification.scheduledevent.ScheduledPolling;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
@ComponentScan(basePackages = { "com.micromakers.notification"})
@PropertySource({"classpath:properties/common.properties"})
public class NotificationApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);	
	}
	
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
