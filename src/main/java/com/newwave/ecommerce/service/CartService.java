package com.newwave.ecommerce.service;

import com.newwave.ecommerce.entity.Product;

public interface CartService {
    //user
    boolean addProductToCart(Product product);
    boolean removeProductFromCart(Product product);
    Double calTotalPrice();

}
