package com.sabre.api.sacs.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabre.api.sacs.config.ConfigurationConfig;
import com.sabre.api.sacs.rest.common.context.SharedContext;
import com.sabre.api.sacs.rest.domain.leadpricecalendar.LeadPriceCalendarResponse;

/**
 * Module configuration an main class for running test flow.
 */
@SpringBootApplication
@ComponentScan
@RestController
//@EnableEurekaClient
public class Application {
	
//	private static final Logger LOG = LogManager.getLogger(Application.class);
//	private static final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private ApplicationContext ctx;

	public ApplicationContext getApplicationContext(String... args) throws Exception {
		return SpringApplication.run(new Object[] { ConfigurationConfig.class, Application.class }, args);
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(new Object[] { ConfigurationConfig.class, Application.class }, args);
	}
	

}
