package com.example.fetchMailBoxOwnersMicroServiceCall.microServiceCall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



@SpringBootApplication
@EnableEurekaClient
public class MicroServiceCallApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceCallApplication.class, args);
	}

}
