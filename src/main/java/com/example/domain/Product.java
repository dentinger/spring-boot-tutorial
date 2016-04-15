package com.example.domain;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public Set<UserReview> getReviews() {
    return reviews;
  }

  public void setReviews(Set<UserReview> reviews) {
    this.reviews = reviews;
  }

  private String productId;

  @ManyToMany
  private Set<UserReview> reviews;

  public Product() {}

  public Product(String productId) {
    this.productId = productId;
  }

  @Override
  public String toString() {
    return String.format(
        "Product[id=%d, productId='%s']",
        id, productId);

  }

}
