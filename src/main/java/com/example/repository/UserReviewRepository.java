package com.example.repository;

import com.example.domain.User;
import com.example.domain.UserReview;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = UserReview.UserReviewExcerpt.class)
public interface UserReviewRepository extends CrudRepository<UserReview, Long> {
   List<UserReview> findByUser(User user);
   List<UserReview> findByUserAndProductId(User user, String productId);
}
