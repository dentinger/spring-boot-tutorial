package com.example.service;

import com.example.domain.Product;
import com.example.domain.User;
import com.example.domain.UserReview;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import com.example.repository.UserReviewRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    List<User> users = userRepository.findByUserId(userid);
    User currentUser = null;
    if(users.size() == 0){
      currentUser = userRepository.save(new User(userid));
    } else {
      currentUser = users.get(0);
    }
    List<Product> productId1 = productRepository.findByProductId(productId);
    Product product = null;
    if(productId1.size() == 0) {
      product = productRepository.save(new Product(productId));
    }
    else {
      product = productId1.get(0);

    }
    UserReview review = null;
    List<UserReview> reviews = userReviewRepository.findByUser(currentUser);
    if(reviews.size() == 0) {
       review = userReviewRepository.save(new UserReview(currentUser,rating, productId));
    } else {
      review = reviews.get(0);
    }
    return true;
  }

  public Map<String, Object> findUser(String userid) {


    List<User> userList = userRepository.findByUserId(userid);
    List<UserReview> userReviews = userReviewRepository.findByUser(userList.get(0));
    Map<String, Object> data = new HashMap<>();
    data.put("user", userList.get(0));
    data.put("reviews", userReviews);
    return data;


  }
}
