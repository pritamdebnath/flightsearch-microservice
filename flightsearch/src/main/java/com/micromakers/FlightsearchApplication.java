package com.micromakers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sabre.api.sacs.config.ConfigurationConfig;
import com.sabre.api.sacs.rest.LeadPriceCalendar;
import com.sabre.api.sacs.rest.common.context.SharedContext;
import com.sabre.api.sacs.rest.domain.leadpricecalendar.LeadPriceCalendarResponse;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = { "com.micromakers", "com.sabre.api.sacs" })
@EnableEurekaClient
@EnableResourceServer
public class FlightsearchApplication {

	@Autowired
	private LeadPriceCalendar leadPriceCalendar;
	@Autowired
	private SharedContext context;

	public static void main(String[] args) {
		SpringApplication.run(new Object[] { ConfigurationConfig.class, FlightsearchApplication.class }, args);
	}

	@RequestMapping(value = "/lowAirfareSearch")
	public LeadPriceCalendarResponse lowAirfareSearch(@RequestParam(value = "origin") String origin,
			@RequestParam(value = "destination") String destination,
			@RequestParam(value = "departureDate") String departureDate,
			@RequestParam(value = "lengthOfStay") int lengthOfStay,
			@RequestParam(value = "minfare", required = false) String minFare,
			@RequestParam(value = "maxfare", required = false) String maxFare) {
		return leadPriceCalendar.doCalendarPricing(context, origin, destination, departureDate, lengthOfStay, minFare,
				maxFare);
	}

	@RequestMapping(value = "/api/lowAirfareSearch")
	public LeadPriceCalendarResponse getLowAirfare(@RequestParam(value = "origin") String origin,
			@RequestParam(value = "destination") String destination,
			@RequestParam(value = "departureDate") String departureDate,
			@RequestParam(value = "lengthOfStay") int lengthOfStay,
			@RequestParam(value = "minfare", required = false) String minFare,
			@RequestParam(value = "maxfare", required = false) String maxFare) {
		return leadPriceCalendar.doCalendarPricing(context, origin, destination, departureDate, lengthOfStay, minFare,
				maxFare);
	}
}
