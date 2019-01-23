package io.letstry.rest.restwebservices.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.letstry.rest.restwebservices.exception.UserNotFoundException;
import io.letstry.rest.restwebservices.modal.User;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static AtomicInteger id = new AtomicInteger(1);

	static {
		users.add(new User(id.getAndIncrement(), "Abhi", new Date()));
		users.add(new User(id.getAndIncrement(), "Athi", new Date()));
		users.add(new User(id.getAndIncrement(), "Ambu", new Date()));
		users.add(new User(id.getAndIncrement(), "Rama", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User findOne(int id) {
		return users.stream().filter(user -> user.getId() == id).findFirst().orElseThrow(() -> new UserNotFoundException("Id-" + id));
	}

	public User save(User user) {
		user.setId(id.getAndIncrement());
		users.add(user);
		return user;
	}
	
	public User deleteById(int id) {
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User user = it.next();
			if(user.getId() == id) {
				it.remove();
				return user;
			}
		}
		return null;
	}

}
