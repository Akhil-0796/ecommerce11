package com.example.ecommerce.ecommerce;

import com.example.ecommerce.ecommerce.model.User;
import com.example.ecommerce.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.UUID;

@SpringBootApplication
@ComponentScan("com.example.ecommerce.ecommerce.service")
public class EcommerceApplication {

	@Autowired
	public UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}
}
