package com.newwave.ecommerce.repository;

import com.newwave.ecommerce.entity.Product;
import com.newwave.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {
    Product getProductByName(String name);
}
