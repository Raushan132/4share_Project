package com.share.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.share.app.repository")
@EntityScan("com.share.app.entity")
public class BootDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(BootDemo1Application.class, args);
	}

}
