package com.newwave.ecommerce.controller;

import com.newwave.ecommerce.domain.CartDTO;
import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.service.impl.CartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    private final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<CartDTO> getCartByUser(@RequestParam String username) {
        CartDTO cart = cartService.getCartByUser(username);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<CartDTO> addCart(@RequestBody CartDTO cart) {
//        CartDTO cartdto = cartService.addCart(cart);
//        return new ResponseEntity<>(cartdto, HttpStatus.CREATED);
//    }

//    @DeleteMapping
//    public ResponseEntity<CartDTO> removeCartByUser(@RequestBody String username) {
//        CartDTO cartDTO = cartService.clearCartByUser(username);
//        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
//    }
//
//    @DeleteMapping
//    public ResponseEntity<CartDTO> removeProductFromCart(@RequestBody String productName) {
//        CartDTO cartDTO = cartService.removeProductFromCart(productName);
//        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<CartDTO> addProductToCart(@RequestBody ProductDTO product, @RequestBody String username) {
        CartDTO cartDTO = cartService.addProductToCart(product, username);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

//    @GetMapping
//    public Double calTotalPrice(String username) {
//        return cartService.calTotalPrice(username);
//    }
}
