package com.newwave.ecommerce.repository;

import com.newwave.ecommerce.entity.Category;
import com.newwave.ecommerce.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    Optional<Product> findByProductName(String productName);
    long countByCategory(Category category);
    List<Product> findByCategory_CategoryName(String categoryName);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM PRODUCT WHERE PRODUCT_NAME =:productName", nativeQuery = true)
    void deleteProductByProductName(@Param("productName") String productName);

    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchByKeyword(@Param("keyword") String keyword);
}
