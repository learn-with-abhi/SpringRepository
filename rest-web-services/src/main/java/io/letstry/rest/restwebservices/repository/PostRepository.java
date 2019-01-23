package io.letstry.rest.restwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.letstry.rest.restwebservices.modal.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
