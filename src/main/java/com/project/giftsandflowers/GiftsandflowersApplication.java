package com.project.giftsandflowers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class GiftsandflowersApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiftsandflowersApplication.class, args);
	}

}
