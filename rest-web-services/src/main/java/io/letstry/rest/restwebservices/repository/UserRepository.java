package io.letstry.rest.restwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.letstry.rest.restwebservices.modal.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
