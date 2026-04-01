package com.student.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class StudentDetails1Application {
	private static final Logger logger = LoggerFactory.getLogger(StudentDetails1Application.class);
	public static void main(String[] args) {
		SpringApplication.run(StudentDetails1Application.class, args);
		logger.info("...........in StudentDetails1Application");
	}

}
