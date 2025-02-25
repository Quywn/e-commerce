package com.newwave.ecommerce.repository;

import com.newwave.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {
    Cart findCartByUsername(String username);
    Cart deleteCartByUsername(String username);
//    Cart updateCart(Cart cart);
}
