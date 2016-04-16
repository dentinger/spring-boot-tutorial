package com.example.controller;

import com.example.domain.Product;
import com.example.domain.User;
import com.example.service.ReviewService;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 */
@RestController
public class FeedbackController {

  public static final String KEY_PREFIX = "org.example.feedback:";

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private RedisTemplate redisTemplate;

  @Autowired
  private ReviewService reviewService;

  @RequestMapping(value = "/feedback/{pid}/{uid}/{rating:.+}", method = POST)
  public String submitUserFeedback(@PathVariable("pid") String pid,
                                   @PathVariable("uid") String uid,
                                   @PathVariable("rating") Double rating) {
    HashOperations hashOps = redisTemplate.opsForHash();
    String key = KEY_PREFIX + pid;
    hashOps.put(key, uid, rating);

    reviewService.processReview(uid, pid, rating);
    return "Okay: key=" + key;
  }

  @RequestMapping(value = "/feedback/{pid}/{uid}")
  public String getUserFeedback(@PathVariable("pid") String pid,
                                @PathVariable("uid") String uid) {
    HashOperations hashOps = redisTemplate.opsForHash();
    return "Okay: " + hashOps.get(KEY_PREFIX + pid, uid);
  }

  @RequestMapping(value = "/feedback/{pid}")
  public DoubleSummaryStatistics getAverageFeedback(@PathVariable("pid") String pid) {
    HashOperations hashOps = redisTemplate.opsForHash();
    Map<String, Double> entries = hashOps.entries(KEY_PREFIX + pid);

    return entries.values().stream()
        .mapToDouble(e -> e)
        .summaryStatistics();
  }

  @RequestMapping(value = "/feedback/{pid}/histogram")
  public Map getFeedbackHistogram(@PathVariable("pid") String pid) {
    HashOperations hashOps = redisTemplate.opsForHash();
    Map<String, Double> entries = hashOps.entries(KEY_PREFIX + pid);

    HashMap<Integer, Long> countByRating = entries.values().stream()
        .collect(Collectors.groupingBy(Double::intValue, HashMap::new, Collectors.counting()));

    return countByRating;
  }

  @RequestMapping(value = "/feedback/db/user/{uid}")
  public User findUserData(@PathVariable("uid") String userid) {

    final SimpleFilterProvider filter = new SimpleFilterProvider();
    filter.addFilter("Product", SimpleBeanPropertyFilter.serializeAllExcept("reviews"));
    filter.addFilter("User", SimpleBeanPropertyFilter.serializeAll());
    objectMapper.setFilterProvider(filter);

    return reviewService.findUser(userid);
  }

  @RequestMapping(value = "/feedback/db/product/{pid}")
  public Product findProductData(@PathVariable("pid") String productId) {

    final SimpleFilterProvider filter = new SimpleFilterProvider();
    filter.addFilter("Product", SimpleBeanPropertyFilter.serializeAll());
    filter.addFilter("User", SimpleBeanPropertyFilter.serializeAllExcept("userReviewList"));
    objectMapper.setFilterProvider(filter);

    return reviewService.findProduct(productId);
            //new ResponseEntity<>(objectMapper.writer(filter).writeValueAsString(product), HttpStatus.OK);
  }
}
