package com.micromakers.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
@EnableResourceServer
public class FlightsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightsearchApplication.class, args);
    }

    @RequestMapping("/test")
    public String test() {
        return "you are in flight search app";
    }
    
    @RequestMapping("/api/test")
    public String test2(@RequestHeader HttpHeaders headers) {
        return "protected ";
    }
}
