package com.newwave.ecommerce.controller;

import com.newwave.ecommerce.domain.CartDTO;
import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.service.impl.CartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    private final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/user/cart")
    public ResponseEntity<String> getCartByUser(@RequestParam String username) {
        return new ResponseEntity<>(cartService.getCartByUser(username).toString(), HttpStatus.OK);
    }

    @DeleteMapping("/user/cart/clear")
    public ResponseEntity<CartDTO> clearCartByUsername(@RequestParam String username) {
        CartDTO cartDTO = cartService.clearCartByUser(username);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @DeleteMapping("user/cart/product")
    public ResponseEntity<String> removeProductFromCart(@RequestParam String productName, @RequestParam String username) {
        return new ResponseEntity<>(cartService.removeProductFromCart(productName, username), HttpStatus.OK);
    }

    @PostMapping("/user/cart/{username}")
    public ResponseEntity<String> addProductToCart(@RequestBody ProductDTO product, @PathVariable String username) {
        return new ResponseEntity<>(cartService.addProductToCart(product, username), HttpStatus.OK);
    }
}
