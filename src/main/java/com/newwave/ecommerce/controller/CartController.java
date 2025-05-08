package com.newwave.ecommerce.controller;

import com.newwave.ecommerce.dto.CartDTO;
import com.newwave.ecommerce.dto.ProductDTO;
import com.newwave.ecommerce.dto.request.UpdateQuantityRequest;
import com.newwave.ecommerce.service.impl.CartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    private final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/user/cart")
    public ResponseEntity<CartDTO> getCartByUser() {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return new ResponseEntity<>(cartService.getCartByUser(user), HttpStatus.OK);
    }

    @PutMapping("cart/{username}/product/{productCode}")
    public ResponseEntity<?> updateProductQuantityInCart(
            @PathVariable String username,
            @PathVariable String productCode,
            @RequestBody UpdateQuantityRequest request
    ) {
        cartService.updateProductQuantity(username, productCode, request.getQuantity());
        return ResponseEntity.ok().build();
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

    @PostMapping("/user/cart")
    public ResponseEntity<String> addProductToCart(@RequestBody ProductDTO product) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not authenticated");
    }

        String result = cartService.addProductToCart(product, username);
        return ResponseEntity.ok(result);
    }


}
