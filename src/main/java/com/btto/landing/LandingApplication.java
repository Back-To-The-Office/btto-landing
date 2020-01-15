package com.btto.landing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:btto-landing.properties")
@PropertySource(value = "file:./btto-landing.properties", ignoreResourceNotFound = true)
@Configuration
public class LandingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandingApplication.class, args);
	}

}
