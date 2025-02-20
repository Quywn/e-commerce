package com.newwave.ecommerce.service;

import com.newwave.ecommerce.entity.Product;

public interface CartService {
    //user
    public boolean addProductToCart(Product product);
    public boolean removeProductFromCart(Product product);
    public Double calTotalPrice();

}
