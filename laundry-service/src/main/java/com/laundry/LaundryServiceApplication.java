package com.laundry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.laundry.ordermgmt.*")
@ComponentScan(basePackages = { "com.laundry.ordermgmt.*" })
@EntityScan("com.laundry.ordermgmt.*")
public class LaundryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaundryServiceApplication.class, args);
	}

}
