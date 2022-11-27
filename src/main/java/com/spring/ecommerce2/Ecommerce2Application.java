package com.spring.ecommerce2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties({FileStorageProperties.class})
public class Ecommerce2Application {

	public static void main(String[] args) {
		SpringApplication.run(Ecommerce2Application.class, args);
	}

}
