package com.newwave.ecommerce.controller;

import com.newwave.ecommerce.domain.CartDTO;
import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.exception.NotFoundException;
import com.newwave.ecommerce.service.impl.CartServiceImpl;
import com.newwave.ecommerce.service.impl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CartController {
    private final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public ResponseEntity<CartDTO> getCartByUser(@RequestParam String username) {
        return new ResponseEntity<>(cartService.getCartByUser(username).get(), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<CartDTO> addCart(@RequestBody CartDTO cart) {
//        CartDTO cartdto = cartService.addCart(cart);
//        return new ResponseEntity<>(cartdto, HttpStatus.CREATED);
//    }

    @DeleteMapping("/cart/clear")
    public ResponseEntity<CartDTO> clearCartByUsername(@RequestParam String username) {
        CartDTO cartDTO = cartService.clearCartByUser(username);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @DeleteMapping("/cart/product")
    public ResponseEntity<CartDTO> removeProductFromCart(@RequestParam String productName, @RequestParam String username) {
        CartDTO cartDTO = cartService.removeProductFromCart(productName, username);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @PostMapping("/cart")
    public ResponseEntity<CartDTO> addProductToCart(@RequestBody ProductDTO product, @RequestParam String username) {
        CartDTO cartDTO = cartService.addProductToCart(product, username);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }
}
