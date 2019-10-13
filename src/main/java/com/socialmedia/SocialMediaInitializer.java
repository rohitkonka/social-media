package com.socialmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.socialmedia")
public class SocialMediaInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaInitializer.class, args);
	}
}
