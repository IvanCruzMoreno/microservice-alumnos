package com.ivanmoreno.alumnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroserviceAlumnosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAlumnosApplication.class, args);
	}

}
