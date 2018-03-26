package com.spring.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("greetings")
	public String getGreetings(@RequestParam String name) {
		return "Hello " + name + " !, How are you today?";
	}
	
}
