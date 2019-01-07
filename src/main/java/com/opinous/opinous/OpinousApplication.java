package com.opinous.opinous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class}, scanBasePackages = {"com.opinous.security", "com.opinous.service.impl", "com.opinous.controller", "com.opinous.controller.admin", "com.opinous.validator"})
@EntityScan({"com.opinous.model"})
@EnableJpaRepositories(basePackages = {"com.opinous.repository"})
public class OpinousApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(OpinousApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(OpinousApplication.class, args);
	}

}

