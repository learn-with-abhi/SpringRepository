package io.letstry.rest.restwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.letstry.rest.restwebservices.exception.UserNotFoundException;
import io.letstry.rest.restwebservices.modal.Post;
import io.letstry.rest.restwebservices.modal.User;
import io.letstry.rest.restwebservices.repository.PostRepository;
import io.letstry.rest.restwebservices.repository.UserRepository;
import io.letstry.rest.restwebservices.service.UserDaoService;

@RestController
@RequestMapping("jpa")
public class UserJPAResource {

	private UserRepository userRepository;
	private PostRepository postRepository;

	@Autowired
	public void setUserDaoService(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@GetMapping("users")
	public List<User> listUsers() {
		return userRepository.findAll();
	}

	@GetMapping("users/{id}")
	public Resource<User> findUser(@PathVariable int id) {
		Resource<User> resource = new Resource<>(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Id-" + id)));
		ControllerLinkBuilder link = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).listUsers());
		resource.add(link.withRel("list-users"));
		return resource;
	}

	@PostMapping("users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userRepository.save(user).getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping("users/{id}/posts")
	public Resources<Post> findUsersPosts(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Id-" + id));
		Resources<Post> resource = new Resources<>(user.getPosts());
		ControllerLinkBuilder link = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).listUsers());
		ControllerLinkBuilder link2 = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findUser(id));
		resource.add(link.withRel("list-users"));
		resource.add(link2.withRel("user-details"));
		return resource;
	}
	
	@PostMapping("users/{id}/posts")
	public ResponseEntity<Object> saveUserPosts(@PathVariable int id, @RequestBody Post post) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Id-" + id));
		post.setUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postRepository.save(post).getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
