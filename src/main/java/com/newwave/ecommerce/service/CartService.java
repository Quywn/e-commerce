package com.newwave.ecommerce.service;

import com.newwave.ecommerce.domain.CartDTO;
import com.newwave.ecommerce.domain.ProductDTO;


public interface CartService {

    CartDTO clearCartByUser(String username);
    CartDTO getCartByUser(String username);
    String addProductToCart(ProductDTO product, String username);
    String removeProductFromCart(String productName, String username);
}
