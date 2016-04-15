package com.example.repository;

import com.example.domain.Product;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Long> {

  List<Product> findByProductId(String id);
}
