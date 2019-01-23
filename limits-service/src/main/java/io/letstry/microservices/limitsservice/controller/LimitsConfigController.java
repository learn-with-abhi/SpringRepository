package io.letstry.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.letstry.microservices.limitsservice.properties.config.LimitConfiguration;

@RestController
public class LimitsConfigController {
	
	@Autowired
	private LimitConfiguration limitConfiguration;
	
	@GetMapping("limits")
	public LimitConfiguration getLimitsFromConfig() {
		return limitConfiguration;
	}
	
	@GetMapping("limits-faulty")
	@HystrixCommand(fallbackMethod = "fallbackGetLimitsFromConfig")
	public LimitConfiguration getLimitsFromConfigfaulty() {
		throw new RuntimeException();
	}
	
	public LimitConfiguration fallbackGetLimitsFromConfig() {
		return limitConfiguration;
	}

}
