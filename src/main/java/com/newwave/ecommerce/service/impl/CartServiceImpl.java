package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.entity.Product;
import com.newwave.ecommerce.service.CartService;

public class CartServiceImpl implements CartService {
//todo
    @Override
    public boolean addProductToCart(Product product) {
        return false;
    }

    @Override
    public boolean removeProductFromCart(Product product) {
        return false;
    }

    @Override
    public Double calTotalPrice() {
        return 0.0;
    }
}
