package com.example.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

@Entity
public class UserReview {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;
  @ManyToOne(optional = false)
  //@JsonBackReference
  private User user;
  @ManyToOne(optional = false)
  //@JsonBackReference
  //@JsonManagedReference
  private Product product;
  private Double rating;
  private String comments;

  public UserReview() {}

  public UserReview(User user, Product product, Double rating, String comments) {
    this.user = user;
    this.product = product;
    this.rating = rating;
    this.comments = comments;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  @Override
  public String toString() {
    return String.format(
        "UserReview[id=%d, userid='%s', rating='%f', productId='%s', comment='%s']",
        id, user.toString(), rating, product.toString(), StringUtils.isEmpty(comments)?"none":comments);
  }
}
