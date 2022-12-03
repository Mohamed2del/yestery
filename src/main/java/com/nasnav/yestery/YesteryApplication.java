package com.nasnav.yestery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class YesteryApplication {
	public static void main(String[] args) {
		SpringApplication.run(YesteryApplication.class, args);
	}
}
