package com.dizimo.dizimo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DizimoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DizimoApplication.class, args);
	}


	@PostMapping("/api/withdraw")
	public String widthdraw(@RequestBody String requestBody){

		return requestBody;
	}

	@GetMapping("/")
	public String home() {
		return "Olá";
	}

	@GetMapping("*")
	public String notFound(){
		return "Not found";
	}


}
