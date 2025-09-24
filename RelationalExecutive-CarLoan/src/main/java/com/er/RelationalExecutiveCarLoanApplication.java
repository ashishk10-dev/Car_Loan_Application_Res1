package com.er;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class RelationalExecutiveCarLoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelationalExecutiveCarLoanApplication.class, args);
	}

}
