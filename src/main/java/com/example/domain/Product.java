package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(unique = true)
  private String productId;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
  private Set<UserReview> reviews;

  ///

  public Product() {}

  public Product(String productId) {
    this.productId = productId;
  }

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

  @Override
  public String toString() {
    return String.format(
        "Product[id=%d, productId='%s']",
        id, productId);

  }

}
