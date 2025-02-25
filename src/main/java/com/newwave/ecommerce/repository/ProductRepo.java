package com.newwave.ecommerce.repository;

import com.newwave.ecommerce.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    Product findByProductName(String productName);
    @Modifying
    @Transactional
    void deleteProductByProductName(String name);
}
