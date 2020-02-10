package com.quedacoder.WorkTaskApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WorkTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkTaskApplication.class, args);
	}

}
