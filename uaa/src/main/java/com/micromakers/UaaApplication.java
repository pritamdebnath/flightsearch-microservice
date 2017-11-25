package com.micromakers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.micromakers.entity.CustomUserDetails;
import com.micromakers.entity.Role;
import com.micromakers.entity.User;
import com.micromakers.repository.UserRepository;
import com.micromakers.service.UserService;

@SpringBootApplication
@EnableEurekaClient
public class UaaApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(UaaApplication.class, args);
	}

	private UserDetailsService userDetailsService(final UserRepository repository) {
		return username -> new CustomUserDetails(repository.findByUsername(username));
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository,
			UserService service) throws Exception {
		if (repository.count() == 0) {
			service.save(new User(new Long(1), "user", "user", new ArrayList<Role>()));
			service.save(new User(new Long(2), "pritam", "viv123", new ArrayList<Role>()));
			service.save(new User(new Long(3), "lopa", "lopa123", new ArrayList<Role>()));
		}
		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
	}

}
