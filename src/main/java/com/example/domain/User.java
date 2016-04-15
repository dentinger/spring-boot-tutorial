package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;

  private String userId;

  public User() {}

  public User(String uid) {
    userId = uid;
  }

  @Override
  public String toString() {
    return String.format(
        "User[id=%d, userid='%s']",
        id, userId);

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
}
