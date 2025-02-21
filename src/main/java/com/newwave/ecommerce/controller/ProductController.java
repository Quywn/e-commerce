package com.newwave.ecommerce.controller;

import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.domain.UserDTO;
import com.newwave.ecommerce.entity.Product;
import com.newwave.ecommerce.service.impl.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product")
    public ProductDTO getProductByName(@RequestParam("name") String name) {
        return productService.getProductByName(name);
    }

    @PostMapping("/product")
    public String addProduct(@RequestBody ProductDTO productDTO) {
        try {
            productService.addProduct(productDTO);
        } catch (Exception e) {
            return "Add product failed. Lỗi: " + e.getMessage();
        }
        return "Add product successful";
    }
}