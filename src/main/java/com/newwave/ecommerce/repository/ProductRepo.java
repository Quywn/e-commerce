package com.newwave.ecommerce.repository;

import com.newwave.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    public Product findByProductName(String productName);
}
