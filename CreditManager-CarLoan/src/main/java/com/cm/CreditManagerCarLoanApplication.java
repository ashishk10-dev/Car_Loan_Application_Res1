package com.cm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class CreditManagerCarLoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditManagerCarLoanApplication.class, args);
	}

}
