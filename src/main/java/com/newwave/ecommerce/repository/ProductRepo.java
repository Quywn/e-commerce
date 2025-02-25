package com.newwave.ecommerce.repository;

import com.newwave.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    @Query(value = "SELECT * FROM PRODUCT WHERE PRODUCT_NAME=:productName", nativeQuery = true)
    Optional<Product> findByProductName(@Param("productName") String productName);
}
