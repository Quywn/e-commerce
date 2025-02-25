package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.CartDTO;
import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Cart;
import com.newwave.ecommerce.repository.CartRepo;
import com.newwave.ecommerce.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    public final CartRepo cartRepo;
    public CartServiceImpl(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

//    @Override
//    public CartDTO addCart(CartDTO cart) {
//        return null;
//    }

    @Override
    public CartDTO addProductToCart(ProductDTO product, String username) {
        //todo
        //check tồn tại giỏ hàng chưa, chưa thì tạo + thêm sp
        Cart cart = Cart.builder().build();
//        cartRepo.updateCart(cart);
        return null;
    }


    @Override
    public CartDTO removeProductFromCart(String productName) {
        //todo
        Cart cart = Cart.builder().build();
//        cartRepo.updateCart(cart);
        return null;
    }

    @Override
    public CartDTO getCartByUser(String username) {
        Cart c = cartRepo.findCartByUsername(username);
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(c.getCartId());
        cartDTO.setUsername(c.getUsername());
        cartDTO.setOrderedProducts(c.getOrderProducts());
        return cartDTO;
    }

    @Override
    public CartDTO clearCartByUser(String username) {
        Cart c = cartRepo.deleteCartByUsername(username);
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(c.getCartId());
        cartDTO.setUsername(c.getUsername());
        cartDTO.setOrderedProducts(c.getOrderProducts());
        return cartDTO;
    }

//    @Override
//    public Double calTotalPrice(String username) {
//        return 0.0;
//    }
}
