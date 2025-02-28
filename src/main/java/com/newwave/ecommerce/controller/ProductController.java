package com.newwave.ecommerce.controller;

import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.service.impl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/productsA_Z")
    public ResponseEntity<List<ProductDTO>> getProductsAZ() {
        List<ProductDTO> products = productService.getProductListA_Z();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<ProductDTO> getProductByName(@RequestParam("productName") String productName) {
        ProductDTO productDTO = productService.getProductByName(productName);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

}