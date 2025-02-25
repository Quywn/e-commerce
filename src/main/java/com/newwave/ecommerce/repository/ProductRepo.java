package com.newwave.ecommerce.repository;

import com.newwave.ecommerce.entity.Product;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    Product findByProductName(String productName);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM PRODUCT p WHERE p.product_name=:productName", nativeQuery = true)
    void deleteProductByProductName(@Param("productName") String productName);
}
