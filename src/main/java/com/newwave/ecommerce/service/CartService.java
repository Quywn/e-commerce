package com.newwave.ecommerce.service;

import com.newwave.ecommerce.dto.CartDTO;
import com.newwave.ecommerce.dto.ProductDTO;


public interface CartService {

    CartDTO clearCartByUser(String username);
    CartDTO getCartByUser(String username);
    String addProductToCart(ProductDTO product, String username);
    String removeProductFromCart(String productName, String username);
    void updateProductQuantity(String username, String productCode, int quantity);
}
