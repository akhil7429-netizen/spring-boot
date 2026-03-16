package com.example.demoAws.HelloController;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloWorld {

	@GetMapping("/path")
	public String HelloAWS() {
		return "Hello EC2";
	}

}
