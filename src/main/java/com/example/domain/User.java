package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;

  @Column(unique = true)
  private String userId;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<UserReview> userReviewList = new LinkedList<>();

  ///

  public User() {}

  public User(String uid) {
    userId = uid;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public List<UserReview> getUserReviewList() {
    return userReviewList;
  }

  public void setUserReviewList(List<UserReview> userReviewList) {
    this.userReviewList = userReviewList;
  }

  public void addUserReview(UserReview userReview) {
    this.userReviewList.add(userReview);
  }

  @Override
  public String toString() {
    return String.format(
            "User[id=%d, userid='%s']",
            id, userId);
  }

  @Projection(name = "noReviews", types = { User.class })
  public interface WithoutReviews {
    String getUserId();
  }

  @Projection(name = "withReviews", types = { User.class })
  public interface WithReviews {
      String getUserId();
      List<UserReview> getUserReviewList();
  }
}
