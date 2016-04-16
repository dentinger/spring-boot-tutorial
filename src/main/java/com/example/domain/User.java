package com.example.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@JsonFilter("User")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;
  @Column(unique = true)
  private String userId;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<UserReview> userReviewList = new LinkedList<>();

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
}
