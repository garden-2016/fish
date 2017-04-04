package com.fish.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( basePackages = "com.fish.learn")
public class FishLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishLearnApplication.class, args);
	}
}
