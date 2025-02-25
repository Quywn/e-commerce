package com.newwave.ecommerce.service;

import com.newwave.ecommerce.domain.CartDTO;
import com.newwave.ecommerce.domain.ProductDTO;

public interface CartService {

    CartDTO addCart(CartDTO cart);
    CartDTO removeCartByUser(String username);
    CartDTO getCartByUser(String username);
    CartDTO addProductToCart(ProductDTO product);
    CartDTO removeProductFromCart(String productName);
    Double calTotalPrice(String username);
}
