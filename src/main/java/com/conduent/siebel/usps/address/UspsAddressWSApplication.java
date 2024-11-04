package com.conduent.siebel.usps.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.conduent.siebel.usps.address.*"})
public class UspsAddressWSApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(UspsAddressWSApplication.class, args);
	}

}
