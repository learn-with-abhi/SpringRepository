package io.letstry.microservices.currencyconversionservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.letstry.microservices.currencyconversionservice.model.ConversionValue;

//When you use feign client with known url
//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")

//When you use service discovery to provide the url
//@FeignClient(name = "currency-exchange-service")

@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	//path variable has to be mandatorily provided - feign bug
	//@GetMapping("currency-exchange/from/{from}/to/{to}")
	@GetMapping("currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public ConversionValue retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
	
}
