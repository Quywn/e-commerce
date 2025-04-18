package com.newwave.ecommerce.controller;

import com.newwave.ecommerce.dto.ProductDTO;
import com.newwave.ecommerce.service.impl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class ProductController {
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/general/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
//        return new ResponseEntity<>(products, HttpStatus.OK);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(products);
    }

    @GetMapping("/user/productsA_Z")
    public ResponseEntity<List<ProductDTO>> getProductsAZ() {
        List<ProductDTO> products = productService.getProductListA_Z();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/user/product")
    public ResponseEntity<ProductDTO> getProductByName(@RequestParam("productName") String productName) {
        ProductDTO productDTO = productService.getProductByName(productName);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping("/user/products/page")
    public ResponseEntity<List<ProductDTO>> getProductsPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        List<ProductDTO> products = productService.getProductsPage(page, pageSize);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/admin/product/create")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO productDTO1 = productService.addProduct(productDTO);
        return new ResponseEntity<>(productDTO1, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/product/delete")
    public ResponseEntity<String> removeProductByName(@RequestParam String productName) {
        return new ResponseEntity<>(productService.removeProductByName(productName), HttpStatus.OK);
    }

    @GetMapping("/user/product/category")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@RequestParam String categoryName) {
        List<ProductDTO> products = productService.getProductsByCategory(categoryName);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/admin/product/update")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO productDTO1 = productService.updateProduct(productDTO);
        return new ResponseEntity<>(productDTO1, HttpStatus.OK);
    }
}