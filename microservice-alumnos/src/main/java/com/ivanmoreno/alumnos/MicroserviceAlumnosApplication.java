package com.ivanmoreno.alumnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.ivanmoreno.commons.models.entity"})
public class MicroserviceAlumnosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAlumnosApplication.class, args);
	}

}
