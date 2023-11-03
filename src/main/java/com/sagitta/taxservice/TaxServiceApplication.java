package com.sagitta.taxservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TaxServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TaxServiceApplication.class, args);
	}
	
}
