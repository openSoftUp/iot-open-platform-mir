package com.open.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.SpringCloudApplication;

import com.open.iot.annotation.EnableLogging;

@EnableLogging
@SpringBootApplication
public class BizServerApp {
	
	public static void main(String[] args) {
		SpringApplication.run(BizServerApp.class, args);
	}

}
