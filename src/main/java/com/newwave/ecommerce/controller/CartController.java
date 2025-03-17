package com.newwave.ecommerce.controller;

import com.newwave.ecommerce.domain.CartDTO;
import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.service.impl.CartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

@PostAuthorize("returnObject.username == authentication.name")
@RestController("/user")
public class CartController {
    private final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public ResponseEntity<CartDTO> getCartByUser(@RequestParam String username) {
        return new ResponseEntity<>(cartService.getCartByUser(username), HttpStatus.OK);
    }

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
