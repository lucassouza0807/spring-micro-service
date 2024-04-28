package com.dizimo.dizimo;

import java.util.HashMap;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class DizimoApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(DizimoApplication.class, args);
	}

	@GetMapping("/")
	public String home() {
		return "Olá";
	}

	@GetMapping("*")
	public String notFound() {
		return "Not found";
	}

}
