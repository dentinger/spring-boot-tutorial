package com.example.service;

import com.example.domain.Product;
import com.example.domain.User;
import com.example.domain.UserReview;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import com.example.repository.UserReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private UserReviewRepository userReviewRepository;

  public  ReviewService() {
  }

  public boolean processReview(String userid, String productId, Double rating) {
    User currentUser = userRepository.findByUserId(userid);

    if (currentUser == null){
      currentUser = userRepository.save(new User(userid));
    }

    Product productUnderReview = productRepository.findByProductId(productId);

    if (productUnderReview == null) {
      productUnderReview = productRepository.save(new Product(productId));
    }

    UserReview review = userReviewRepository.save(new UserReview(currentUser, productUnderReview, rating, "None"));
    return true;
  }
}
