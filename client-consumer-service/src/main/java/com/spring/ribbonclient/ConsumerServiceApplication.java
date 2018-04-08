package com.spring.ribbonclient;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
//@RibbonClient(name = "eureka-discovery-client-service", configuration = SayHelloConfiguration.class)
public class ConsumerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerServiceApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/hi")
	public String hi(@RequestParam(value = "name", defaultValue = "Artaban") String name) {
		String greeting = this.restTemplate.getForObject("http://eureka-discovery-client-service/greeting", String.class);
		return String.format("%s, %s!", greeting, name);
	}

	@FeignClient("eureka-discovery-client-service")
	interface HelloClient {
		@RequestMapping(value = "/", method = GET)
		String hello();
	}
}
