package com.newwave.ecommerce.repository;

import com.newwave.ecommerce.entity.Category;
import com.newwave.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    Optional<Product> findByProductName(String productName);
    long countByCategory(Category category);
    List<Product> findByCategory_CategoryName(String categoryName);
}
