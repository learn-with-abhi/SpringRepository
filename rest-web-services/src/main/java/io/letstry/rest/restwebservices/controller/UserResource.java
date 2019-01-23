package io.letstry.rest.restwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.letstry.rest.restwebservices.exception.UserNotFoundException;
import io.letstry.rest.restwebservices.modal.User;
import io.letstry.rest.restwebservices.service.UserDaoService;

@RestController
public class UserResource {

	private UserDaoService userDaoService;

	@Autowired
	public void setUserDaoService(UserDaoService userDaoService) {
		this.userDaoService = userDaoService;
	}

	@GetMapping("users")
	public List<User> listUsers() {
		return userDaoService.findAll();
	}

	@GetMapping("users/{id}")
	public Resource<User> findUser(@PathVariable int id) {
		Resource<User> resource = new Resource<>(userDaoService.findOne(id));
		ControllerLinkBuilder link = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).listUsers());
		resource.add(link.withRel("list-users"));
		return resource;
	}

	@PostMapping("users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDaoService.save(user).getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable int id) {
		Optional.of(userDaoService.deleteById(id)).orElseThrow(() -> new UserNotFoundException("id-" + id));
	}
}
