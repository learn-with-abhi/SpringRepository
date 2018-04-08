package com.spring.edc;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
public class EurekaDiscoveryClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaDiscoveryClientApplication.class, args);
	}
}

@RestController
class ServiceInstanceRestController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/actuator/info")
	public ResponseEntity<?> ping() {
		return new ResponseEntity<>("Up & Running", HttpStatus.OK);
	}

	@RequestMapping("/service-instances/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
		return this.discoveryClient.getInstances(applicationName);
	}

	@RequestMapping(value = "/greeting")
	public String greet() {
		List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
		Random rand = new Random();

		int randomNum = rand.nextInt(greetings.size());
		return greetings.get(randomNum);
	}

	@RequestMapping(value = "/")
	public String home() {
		ServiceInstance localInstance = discoveryClient.getLocalServiceInstance();
		return "Hello World: "+ localInstance.getServiceId()+":"+localInstance.getHost()+":"+localInstance.getPort();
	}
}