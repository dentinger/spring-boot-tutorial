package com.example.repository;

import com.example.domain.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(excerptProjection = User.WithReviews.class)
public interface UserRepository extends CrudRepository<User, Long> {
  User findByUserId(String userId) ;
}
