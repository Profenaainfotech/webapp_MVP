package com.example.Profenaa_touch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(
		scanBasePackages = "com.example.Profenaa_touch",
		exclude = { UserDetailsServiceAutoConfiguration.class }
)
public class ProfenaaTouchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfenaaTouchApplication.class, args);
	}
}
