package io.letstry.rest.restwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.letstry.rest.restwebservices.modal.PersonV1;
import io.letstry.rest.restwebservices.modal.PersonV2;

@RestController
public class VersioningController {

	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Abhilash Ramachandran");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		PersonV2 personV2 = new PersonV2();
		personV2.setName(personV2.new Name("Abhilash", "Ramachandran"));
		return personV2;
	}
	
	@GetMapping(value = "person/param", params = "v1")
	public PersonV1 personV1Params() {
		return new PersonV1("Abhilash Ramachandran");
	}
	
	@GetMapping(value = "person/param", params = "v2")
	public PersonV2 personV2Params() {
		PersonV2 personV2 = new PersonV2();
		personV2.setName(personV2.new Name("Abhilash", "Ramachandran"));
		return personV2;
	}
	
	@GetMapping(value = "person/header", headers = "x-api-version=1")
	public PersonV1 personV1Header() {
		return new PersonV1("Abhilash Ramachandran");
	}
	
	@GetMapping(value = "person/header", headers = "x-api-version=2")
	public PersonV2 personV2Header() {
		PersonV2 personV2 = new PersonV2();
		personV2.setName(personV2.new Name("Abhilash", "Ramachandran"));
		return personV2;
	}
	
	@GetMapping(value = "person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 personV1Produces() {
		return new PersonV1("Abhilash Ramachandran");
	}
	
	@GetMapping(value = "person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 personV2Produces() {
		PersonV2 personV2 = new PersonV2();
		personV2.setName(personV2.new Name("Abhilash", "Ramachandran"));
		return personV2;
	}
	
}
