package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.CartDTO;
import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Product;
import com.newwave.ecommerce.repository.CartRepo;
import com.newwave.ecommerce.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {


    @Override
    public CartDTO addCart(CartDTO cart) {
        return null;
    }

    @Override
    public CartDTO removeCartByUser(String username) {
        return null;
    }

    @Override
    public CartDTO getCartByUser(String username) {
        return null;
    }


    @Override
    public CartDTO addProductToCart(ProductDTO product) {
        return null;
    }

    @Override
    public CartDTO removeProductFromCart(String productname) {
        return null;
    }

    @Override
    public Double calTotalPrice(String username) {
        return 0.0;
    }
}
