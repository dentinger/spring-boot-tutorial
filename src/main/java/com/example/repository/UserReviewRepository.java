package com.example.repository;

import com.example.domain.User;
import com.example.domain.UserReview;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReviewRepository extends CrudRepository<UserReview, Long> {
   List<UserReview> findByUser(User user);
   List<UserReview> findByUserAndProductId(User user, String productId);
}
