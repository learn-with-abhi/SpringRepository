package io.letstry.rest.restwebservices.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {

	@Autowired
	private MessageSource messageSource;

	// Instead of getting locale header in every request we can get it from the
	// LocaleContextHolder which inturn gets from the header
	/*@GetMapping("/hello")
	public String helloWorldInternationalized(
			@RequestHeader(value = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}*/
	
	@GetMapping("/hello")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}
