package com.btto.landing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class LandingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandingApplication.class, args);
	}

}
