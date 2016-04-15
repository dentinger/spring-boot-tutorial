package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.springframework.util.StringUtils;

@Entity
public class UserReview {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;


  private String productId;
  private String comments;
  private Double rating;

  @OneToOne
  private User user;


  public UserReview() {}

  public UserReview(User userid, Double rating, String productId) {
    user = userid;
    this.rating = rating;
    this.productId = productId;
  }

  public UserReview(User userid, Double rating, String productId, String comments) {
    this(userid, rating,productId);
    this.comments = comments;
  }

  @Override
  public String toString() {
    return String.format(
        "UserReview[id=%d, userid='%s', rating='%f', productId='%s', comment='%s']",
        id, user.toString(), rating, productId, StringUtils.isEmpty(comments)?"none":comments);

  }
}
