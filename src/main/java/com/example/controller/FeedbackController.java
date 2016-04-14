package com.example.controller;

import java.util.Map;
import java.util.stream.DoubleStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
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
  private RedisTemplate redisTemplate;

  @RequestMapping(value = "/feedback/{pid}/{uid}/{rating:.+}", method = POST)
  public String submitUserFeedback(@PathVariable("pid") String pid,
                                   @PathVariable("uid") String uid,
                                   @PathVariable("rating") Double rating) {
    HashOperations hashOps = redisTemplate.opsForHash();
    String key = KEY_PREFIX + pid;
    hashOps.put(key, uid, rating);
    return "Ok: key=" + key;
  }

  @RequestMapping(value = "/feedback/{pid}/{uid}")
  public String getUserFeedback(@PathVariable("pid") String pid,
                                @PathVariable("uid") String uid) {
    HashOperations hashOps = redisTemplate.opsForHash();
    return "Ok: " + hashOps.get(KEY_PREFIX + pid, uid);
  }

  @RequestMapping(value = "/feedback/{pid}")
  public String getAverageFeedback(@PathVariable("pid") String pid) {
    HashOperations hashOps = redisTemplate.opsForHash();
    Map entries = hashOps.entries(KEY_PREFIX + pid);
    return "Ok: ";
  }
}
