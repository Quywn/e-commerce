package com.newwave.ecommerce.service;

import com.newwave.ecommerce.domain.CartDTO;
import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Cart;
import java.util.Optional;

public interface CartService {

    CartDTO clearCartByUser(String username);
    Optional<CartDTO> getCartByUser(String username);
    CartDTO addProductToCart(ProductDTO product, String username);
    CartDTO removeProductFromCart(String productName, String username);
    Double getCartTotal(Cart cart);
}
