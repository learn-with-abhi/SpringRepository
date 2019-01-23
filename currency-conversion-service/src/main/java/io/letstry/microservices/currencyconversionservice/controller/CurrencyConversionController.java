package io.letstry.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.letstry.microservices.currencyconversionservice.model.ConversionValue;
import io.letstry.microservices.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;

@RestController
@RequestMapping("currency-conversion")
public class CurrencyConversionController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyConversionController.class);
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("from/{from}/to/{to}/quantity/{quantity}")
	public ConversionValue retrieveConversionValue(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<ConversionValue> forEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", ConversionValue.class, uriVariables);
		ConversionValue conversionValue = forEntity.getBody();
		conversionValue.setQuantity(quantity);
		conversionValue.setTotalAmount(quantity.multiply(conversionValue.getConversionMultiple()));
		LOGGER.info("{}", conversionValue);
		return conversionValue;
	}
	
	@GetMapping("feign/from/{from}/to/{to}/quantity/{quantity}")
	public ConversionValue retrieveConversionValueFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		ConversionValue conversionValue = proxy.retrieveExchangeValue(from, to);
		conversionValue.setQuantity(quantity);
		conversionValue.setTotalAmount(quantity.multiply(conversionValue.getConversionMultiple()));
		LOGGER.info("{}", conversionValue);
		return conversionValue;
	}
}
