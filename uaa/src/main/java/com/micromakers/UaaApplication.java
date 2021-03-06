package com.micromakers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.micromakers.entity.CustomUserDetails;
import com.micromakers.entity.Role;
import com.micromakers.entity.User;
import com.micromakers.repository.UserRepository;
import com.micromakers.service.UserService;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class UaaApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserService service;

	public static void main(String[] args) {
		SpringApplication.run(UaaApplication.class, args);
	}

	private UserDetailsService userDetailsService(final UserRepository repository) {
		return username -> new CustomUserDetails(repository.findByUsername(username));
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/findall")
	List<User> allUsers() {
		return repository.findAll();
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	User save(@RequestBody User user) {
		return service.save(user);
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/findByUsername")
	User save(@RequestParam String username) {
		return repository.findByUsername(username);
	}

}
