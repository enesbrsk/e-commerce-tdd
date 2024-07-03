package com.demo.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.demo.**") // MyCommandLineRunner'ın bulunduğu paket
public class CartApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}

}
